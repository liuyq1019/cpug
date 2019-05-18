package com.zxelec.cpug.vl.entity;

import java.util.Date;

/**
 * 消息元数据MessageMetaDataType.
 * 详见文档 《高并发消息总线接口规范.docx》附录：C.5
 * add by li.peng
 */
public class MessageMetaDataType {

    private String sender;

    private String souce;

    private Date sendTime;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSouce() {
        return souce;
    }

    public void setSouce(String souce) {
        this.souce = souce;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
