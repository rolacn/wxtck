package org.ares.app.wxtck.sys.wx.general.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class WxModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "WxModel [signature=" + signature + ", timestamp=" + timestamp + ", nonce=" + nonce + ", echostr="
				+ echostr + "]";
	}

	private String signature;// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	private String timestamp;// 时间戳
	private String nonce;// 随机数
	private String echostr;// 随机字符串
	
	private String code;
}
