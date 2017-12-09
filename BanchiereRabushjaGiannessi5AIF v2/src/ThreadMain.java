
/* Classe Main del programma
 * 
 */
import java.util.*; //package che ci permette di utilizzare lo scanner

public class ThreadMain { // inizio della classe Main
	// variabili static = sono globali nella classe.
	private static Scanner input = new Scanner(System.in); // creazione dello scanner che ci permette di prendere in
															// input i dati
	private static int cassa; // variabile intera cassa, rappresenta il valore che tiene il banchiere da dare
								// ai clienti
	private static int nrClienti; // variabile intera nrClienti, rappresenta il numero effettivo dei clienti
									// coinvolti

	private static int[] fidoClienti; // vettore intero fidoClienti, qui dentro ci andranno i fidi dei clienti

	private static Cliente[] C; // creazione di un vettore di Clienti(thread) di nome C della classe Cliente

	public static void main(String[] args) //metodo main della classe
	{
		input(); // richiamo del metodo 'input' della classe
		controlloFidi();
		inizio();
		
	}
	public static void inizio () {
		C = new Cliente[nrClienti];
		
		Banchiere B = new Banchiere(cassa, C); // creazione oggetto B della classe Banchiere
		for (int i = 0; i < nrClienti; i++) {
			C[i] = new Cliente(fidoClienti[i], B , "Cliente "+ (i+1) );
		} 
		for (int i = 0; i < nrClienti; i++)
			C[i].start();
	}

	public static void input() { // Qui vi si sono le richieste del programma
		int fido; // dichiarazione della variabile intera5
		
		System.out.println("Inserisci quanti soldi deve avere la cassa della banca: "); // stampa a video la frase
		cassa = input.nextInt(); // prende il valore inserito dall'utente e lo mette nella variabile
		System.out.println("Inserisci quanti clienti devono essere interpellati: "); // stampa a video la frase
		nrClienti = input.nextInt(); // prende il valore inserito dall'utente e lo mette nella variabile
		fidoClienti = new int[nrClienti]; // creo il vettore fidoclienti e sarà di tipo intero come indice avra' il
											// numero dei clienti coinvolti

		for (int i = 0; i < nrClienti; i++) { // ciclo for: inizialmente i=0. cicla se i è minore del numero dei clienti
			System.out.println("Inserisci il fido del cliente " + (i + 1) + ": "); // stampa a video la frase
			fidoClienti[i] = fido = input.nextInt(); // dichiarazione multipla: metto il valore preso in input sulla
														// variabile fido.Il valore della variabile fido lo metto nella
														// cella i del vettore
			while (fido > cassa) { //Finche' il valore del fido e' maggiore di quello della cassa
				System.out.println("Errore! Inserisci nuovamente il fido del cliente " + (i + 1) + ": "); // stampa a
																											// video la
																											// frase
				fido = fidoClienti[i] = input.nextInt(); // dichiarazione multipla: metto il valore preso in input sul
															// vettore a indice i.Il valore poi lo passo nella variabile
															// fido
			}
			// incrementa i di 1...
		}

	}

	public static void controlloFidi() { // metodo della classe che controlla la somma dei fidi dei clienti coinvolti
		int sommaFidi = 0; // dichiarazione della variabile sommafidi uguale a 0
		String risposta; // dichiarazione della variabile di tipo stringa risposta
		for (int i = 0; i < nrClienti; i++) { // ciclo for: inizialmente i=0. cicla se i è minore del numero dei clienti
			sommaFidi += fidoClienti[i]; // aggiorna la variabile sommafidi, sommando ogni volta il fido del cliente
											// coinvolto
			// incrementa i di 1...
		}
		if (sommaFidi == cassa) { // se la somma totale dei fidi è uguale al valore della cassa, allora fa'...

			System.out.println(
					"In questo caso non c'è bisogno di fare l'algoritmo di Dijkastra, pigia 'y' per continuare..."); // stampa
																													// a
																													// video
																													// la
																													// frase
			risposta = input.next(); // prende l'inserimento dell'utente e lo mette nella variabile risposta
			if (risposta.equals("y")) // se la risposta dichiarata precedentemente dall'utente è 'Y', allora...
			{
				inizio(); // mi ritorna vero il metodo
			}
			else
				  System.exit(0);

		}
		 // sennò rimane falso il metodo
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
