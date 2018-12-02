package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the b_tourist database table.
 * 
 */
@Entity
@Table(name="b_tourist")
@NamedQuery(name="Tourist.findAll", query="SELECT t FROM Tourist t")
public class Tourist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String openid;

	private String email;

	private String mobile;

	private String password;

	private String status;

	private String tourname;

	//bi-directional many-to-one association to TicketOrder
	@OneToMany(mappedBy="BTourist")
	private List<TicketOrder> BTckorders;

	//bi-directional many-to-one association to TouristTicket
	@OneToMany(mappedBy="BTourist")
	private List<TouristTicket> BTourtcks;

	public Tourist() {
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTourname() {
		return this.tourname;
	}

	public void setTourname(String tourname) {
		this.tourname = tourname;
	}

	public List<TicketOrder> getBTckorders() {
		return this.BTckorders;
	}

	public void setBTckorders(List<TicketOrder> BTckorders) {
		this.BTckorders = BTckorders;
	}

	public TicketOrder addBTckorder(TicketOrder BTckorder) {
		getBTckorders().add(BTckorder);
		BTckorder.setBTourist(this);

		return BTckorder;
	}

	public TicketOrder removeBTckorder(TicketOrder BTckorder) {
		getBTckorders().remove(BTckorder);
		BTckorder.setBTourist(null);

		return BTckorder;
	}

	public List<TouristTicket> getBTourtcks() {
		return this.BTourtcks;
	}

	public void setBTourtcks(List<TouristTicket> BTourtcks) {
		this.BTourtcks = BTourtcks;
	}

	public TouristTicket addBTourtck(TouristTicket BTourtck) {
		getBTourtcks().add(BTourtck);
		BTourtck.setBTourist(this);

		return BTourtck;
	}

	public TouristTicket removeBTourtck(TouristTicket BTourtck) {
		getBTourtcks().remove(BTourtck);
		BTourtck.setBTourist(null);

		return BTourtck;
	}

}