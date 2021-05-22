package bank;
import proiect.DatabaseConection;
import java.util.*;

public class Portofel {
	
	private Cont Propietar;
	private DatabaseConection db = DatabaseConection.getConection();
	
	public Portofel(Cont propietar) {
		Propietar = propietar;
	}
	
	public Card addCard(String pin) {
		return db.addCard(Propietar.getId(), pin);
	}
	
	public void removeCard(int id) {
		db.removeCard(id, Propietar.getId());
	}
	
	
	public Card getCard(int id) {
		return db.getCardById(id,Propietar.getId());
	}
	
	public void afiseazaCarduri() {
		ArrayList<Card> carduri = db.getAllCards(Propietar.getId());
		for (Card c : carduri) {
			System.out.println(c.toString());
		}
	}
	
}
