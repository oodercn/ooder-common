/**
 * $RCSfile: MsgClient.java,v $
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
package net.ooder.msg;

import net.ooder.annotation.JLuceneIndex;
import net.ooder.common.Condition;
import net.ooder.common.JDSException;
import net.ooder.config.ListResultModel;
import net.ooder.org.query.MsgConditionKey;

import java.util.List;

public interface MsgClient<V extends Msg> {



    public   V  getMsgById(String msgId);

    /**
     * 获取所有消息（包含已发送及接受�?
     *
     * @return
     * @throws JDSException
     */
    public  <T extends List<V>> ListResultModel<T> getAllSendMsg() throws JDSException;

    /**
     * 获取指定参与者信息列�?
     *
     * @param personId
     * @return
     * @throws JDSException
     */
    public  <T extends List<V>> ListResultModel<T> getSendMsgByPerson(String personId) throws JDSException;

    /**
     * 获取所有接收到的消息V
     *
     * @return
     * @throws JDSException
     */
    public  <T extends List<V>> ListResultModel<T> getAllReceiveMsg() throws JDSException;

    /**
     * 获取指定接收对象的消息集�?
     *
     * @param fromPersonId
     * @return
     * @throws JDSException
     */
    public <T extends List<V>> ListResultModel<T> getReceiveMsgByPerson(String fromPersonId) throws JDSException;

    /**
     * 创建向指定用户发送的消息
     *
     * @param toPersonId
     * @return
     * @throws JDSException
     */
    public   V creatMsg2Person(String toPersonId) throws JDSException;

    /**
     * 创建消息
     *
     * @return
     * @throws JDSException
     */
    public V creatMsg() throws JDSException;

    /**
     * 查询消息
     *
     * @param condition
     * @return
     * @throws JDSException
     */
    public <T extends List<V>>  ListResultModel<T> getMsgList(Condition<MsgConditionKey,JLuceneIndex> condition) throws JDSException;



    /**
     * 群发信息
     *
     * @param msg
     * @param personIds
     * @throws JDSException
     */
    public void sendMassMsg(V msg, List<String> personIds) throws JDSException;


    public   V cloneMsg(Msg msg);

    public void updateMsg(V msg);

    public void deleteMsg(String msgId);


}
