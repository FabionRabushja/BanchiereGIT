
public class  Stampa {
	
	private Banchiere B;													//dichiarazione del banchiere 
	private Cliente[] C;													//dichiareazione del vettore di clienti 
	
	
	/*
	 * costruttore 
	 * li vengono passati i clienti e il banchiere 
	 */
	
	public Stampa (Cliente C[] ,Banchiere B) {									
			this.C=C;														
			this.B=B;
		
	}
	
	
	
	
	/*
	 * Metodo per stampare in modo sinchronizato tutti gli stati di dijkastra 
	 * 
	 */
	public synchronized void print() {
		
	System.out.println("Nome Cliente \t  P.A \t Fido \t P.R");				//stampo la stringa iniziale 
	
	for (int i = 0 ; i<C.length ; i++) {									//con un ciclo riesco a stampare tutti i clienti 
		System.out.print(C[i].getName()+ "\t   " + C[i].getPrestito() + 						
				
				"  \t" + C[i].getFido() + "\t"+ C[i].getPotenzialeRichiesta()+"   " );
		
		if (C[i].getTerminato()) {											//se il cliente ha il valore del terminato a true allora stampo che il cliente ha terminato 
			
			System.out.println(" TERMINATO ");
		}
		else{																//diversamente non stampo nulla e vado a capo 
			
			System.out.println();
		}
		}
	System.out.println("Cassa : " + B.getCassa());							//stampo la cassa per vedere quanti soldi rimangono
	System.out.println("*****************************************************************************************");
	}
}

