package org.ares.app.wxtck.common.security.rbac.dao.jpa;

import org.ares.app.wxtck.sys.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, String> {
	
}
