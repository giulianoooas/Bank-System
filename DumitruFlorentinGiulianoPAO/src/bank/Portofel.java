package bank;
import bank.Cont;
import bank.Card;
import java.util.ArrayList;

public class Portofel {
	
	private Cont Propietar;
	private  ArrayList<Card> carduri;
	private int nrCarduri;
	
	public Portofel(Cont propietar) {
		Propietar = propietar;
		nrCarduri = 1;
		carduri = new ArrayList<Card>();
		Card card = new Card(Propietar,"1234");
	}
	
	public void addCard(String pin) {
		Card card = new Card(Propietar,pin);
		nrCarduri ++;
		carduri.add(card);
	}
	
	public void removeCard() {
		if (nrCarduri != 1) { /// trebuie neaparat sa existe un card
			nrCarduri --;
			carduri.remove(carduri.get(nrCarduri));
		}
	}
	
	public Card getCard(int id) {
		if (id > nrCarduri - 1)
			id = nrCarduri - 1;
		return carduri.get(id);
	}
	
}
