package  net.ooder.command.task;

import  net.ooder.command.Command;
import  net.ooder.common.ConfigCode;
import  net.ooder.common.JDSConstants;
import  net.ooder.common.logging.Log;
import  net.ooder.common.logging.LogFactory;
import  net.ooder.context.JDSActionContext;
import  net.ooder.context.JDSContext;
import  net.ooder.context.RunableActionContextImpl;
import  net.ooder.engine.JDSSessionHandle;
import  net.ooder.home.client.CommandClient;
import  net.ooder.home.ct.CtMsgDataEngine;
import  net.ooder.server.context.MinServerActionContextImpl;

import java.util.concurrent.Callable;

public class IRCommandTask implements Callable<Command> {

    private static final Log logger = LogFactory.getLog(JDSConstants.CONFIG_KEY, IRCommandTask.class);
    private final String sensorieee;
    private final String value;

    private MinServerActionContextImpl autoruncontext;
    private ConfigCode configCode;
    private String gatewayieee;

    public IRCommandTask(String gatewayieee,String sensorieee,String value, String systemCode) {
        this.gatewayieee = gatewayieee;
        JDSContext context = JDSActionContext.getActionContext();
        this.sensorieee=sensorieee;
        this.value=value;
        this.autoruncontext = new MinServerActionContextImpl(context.getHttpRequest(), context.getOgnlContext());

        autoruncontext.setParamMap(context.getContext());
        this.configCode=autoruncontext.getConfigCode();
        if (context.getSessionId() != null) {
            autoruncontext.setSessionId(context.getSessionId());
            autoruncontext.getSession().put("sessionHandle", context.getSession().get("sessionHandle"));
        }
        autoruncontext.setSessionMap(context.getSession());
    }

    public Command call() {
        JDSActionContext.setContext(autoruncontext);
        Command msg = null;
        logger.info("start SersorReportCommand:" +gatewayieee+""+" IRCommandTask");
        try {
            CtMsgDataEngine msgEngine = CtMsgDataEngine.getEngine(configCode);
            JDSSessionHandle handle = (JDSSessionHandle) autoruncontext.getSession().get("sessionHandle");
            JDSActionContext.setContext(new RunableActionContextImpl());
            JDSActionContext.getActionContext().getContext().put(JDSContext.SYSCODE, configCode);

            CommandClient commandClient = msgEngine.getCommandClientByieee(gatewayieee);

            msg = commandClient.sendIRControlCommand(sensorieee,value).get();
        } catch (Exception e) {
            logger.error("SersorReportCommand:" + " error:" + e.getMessage());
            e.printStackTrace();
        }
        return msg;
    }

}
