package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="b_tckorder")
@NamedQuery(name="TicketOrder.findAll", query="SELECT t FROM TicketOrder t")
public class TicketOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ordid;

	private String ordescr;

	private BigDecimal ordmount;

	private String ordstate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ordtime;

	//bi-directional many-to-one association to Tourist
	@ManyToOne
	@JoinColumn(name="openid")
	private Tourist BTourist;

	//bi-directional many-to-one association to TouristTicket
	@OneToMany(mappedBy="BTckorder")
	private List<TouristTicket> BTourtcks;

	public TicketOrder() {
	}

	public int getOrdid() {
		return this.ordid;
	}

	public void setOrdid(int ordid) {
		this.ordid = ordid;
	}

	public String getOrdescr() {
		return this.ordescr;
	}

	public void setOrdescr(String ordescr) {
		this.ordescr = ordescr;
	}

	public BigDecimal getOrdmount() {
		return this.ordmount;
	}

	public void setOrdmount(BigDecimal ordmount) {
		this.ordmount = ordmount;
	}

	public String getOrdstate() {
		return this.ordstate;
	}

	public void setOrdstate(String ordstate) {
		this.ordstate = ordstate;
	}

	public Date getOrdtime() {
		return this.ordtime;
	}

	public void setOrdtime(Date ordtime) {
		this.ordtime = ordtime;
	}

	public Tourist getBTourist() {
		return this.BTourist;
	}

	public void setBTourist(Tourist BTourist) {
		this.BTourist = BTourist;
	}

	public List<TouristTicket> getBTourtcks() {
		return this.BTourtcks;
	}

	public void setBTourtcks(List<TouristTicket> BTourtcks) {
		this.BTourtcks = BTourtcks;
	}

	public TouristTicket addBTourtck(TouristTicket BTourtck) {
		getBTourtcks().add(BTourtck);
		BTourtck.setBTckorder(this);

		return BTourtck;
	}

	public TouristTicket removeBTourtck(TouristTicket BTourtck) {
		getBTourtcks().remove(BTourtck);
		BTourtck.setBTckorder(null);

		return BTourtck;
	}

}