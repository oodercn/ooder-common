package  net.ooder.home.event;

import  net.ooder.iot.Device;
import  net.ooder.iot.enums.DeviceEventEnums;
import  net.ooder.server.JDSClientService;

/**
 * <p>
 * Title: HOME管理系统
 * </p>
 * <p>
 * Description: 设备传感器事件 包含，网关以及传感器相关的事件
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: raddev.cn
 * </p>
 *
 * @author  ooder
 * @version 1.0
 */
@SuppressWarnings("all")
public class DeviceEvent<T extends  Device> extends HomeEvent<T> {

    public static final String CONTEXT_BINDSENSORS = "DEVICEEVENT.CONTEXT_SENSORS";

    public static final String CONTEXT_MSG = "DEVICEEVENT.CONTEXT_MSG";

    public static final String CONTEXT_ATT_KEY = "DEVICEEVENT.CONTEXT_ATT_KEY";

    public static final String CONTEXT_ATT_VALUE = "DEVICEEVENT.CONTEXT_ATT_VALUE";


    /**
     * DeviceEvent
     *
     * @param path
     * @param eventID
     */
    public DeviceEvent(T device, JDSClientService client, DeviceEventEnums eventID, String sysCode) {
        super(device, null);
        id = eventID;
        this.systemCode = sysCode;
        this.setClientService(client);


    }


    @Override
    public T getSource() {
        return super.getSource();
    }

    @Override
    public DeviceEventEnums getID() {
        return (DeviceEventEnums) id;
    }

}
