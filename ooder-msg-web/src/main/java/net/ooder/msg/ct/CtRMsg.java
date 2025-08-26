/**
 * $RCSfile: CtRMsg.java,v $
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

import net.ooder.common.CommandEventEnums;
import net.ooder.common.MsgStatus;
import net.ooder.msg.RMsg;
import net.ooder.msg.TopicMsg;

public class CtRMsg extends CtMsg implements RMsg, TopicMsg {


    String lasterSystemCode;

    String modeId;

    String passId;

    String event;

    CommandEventEnums resultCode;


    String sensorId;

    String gatewayId;


    private String topic;
    private Boolean retained = true;
    private Integer qos = 0;


    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public Boolean getRetained() {
        return retained;
    }

    @Override
    public void setRetained(Boolean retained) {
        this.retained = retained;
    }

    @Override
    public Integer getQos() {
        return qos;
    }

    @Override
    public void setQos(Integer qos) {
        this.qos = qos;
    }

    @Override
    public String getSystemCode() {
        return systemCode;
    }

    @Override
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }


    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }


    public void setReceiveTime(Long receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Override
    public Long getReceiveTime() {
        return receiveTime;
    }

    @Override
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Integer getTimes() {
        return times;
    }


    public void setTimes(Integer times) {
        this.times = times;
    }


    @Override
    public String getFrom() {
        return from;
    }


    public void setFrom(String from) {
        this.from = from;

    }


    @Override
    public Long getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(Long arrivedTime) {
        this.arrivedTime = arrivedTime;
    }


    @Override
    public Long getEventTime() {
        return eventTime;
    }


    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }


    @Override
    public String getBody() {
        return body;
    }


    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public MsgStatus getStatus() {
        return status;
    }


    public void setStatus(MsgStatus status) {
        this.status = status;
    }


    @Override
    public String getLasterSystemCode() {
        return lasterSystemCode;
    }


    public void setLasterSystemCode(String lasterSystemCode) {
        this.lasterSystemCode = lasterSystemCode;
    }

    @Override
    public String getModeId() {
        return modeId;
    }

    @Override
    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    @Override
    public String getPassId() {
        return passId;
    }

    @Override
    public void setPassId(String passId) {
        this.passId = passId;
    }

    @Override
    public String getEvent() {
        return event;
    }

    @Override
    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public CommandEventEnums getResultCode() {
        return resultCode;
    }

    @Override
    public void setResultCode(CommandEventEnums resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String getSensorId() {
        return sensorId;
    }

    @Override
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String getGatewayId() {
        return gatewayId;
    }

    @Override
    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }
}
