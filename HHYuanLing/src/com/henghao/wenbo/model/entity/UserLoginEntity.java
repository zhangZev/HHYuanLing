package com.henghao.wenbo.model.entity;

import com.google.gson.annotations.Expose;
import com.henghao.wenbo.model.IdEntity;

public class UserLoginEntity extends IdEntity {

    //    {
    //        "shopName": "富特瓷砖",
    //        "shopId": "402883fc5148b6d9015148cfe6b3001b",
    //        "logo": null,
    //        "isBusiness": 0
    //      }
    @Expose
    private String shopName;

    @Expose
    private String shopId;

    @Expose
    private String logo;

    @Expose
    private String isBusiness;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIsBusiness() {
        return isBusiness;
    }

    public void setIsBusiness(String isBusiness) {
        this.isBusiness = isBusiness;
    }
}
