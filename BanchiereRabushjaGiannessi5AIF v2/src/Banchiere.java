
public class Banchiere {
	private int cassa; // il numero massimo delle risorse condivise che abbiamo
	private int[] fidoClienti; // il vettore della massima richiesta di ognuno dei clienti
	private int potenzialeRichiesta;// la potenziale richiesta che può avere un cliente
	private Cliente[] C; // dichiarazione vettore C di clienti
	public  Stampa stampa ;

	public Banchiere(int cassa, Cliente C[]) { // costruttore della classe Banchiere.
		// come parametri: la cassa e il vettore di clienti
		this.cassa = cassa;
		this.C = C;
		stampa = new Stampa (C, this);
		
	}
	
	public synchronized boolean richiedoPrestito (Cliente cAttuale) throws InterruptedException {  // la prima funziona che viene chiamata  e se ritorn true allora il prestito è statto accettato 
		
		while (true) {
		if (controlloRichiesta (cAttuale)) {  //prima di accettare la richiessta vengono fatti una serie di controlli che aiutano il banchiere di non andare nella situazione di stallo 
			cassa-=cAttuale.getRichiesta();
			
			return prestitoAccettato(); //ritornando true allora il cliente si occupa di mettere apposto la sua tabella
			
		}
		else {			//in caso la sua richiesta non viene accettata allora il cliente viene messo in wait 
			wait();
			}
		}
		
	}

	public boolean  controlloRichiesta (Cliente cAttuale) {   //funzione che controlla se la richiesta deve essere accettata oppure no 
					//serve la richiesta , potenzialeRichiesta , nome del thread 
		if ( cAttuale.getRichiesta() <= cassa ) {
			if (cAttuale.getRichiesta() == cAttuale.getPotenzialeRichiesta()) {
				return true;
			}
			else 
				if (controlloSimulazionePrestito(cAttuale)) {
					return true ; 
				}
		}
		return false;
		
	}


	private boolean prestitoAccettato() {
		
		return true ;
	}
	public synchronized void recuperoPrestito (int recupero ) {
		//cliente.setPrestito(cliente.getPrestito()+richiesta);
		//cambia potenziale richiesta
		cassa+=recupero;
		notifyAll();
		stampa.print();
	}


	private boolean controlloSimulazionePrestito(Cliente cAttuale) {
	

		int cassaSimulazione = cassa - cAttuale.getRichiesta();
		int potenzialeRichiestaSimulazione = cAttuale.getPotenzialeRichiesta() -  cAttuale.getRichiesta();  
		for (int i=0;i<C.length ;i++)
			if (cassaSimulazione >= C[i].getPotenzialeRichiesta() && !cAttuale.getTerminato()) {
				return true ; 							
			}
			else if (cassaSimulazione >= potenzialeRichiestaSimulazione &&  C[i].getName().equals( cAttuale.getName()) ) {
				return true;
			}

		return false;
	}
	
	
	public int getCassa () {
		return cassa;
	}
		

}
