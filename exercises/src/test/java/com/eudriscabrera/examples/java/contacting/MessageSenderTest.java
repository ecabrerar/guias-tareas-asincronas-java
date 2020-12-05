package com.eudriscabrera.examples.java.contacting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderTest {

	@Test
	void sendMessageBloqueante() {
		MessageSender messageSender = new MessageSender();

		messageSender.sendMessageBloqueante( new Message( "Hola",
												"mimail@correo.com",
												"contacto@hooya.com",
												"3003345677",
												"(+1)2334589898"
												) );
	}

	@Test
	void sendMessageCF() {
		MessageSender messageSender = new MessageSender();

		messageSender.sendMessageCF( new Message( "Hola",
												"mimail@correo.com",
												"contacto@hooya.com",
												"3003345677",
												"(+1)2334589898"
		) );
		System.out.println("Finaliza la invocación del método ... mientras se ejecuta de manera asíncrona");

		sleep( 30_000 );
	}

	@Test
	void sendMessageFuture() {
		MessageSender messageSender = new MessageSender();

		messageSender.sendMessageFuture( new Message( "Hola",
												  "mimail@correo.com",
												  "contacto@hooya.com",
												  "3003345677",
												  "(+1)2334589898"
		) );

		System.out.println("Finaliza la invocación del método ... mientras se ejecuta de manera asíncrona");
		sleep( 30_000 );
	}

	private void sleep(long time) {
		try {
			Thread.sleep( time );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
