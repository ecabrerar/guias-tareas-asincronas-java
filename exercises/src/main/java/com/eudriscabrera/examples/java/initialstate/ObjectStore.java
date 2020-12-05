package com.eudriscabrera.examples.java.initialstate;

import java.util.Optional;
import java.util.UUID;

/**
 * @author ecabrerar
 * @since Dec 3, 2020
 */
public interface ObjectStore {
	 Optional<Object> read(UUID id);
	    boolean write(UUID id, Object obj);
}
