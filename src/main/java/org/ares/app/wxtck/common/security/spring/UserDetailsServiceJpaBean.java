package org.ares.app.wxtck.common.security.spring;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.ares.app.wxtck.common.security.rbac.dao.jpa.UserDao;
import org.ares.app.wxtck.sys.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDetailsServiceJpaBean implements UserDetailsService {

	@Override  
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User cu=userDao.findById(username).get();
        log.debug(cu+"");
        if (cu == null)
			throw new UsernameNotFoundException("username not found");
        UserDetailsModel user = new UserDetailsModel();
        user.setUsername(username);
        user.setPassword(cu.getPassword());
        Set<GrantedAuthority> auth = new HashSet<>();
        cu.getRoles().stream().forEach(e->auth.add(new SimpleGrantedAuthority(e.getRolename())));
		user.setAuthorities(auth);
        return user;
    }  

	@Resource UserDao userDao;
}
