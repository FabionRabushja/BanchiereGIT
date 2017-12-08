
public class  Stampa {
	
	private Banchiere B;
	private Cliente[] C;
	public Stampa (Cliente C[] ,Banchiere B) {
		this.C=C;
		this.B=B;
		
	}
	public void print() {
		
	
	System.out.println("Nome Cliente \t  P.A \t Fido \t P.R");
	for (int i = 0 ; i<C.length ; i++) {
		System.out.print(C[i].getName()+ "\t   " + C[i].getPrestito() + "   \t" + C[i].getFido() + "\t"+ C[i].getPotenzialeRichiesta()+"   " );
		if (C[i].getTerminato()) {
			System.out.println(" TERMINATO ");
		}
		else{
			System.out.println();
		}
		}
	System.out.println("Cassa : " + B.getCassa());
	System.out.println("*****************************************************************************************");
	}
}

