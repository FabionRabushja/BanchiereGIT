
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
	
	
}
