package com.eudriscabrera.examples.java.blockingcall;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author ecabrerar
 * @since Dec 5, 2020
 */
class FlightDashboardServiceTest {
	FlightDashboardService flightDashboardService;
	
    @BeforeEach
	void setup() {
		flightDashboardService = new FlightDashboardService(new FlightDao());
	}
	

	@Test
	void testGetFlightCurrentStatus() {
		flightDashboardService.updateFlightStatus();
	}

	@Test
	void testGetFlightCurrentStatusWithFuture() throws InterruptedException, ExecutionException {
		
		
		flightDashboardService.updateFlightStatusWithFuture();
		System.out.println("Termina la invocacion");
		sleep(25000);
	}

	private void sleep(long time) {
		try {
			Thread.sleep( time );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	


}
