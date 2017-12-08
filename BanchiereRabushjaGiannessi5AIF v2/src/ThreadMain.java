
/* Classe Main del programma
 * 
 */
import java.util.*; //package che ci permette di utilizzare lo scanner

public class ThreadMain {
	private static Scanner input = new Scanner(System.in); // creazione dello scanner che ci permette di prendere in
															// input i dati
	private static int cassa; // creazione variabile intera cassa , statica perch� deve essere globale nella
								// classe
	private static int nrClienti; // creazione variabile intera nrClienti , statica perch� deve essere globale nella classe
									
	private static int[] fidoClienti; // creazione vettore intero fidoClienti , statico perch� deve essere globale nella classe

	private static Cliente[] C; // creazione di un vettore di thread C della classe Cliente

	public static void main(String[] args) // metodo main della classe
	{
		input(); // richiamo del metodo 'input' della classe
		if (controlloFidi() ) {
		C = new Cliente[nrClienti];
		Banchiere B = new Banchiere(cassa, C); // creazione oggetto B della classe Banchiere
		for (int i = 0; i < nrClienti; i++) {
			C[i] = new Cliente(fidoClienti[i], B);
		} 
		for (int i = 0; i < nrClienti; i++)
			C[i].start();
		}
		
	}

	public static void input() {
		int fido;
		System.out.println("Inserisci quanti soldi deve avere la cassa della banca: ");
		cassa = input.nextInt(); //prende il valore inserito dall'utente e lo mette nella variabile
		System.out.println("Inserisci quanti clienti devono essere interpellati: ");
		nrClienti = input.nextInt(); //prende il valore inserito dall'utente e lo mette nella variabile
		fidoClienti = new int[nrClienti];
		for (int i = 0; i < nrClienti; i++) {
			System.out.println("Inserisci il fido del cliente " + (i + 1) + " : ");
			fidoClienti[i]= fido= input.nextInt(); 
			while (fido > cassa ) {
			System.out.println("Inserisci il fido del cliente un'altra volta  " + (i + 1) + " : ");
			fido=  fidoClienti[i] = input.nextInt(); // metto il valore preso in input e lo metto nella cella i del vettore
			}
			
			}

	}

	public static boolean controlloFidi() { // metodo della classe che controlla la somma dei fidi dei clienti coinvolti
		int sommaFidi = 0; // dichiarazione della variabile sommafidi uguale a 0
		String risposta; // dichiarazione della variabile di tipo stringa risposta
		for (int i = 0; i < nrClienti; i++) { // ciclo for: inizialmente i=0. cicla se i � minore del numero dei clienti
			sommaFidi += fidoClienti[i]; // aggiorna la variabile sommafidi, sommando ogni volta il fido del cliente
											// coinvolto
			// incrementa i di 1...
		}
		if (sommaFidi == cassa) { // se la somma totale dei fidi � uguale al valore della cassa, allora fa'...

			System.out.println(
					"In questo caso non c'� bisogno di fare l'algoritmno di Dijkastra , pigia Y per continuare"); // stampa
																													// a
																													// video
																													// la
																													// frase
			risposta = input.next(); // prende l'inserimento dell'utente e lo mette nella variabile risposta
			if (risposta.equals("Y")) // se la risposta dichiarata precedentemente dall'utente � 'Y', allora...
			{
				return true; // mi ritorna vero il metodo
			}

		}
		return false; // senn� rimane falso il metodo
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
