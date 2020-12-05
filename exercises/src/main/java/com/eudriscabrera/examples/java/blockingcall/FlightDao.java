package com.eudriscabrera.examples.java.blockingcall;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author ecabrerar
 * @since Dec 5, 2020
 */
public class FlightDao {
	
    private Logger logger =	Logger.getLogger(this.getClass().getName());

	
	private List<Flight> flights ;
	
	public FlightDao() {
		flights = List.of(
				new Flight("AA0001", "Santo Domingo", "Medellin", Flight.Status.ON_TIME, LocalDateTime.now(), LocalDateTime.now().plusHours(3)),
				new Flight("AA0002", "Santo Domingo", "Guatemala", Flight.Status.ON_TIME, LocalDateTime.now(), LocalDateTime.now().plusHours(3)),
				new Flight("AA0003", "Santo Domingo", "Panama", Flight.Status.ON_TIME, LocalDateTime.now(), LocalDateTime.now().plusHours(3)),
				new Flight("AA0004", "Santo Domingo", "San Salvador", Flight.Status.ON_TIME, LocalDateTime.now(), LocalDateTime.now().plusHours(3)),
				new Flight("AA0005", "Santo Domingo", "Managua", Flight.Status.ON_TIME, LocalDateTime.now(), LocalDateTime.now().plusHours(3))
				);
		
		logger.info("Iniciando FlightDao");
	}

	public List<Flight> getFlights() {
		return flights;
	}
	
	public void updateFlight(Flight flight) {
		
	}
	
	
  
}
