/*
 * 文件名：ProtocolUrl.java
 * 版权：Copyright 2009-2010 companyName MediaNet. Co. Ltd. All Rights Reserved.
 * 描述：
 * 修改人：
 * 修改时间：
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.henghao.wenbo;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * @author zhangxianwen
 * @version HDMNV100R001, 2015-4-20
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ProtocolUrl {

	/**
	 * 服务端根地址
	 */
	public static String ROOT_URL = "";

	public static boolean isURL = true;

	static {
		if (!isURL) {
			// 生产地址
			ROOT_URL = "http://";
		}
		else {
			// 测试地址/192.168.1.12
//			ROOT_URL = "http://safe.higdata.com/Java_Nfc/";
			ROOT_URL = "http://192.168.1.12/Java_Nfc/";
		}
	}

	// TODO 用户相关
	/************************ 用户相关 **************************/
	public static final String USER = "user/";

	/**
	 * 用户登录
	 */
	public static String APP_GET_NFCBYID = USER + "findByNfcId";

	/************************ 用户相关 end **************************/

	// TODO app系统 相关
	/************************ app系统 相关 **************************/
	public static final String SYSTEM = "j_appSystem/";

	/**
	 * app启动页面信息
	 */
	public static final String APP_START = SYSTEM + "appStart";

	/**
	 * app引导页面信息
	 */
	public static final String APP_GUIDE = SYSTEM + "appGuide";

	/**
	 * app系统版本更新
	 */
	public static final String APP_SYS_UPDATE = SYSTEM + "appUserUpdate";
	/************************ app系统 end **************************/

	/**
	 * 上传错误日志到服务器
	 */
	public static final String UPLOAD_ERROR_SERVER = "appError";
}
