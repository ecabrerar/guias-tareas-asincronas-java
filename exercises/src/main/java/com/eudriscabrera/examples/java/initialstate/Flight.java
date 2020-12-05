package com.eudriscabrera.examples.java.initialstate;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

/**
 * @author ecabrerar
 * @since Dec 3, 2020
 */
public class Flight implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	private final UUID id;
    private final String name;
    private final String departure;
    private final String destination;
    private final String number;
    private final double price;
    private final boolean delayed;
    private final boolean active;
    private final LocalTime arrivalTime; 
    private final LocalTime departureTime;
    
    /**
     * 
     * @param id
     * @param name
     * @param departure
     * @param destination
     * @param number
     * @param price
     * @param delayed
     * @param active
     * @param arrivalTime
     * @param departureTime
     */
	public Flight(UUID id, String name, String departure, String destination, String number, double price,
			boolean delayed, boolean active, LocalTime arrivalTime, LocalTime departureTime) {
		super();
		this.id = id;
		this.name = name;
		this.departure = departure;
		this.destination = destination;
		this.number = number;
		this.price = price;
		this.delayed = delayed;
		this.active = active;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDeparture() {
		return departure;
	}

	public String getDestination() {
		return destination;
	}

	public String getNumber() {
		return number;
	}

	public double getPrice() {
		return price;
	}

	public boolean isDelayed() {
		return delayed;
	}

	public boolean isActive() {
		return active;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, arrivalTime, delayed, departure, departureTime, destination, id, name, number,
				price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return active == other.active && Objects.equals(arrivalTime, other.arrivalTime) && delayed == other.delayed
				&& Objects.equals(departure, other.departure) && Objects.equals(departureTime, other.departureTime)
				&& Objects.equals(destination, other.destination) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(number, other.number)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}
   

    
}
