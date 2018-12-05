package org.ares.app.wxtck.common.security.rbac.dao.jpa;

import org.ares.app.wxtck.sys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
	
}
