package org.ares.app.wxtck.sys.wx.general.action;

import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.WECHAT_PLATFORM;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ares.app.wxtck.common.exception.AppSysException;
import org.ares.app.wxtck.sys.wx.general.model.WxModel;
import org.ares.app.wxtck.sys.wx.general.service.WxVerifyService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value={"/wx"})
public class WeAction {

	@RequestMapping(value={"/entry"})
	public @ResponseBody String joinVerify(WxModel model){
		String r="sign error";
		Map<String,Object> params=new HashMap<>();
		if(verify.check(params))
			r=model.getEchostr();
		log.info(model+"");
		return r;
	}
	
	/**
	 * 微信网页授权跳转地址
	 * @param model
	 * @param tourist_func
	 * @param m
	 * @return
	 */
	@RequestMapping(value={"/wte/{tourist_func}"})
	public String wechatTicketEntry(String code,@PathVariable String tourist_func,Model m,HttpServletRequest request){
		String r=TOURIST_WEB_FUNC_PREFIX+tourist_func;
		
		String userAgent = request.getHeader("user-agent").toLowerCase();
		if(userAgent.indexOf(WECHAT_PLATFORM)> 0||StringUtils.isEmpty(code=request.getParameter(KEY_CODE)))//is not wechat or code is empty
			throw new AppSysException(ERR_MSG_BE_WX_CALLED);
		String openid=code/*getOpenid(code)*/;
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(openid,V_PASS);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser=authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		r="forward:"+r;
		return r;
	}

	/**
	 * code is not null,have been inspected already.
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	String getOpenid(String code){
		String r=null;
		Map<String,String> data=rest.postForObject(UrlForAccessToken(code), null, Map.class);
		r= data.get(KEY_OPENID);
		log.info("openid is:"+r);
		return r;
	}
	
	String UrlForAccessToken(String code){
		return ACCESS_TOKEN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
	}
	
	@Resource WxVerifyService verify;
	@Resource RestTemplate rest;
	@Resource AuthenticationManager authenticationManager;
	
	final static String KEY_OPENID="openid";
	final static String KEY_CODE="code";
	final static String V_PASS="ares@20196yhn^YHN";
	final static String APPID="wx4057b670e93e5309";
	final static String APPSECRET="36929349adab7a64be75c66317fc85d6";
	final static String TOURIST_WEB_FUNC_PREFIX="/tourist/";
	final static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	final static String ERR_MSG_BE_WX_CALLED="an error occurred communicating with WeChat";
}
