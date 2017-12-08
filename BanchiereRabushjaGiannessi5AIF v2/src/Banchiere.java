
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

}
