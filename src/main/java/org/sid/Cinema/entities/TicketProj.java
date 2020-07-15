package org.sid.Cinema.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "ticketproj",types =Ticket.class )
	
	public interface TicketProj {

		public Long getId_ticket();
		public String getNomclient();
		public double getPrix();
		public Integer getCodepayement();
		public boolean getReservee();
		public Place getPlace();
}
