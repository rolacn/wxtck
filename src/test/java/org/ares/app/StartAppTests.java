package org.ares.app;

import javax.annotation.Resource;

import org.ares.app.wxtck.StartApp;
import org.ares.app.wxtck.common.security.rbac.dao.jpa.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes=StartApp.class)
public class StartAppTests {

	@Test
	public void test_Jpa() {
		userDao.findAll().stream().forEach(e->log.info(e.getUsername()));
		log.info(""+userDao.findAll().get(0).getRoles().get(0).getRess().get(0).getResurl());
		userDao.findAll().stream().forEach(u->{
			log.info(u.getUsername());
			u.getRoles().stream().forEach(r->{
				log.info(r.getRolename());
				r.getRess().stream().forEach(r2->{
					log.info(r2.getResname()+":"+r2.getResurl());
				});
			});
		});
	}

	@Resource UserDao userDao;
}
