package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
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

	@OneToMany(mappedBy="tourist")
	private List<TicketOrder> tckorders;

	@OneToMany(mappedBy="tourist")
	private List<TouristTicket> tourtcks;

	public TicketOrder addTckorder(TicketOrder BTckorder) {
		getTckorders().add(BTckorder);
		BTckorder.setTourist(this);
		return BTckorder;
	}

	public TicketOrder removeTckorder(TicketOrder BTckorder) {
		getTckorders().remove(BTckorder);
		BTckorder.setTourist(null);
		return BTckorder;
	}

	public TouristTicket addTourtck(TouristTicket BTourtck) {
		getTourtcks().add(BTourtck);
		BTourtck.setTourist(this);
		return BTourtck;
	}

	public TouristTicket removeTourtck(TouristTicket BTourtck) {
		getTourtcks().remove(BTourtck);
		BTourtck.setTourist(null);
		return BTourtck;
	}

}