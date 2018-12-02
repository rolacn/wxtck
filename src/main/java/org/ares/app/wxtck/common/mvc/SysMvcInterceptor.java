package org.ares.app.wxtck.common.mvc;

import static org.ares.app.wxtck.sys.param.SysParam.CR_CURRENT_PAGE;
import static org.ares.app.wxtck.sys.param.SysParam.CR_PAGE_SIEZ;
import static org.ares.app.wxtck.sys.param.SysParam.CU_KEY_USERNAME;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ares.app.wxtck.sys.param.SysParam;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


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
	
	@Resource Map<String,Object> currentUser;
}
