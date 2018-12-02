package org.ares.app.wxtck.sys.param;

public class SysParam {

	public static final String SQL_QUERY_USER_BY_NAME="select userid,password,true from v_user where userid =?";
    //public static final String SQL_QUERY_AUTH_BY_NAME="select u.userid username,r.rolename as authority from s_user u join s_user_role ur on u.userid=ur.userid join s_role r on r.roleid=ur.roleid where u.userid=?";
    public static final String SQL_QUERY_AUTH_BY_NAME="select * from v_userauth where userid=?";
    
	public static final String POINTCUT_SERVICE="execution(* org.ares.app..*ServiceImp*.*(..))";
	public static final String POINTCUT_DAO="execution(* org.ares.app..*Mapper*.*(..)) || execution(* org.ares.app..*Dao*.*(..))";
	public static final String POINTCUT_ACTION="execution(* org.ares.app..*Action*.*(..))";
	
	
	//CR=>current_request	request CU=>current_user session
	public static final String CR_CURRENT_PAGE="currentPage";
	public static final String CR_PAGE_SIEZ="pageSize";
	
	public static final int DEFAULT_PAGE_INDEX=1;
	public static final int DEFAULT_PAGE_SIEZ=20;
	
	public static final String CU_KEY_USERNAME="cu_username";
	public static final String CU_KEY_USERROLE="cu_userrole";
	
	public static final String SYS_PATH="/sys";
	
}
