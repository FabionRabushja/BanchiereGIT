
public class Banchiere {
	private int cassa ;  //il numero massimo delle risorse condivise che abbiamo
	private int[] fidoClienti ; //il vettore della massima richiesta di ognuno dei clienti
	private int potenzialeRichiesta ;//la potenziale richiesta che può avere un cliente 
	private Cliente[] C;
	private Cliente CA;
	
	public Banchiere (int cassa , Cliente C[]) {
		this.cassa = cassa ; 
		this.C=C;
	}
	
	public synchronized boolean chiedoPrestito (int richiesta , int fidoCliente , int potenzialeRichiesta, Cliente CA) throws InterruptedException {
	this.CA=CA;
		if (statoSicuro(richiesta, fidoCliente)) {
			cassa-=richiesta ; 
			notifyAll();
			for(int i=0  ; i<C.length ; i++) {
				System.out.println("Cliente "+ C[i].getName() + "prestito " + C[i].getPrestito() + " Fido " + C[i].getFido() +" Potenziale Richiesta "+ C[i].getPotenzialeRichiesta());
			}
		}
		else {
			wait();
		}
		return false;
	}
	public void recupero(int recupero) {
		cassa+=recupero;
		notifyAll();
	}
	public boolean statoSicuro (int richiesta , int fidoCliente) {
		if (richiesta == potenzialeRichiesta && cassa >= richiesta && statoSicuro2(fidoCliente,  C) == true ) {
			
			return true ; 
		}
		else if(cassa>= richiesta && statoSicuro3(fidoCliente , CA ,C))
		{
			
		}
		return false;
		
	}
	public boolean statoSicuro2 (int fidoCliente ,Cliente[] C ) {
		for (int i= 0 ; i<C.length ; i++) {
			if ((fidoCliente+cassa) >= C[i].getPotenzialeRichiesta() && C[i].getName() != CA.getName() ) {
				return true ;
				
			}
		}
		
		return false;
		
	}
	public boolean statoSicuro3(int fidoCliente ,Cliente CA, Cliente C[]) {
		for (int i= 0 ; i<C.length ; i++) {
			if ((cassa) >= C[i].getPotenzialeRichiesta()) {
				return true ;
				
			}
		}
		return false;
		
	}
	
}
