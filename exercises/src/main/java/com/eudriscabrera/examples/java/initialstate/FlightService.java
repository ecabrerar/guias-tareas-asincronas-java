package com.eudriscabrera.examples.java.initialstate;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author ecabrerar
 * @since Dec 3, 2020
 */
public class FlightService {
	private FlightRepository flightRepository;

	public FlightService(FlightRepository flightRepository) {
		super();
		this.flightRepository = flightRepository;
	}
	
	  UUID addFlight(String name, String departure, String destination, String number, double price,
				boolean delayed, boolean active, LocalTime arrivalTime, LocalTime departureTime) {
	        UUID flightId = UUID.randomUUID();
	        flightRepository.saveFlight(new Flight(flightId, name, departure, destination, number,
	        		price,	delayed,active,arrivalTime,departureTime)
	        );

	        return flightId;
	    }

	    Optional<String> getFlightName(UUID flightId) {
	        return flightRepository.getFlight(flightId).map(Flight::getName);
	    }

	    Optional<String> getFlightNumber(UUID flightId) {
	        return flightRepository.getFlight(flightId).map(Flight::getNumber);
	    }

	    Optional<String> getDestination(UUID flightId) {
	        return flightRepository.getFlight(flightId).map(Flight::getDestination);
	    }

	    Optional<String> getDeparture(UUID flightId) {
	        return flightRepository.getFlight(flightId).map(Flight::getDeparture);
	    }

}
