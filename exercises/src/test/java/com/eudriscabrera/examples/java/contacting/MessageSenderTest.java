package com.eudriscabrera.examples.java.contacting;

import org.junit.jupiter.api.Test;

class MessageSenderTest {

	private void sleep(long time) {
		try {
			Thread.sleep( time );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testSendMessageBlockingSync() {
		MessageSender messageSender = new MessageSender();

		messageSender.sendMessageBlockingSync( new Message( "Hola",
												  "mimail@correo.com",
												  "contacto@hooya.com",
												  "3003345677",
												  "(+1)2334589898"
		) );
		System.out.println("Finaliza la invocación del método");
	}

	@Test
	void testSendMessageAsyncWithFuture() {
		MessageSender messageSender = new MessageSender();

		messageSender.sendMessageAsyncWithFuture( new Message( "Hola",
												  "mimail@correo.com",
												  "contacto@hooya.com",
												  "3003345677",
												  "(+1)2334589898"
		) );
		System.out.println("Finaliza la invocación del método ... mientras se ejecuta de manera asíncrona");

		sleep( 30_000 );//30 segundos para poder ver la ejecución completa
	}

	@Test
	void testSendMessageAsyncWithCompletableFuture() {
		MessageSender messageSender = new MessageSender();

		messageSender.sendMessageAsyncWithCompletableFuture( new Message( "Hola",
												  "mimail@correo.com",
												  "contacto@hooya.com",
												  "3003345677",
												  "(+1)2334589898"
		) );
		System.out.println("Finaliza la invocación del método ... mientras se ejecuta de manera asíncrona");

		sleep( 30_000 );//30 segundos para poder ver la ejecución completa
	}

	@Test
	void testSendMessageAsyncAndConcurrentWithCompletableFuture() {
		MessageSender messageSender = new MessageSender();

		messageSender.sendMessageAsyncAndConcurrentWithCompletableFuture( new Message( "Hola",
												  "mimail@correo.com",
												  "contacto@hooya.com",
												  "3003345677",
												  "(+1)2334589898"
		) );
		System.out.println("Finaliza la invocación del método ... mientras se ejecuta de manera asíncrona");

		sleep( 30_000 );//30 segundos para poder ver la ejecución completa
	}

	@Test
	void testSendMessageAsyncAndConcurrentWithCompletableFutureAndExecutor() {
		MessageSender messageSender = new MessageSender();

		messageSender.sendMessageAsyncAndConcurrentWithCompletableFutureAndExecutor( new Message( "Hola",
												  "mimail@correo.com",
												  "contacto@hooya.com",
												  "3003345677",
												  "(+1)2334589898"
		) );
		System.out.println("Finaliza la invocación del método ... mientras se ejecuta de manera asíncrona");

		sleep( 30_000 );//30 segundos para poder ver la ejecución completa
	}
}
