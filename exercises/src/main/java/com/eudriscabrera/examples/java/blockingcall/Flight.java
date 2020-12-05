package com.eudriscabrera.examples.java.blockingcall;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author ecabrerar
 * @since Dec 3, 2020
 */
public class Flight implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
   public enum Status {
		 ON_TIME,
		 DELAYED
	}
	
	private final String number;
    private final String departure;
    private final String destination;
    private final Status status;
    private final LocalDateTime arrivalTime; 
    private final LocalDateTime departureTime;
    
    
    
	public Flight(String number, String departure, String destination, Status status, LocalDateTime arrivalTime,
			LocalDateTime departureTime) {
		super();
		this.number = number;
		this.departure = departure;
		this.destination = destination;
		this.status = status;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getNumber() {
		return number;
	}


	public String getDeparture() {
		return departure;
	}


	public String getDestination() {
		return destination;
	}


	public Status getStatus() {
		return status;
	}


	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}


	public LocalDateTime getDepartureTime() {
		return departureTime;
	} 	
    
	@Override
	public int hashCode() {
		return Objects.hash(arrivalTime, departure, departureTime, destination, number, status);
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
		return Objects.equals(arrivalTime, other.arrivalTime) && Objects.equals(departure, other.departure)
				&& Objects.equals(departureTime, other.departureTime) && Objects.equals(destination, other.destination)
				&& Objects.equals(number, other.number) && status == other.status;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Flight [number=").append(number).append(", departure=").append(departure)
				.append(", destination=").append(destination).append(", status=").append(status)
				.append(", arrivalTime=").append(arrivalTime).append(", departureTime=").append(departureTime)
				.append("]");
		return builder.toString();
	}
   

    
}
