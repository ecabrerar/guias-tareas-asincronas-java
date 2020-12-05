package com.eudriscabrera.examples.java.initialstate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author ecabrerar
 * @since Dec 5, 2020
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileBasedObjectStoreTest {

	private Path storeFolder;
	private ObjectStore objectStore;

	@BeforeAll
	void setup() throws Exception {
		storeFolder = Files.createTempDirectory("FileBasedObjectStoreTest");
		objectStore = new FileBasedObjectStore(storeFolder);
	}

	@Test
	void write_shouldWriteTheObjectToAFile() {
		Flight flight = TestHelpers.generateFlight();

		boolean writeResult = objectStore.write(flight.getId(), flight);

		File expectedFile = new File(storeFolder.toFile(), flight.getId().toString());

		assertTrue(writeResult);
		assertTrue(expectedFile.exists());

		try (FileInputStream fileIn = new FileInputStream(expectedFile);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			Flight actualFlight = (Flight) in.readObject();
			assertEquals(flight, actualFlight);
		} catch (Exception ex) {
			fail(ex);
		}
	}

	@Test
	void read_shouldReadTheObjectFromTheFile() {
		Flight flight = TestHelpers.generateFlight();

		File expectedFile = new File(storeFolder.toFile(), flight.getId().toString());

		try (FileOutputStream fileOut = new FileOutputStream(expectedFile);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(flight);
		} catch (Exception ex) {
			fail(ex);
		}

		Optional<Flight> result = objectStore.read(flight.getId()).map(obj -> (Flight) obj);

		assertTrue(result.isPresent());
		assertEquals(flight, result.get());
	}

	@AfterEach
	void teardown() throws Exception {
		Files.walk(storeFolder).forEach(path -> path.toFile().deleteOnExit());
		storeFolder.toFile().deleteOnExit();
	}

}
