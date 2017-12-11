import java.util.*;
public class Cliente extends Thread {
	private int fidoCliente; 		            //il fido massimo che pu� prendere 
	private int prestito=0 ; 	                 //qunti soldi ha gi� preso
	private int richiesta;  		             //quello che richiede
	private int tempo;     						//tempo a caso che ci impiega per fare delle cazzate dio bestia
	private int potenzialeRichiesta;			 //quello che pu� richiedere
	private boolean terminato = false ; 		//quando � messo a false allora il cliente non ha ancora terminato 
	private int recupero;  						//variabile che contiene il recupero dei soldi 
	Random random = new Random();  				//dichiarazione di random 
	private Banchiere B;						//creare l'oggetto banchiere
	
	
	
	
	
	/*
	 * Costuttore 
	 */
	public Cliente (int fidoCliente , Banchiere B , String name) {
		this.fidoCliente = fidoCliente;			 	//il massimo del fido che il cliente pu� ottenere dal banchiere
		this.potenzialeRichiesta=fidoCliente;		//la potenziale richiesta del cliente all'inizio � pari al fido massimo 
		this.B = B; 								//Banchiere in comune tra i thread 
		this.setName(name);							//chiamato metodo per settare il nome del thread
	}
	
	
	
	/* 
	 * 
	 * 
	 * Metodo run del thread praticamente ci sono tutti i commandi che deve esseguire un thread Cliente 
	 * Nella prima parte  fa un ciclo finch� arriva ad avere  un valore pari al fido massimo
	 * Poi proccede con il recupero dei soldi , restituisce i soldi alla banca in modalit� random come li ottiene 
	 * 
	 *
	 */
	public void run () { //metodo della classe cliente
		
		try {
			B.sincronizzatore();                            //Sincronizzatore della partenza 
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (prestito < fidoCliente) {  						//quandoi il prestito � minore del fido allora si ripete la funzione 
			
			richiesta=random.nextInt(potenzialeRichiesta)+1;   //richiesta generata in modalit� random 
			  
			try {
					
					B.richiedoPrestito(this);                   //chiama funzione della banca per chiedere il prestito
			    	tempo= random.nextInt(3000)+100;
					sleep(tempo);								//per farlo sembrare pi� reale allora lo mettiamo un po' in pausa di random milisendondi
									
			} catch (InterruptedException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
			B.stampa.print();									//chiama la funzione della stampa per stampare lo status di dijkastra 		
		}
			
		terminato=true;											//quando terminato � a true allora il cliente � terminato ed inizia a restituire 
		while (prestito > 0 ) {

			recupero = random.nextInt(prestito)+1;				//restituisce in modalit� random 
			B.recuperoPrestito(this);							//funzione che esegue la restituzione dei soldi ottenuti precedemente 

			tempo = random.nextInt(3000)+100;										
			
			try {	
				
				sleep(tempo);									//per farlo sembrare pi� reale allora lo mettiamo un po' in pausa di random milisendondi
			
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			
			
		}
		
	}
	
	/*
	 * Tutte le funzioni per il get e il set di tutti i valori
	 * 
	 * GET=PRENDI
	 * 
	 * SET=IMPOSTA
	 */
	public int getFido () { 						
		return fidoCliente; 						
	}
	
	public int getPotenzialeRichiesta () {
		return potenzialeRichiesta;
	}
	
	public int getPrestito () {
		return prestito;
	}
	
	public int getRichiesta () {
		return richiesta; 
	}
	
	public void setPrestito(int prestito) {
		this.prestito=prestito;
	}
	
	public boolean getTerminato() {
		return terminato;
	}
	
	public void setPotenzialeRichiesta(int potenzialeRichiesta) {
		this.potenzialeRichiesta = potenzialeRichiesta;
	}
	
	public int getRecupero() {
		return recupero; 
	}
	
}
