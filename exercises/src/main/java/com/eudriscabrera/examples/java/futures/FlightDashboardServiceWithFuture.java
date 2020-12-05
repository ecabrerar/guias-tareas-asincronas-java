package com.eudriscabrera.examples.java.futures;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import com.eudriscabrera.examples.java.blockingcall.Flight;
import com.eudriscabrera.examples.java.blockingcall.FlightDao;

/**
 * @author ecabrerar
 * @since Dec 5, 2020
 */
public class FlightDashboardServiceWithFuture {
	private ExecutorService executor = Executors.newSingleThreadExecutor();

	private Logger logger = Logger.getLogger(this.getClass().getName());

	private final FlightDao flightDao;

	public FlightDashboardServiceWithFuture(FlightDao flightDao) {
		super();
		this.flightDao = flightDao;
	}

	public List<Flight> getFlightCurrentStatus() {
		return flightDao.getFlights();
	}

	public void updateFlightStatus() throws InterruptedException, ExecutionException {

		List<Flight> list = flightDao.getFlights();

		for (Flight flight : list) {
			System.out.println(" -------------------------------------------------------------- ");
			
			Future<Flight> futureFlight = getFlightStatus(flight);
			
			while(!futureFlight.isDone()) {
			    System.out.println("Esperando...");
			    Thread.sleep(300);
			}

			Flight currentStatus = futureFlight.get();
			
			
			flightDao.updateFlight(currentStatus);
		}
	}

	// Simulando que la aerolinea nos devuelve el status del vuelo via un servicio
	// rest
	private Future<Flight> getFlightStatus(Flight flight) {
		logger.info(String.format("Confirmar el status del vuelo %s", flight.toString()));

		  return executor.submit(() -> {
			  sleep();
	            return flight;
	        });
	}

	private void sleep() {
		try {

			Random r = new Random();

			Thread.sleep(r.nextInt(10) * 1000);

			logger.info("Respuesta de aerolinea recibida");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
