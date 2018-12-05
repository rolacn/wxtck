package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="s_res")
@NamedQuery(name="Res.findAll", query="SELECT u FROM Res u")
public class Res implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String resid;

	private String resurl;

	private String resname;

	private String descr;

}