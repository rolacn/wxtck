package org.ares.app.wxtck.sys.wx.general.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.ares.app.wxtck.sys.wx.general.model.WxModel;
import org.ares.app.wxtck.sys.wx.general.service.WxVerifyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WeAction {

	@RequestMapping(value={"/wx"})
	public @ResponseBody String joinVerify(WxModel model){
		String r="sign error";
		Map<String,Object> params=new HashMap<>();
		if(verify.check(params))
			r=model.getEchostr();
		log.info(model+"");
		return r;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/gci"})
	public String getCustomerInfo(WxModel model,Model m){
		String r="gci";
		String code=model.getCode();
		log.info("code is:"+model.getCode());
		Map<String,String> data=rest.postForObject(getAccessToken(code), null, Map.class);
		m.addAttribute("code", data.get("openid"));
		log.info(data+"");
		return r;
	}
	
	String getAccessToken(String code){
		String r="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		r=r.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
		return r;
	}
	
	@Resource WxVerifyService verify;
	@Resource RestTemplate rest;
	final static String APPID="wx4057b670e93e5309";
	final static String APPSECRET="36929349adab7a64be75c66317fc85d6";
}
