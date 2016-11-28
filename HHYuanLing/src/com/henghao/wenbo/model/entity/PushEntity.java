package com.henghao.wenbo.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.henghao.wenbo.model.IdEntity;

public class PushEntity extends IdEntity {

    @Expose
    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
