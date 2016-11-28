/* 
 * 文件名：BaseModel.java
 * 版权：Copyright 2009-2010 companyName MediaNet. Co. Ltd. All Rights Reserved. 
 * 描述： 
 * 修改人：
 * 修改时间：
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.henghao.wenbo.model.entity;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * @author qyl
 * @version HDMNV100R001, 2015-4-23
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -3440061414071692254L;

    /**
     * 返回值是否请求成功，1、代表请求成功，-1代表请求失败
     */
    @Expose
    @SerializedName("success")
    private int success;

    /**
     * 状态值和msg一一对应
     */
    @Expose
    @SerializedName("state")
    private int state;

    /**
     * 状态所反映的信息
     */
    @Expose
    @SerializedName("msg")
    private String msg;

    /**
     * 返回的加密数据
     */
    @Expose
    @SerializedName("data")
    private String data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
