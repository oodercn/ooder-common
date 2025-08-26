package  net.ooder.vfs.ct;

import  net.ooder.common.JDSException;
import  net.ooder.config.JDSConfig;
import  net.ooder.context.JDSActionContext;
import  net.ooder.server.JDSClientService;
import  net.ooder.vfs.VFSConstants;
import  net.ooder.vfs.ct.admin.CtAdminVfsServiceImpl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CtVfsFactory {


    public CtVfsFactory() {

    }

    static Map<JDSClientService, CtVfsService> clientMap = new HashMap<JDSClientService, CtVfsService>();

    public static CtVfsService getCtVfsService() {
        CtVfsService vfsService = (CtVfsService) JDSActionContext.getActionContext().getContext().get(VFSConstants.VFSContextKey);
        if (vfsService == null) {
            vfsService = new CtAdminVfsServiceImpl();
            JDSActionContext.getActionContext().getContext().put(VFSConstants.VFSContextKey, vfsService);
        }

        return vfsService;

    }

    public static  String  getLocalCachePath(){
        String localPath = JDSConfig.Config.tempPath().getPath() + File.separator + "md5hash" + File.separator;
        return localPath;
    }

    public static CtVfsService getMyVfsClient(JDSClientService client) throws JDSException {
        CtVfsService vfsService = clientMap.get(client);
        if (client == null) {
            vfsService = new CtVfsServiceImpl(client);
            clientMap.put(client, vfsService);
        }
        return vfsService;
    }


}
