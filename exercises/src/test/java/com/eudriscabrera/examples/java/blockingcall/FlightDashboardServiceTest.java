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
	void testUpdateFlightStatusBlockingSync() {
		flightDashboardService.updateFlightStatusBlockingSync();
	}

	@Test
	void testUpdateFlightStatusAsyncWithFuture() throws InterruptedException, ExecutionException {
		flightDashboardService.updateFlightStatusAsyncWithFuture();
		System.out.println("Finaliza la invocación del método ... mientras se ejecuta de manera asíncrona");

		sleep( 30_000 );//30 segundos para poder ver la ejecución completa
	}

	@Test
	void testUpdateFlightStatusAsyncWithFutureButBlocking() throws InterruptedException, ExecutionException {
		flightDashboardService.updateFlightStatusAsyncWithFutureButBlocking();
		System.out.println("Finaliza la invocación del método");
	}


	@Test
	void testUpdateFlightStatusAsyncWithCompletableFuture() throws InterruptedException, ExecutionException {
		flightDashboardService.updateFlightStatusAsyncWithCompletableFuture();
		System.out.println("Finaliza la invocación del método ... mientras se ejecuta de manera asíncrona");

		sleep( 30_000 );//30 segundos para poder ver la ejecución completa
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
