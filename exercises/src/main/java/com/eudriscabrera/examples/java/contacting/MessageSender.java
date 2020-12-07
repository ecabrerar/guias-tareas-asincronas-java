package com.eudriscabrera.examples.java.contacting;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class MessageSender {

	private Logger logger =	Logger.getLogger(this.getClass().getName());

	public void sendMessageBlockingSync(Message message){
		logger.info("Contactando usuario");
		sendMail( message.getEmail1() );
		System.out.println("---------------------------------------------------");

		sendMail( message.getEmail2() );
		System.out.println("---------------------------------------------------");

		sendSMS( message.getMobileNumber() );
		System.out.println("---------------------------------------------------");

		sendWhatsApp( message.getWhatsAppNumber() );
	}

	public void sendMessageAsyncWithFuture(Message message) {
		logger.info("Contactando usuario");

		ExecutorService executor = Executors.newSingleThreadExecutor();

		Future<Void> futureMail1 = (Future<Void>) executor.submit(()-> sendMail( message.getEmail1() ));
		Future<Void> futureMail2 = (Future<Void>) executor.submit(()-> sendMail( message.getEmail2() ));
		Future<Void> futureMailSMS = (Future<Void>) executor.submit(()-> sendSMS( message.getMobileNumber() ));
		Future<Void> futureWhatsapp = (Future<Void>) executor.submit(()-> sendWhatsApp( message.getWhatsAppNumber() ));
	}

	public void sendMessageAsyncWithCompletableFuture(Message message){
		logger.info("Contactando usuario");

		CompletableFuture.runAsync( ()-> sendMail( message.getEmail1() )).
				thenRunAsync( ()-> sendMail( message.getEmail2() ) ).
				thenRunAsync(() -> sendSMS( message.getMobileNumber() ) ).
				thenRunAsync(   ()-> sendWhatsApp( message.getWhatsAppNumber() ));

	}

	public void sendMessageAsyncAndConcurrentWithCompletableFuture(Message message){
		logger.info("Contactando usuario");

		CompletableFuture.runAsync( ()-> sendMail( message.getEmail1() ));
		CompletableFuture.runAsync( ()-> sendMail( message.getEmail2() ));
		CompletableFuture.runAsync( ()-> sendSMS( message.getMobileNumber() ));
		CompletableFuture.runAsync( ()-> sendWhatsApp( message.getWhatsAppNumber() ) );
	}

	public void sendMessageAsyncAndConcurrentWithCompletableFutureAndExecutor(Message message){
		logger.info("Contactando usuario");

		ExecutorService executor = Executors.newFixedThreadPool( 2 );

		CompletableFuture.runAsync( ()-> sendMail( message.getEmail1() ), executor);
		CompletableFuture.runAsync( ()-> sendMail( message.getEmail2() ), executor);
		CompletableFuture.runAsync( ()-> sendSMS( message.getMobileNumber() ), executor);
		CompletableFuture.runAsync( ()-> sendWhatsApp( message.getWhatsAppNumber() ), executor );
	}


	private void sendMail(String email){

		logger.info(String.format("Enviando Correo electrónico: %s: " + getPoolInfo(), email));
		sleep();
		logger.info("Correo electrónico enviado ");
	}

	private void sendSMS(String mobileNumber){

		logger.info("Enviando mensaje de texto: " + getPoolInfo());
		sleep();
		logger.info("Mensaje de texto enviado ");
	}

	private void sendWhatsApp(String mobileNumber){
		logger.info("Enviando mensaje a whatsapp: " + getPoolInfo());
		sleep();
		logger.info("Mensaje a whatsapp enviado");
	}

	private void sleep() {
		try {
			Random r = new Random();
			Thread.sleep( r.nextInt(10) * 1000 );

		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String getPoolInfo(){
		return ( "Pool size: " + ForkJoinPool.commonPool().getPoolSize()
									+ ". Thread:  " + Thread.currentThread().getName());
	}

}
