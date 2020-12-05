package com.eudriscabrera.examples.java;

import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

/**
 * @author ecabrerar
 * @since Dec 3, 2020
 */
public class TestHelpers {
	
    private static Random rnd = new Random(System.currentTimeMillis());
    
    
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
    
    static Flight generateFlight() {
        return new Flight(
                UUID.randomUUID(),
                "name"+rnd.nextInt(1000),
                "departure"+rnd.nextInt(1000),
                "destination"+rnd.nextInt(1000),
                "number"+rnd.nextInt(1000),
                rnd.nextDouble()+100,
                false,
                false,
                LocalTime.of(17, 0),
                LocalTime.of(14, 25));
    }

}
