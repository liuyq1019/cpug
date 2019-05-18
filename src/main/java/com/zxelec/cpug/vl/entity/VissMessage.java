package com.zxelec.cpug.vl.entity;

///////////////////////////////////////////////////////////////////////
// Copyright(C) 2018 Vitech Corporation. All Rights Reserved.
///////////////////////////////////////////////////////////////////////


import java.util.List;

/**
 * viss 消息体
 * 详见文档 《高并发消息总线接口规范.docx》附录：B
 */
public class VissMessage<T> {

    public static final String START_CODE = "ZXM1";

    private String startCode = START_CODE;

    private MessageMetaDataType type;

    private T body;

    private List<byte[]> binaryData;

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public MessageMetaDataType getType() {
        return type;
    }

    public void setType(MessageMetaDataType type) {
        this.type = type;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public List<byte[]> getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(List<byte[]> binaryData) {
        this.binaryData = binaryData;
    }
}
