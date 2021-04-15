package proiect;
import bank.*;

public class Main {

	public static void main(String[] args) {
		Banca BCR = new Banca("BCR");
		Banca BCR1 = new Banca("BCR");
		Cont cont1 = new Cont("Costel Unsoare",BCR);
		cont1.addCard("2345");
		cont1.removeCard(0);
		cont1.afiseazaCarduri();
	}

}
