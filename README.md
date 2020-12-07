# Guía para programar tareas asíncronas en Java

Este repositorio contiene material utilizado en JConf CentroAmérica 2020. Para este se han utilizado únicamente
clases provistas en el JDK desde la version de Java 8. 

Este demo contiene dos ejercicios que generan llamadas bloqueantes (blocking calls), a cada uno de los demos se les ha he hecho unos ajustes
menores utilizando las clases `java.util.concurrent.Future` y `java.util.concurrent.CompletableFuture` para evitar los bloqueos y 
permitir que se ejecuten de manera asíncrona y/o concurrente.


## El código

Ambos demos presentan situaciones simuladas, como métodos que simulan la ejecución, esto se hace con el fin 
concentrarnos directamente en los cambios de las tareas bloqueantes. La ejecución se hace desde casos de pruebas 

### Demo 1: Tablero de estado de vuelos

Este demo se encuentra desarrollado en la clase `com.eudriscabrera.examples.java.blockingcall.FlightDashboardService` sus métodos pueden ser probados desde la clase de prueba: `com.eudriscabrera.examples.java.blockingcall.FlightDashboardServiceTest`

En este demo tenemos el método `private void updateFlightStatusCommon()` este consisten en un ciclo con un **for** que simula conectarse con las aerolíneas para consultar el estado de los vuelos. Este mismo método es invocado desde otros para ver diferentes formas de ejecución 
 
    private void updateFlightStatusCommon() {
		List<Flight> list = flightDao.getFlights();
		for (Flight flight : list) {
			System.out.println(" -----------------------------------------------------");
			flightDao.updateFlight(getFlightStatus(flight));
		}
	}

#### Ejecución bloqueante y síncrona

    public void updateFlightStatusBlockingSync() {
		this.updateFlightStatusCommon();
	}

#### Ejecución Asíncrona con `java.util.concurrent.Future`

    public void updateFlightStatusAsyncWithFuture() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> future = executor.submit(()-> this.updateFlightStatusCommon());
	}

#### Ejecución Asíncrona con `java.util.concurrent.Future` pero bloqueante

	public void updateFlightStatusAsyncWithFutureButBlocking() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> future = executor.submit(()-> this.updateFlightStatusCommon());
		future.get();
	}

#### Ejecución Asíncrona con `java.util.concurrent.CompletableFuture`

	public void updateFlightStatusAsyncWithCompletableFuture() {
		CompletableFuture.runAsync( ()-> this.updateFlightStatusCommon() );
	}	
	
	
### Demo 2: Envío de mensaje por diferentes medios (Mail, SMS, Redes sociales)

Este demo se encuentra desarrollado en la clase `com.eudriscabrera.examples.java.contacting.MessageSender` sus métodos pueden ser probados desde la clase de prueba: `com.eudriscabrera.examples.java.contacting.MessageSenderTest`

#### Ejecución bloqueante y síncrona

El método `sendMessageBlockingSync` invoca 4 métodos:
`sendMail( message.getEmail1() )`, `sendMail( message.getEmail2() )`, `sendSMS( message.getMobileNumber() )` y
		`sendWhatsApp( message.getWhatsAppNumber() )`, estos métodos se ejecutan de manera secuencial y de forma bloqueante, es decir
		que el siguiente método no se ejecuta hasta que en anterior no finalice.

	public void sendMessageBlockingSync(Message message){
		sendMail( message.getEmail1() );
		sendMail( message.getEmail2() );
		sendSMS( message.getMobileNumber() );
		sendWhatsApp( message.getWhatsAppNumber() );
	}


#### Ejecución Asíncrona con `java.util.concurrent.Future`

    public void sendMessageAsyncWithFuture(Message message) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Void> futureMail1 = (Future<Void>) executor.submit(()-> sendMail( message.getEmail1() ));
		Future<Void> futureMail2 = (Future<Void>) executor.submit(()-> sendMail( message.getEmail2() ));
		Future<Void> futureMailSMS = (Future<Void>) executor.submit(()-> sendSMS( message.getMobileNumber() ));
		Future<Void> futureWhatsapp = (Future<Void>) executor.submit(()-> sendWhatsApp( message.getWhatsAppNumber() ));
	}	
	
	
#### Ejecución Asíncrona con `java.util.concurrent.CompletableFuture`

    public void sendMessageAsyncWithCompletableFuture(Message message){
		CompletableFuture.runAsync( ()-> sendMail( message.getEmail1() )).
				thenRunAsync( ()-> sendMail( message.getEmail2() ) ).
				thenRunAsync(() -> sendSMS( message.getMobileNumber() ) ).
				thenRunAsync(   ()-> sendWhatsApp( message.getWhatsAppNumber() ));
	}

#### Ejecución Asíncrona y concurrente con `java.util.concurrent.CompletableFuture`

	public void sendMessageAsyncAndConcurrentWithCompletableFuture(Message message){
		logger.info("Contactando usuario");

		CompletableFuture.runAsync( ()-> sendMail( message.getEmail1() ));
		CompletableFuture.runAsync( ()-> sendMail( message.getEmail2() ));
		CompletableFuture.runAsync( ()-> sendSMS( message.getMobileNumber() ));
		CompletableFuture.runAsync( ()-> sendWhatsApp( message.getWhatsAppNumber() ) );
	}
	
#### Ejecución Asíncrona  y concurrente  `java.util.concurrent.CompletableFuture` definiendo el numero de hilos (threads) con `java.util.concurrent.Executors`

	public void sendMessageAsyncAndConcurrentWithCompletableFutureAndExecutor(Message message){
		ExecutorService executor = Executors.newFixedThreadPool( 2 );

		CompletableFuture.runAsync( ()-> sendMail( message.getEmail1() ), executor);
		CompletableFuture.runAsync( ()-> sendMail( message.getEmail2() ), executor);
		CompletableFuture.runAsync( ()-> sendSMS( message.getMobileNumber() ), executor);
		CompletableFuture.runAsync( ()-> sendWhatsApp( message.getWhatsAppNumber() ), executor );
	}


## Consideraciones

* Convertir códigos bloqueantes puede hacerse sin mucho esfuerzo utilizando las clases `Future` y `CompletableFuture`

* Utilizar `Future` y `CompletableFuture` no necesariamente hace que el código se ejecute de manera asíncrona, si usamos métodos como `get()` o `join()` el código se ejecutará de manera bloqueante

* Utilizando `CompletableFuture` podemos hacer que el código se ejecute de manera concurrente

* A partir de Java 8 utilizando instrucciones lambdas podemos invocar cualquier método para que se ejecute de manera asíncrona y/o concurrente, y sin hacer cambios a dichos métodos 
