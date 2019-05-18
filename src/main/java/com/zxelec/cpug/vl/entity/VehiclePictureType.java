package com.zxelec.cpug.vl.entity;

///////////////////////////////////////////////////////////////////////
// Copyright(C) 2018 Vitech Corporation. All Rights Reserved.
///////////////////////////////////////////////////////////////////////


import java.util.Date;

/**
 * 图片类型
 * 详见文档 《高并发消息总线接口规范.docx》附录：C.8
 */
public class VehiclePictureType {


    private int type;

    private Date shotTime;

    private String fmt;

    private Integer ref;

    private String fileRef;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getShotTime() {
        return shotTime;
    }

    public void setShotTime(Date shotTime) {
        this.shotTime = shotTime;
    }

    public String getFmt() {
        return fmt;
    }

    public void setFmt(String fmt) {
        this.fmt = fmt;
    }

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public String getFileRef() {
        return fileRef;
    }

    public void setFileRef(String fileRef) {
        this.fileRef = fileRef;
    }
}
