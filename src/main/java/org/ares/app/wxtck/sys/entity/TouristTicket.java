package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
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

	@ManyToOne
	@JoinColumn(name="ordid")
	private TicketOrder tckorder;

	@ManyToOne
	@JoinColumn(name="tpid")
	private TicketPolicy tckpolicy;

	@ManyToOne
	@JoinColumn(name="openid")
	private Tourist tourist;
	
}