package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="s_role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String roleid;

	private String rolaname;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="SRoles")
	private List<User> SUsers;

	public Role() {
	}

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolaname() {
		return this.rolaname;
	}

	public void setRolaname(String rolaname) {
		this.rolaname = rolaname;
	}

	public List<User> getSUsers() {
		return this.SUsers;
	}

	public void setSUsers(List<User> SUsers) {
		this.SUsers = SUsers;
	}

}