
public class Banchiere {
	private int cassa ; 																			// il numero massimo delle risorse condivise che abbiamo
	private int[] fidoClienti; 																		// il vettore della massima richiesta di ognuno dei clienti
	private int potenzialeRichiesta;																// la potenziale richiesta che può avere un cliente
	private Cliente[] C; 																			// dichiarazione vettore C di clienti
	public  Stampa stampa ;																			//creazione della stampa CONDIVISA 
	private int indice=0;																			//indice per il sincronizzatore della partenza 
	
	
	public Banchiere(int cassa, Cliente C[]) { 														// costruttore della classe Banchiere.
																									// come parametri: la cassa e il vettore di clienti
		this.cassa = cassa;
		this.C = C;
		stampa = new Stampa (C, this);
		
	}
	/*
	 * 
	 * 
	 * Metodo della richiesta del prestito 
	 * Esegue tutti i controlli neccesssaroi per accettare o no la richiesta 
	 * chiama diverse funzioni per raggiungere la metà
	 * 
	 * 	
	 */
	public synchronized boolean richiedoPrestito (Cliente cAttuale) throws InterruptedException {  	// la prima funziona che viene chiamata  e se ritorn true allora il prestito è statto accettato 
		
		while(true){
				
				if(	cAttuale.getPotenzialeRichiesta() == cAttuale.getRichiesta()  &&  				//quando la potenziale richiesta è uguale alla richiesta  E
						
						cassa >= cAttuale.getRichiesta()  && 										// quando la cassa è maggior uguaòe della richiesta E
						
						controlloFido(cAttuale) ){ 													//il controlloFido è true ALLORA
				
					imposta(cAttuale);																//impostiamo i valori 
						
					stampa.print();																	//stampiamo lo stato 
					
					return true;																	//usciamo dal ciclo
					
				}else if(cassa>= cAttuale.getRichiesta()   &&  										//quando òa cassa è maggiore della richiesta E			
						
						controlloSimulazionePrestito(cAttuale) )  {									//ControlloSimualzionePrestito è true ALLORA
					
					imposta(cAttuale);																//impostiamo i valori 
					
					stampa.print();																	//stampiamo lo stato 
					
					return true;																	//usciamo dal ciclo
					
				}else{
						try {
							wait();																	//nel caso il cliente e la cassa non soddisfano le regole
																									//allora cliente va in wait 
						}
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				
			}
		
	}
	
	
	/*
	 * 
	 *Metodo del controllo del fido 
	 *Controllo se il fido di un cliente è maggior uguale del fido di un'altro cliente 
	 *Se il cliente di cui si parla ha finito oppure no 
	 *Se il cliente del vettore è diverso da quello che ha fatto la richiesta 
	 */
	
	private boolean controlloFido(Cliente cAttuale) {
		cAttuale.setPotenzialeRichiesta(cAttuale.getPotenzialeRichiesta()-cAttuale.getRichiesta());						//cambio la potenzialerichiesta soltanto per fare una simulazione del controllo del fido
		
		for(int i=0; i<C.length ;i++){
			
			if(C[i].getPotenzialeRichiesta() <= (cAttuale.getFido())  && 												  //Controllo se il fido di un cliente è maggior uguale del fido di un'altro cliente 
					
					!C[i].getTerminato() && 																			  //Se il cliente di cui si parla ha finito oppure no 
					
					!C[i].equals(cAttuale)){ 																				//Se il cliente del vettore è diverso da quello che ha fatto la richiesta 
				
				cAttuale.setPotenzialeRichiesta(cAttuale.getPotenzialeRichiesta()+cAttuale.getRichiesta());					//rimetto com'era prima la potenziale richiesta 
				
				return true;																								//true se sono state rispettate le condizioni
			}
		}
		cAttuale.setPotenzialeRichiesta(cAttuale.getPotenzialeRichiesta()+cAttuale.getRichiesta());							//rimetto com'era prima la potenziale richiesta 
		
		return false;																										//false se non sono state rispettate le condizioni	
				
	}
	
	/*
	 * 
	 * Metodo dek controllo del simaulazione del prestito 
	 * vede se la potenziale richiesta del cliente è minore o uguale della cassa - la richiesta del cliente attuale 
	 * e se il cliente ha finito oppure no 
	 */
	
	private  boolean controlloSimulazionePrestito(Cliente cAttuale) {

																	
		cAttuale.setPotenzialeRichiesta(cAttuale.getPotenzialeRichiesta()-cAttuale.getRichiesta()); 						//cambio la potenzialerichiesta soltanto per fare una simulazione
		
		for(int i=0; i<C.length  ;i++){																						
			
			if(C[i].getPotenzialeRichiesta() <= (cassa-cAttuale.getRichiesta())  &&											//se la potenziale richiesta è minore o uguale della cassa meno la richiesta del cliente attuale 
					
					!C[i].getTerminato()) { 																				//e se il cliente ha terminato oppure no 			
						
			
				cAttuale.setPotenzialeRichiesta(cAttuale.getPotenzialeRichiesta()+cAttuale.getRichiesta()); 				//rimetto come prima la potenziale richiesta , cosi imposta non si confonde 		
				
				return true;																								//faccio ritornare true la funzione booleana ,in segno di affermazione che le regole sono state rispettate 
			}
		}
		
		cAttuale.setPotenzialeRichiesta(cAttuale.getPotenzialeRichiesta()+cAttuale.getRichiesta()); 						//la potenziale richiesta viene rimessa come prima in caso della rifiuta della richiesta
		
		return false;																										//faccio ritornare false la funzione in caso di non rispetto alle condizioni  
	}		
	
	/*
	 * Metodo imposta 
	 * imposta tutti i valori al cliente quando viene accettata la richiesta 
	 * cambia lo stato della cassa
	 * 
	 */
	
	private void imposta(Cliente cAttuale) {
		// TODO Auto-generated method stub
		
		cassa-=cAttuale.getRichiesta();																					//cassa diminiuisce in base della richiesta 
		
		cAttuale.setPrestito(cAttuale.getPrestito()+cAttuale.getRichiesta());											//il prestito del cliente cresce in base alla richiesta effetuata  
		
		cAttuale.setPotenzialeRichiesta(cAttuale.getFido() - cAttuale.getPrestito());									//la potenziale richiesta diminuisce in pase al fido massimo e il prestito attuale 
	}
	
	
	/*
	 * 
	 * Metodo del recupero del prestito 
	 * restituisco i soldi alla banca in base al numero generato casualmente dal cliente 
	 * incremento la cassa 
	 * diminuisco il prestito attuale 
	 * 
	 */
	
	public synchronized void recuperoPrestito (Cliente cAttuale ) {
		
		cassa+=cAttuale.getRecupero();																				//cassa incremeneta in base del recupero del cliente 
	
		cAttuale.setPrestito(cAttuale.getPrestito()-cAttuale.getRecupero());										//il prestito diminuisce in base al recupero del cliente 
		
		stampa.print();																								//stampo lo stato di dijskastra 
			
		notifyAll();																								//sveglio tutti i thread che sono stati messi a dormire 
		
	}


	
	/*
	 * 
	 * Sinrconizzatore di partenza 
	 * fa iniziare tutti i thread nello stesso tempo 
	 * inizialmente tutti vengono messi in wait per poi esseere svegliati tutti nello stesso tempo
	 * ci garantisce una concorrenza migliore 
	 * 
	 */
	
	public synchronized void  sincronizzatore () throws InterruptedException {										
		
		indice++;																								//incrementa l'indice tutte le volte che viene chiamata la funzione 
		
		if (indice == C.length) {																				//se l'indice è uguale al numero dei clienti allora  sveglia tutti 
			
			notifyAll();
			
			System.out.println("PAWWWWWWWWW");															
		} 
		else {																									//sennò  lo mette in wait il cliente che lo ha chiamato 
			wait();
		}

	}
	
	/*
	 * 
	 * Metodo per prendere il valore della cassa 
	 */

	public synchronized int getCassa () {
		return cassa;
	}
	
}	
	
