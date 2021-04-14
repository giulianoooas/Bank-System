package bank;
import java.util.ArrayList;


public class Extras {
	private ArrayList<Tranzactie> ex;
	private Cont Propietar;
	
	public Extras(Cont a) {
		ex = new ArrayList<Tranzactie>();
		Propietar = a;
	}
	
	public void addTranz(Tranzactie a) {
		try {
			ex.add(a);
		} catch(Exception e) {
			return;
		}
	}
	
	public void showExtras() {
		System.out.println("Extrasul pentru contul meu este: ");
		for (Tranzactie a : ex) {
			System.out.print(a.toString());
		}
	}
	
}
