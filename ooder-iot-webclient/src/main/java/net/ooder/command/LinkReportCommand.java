package  net.ooder.command;

import  net.ooder.common.ContextType;
import  net.ooder.enums.CommandEnums;
import  net.ooder.annotation.EsbBeanAnnotation;

@EsbBeanAnnotation(id = "LinkReport",dataType=ContextType.Command, name = "链路上报", expressionArr = "LinkReportCommand()", desc = "链路上报")

public class LinkReportCommand extends SensorCommand implements ADCommand, NetCommand {
    @Override
    public String getFactory() {
        return "jds";
    }

    public LinkReportCommand() {
        super(CommandEnums.LinkReport);
    }

}
