package org.ares.app.wxtck.common.security.rbac.model;

import java.util.List;

import lombok.Data;

@Data
public class SUser {
	private String userid;
	private String username;
	private String password;
	private boolean status;
	private String email;
	private List<SRole> roles;//roles owned by the current user
}
