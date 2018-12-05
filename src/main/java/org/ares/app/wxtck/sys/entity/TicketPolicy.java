package org.ares.app.wxtck.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
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

	@OneToMany(mappedBy="tckpolicy")
	private List<TouristTicket> tourtcks;

	public TouristTicket addTourtck(TouristTicket BTourtck) {
		getTourtcks().add(BTourtck);
		BTourtck.setTckpolicy(this);
		return BTourtck;
	}

	public TouristTicket removeTourtck(TouristTicket BTourtck) {
		getTourtcks().remove(BTourtck);
		BTourtck.setTckpolicy(null);
		return BTourtck;
	}

}