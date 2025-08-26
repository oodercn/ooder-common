package  net.ooder.iot.ct;

import  net.ooder.common.JDSException;
import  net.ooder.home.client.AdminClient;
import  net.ooder.home.client.CommandClient;
import  net.ooder.home.ct.CtAdminClientImpl;
import  net.ooder.home.ct.CtCommandClientImpl;
import  net.ooder.home.ct.CtMsgDataEngine;
import  net.ooder.iot.Device;
import  net.ooder.server.JDSClientService;
import  net.ooder.server.JDSServer;
import  net.ooder.common.ConfigCode;

public class CtIotFactory {

    static CtIotService iotService;

    static AdminClient adminClient;

    public CtIotFactory() {

    }

    public static CtIotService getCtIotService() {
        if (iotService == null) {
            iotService = new CtIotServiceImpl();
        }
        return iotService;


    }

    public static CommandClient getCommandClient(String gatewayieee) {
        Device device = null;
        CommandClient iotService = null;
        try {
            synchronized (gatewayieee) {
                device = getCtIotService().getDeviceByIeee(gatewayieee);
                ConfigCode configCode = JDSServer.getClusterClient().getSystem(device.getSubsyscode()).getConfigname();
                CtMsgDataEngine msgEngine = CtMsgDataEngine.getEngine(configCode);
                iotService = msgEngine.getCommandClientByieee(gatewayieee);
            }
        } catch (JDSException e) {
            e.printStackTrace();
        }

        return iotService;

    }


    public static CommandClient newCommandClient(JDSClientService client, String gatewayieee) {
        CommandClient iotService = new CtCommandClientImpl(client, gatewayieee);
        return iotService;

    }


    public static AdminClient getAdminClient() {
        AdminClient adminClient = new CtAdminClientImpl();
        return adminClient;

    }

}
