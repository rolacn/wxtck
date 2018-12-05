package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name="b_paylog")
@NamedQuery(name="PayLog.findAll", query="SELECT p FROM PayLog p")
public class PayLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int logid;
	private String logbody;
	@Temporal(TemporalType.TIMESTAMP)
	private Date logtime;
	private String logtype;
	
}