
/* Classe Main del programma
 * 
 */
import java.util.*; //package che ci permette di utilizzare lo scanner

public class ThreadMain {
	private static Scanner input = new Scanner(System.in); // creazione dello scanner che ci permette di prendere in
															// input i dati
	private static int cassa; // creazione variabile intera cassa , statica perchè deve essere globale nella
								// classe
	private static int nrClienti; // creazione variabile intera nrClienti , statica perchè deve essere globale
									// nella classe
	private static int[] fidoClienti; // creazione vettore intero fidoClienti , statico perchè deve essere globale
										// nella classe

	private static Cliente[] C; // creazione di un vettore di thread C della classe Cliente

	public static void main(String[] args) // metodo main della classe
	{
		input(); // richiamo del metodo 'input' della classe
		C = new Cliente[nrClienti];
		Banchiere B = new Banchiere(cassa, C); // creazione oggetto B della classe Banchiere
		for (int i = 0; i < nrClienti; i++) {
			C[i] = new Cliente(fidoClienti[i], B);
		}
		for (int i = 0; i < nrClienti; i++)
			C[i].start();

	}

	public static void input() {
		System.out.println("Inserisci quanti soldi deve avere la cassa della banca: ");
		cassa = input.nextInt(); //prende il valore inserito dall'utente e lo mette nella variabile
		System.out.println("Inserisci quanti clienti devono essere interpellati: ");
		nrClienti = input.nextInt(); //prende il valore inserito dall'utente e lo mette nella variabile
		fidoClienti = new int[nrClienti];
		for (int i = 0; i < nrClienti; i++) {
			System.out.println("Inserisci il fido del cliente " + (i + 1) + " : ");
			fidoClienti[i] = input.nextInt(); // metto il valore preso in input e lo metto nella cella i del vettore
		}

	}

}
