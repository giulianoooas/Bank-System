package bank;
import java.util.ArrayList;
import proiect.DatabaseConection;


public class Extras {
	private Cont Propietar;
	private DatabaseConection db = DatabaseConection.getConection();
	
	public Extras(Cont a) {
		Propietar = a;
	}
	
	public void showExtras() {
		System.out.println("Extrasul pentru contul meu este: ");
		ArrayList<Tranzactie> ex = db.getAllTranzactii(Propietar.getId());
		for (Tranzactie a : ex) {
			System.out.print(a.toString());
		}
	}
	
}
