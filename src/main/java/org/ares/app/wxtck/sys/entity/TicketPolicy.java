package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the b_tckpolicy database table.
 * 
 */
@Entity
@Table(name="b_tckpolicy")
@NamedQuery(name="TicketPolicy.findAll", query="SELECT t FROM TicketPolicy t")
public class TicketPolicy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int tpid;

	private BigDecimal disrate;

	private int maxusecount;

	private String tpname;

	private String tpstatus;

	private String tptype;

	private BigDecimal unitprice;

	//bi-directional many-to-one association to TouristTicket
	@OneToMany(mappedBy="BTckpolicy")
	private List<TouristTicket> BTourtcks;

	public TicketPolicy() {
	}

	public int getTpid() {
		return this.tpid;
	}

	public void setTpid(int tpid) {
		this.tpid = tpid;
	}

	public BigDecimal getDisrate() {
		return this.disrate;
	}

	public void setDisrate(BigDecimal disrate) {
		this.disrate = disrate;
	}

	public int getMaxusecount() {
		return this.maxusecount;
	}

	public void setMaxusecount(int maxusecount) {
		this.maxusecount = maxusecount;
	}

	public String getTpname() {
		return this.tpname;
	}

	public void setTpname(String tpname) {
		this.tpname = tpname;
	}

	public String getTpstatus() {
		return this.tpstatus;
	}

	public void setTpstatus(String tpstatus) {
		this.tpstatus = tpstatus;
	}

	public String getTptype() {
		return this.tptype;
	}

	public void setTptype(String tptype) {
		this.tptype = tptype;
	}

	public BigDecimal getUnitprice() {
		return this.unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public List<TouristTicket> getBTourtcks() {
		return this.BTourtcks;
	}

	public void setBTourtcks(List<TouristTicket> BTourtcks) {
		this.BTourtcks = BTourtcks;
	}

	public TouristTicket addBTourtck(TouristTicket BTourtck) {
		getBTourtcks().add(BTourtck);
		BTourtck.setBTckpolicy(this);

		return BTourtck;
	}

	public TouristTicket removeBTourtck(TouristTicket BTourtck) {
		getBTourtcks().remove(BTourtck);
		BTourtck.setBTckpolicy(null);

		return BTourtck;
	}

}