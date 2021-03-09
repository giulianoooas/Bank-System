package proiect;
import bank.*;

public class Main {

	public static void main(String[] args) {
		Banca BCR = new Banca("BCR");
		Banca BCR1 = new Banca("BCR");
		Cont cont1 = new Cont("Costel Unsoare",BCR);
		Cont cont2 = new Cont("",BCR1);
		cont1.addMoney(12);
		cont1.sentMoney(cont2, 10);
		System.out.println(cont2.getDetinator());
		System.out.print("Mai am " + cont1.getSuma() + " bani.\n");
		cont1.imprumuta(10);
		cont2.depoziteaza(10);
		cont1.Extras();
	}

}
