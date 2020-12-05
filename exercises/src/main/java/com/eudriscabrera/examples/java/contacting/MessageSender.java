package com.eudriscabrera.examples.java.contacting;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MessageSender {

	private Logger logger =	Logger.getLogger(this.getClass().getName());

	public void sendMessageBloqueante(Message message){
		logger.info("Contactando usuario");
		sendMail( message.getEmail1() );
		System.out.println("---------------------------------------------------");

		sendMail( message.getEmail2() );
		System.out.println("---------------------------------------------------");

		sendSMS( message.getMobileNumber() );
		System.out.println("---------------------------------------------------");

		sendWhatsApp( message.getWhatsAppNumber() );

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


	public void sendMessageCF(Message message){
		logger.info("Contactando usuario");

		//sendMail( message.getMail1() );
		CompletableFuture.runAsync( ()-> sendMail( message.getEmail1() )).join();
		System.out.println("---------------------------------------------------");

		//sendMail( message.getMail2() );
		CompletableFuture.runAsync( ()-> sendMail( message.getEmail2() )).join();
		System.out.println("---------------------------------------------------");

		//sendSMS( message.getMobileNumber() );
		CompletableFuture.runAsync( ()-> sendSMS( message.getMobileNumber() )).join();
		System.out.println("---------------------------------------------------");

		//sendWhatsApp( message.getWhatsAppNumber() );
		CompletableFuture.runAsync( ()-> sendWhatsApp( message.getWhatsAppNumber() ) ).join();

	}

	public void sendMessageCFFinalWithThen(Message message){
		logger.info("Contactando usuario");

		CompletableFuture.runAsync( ()-> sendMail( message.getEmail1() )).
				thenRunAsync( ()-> sendMail( message.getEmail2() ) ).
				thenRunAsync(() -> sendSMS( message.getMobileNumber() ) ).
				thenRunAsync(   ()-> sendWhatsApp( message.getWhatsAppNumber() )).join();

		System.out.println("END sendMessageCFFinalWithThen");


	}


	public void sendMessageCFFinal(Message message){
		logger.info("Contactando usuario");

		//sendMail( message.getMail1() );
		CompletableFuture.runAsync( ()-> sendMail( message.getEmail1() ));
		System.out.println("---------------------------------------------------");

		//sendMail( message.getMail2() );
		CompletableFuture.runAsync( ()-> sendMail( message.getEmail2() ));
		System.out.println("---------------------------------------------------");

		//sendSMS( message.getMobileNumber() );
		CompletableFuture.runAsync( ()-> sendSMS( message.getMobileNumber() ));
		System.out.println("---------------------------------------------------");

		//sendWhatsApp( message.getWhatsAppNumber() );
		CompletableFuture.runAsync( ()-> sendWhatsApp( message.getWhatsAppNumber() ) );

	}


	public void sendMessageCFAsynAndConcurrent(Message message){
		logger.info("Contactando usuario");

		//sendMail( message.getMail1() );
		CompletableFuture<Void> cfMail1 = CompletableFuture.runAsync( ()-> sendMail( message.getEmail1() ));

		//sendMail( message.getMail2() );
		CompletableFuture<Void> cfMail2 = CompletableFuture.runAsync( ()-> sendMail( message.getEmail2() ));

		//sendSMS( message.getMobileNumber() );
		CompletableFuture<Void> cfSMS = CompletableFuture.runAsync( ()-> sendSMS( message.getMobileNumber() ));

		//sendWhatsApp( message.getWhatsAppNumber() );
		CompletableFuture<Void> cfWhatsApp =  CompletableFuture.runAsync( ()-> sendWhatsApp( message.getWhatsAppNumber() ) );

		CompletableFuture.allOf( cfMail1,
								 cfMail2,
								 cfSMS,
								 cfWhatsApp).join();

		System.out.println("FINALIZA EJECUCION CONCURRENTE");

		/*CompletableFuture.allOf( cfList.toArray( new CompletableFuture[0] ) ).join();
		developerList = cfList.stream()
				.filter( CompletableFuture::isDone )
				.map( CompletableFuture::join )
				.collect( Collectors.toList() );*/

	}

	public void sendMessageFuture(Message message) {
		logger.info("Contactando usuario");

		ExecutorService executor = Executors.newSingleThreadExecutor();

		//sendMail( message.getMail1() );
		Future<Void> futureMail1 = (Future<Void>) executor.submit(()-> sendMail( message.getEmail1() ));


		sendMail( message.getEmail2() );
		Future<Void> futureMail2 = (Future<Void>) executor.submit(()-> sendMail( message.getEmail2() ));

		//sendSMS( message.getMobileNumber() );
		Future<Void> futureMailSMS = (Future<Void>) executor.submit(()-> sendSMS( message.getMobileNumber() ));

		//sendWhatsApp( message.getWhatsAppNumber() );
		Future<Void> futureMail = (Future<Void>) executor.submit(()-> sendWhatsApp( message.getWhatsAppNumber() ));


	}


}
