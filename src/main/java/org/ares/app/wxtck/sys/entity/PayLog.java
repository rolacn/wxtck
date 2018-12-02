package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the b_paylog database table.
 * 
 */
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

	public PayLog() {
	}

	public int getLogid() {
		return this.logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}

	public String getLogbody() {
		return this.logbody;
	}

	public void setLogbody(String logbody) {
		this.logbody = logbody;
	}

	public Date getLogtime() {
		return this.logtime;
	}

	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}

	public String getLogtype() {
		return this.logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

}