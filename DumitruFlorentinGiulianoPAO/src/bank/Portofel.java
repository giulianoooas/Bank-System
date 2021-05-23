package bank;
import proiect.DatabaseConection;
import java.util.*;

public class Portofel {
	
	private Cont Propietar;
	private DatabaseConection db = DatabaseConection.getConection();
	
	public Portofel(Cont propietar) {
		Propietar = propietar;
	}
	
	public void addCard(String pin) {
		db.addCard(Propietar.getId(), pin);
	}
	
	public void removeCard(int id) {
		db.removeCard(id, Propietar.getId());
	}
	
	
	public Card getCard(int id) {
		return db.getCardById(id,Propietar.getId());
	}
	
	public void afiseazaCarduri() {
		ArrayList<Card> carduri = db.getAllCards(Propietar.getId());
		if (carduri.isEmpty()) System.out.println("Contul acesta nu are inca carduri.");
		for (Card c : carduri) {
			System.out.println(c.toString());
		}
	}
	
}