	/*
	 * 
	 * 
	 while (true)	{
		
		if (controlloRichiesta (cAttuale)) {  //prima di accettare la richiessta vengono fatti una serie di controlli che aiutano il banchiere di non andare nella situazione di stallo 
	
			return prestitoAccettato(); //ritornando true allora il cliente si occupa di mettere apposto la sua tabella
			
		}
		
		else {			//in caso la sua richiesta non viene accettata allora il cliente viene messo in wait 
			//-System.out.println(cAttuale.getName() + " viene rifiutato " + cAttuale.getRichiesta());
			wait();
			}
	 }
	 
	 *
	 *
	 *
	 *
	 *
	public synchronized boolean  controlloRichiesta (Cliente cAttuale) throws InterruptedException {   //funzione che controlla se la richiesta deve essere accettata oppure no 
					//serve la richiesta , potenzialeRichiesta , nome del thread 
	
			if (cAttuale.getRichiesta() <= cassa && cAttuale.getRichiesta() == cAttuale.getPotenzialeRichiesta() && controlloFido(cAttuale)  ) {
				
				return true;
			}
			else 
				if (cAttuale.getRichiesta() <= cassa && controlloSimulazionePrestito(cAttuale)) {
					cassa-=cAttuale.getRichiesta();
					return true ; 
				}
				else
					wait();
		
		return false;
		
	}

	 *
	 *
	 *		
	int cassaSimulazione = cassa - cAttuale.getRichiesta();
	System.out.println(cassaSimulazione);
			
	for (int i=0;i<C.length ;i++){
			if (cassaSimulazione >= C[i].getPotenzialeRichiesta() && !C[i].getTerminato() && !C[i].getName().equals(cAttuale.getName())) {
					return true ; 							
		}
		else if (cassaSimulazione >= potenzialeRichiestaSimulazione &&  C[i].getName().equals( cAttuale.getName()) && !cAttuale.getTerminato() ) {
				
				System.out.println("entro");
				return true;
			}
	
			return false; 

		 //viene momentaneamente tolto dalla p.r. il valore della richiesta del 
		
	}	
	 *
	 *
	 *
	 *	
	for (int i=0 ; i<C.length;i++) {
		if (cAttuale.getFido() >= C[i].getPotenzialeRichiesta())
			return true;
	}
	return false;
	 */


