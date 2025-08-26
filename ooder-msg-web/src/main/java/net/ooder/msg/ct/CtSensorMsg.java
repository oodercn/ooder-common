/**
 * $RCSfile: CtSensorMsg.java,v $
 * $Revision: 1.0 $
 * $Date: 2025/08/25 $
 * <p>
 * Copyright (c) 2025 ooder.net
 * </p>
 * <p>
 * Company: ooder.net
 * </p>
 * <p>
 * License: MIT License
 * </p>
 */
package net.ooder.msg.ct;

import net.ooder.msg.SensorMsg;

public class CtSensorMsg extends CtMsg implements SensorMsg {

    private String sensorId;
    private String gatewayId;
    private String event;
    @Override
    public String getSensorId() {
        return sensorId;
    }

    @Override
    public String getEvent() {
        return event;
    }

    @Override
    public void setEvent(String event) {

        this.event=event;
    }


    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String getGatewayId() {
        return gatewayId;
    }


    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

}
