package com.eudriscabrera.examples.java.initialstate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author ecabrerar
 * @since Dec 5, 2020
 */
class CachedFlightRepositoryTest {
	private CachedFlightRepository repository;
    private ObjectStore objectStore;

    @BeforeEach
    void Setup() {
        objectStore = mock(ObjectStore.class);
        repository = new CachedFlightRepository(objectStore);
    }

    @Test
    void save_shouldSaveTheFlightInTheObjectStore() {
        Flight flight = TestHelpers.generateFlight();

        when(objectStore.write(flight.getId(), flight)).thenReturn(true);

        repository.saveFlight(flight);

        verify(objectStore).write(flight.getId(), flight);
    }

    @Test
    void save_shouldUpdateTheCache() {
        Flight flight = TestHelpers.generateFlight();

        when(objectStore.write(flight.getId(), flight)).thenReturn(true);

        repository.saveFlight(flight);
        Optional<Flight> result = repository.getFlight(flight.getId());

        verify(objectStore, times(1)).write(flight.getId(), flight);
        verify(objectStore, never()).read(flight.getId());

        assertTrue(result.isPresent());
        assertEquals(flight, result.get());
    }

    @Test
    void get_shouldQueryTheObjectStoreAndReturnAFlightIfPresent() {
        Flight flight = TestHelpers.generateFlight();

        when(objectStore.read(flight.getId())).thenReturn(Optional.of(flight));

        Optional<Flight> result = repository.getFlight(flight.getId());

        verify(objectStore).read(flight.getId());
        assertTrue(result.isPresent());
        assertEquals(flight, result.get());

    }

    @Test
    void get_shouldQueryTheObjectStoreAndNotReturnAFlightIfNotPresent() {
        Flight flight = TestHelpers.generateFlight();

        when(objectStore.read(flight.getId())).thenReturn(Optional.empty());

        Optional<Flight> result = repository.getFlight(flight.getId());

        verify(objectStore).read(flight.getId());
        assertFalse(result.isPresent());
    }

    @Test
    void get_shouldQueryTheObjectStoreForEachUniqueId() {
        Flight flight1 = TestHelpers.generateFlight();
        Flight flight2 = TestHelpers.generateFlight();

        when(objectStore.read(flight1.getId())).thenReturn(Optional.of(flight1));
        when(objectStore.read(flight2.getId())).thenReturn(Optional.of(flight2));

        Optional<Flight> result1 = repository.getFlight(flight1.getId());
        Optional<Flight> result2 = repository.getFlight(flight2.getId());

        verify(objectStore, times(1)).read(flight1.getId());
        verify(objectStore, times(1)).read(flight2.getId());
        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
    }
}
