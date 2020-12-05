package com.eudriscabrera.examples.java.initialstate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

/**
 * @author ecabrerar
 * @since Dec 5, 2020
 */
class FlightServiceTest {
	  private FlightRepository flightRepo;
	  private FlightService flightService;

	    @BeforeEach
	    void setup() {
	        flightRepo = mock(FlightRepository.class);
	        flightService = new FlightService(flightRepo);
	    }

	    @Test
	    void addFlight_shouldAddANewFlightToTheRepo() {
	        ArgumentCaptor<Flight> captor = ArgumentCaptor.forClass(Flight.class);

	        Flight expectedFlight = TestHelpers.generateFlight();
	        
	        	 
	        UUID flightId = flightService.addFlight(expectedFlight.getName(), expectedFlight.getDeparture(), expectedFlight.getDestination(), expectedFlight.getNumber(),
	        		expectedFlight.getPrice(),	expectedFlight.isDelayed(),expectedFlight.isActive(),expectedFlight.getArrivalTime(),expectedFlight.getDepartureTime());
	       
	        
	        verify(flightRepo).saveFlight(captor.capture());
	        
	        Flight actualFlight = captor.getValue();
	        

	        assertEquals(flightId, expectedFlight.getId());
	        assertEquals(expectedFlight.getName(), actualFlight.getName());
	        assertEquals(expectedFlight.getNumber(), actualFlight.getNumber());
	        assertEquals(expectedFlight.getDestination(), actualFlight.getDestination());
	        assertEquals(expectedFlight.getDeparture(), actualFlight.getDeparture());
	    }
	    
	    @Test
	    void getFlightName_shouldExtractTheNameFromTheFlight() {
	        Flight flight = TestHelpers.generateFlight();

	        when(flightRepo.getFlight(flight.getId())).thenReturn(Optional.of(flight));

	        Optional<String> result = flightService.getFlightName(flight.getId());

	        assertTrue(result.isPresent());
	        assertEquals(flight.getName(), result.get());
	    }

	    @Test
	    void getFlightNUmber_shouldExtractTheNumberFromTheFlight() {
	        Flight flight = TestHelpers.generateFlight();


	        when(flightRepo.getFlight(flight.getId())).thenReturn(Optional.of(flight));

	        Optional<String> result = flightService.getFlightName(flight.getId());

	        assertTrue(result.isPresent());
	        assertEquals(flight.getName(), result.get());
	    }

}
