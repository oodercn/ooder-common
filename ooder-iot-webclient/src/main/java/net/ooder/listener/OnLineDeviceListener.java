package  net.ooder.listener;

import  net.ooder.home.event.DeviceEvent;
import  net.ooder.home.listener.DefaultDeviceListener;
import  net.ooder.iot.Device;
import  net.ooder.iot.HomeException;
import  net.ooder.iot.enums.DeviceStatus;

//@EsbBeanAnnotation(id = "OnLineDeviceListener", name = "上下线通知", flowType = EsbFlowType.listener, expressionArr = "OnLineDeviceListener()", desc = "上下线通知")
public class OnLineDeviceListener extends DefaultDeviceListener {


    @Override
    public void onLine(DeviceEvent event) throws HomeException {
        Device device = event.getSource();
        device.setStates(DeviceStatus.ONLINE);
        //以下是业务逻辑部分，请使用异步方法传递


    }

    @Override
    public void offLine(DeviceEvent event) throws HomeException {
        Device device = event.getSource();
        device.setStates(DeviceStatus.OFFLINE);
        //以下是业务逻辑部分，请使用异步方法传递

    }
}
