package com.eudriscabrera.examples.java.futures;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.eudriscabrera.examples.java.blockingcall.FlightDao;

class FlightDashboardServiceWithFutureTest {

	FlightDashboardServiceWithFuture flightDashboardService;

	@BeforeEach
	void setup() {
		flightDashboardService = new FlightDashboardServiceWithFuture(new FlightDao());
	}

	@Test
	void testGetFlightCurrentStatus() throws InterruptedException, ExecutionException {
		flightDashboardService.updateFlightStatus();
	}

}
