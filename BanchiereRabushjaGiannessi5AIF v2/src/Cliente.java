import java.util.*;
public class Cliente extends Thread {
	private int fidoCliente; //il fido massimo che può prendere 
	private int prestito=0 ;  //qunti soldi ha già preso
	private int richiesta;  //quello che richiede
	private int tempo;      //tempo a caso che ci impiega per fare delle cazzate dio bestia
	private int potenzialeRichiesta; //quello che può richiedere
	private boolean terminato = false ; 
	Random random = new Random();
	private Banchiere B;
	public Cliente (int fidoCliente , Banchiere B , String name) {
		this.fidoCliente = fidoCliente; //il massimo del fido che il cliente può ottenere dal banchiere
		this.B = B; //Banchiere in comune tra i thread 
		this.setName(name);
	}
	public int getFido () { //metodo della classe cliente
		return fidoCliente; //che da come risultato il fido del cliente in numero
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
	public boolean getTerminato() {
		return terminato;
	}
	public void run () { //metodo della classe cliente
		
		try {
			B.sincronizzatore();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (prestito < fidoCliente)  //quandoi il prestito è minore del fido allora si ripete la funzione 
		{
			potenzialeRichiesta = fidoCliente - prestito;
			richiesta = random.nextInt(potenzialeRichiesta)+1;
			
		
			try {
				if (B.richiedoPrestito(this))
				{
					prestito+=richiesta;
					potenzialeRichiesta-=richiesta;		
					tempo = random.nextInt(100)+1000;
					try {
						sleep(tempo);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			B.stampa.print();
		}
		terminato=true;
		while (prestito > 0 ) {
			
		
			int recupero = random.nextInt(prestito)+1;
			
			B.recuperoPrestito(recupero);
			prestito-=recupero;
			tempo = random.nextInt(100)+1000;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
}
