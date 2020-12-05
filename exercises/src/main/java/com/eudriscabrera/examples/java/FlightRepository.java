package com.eudriscabrera.examples.java;

import java.util.Optional;
import java.util.UUID;

/**
 * @author ecabrerar
 * @since Dec 3, 2020
 */
public interface FlightRepository {
	  void saveFlight(Flight flight);
	    Optional<Flight> getFlight(UUID flightId);
}
