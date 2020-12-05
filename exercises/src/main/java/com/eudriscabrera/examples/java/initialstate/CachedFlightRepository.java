package com.eudriscabrera.examples.java.initialstate;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ecabrerar
 * @since Dec 3, 2020
 */
public class CachedFlightRepository implements FlightRepository{
	private ObjectStore objectStore;
    private ConcurrentHashMap<UUID, Flight> cache = new ConcurrentHashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    
    CachedFlightRepository(ObjectStore objectStore){
    	this.objectStore = objectStore;
    }
    
	@Override
	public void saveFlight(Flight flight) {
		lock.writeLock().lock();

        objectStore.write(flight.getId(), flight);
        cache.put(flight.getId(), flight);

        lock.writeLock().unlock();
		
	}
	
	@Override
	public Optional<Flight> getFlight(UUID flightId) {
		lock.readLock().lock();

        Optional<Flight> result;

        if(cache.containsKey(flightId)) {
            result = Optional.of(cache.get(flightId));
        } else {
            result = objectStore.read(flightId).map(obj -> (Flight) obj);
        }

        lock.readLock().unlock();

        return result;
	}

}
