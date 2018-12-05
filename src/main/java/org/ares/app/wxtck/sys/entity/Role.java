package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="s_role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String roleid;
	private String rolename;

	@ManyToMany(mappedBy="roles")
	private List<User> users;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="s_role_res"
		, joinColumns={
			@JoinColumn(name="roleid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="resid")
			}
		)
	private List<Res> ress;

}