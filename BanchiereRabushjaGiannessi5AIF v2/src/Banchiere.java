
public class Banchiere {
	private int cassa; // il numero massimo delle risorse condivise che abbiamo
	private int[] fidoClienti; // il vettore della massima richiesta di ognuno dei clienti
	private int potenzialeRichiesta;// la potenziale richiesta che può avere un cliente
	private Cliente[] C; // dichiarazione vettore C di clienti
	private Cliente CA; // oggetto CA della classe cliente

	public Banchiere(int cassa, Cliente C[]) { // costruttore della classe Banchiere.
		// come parametri: la cassa e il vettore di clienti
		this.cassa = cassa;
		this.C = C;
	}
	
	public void richiedoPrestito (/* richiesta */) {
		if (controlloRichiesta (/*richiesta */ )) {
			prestitoAccettato(/*richiesta*/);
		}
		
	}

	public boolean  controlloRichiesta () {
		int richiesta = 0; 
		if ( richiesta <= cassa ) {
			if (richiesta == potenzialeRichiesta) {
				return true;
			}
			else 
				if (controlloSimulazionePrestito()) {
					return true ; 
				}
		}
		return false;
		
	}


	private void prestitoAccettato() {
		// TODO Auto-generated method stub
		//cliente.setPrestito(cliente.getPrestito()+richiesta );
		
		
	}
	private void recuperoSoldi () {
		
	}


	private boolean controlloSimulazionePrestito() {
		// TODO Auto-generated method stub
		int richiesta; 
		int nrClienti; 
		int cassaSimulazione = cassa - richiesta ;
		int potenzialeRichiesta = potenzialeRichiesta - richiesta;  
		for (int i=0;i<nrClienti  ;i++)
			if (cassaSimualzione >= potenzialeRichiesta ) {
				return true ; 
				break ; 				
			}
		
		return false;
	}

}
