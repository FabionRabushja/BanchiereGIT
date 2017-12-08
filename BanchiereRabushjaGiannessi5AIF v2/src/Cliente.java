import java.util.*;
public class Cliente extends Thread {
	private int fidoCliente; //il fido massimo che può prendere 
	private int prestito ;  //qunti soldi ha già preso
	private int richiesta;  //quello che richiede
	private int tempo;      //tempo a caso che ci impiega per fare delle cazzate dio bestia
	private int potenzialeRichiesta; //quello che può richiedere
	Random random = new Random();
	private Banchiere B;
	public Cliente (int fidoCliente , Banchiere B) {
		this.fidoCliente = fidoCliente; //il massimo del fido che il cliente può ottenere dal banchiere
		this.B = B; //Banchiere in comune tra i thread 
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
	public void run () { //metodo della classe cliente
		while (prestito < fidoCliente)  //quandoi il prestito è minore del fido allora si ripete la funzione 
		{
			potenzialeRichiesta = fidoCliente - prestito;
			richiesta = random.nextInt(potenzialeRichiesta)+1;
			
		
			try {
				if (B.chiedoPrestito(richiesta,fidoCliente,potenzialeRichiesta, this))
				{
					prestito+=richiesta;
					tempo = random.nextInt(1000);
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
			
			
			
		}
		while (prestito > 0 ) {
			
			richiesta = random.nextInt(prestito-fidoCliente)+1;
			
			B.recupero(richiesta);
			prestito-=richiesta;
			tempo = random.nextInt(1000);
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
