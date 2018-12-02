package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the b_tourtck database table.
 * 
 */
@Entity
@Table(name="b_tourtck")
@NamedQuery(name="TouristTicket.findAll", query="SELECT t FROM TouristTicket t")
public class TouristTicket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int ttid;

	private String ttdescr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ttlastusetime;

	private String ttqrcode;

	private String ttstate;

	private int ttusecount;

	@Temporal(TemporalType.DATE)
	private Date ttvalidate;

	//bi-directional many-to-one association to TicketOrder
	@ManyToOne
	@JoinColumn(name="ordid")
	private TicketOrder BTckorder;

	//bi-directional many-to-one association to TicketPolicy
	@ManyToOne
	@JoinColumn(name="tpid")
	private TicketPolicy BTckpolicy;

	//bi-directional many-to-one association to Tourist
	@ManyToOne
	@JoinColumn(name="openid")
	private Tourist BTourist;

	public TouristTicket() {
	}

	public int getTtid() {
		return this.ttid;
	}

	public void setTtid(int ttid) {
		this.ttid = ttid;
	}

	public String getTtdescr() {
		return this.ttdescr;
	}

	public void setTtdescr(String ttdescr) {
		this.ttdescr = ttdescr;
	}

	public Date getTtlastusetime() {
		return this.ttlastusetime;
	}

	public void setTtlastusetime(Date ttlastusetime) {
		this.ttlastusetime = ttlastusetime;
	}

	public String getTtqrcode() {
		return this.ttqrcode;
	}

	public void setTtqrcode(String ttqrcode) {
		this.ttqrcode = ttqrcode;
	}

	public String getTtstate() {
		return this.ttstate;
	}

	public void setTtstate(String ttstate) {
		this.ttstate = ttstate;
	}

	public int getTtusecount() {
		return this.ttusecount;
	}

	public void setTtusecount(int ttusecount) {
		this.ttusecount = ttusecount;
	}

	public Date getTtvalidate() {
		return this.ttvalidate;
	}

	public void setTtvalidate(Date ttvalidate) {
		this.ttvalidate = ttvalidate;
	}

	public TicketOrder getBTckorder() {
		return this.BTckorder;
	}

	public void setBTckorder(TicketOrder BTckorder) {
		this.BTckorder = BTckorder;
	}

	public TicketPolicy getBTckpolicy() {
		return this.BTckpolicy;
	}

	public void setBTckpolicy(TicketPolicy BTckpolicy) {
		this.BTckpolicy = BTckpolicy;
	}

	public Tourist getBTourist() {
		return this.BTourist;
	}

	public void setBTourist(Tourist BTourist) {
		this.BTourist = BTourist;
	}

}