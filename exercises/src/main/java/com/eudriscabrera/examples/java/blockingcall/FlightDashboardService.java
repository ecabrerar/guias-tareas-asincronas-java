package com.eudriscabrera.examples.java.blockingcall;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * @author ecabrerar
 * @since Dec 5, 2020
 */
public class FlightDashboardService {
	private ExecutorService executor = Executors.newSingleThreadExecutor();


	private Logger logger = Logger.getLogger(this.getClass().getName());

	private final FlightDao flightDao;

	public FlightDashboardService(FlightDao flightDao) {
		super();
		this.flightDao = flightDao;
	}

	public List<Flight> getFlightCurrentStatus() {
		return flightDao.getFlights();
	}

	public void updateFlightStatus() {

		this.updateFlightStatusCommon();
	}
	
	private void updateFlightStatusCommon() {

		List<Flight> list = flightDao.getFlights();

		for (Flight flight : list) {
			System.out.println(" -----------------------------------------------------");
			flightDao.updateFlight(getFlightStatus(flight));
		}
	}
	
	public void updateFlightStatusWithFuture() throws InterruptedException, ExecutionException {
      
	   Future<?> future = executor.submit(()-> this.updateFlightStatusCommon());
	   
	   future.get();
	}

	// Simulando que la aerolinea nos devuelve el status del vuelo via un servicio
	// rest
	private Flight getFlightStatus(Flight flight) {

		logger.info(String.format("Confirmar el status del vuelo %s", flight.toString()));

		sleep();

		return flight;
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
