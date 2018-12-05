package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
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

	@ManyToOne
	@JoinColumn(name="openid")
	private Tourist tourist;

	//bi-directional many-to-one association to TouristTicket
	@OneToMany(mappedBy="tckorder")
	private List<TouristTicket> tourtcks;

	
	public TouristTicket addTourtck(TouristTicket BTourtck) {
		getTourtcks().add(BTourtck);
		BTourtck.setTckorder(this);
		return BTourtck;
	}

	public TouristTicket removeTourtck(TouristTicket BTourtck) {
		getTourtcks().remove(BTourtck);
		BTourtck.setTckorder(null);
		return BTourtck;
	}

}