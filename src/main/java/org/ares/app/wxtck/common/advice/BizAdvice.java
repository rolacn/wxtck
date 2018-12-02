package org.ares.app.wxtck.common.advice;

import static org.ares.app.wxtck.sys.param.SysParam.POINTCUT_DAO;
import static org.ares.app.wxtck.sys.param.SysParam.POINTCUT_SERVICE;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.ares.app.wxtck.common.exception.AppSysException;
import org.ares.app.wxtck.common.exception.BizLogicException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/*@Slf4j*/
@Aspect
@Component
public class BizAdvice {
	
	@Around("pointcut_dao() || pointcut_service()")
	public Object biz_handle(ProceedingJoinPoint pjp) throws Throwable {
		Object r=null;
		String method="";
		try {
			method=pjp.getSignature().getDeclaringTypeName()+" : "+pjp.getSignature().getName();
			log.info("mehtod--["+method+"] start at "+sdf.format(new Date())+"...");
			r=pjp.proceed();
		}catch(DataAccessException e) {
			log.error("mehtod--["+method+"] error: "+e.getMessage());
			e.printStackTrace();
			throw new BizLogicException(e);
		}catch(BizLogicException e) {
			throw e;
		}catch(AppSysException e) {
			throw e;
		}
		catch(Exception e) {
			log.error("mehtod--["+method+"] error: "+e.getMessage());
			e.printStackTrace();
			throw new AppSysException(e);
		}finally{
			log.info("mehtod--["+method+"] end at "+sdf.format(new Date())+"...");
		}
		return r;
	}
	
    @Pointcut(POINTCUT_SERVICE)  
    public void pointcut_service(){}
	
	@Pointcut(POINTCUT_DAO)  
    public void pointcut_dao(){}
	
	static final String L_POINTCUT_SERVICE="execution(* org.ares.app..*ServiceImp*.*(..))";
	static final String L_POINTCUT_DAO="execution(* org.ares.app..*Mapper*.*(..)) || execution(* org.ares.app..*Dao*.*(..))";
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	Logger log = LoggerFactory.getLogger(this.getClass());
}
