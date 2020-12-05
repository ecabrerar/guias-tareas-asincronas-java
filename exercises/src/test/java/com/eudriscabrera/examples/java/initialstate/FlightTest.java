package com.eudriscabrera.examples.java.initialstate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * @author ecabrerar
 * @since Dec 3, 2020
 */
class FlightTest {
	
	 @Test
	    void equals_shouldReturnTrue_ifFlightsAreEqual() {
	        Flight flight1 = TestHelpers.generateFlight();
	        Flight flight2 = new Flight(flight1.getId(), flight1.getName(), flight1.getDeparture(), flight1.getDestination(), flight1.getNumber(),
	        		flight1.getPrice(),	flight1.isDelayed(),flight1.isActive(),flight1.getArrivalTime(),flight1.getDepartureTime());

	        assertEquals(flight1, flight2);
	    }

	    @Test
	    void equals_shouldReturnFalse_ifFlightsAreNotEqual() {
	    	Flight flight1 = TestHelpers.generateFlight();
	    	Flight flight2 = TestHelpers.generateFlight();

	        assertNotEquals(flight1, flight2);
	    }

}
