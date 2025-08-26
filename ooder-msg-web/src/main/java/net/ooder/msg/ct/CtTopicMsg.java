/**
 * $RCSfile: CtTopicMsg.java,v $
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

import net.ooder.msg.TopicMsg;

public class CtTopicMsg extends CtMsg implements TopicMsg {


    private String topic;
    private Boolean retained=false;
    private Integer qos=0;

    public CtTopicMsg() {

    }

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
}
