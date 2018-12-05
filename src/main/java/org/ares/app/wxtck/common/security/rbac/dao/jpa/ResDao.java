package org.ares.app.wxtck.common.security.rbac.dao.jpa;

import org.ares.app.wxtck.sys.entity.Res;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResDao extends JpaRepository<Res, String> {
	
}
