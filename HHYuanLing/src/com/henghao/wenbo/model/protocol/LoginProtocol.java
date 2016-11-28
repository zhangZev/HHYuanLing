/*
 * 文件名：LoginFilfterProtocol.java
 * 版权：Copyright 2009-2010 companyName MediaNet. Co. Ltd. All Rights Reserved.
 * 描述：
 * 修改人：
 * 修改时间：
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.henghao.wenbo.model.protocol;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.benefit.buy.library.http.query.callback.AjaxStatus;
import com.benefit.buy.library.utils.tools.ToolsJson;
import com.benefit.buy.library.utils.tools.ToolsKit;
import com.benefit.buy.library.utils.tools.ToolsSecret;
import com.henghao.wenbo.ProtocolUrl;
import com.henghao.wenbo.model.ascyn.BaseModel;
import com.henghao.wenbo.model.ascyn.BeeCallback;
import com.henghao.wenbo.model.entity.BaseEntity;
import com.henghao.wenbo.model.entity.UserLoginEntity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * @author qyl
 * @version HDMNV100R001, 2015年6月5日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LoginProtocol extends BaseModel {

	public LoginProtocol(Context context) {
		super(context);
	}

	/**
	 * 登陆
	 * @param userName
	 * @param password
	 * @see [类、类#方法、类#成员]
	 * @since [产品/模块版本]
	 */
	public void login(String userName, String password) {
		try {
			String url = ProtocolUrl.APP_GET_NFCBYID;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", userName);
			params.put("pwd", password);
			String str = ToolsKit.getParams(params);
			String paramVal = ToolsSecret.encode(str);
			HashMap<String, String> postParams = new HashMap<String, String>();
			postParams.put("params", paramVal);
			this.mBeeCallback.url(url).type(String.class).params(postParams);
			this.aq.ajax(this.mBeeCallback);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final BeeCallback<String> mBeeCallback = new BeeCallback<String>() {

		@Override
		public void callback(String url, String object, AjaxStatus status) {
			try {
				/**** start ****/
				BaseEntity mBaseEntity = ToolsJson.parseObjecta(object, BaseEntity.class);
				if (mBaseEntity == null) {
					LoginProtocol.this.OnMessageResponse(url, mBaseEntity, status);
					return;
				}
				String data = mBaseEntity.getData();
				if (ToolsKit.isEmpty(data)) {
					LoginProtocol.this.OnMessageResponse(url, mBaseEntity, status);
					return;
				}
				/**** end ****/
				if (url.endsWith(ProtocolUrl.APP_GET_NFCBYID)) {
					// 登录
					String dataConfig = ToolsSecret.decode(data);
					UserLoginEntity shopDatas = ToolsJson.parseObjecta(dataConfig, UserLoginEntity.class);
					LoginProtocol.this.OnMessageResponse(url, shopDatas, status);
				}

			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
}
