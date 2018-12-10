package org.ares.app.wxtck.common.cfg.bean;

import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.UA_CSS;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.UA_FAVICON;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.UA_FONTS;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.UA_JS;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.UA_TEST;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.UA_USER_REGIST;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.U_LOGIN;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.U_LOGIN_ERROR;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.U_LOGIN_PAGE;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.U_LOGIN_PASS_ERROR;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.U_LOGIN_SUCCESS;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.U_LOGOUT;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.U_SMS_CODE;
import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.U_SYS_CODE;
import static org.ares.app.wxtck.sys.param.SysParam.CU_KEY_USERNAME;
import static org.ares.app.wxtck.sys.param.SysParam.CU_KEY_USERROLE;
import static org.ares.app.wxtck.sys.param.SysParam.SYS_PATH;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.ares.app.wxtck.common.security.spring.KaptchaAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecConfig extends WebSecurityConfigurerAdapter {
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(sql_query_user_by_name)
		.authoritiesByUsernameQuery(sql_query_auth_by_name)
		.passwordEncoder(passwordEncoder);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		if(use_kaptcha) {
    		KaptchaAuthenticationFilter kaf=new KaptchaAuthenticationFilter(U_LOGIN, U_LOGIN_ERROR);
    		kaf.setParam_name(param_name);
    		kaf.setSkey(skey);
    		http.addFilterBefore(kaf, UsernamePasswordAuthenticationFilter.class);
    	}
		http.authorizeRequests()
		.antMatchers(kaptcha_url,UA_CSS, UA_JS, UA_FONTS, UA_FAVICON,U_LOGIN,U_LOGOUT,U_LOGIN_PAGE,UA_TEST,U_SMS_CODE+"/*",U_SYS_CODE+"/*",UA_USER_REGIST).permitAll()
		.antMatchers("/wx/**").permitAll()
		.antMatchers("/dev/**").permitAll()
		.antMatchers("/","/sys/**").hasAnyAuthority("tourist","seller")
		.antMatchers("/seller").hasAnyAuthority("seller")
		.antMatchers("/tourist/**").hasAnyAuthority("tourist")
		.antMatchers("/**").hasAnyAuthority("admin")
		.anyRequest().fullyAuthenticated()
		.and().formLogin().successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
            	String cur_user=auth.getName();
            	log.debug("[current user:]"+cur_user+" has logged in"+auth.getAuthorities());
            	for(Object o:auth.getAuthorities())
            		log.info("auth is:"+o);
            	currentUser.put(CU_KEY_USERNAME, cur_user);
            	currentUser.put(CU_KEY_USERROLE, auth.getAuthorities());
            	request.getRequestDispatcher(U_LOGIN_SUCCESS).forward(request, response);
            }
        })
		.loginPage(U_LOGIN).loginPage(U_LOGIN_PAGE).failureUrl(U_LOGIN_PASS_ERROR).permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher(U_LOGOUT)).logoutSuccessUrl("/").invalidateHttpSession(true)
		.and().exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,AccessDeniedException e) throws IOException, ServletException {
				log.info("[username is:] "+currentUser.get(CU_KEY_USERNAME)+", [auth is:] "+currentUser.get(CU_KEY_USERROLE)+":"+request.getRequestURI()+":"+e.getMessage());
				e.printStackTrace();
			}
        })/*.accessDeniedPage("/403")*/;
		
		http.headers().frameOptions().sameOrigin(); // 允许来自同一来源的 控制台的请求
		//http.csrf().disable();
        http.csrf().ignoringAntMatchers(U_LOGIN+"*",SYS_PATH+"/**",U_LOGOUT+"*","/tourist/**","/seller/**","/gci*","/wx*");
	}
    
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    static final String KEY = "org_ares";
    
    @Value("${webapp.content_type.json}") String json_content_type;
    @Value("${kaptcha.chkurl:/sys/img_code}") String kaptcha_url;
    @Value("${webapp.use_kaptcha}") boolean use_kaptcha=true;
    @Value("${kaptcha.input.name:image_kaptcha}") String param_name;
    @Value("${kaptcha.session.key:kaptcha_session_code}") String skey;
    
    @Value("${webapp.security.jdbc.sql_query_user_by_name:select userid,password,true from v_user where userid =?}") String sql_query_user_by_name;
    @Value("${webapp.security.jdbc.sql_query_auth_by_name:select userid username,authority from v_userauth where userid=?}") String sql_query_auth_by_name;
    
    @Resource PasswordEncoder passwordEncoder;
    @Resource DataSource dataSource;
    @Resource Map<String,Object> currentUser;
    
}
