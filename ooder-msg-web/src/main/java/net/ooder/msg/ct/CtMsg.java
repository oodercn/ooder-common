/**
 * $RCSfile: CtMsg.java,v $
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

import com.alibaba.fastjson.annotation.JSONField;
import net.ooder.common.MsgStatus;
import net.ooder.msg.Msg;
import net.ooder.org.conf.OrgConstants;

public class CtMsg implements Msg {

    public String systemCode = OrgConstants.CONFIG_KEY.getType();

    public String id;
    public String type;
    public String receiver;

    public Long receiveTime;


    public String title;
    public Integer times = 1;
    public String from;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Long arrivedTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Long eventTime;
    public String body;
    public MsgStatus status = MsgStatus.NORMAL;

    @Override
    public String getSystemCode() {
        return systemCode;
    }

    @Override
    public void setSystemCode(String systemCode) {
        this.systemCode=systemCode;
    }

    public CtMsg(){

    }
    public CtMsg(Msg msg){
        this.setType(msg.getType());
        this.setId(msg.getId());
        this.setEventTime(msg.getEventTime());
        this.setArrivedTime(msg.getArrivedTime());
        this.setBody(msg.getBody());
        this.setFrom(msg.getFrom());
        this.setReceiver(msg.getReceiver());
        this.setStatus(msg.getStatus());
        this.setTimes(msg.getTimes());
        this.setTitle(msg.getTitle());
        this.setSystemCode(msg.getSystemCode());
        this.setReceiveTime(msg.getReceiveTime());

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
        this.from=from;

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
}
