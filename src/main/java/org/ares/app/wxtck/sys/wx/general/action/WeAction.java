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
		
		/*Map<String,String> data=rest.postForObject(getAccessToken(code), null, Map.class);
		m.addAttribute("code", data.get("openid"));
		log.info(data+"");*/
		String userAgent = request.getHeader("user-agent").toLowerCase();
		if(userAgent.indexOf(WECHAT_PLATFORM)> 0||StringUtils.isEmpty(code=request.getParameter("code")))//is not wechat or code is empty
			throw new AppSysException("an error occurred communicating with WeChat");
		String openid=code;
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(openid,"123456");
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser=authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		r="forward:"+r;
		return r;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/get_openid"})
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
	@Resource AuthenticationManager authenticationManager;
	
	final static String APPID="wx4057b670e93e5309";
	final static String APPSECRET="36929349adab7a64be75c66317fc85d6";
	final static String TOURIST_WEB_FUNC_PREFIX="/tourist/";
}
