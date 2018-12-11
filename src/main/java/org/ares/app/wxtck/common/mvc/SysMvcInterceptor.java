package org.ares.app.wxtck.common.mvc;

import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.WECHAT_PLATFORM;
import static org.ares.app.wxtck.sys.param.SysParam.CR_CURRENT_PAGE;
import static org.ares.app.wxtck.sys.param.SysParam.CR_PAGE_SIEZ;
import static org.ares.app.wxtck.sys.param.SysParam.CU_KEY_USERNAME;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ares.app.wxtck.sys.param.SysParam;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SysMvcInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		if(currentUser.get(CU_KEY_USERNAME)!=null) {
			process_request(request);
		}
		return super.preHandle(request, response, handler);
	}
	
	void process_request(HttpServletRequest request) {
		String val=null;
		int cur_page=SysParam.DEFAULT_PAGE_INDEX;
		int page_size=SysParam.DEFAULT_PAGE_SIEZ;
		if(!StringUtils.isEmpty(val=request.getParameter(CR_CURRENT_PAGE)))
			cur_page=Integer.parseInt(val);
		if(!StringUtils.isEmpty(val=request.getParameter(CR_PAGE_SIEZ)))
			page_size=Integer.parseInt(val);
		currentUser.put(CR_CURRENT_PAGE, cur_page);
		currentUser.put(CR_PAGE_SIEZ, page_size);
	}
	
	void process_wechat_request(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent").toLowerCase();
		String code=null;
		if(userAgent.indexOf(WECHAT_PLATFORM)< 0||StringUtils.isEmpty(code=request.getParameter("code")))//is not wechat or code is empty
			return;
		String openid=getCustomerOpenid(code);
		//构造用户密码口令相同
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(openid,openid);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser=authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

	}
	
	@SuppressWarnings("unchecked")
	String getCustomerOpenid(String code){
		String r="gci";
		log.info("code is:"+code);
		Map<String,String> data=rest.postForObject(getAccessToken(code), null, Map.class);
		String openid= data.get("openid");
		log.info("openid is:"+openid);
		return r;
	}
	
	String getAccessToken(String code){
		String r="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		r=r.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
		return r;
	}
	
	final static String APPID="wx4057b670e93e5309";
	final static String APPSECRET="36929349adab7a64be75c66317fc85d6";
	
	@Resource RestTemplate rest;
	@Resource Map<String,Object> currentUser;
	@Resource AuthenticationManager authenticationManager;
}
