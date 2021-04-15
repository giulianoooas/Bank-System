package bank;
import java.util.ArrayList;

public class Portofel {
	
	private Cont Propietar;
	private  Card[] carduri;
	private int nrCarduri;
	
	public Portofel(Cont propietar) {
		Propietar = propietar;
		nrCarduri = 1;
		carduri = new Card[2];
		Card card = new Card(Propietar,"1234");
		carduri[0] = card;
	}
	
	public void addCard(String pin) {
		Card card = new Card(Propietar,pin);
		nrCarduri ++;
		Card[] c = new Card[nrCarduri];
		for (int i = 0; i < nrCarduri - 1; i ++) {
			c[i] = carduri[i];
		}
		c[nrCarduri - 1] = card;
		carduri = new Card[nrCarduri];
		for (int i = 0; i < nrCarduri; i ++)
			carduri[i] = c[i];
		
	}
	
	public void removeCard() {
		/*
		 * Aici voi sterge decat ultimul card
		 */
		if (nrCarduri != 1) { /// trebuie neaparat sa existe un card
			nrCarduri --;
			Card[] c = new Card[nrCarduri];
			for (int i = 0; i < nrCarduri; i ++) 
				c[i] = carduri[i];
			carduri = new Card[nrCarduri];
			for (int i = 0; i < nrCarduri; i ++)
				carduri[i] = c[i];
			
		}
	}
	
	public void removeCard(int index) {
		/*
		 * Aici voi sterge un card anume, dupa index
		 */
		if (index >= nrCarduri || index < 0) 
			return;
		if (nrCarduri != 1) { /// trebuie neaparat sa existe un card
			Card[] c = new Card[nrCarduri-1];
			int poz = 0;
			for (int i = 0; i < nrCarduri; i ++) 
				if (i != index) {
					c[poz++] = carduri[i];
				}
			carduri = new Card[nrCarduri-1];
			for (int i = 0; i < nrCarduri-1; i ++)
				carduri[i] = c[i];
			nrCarduri --;
			
		}
	}
	
	public Card getCard(int id) {
		if (id > nrCarduri - 1)
			id = nrCarduri - 1;
		if  (id < 0)
			id = 0;
		return carduri[id];
	}
	
	public int nrCarduri() {
		return nrCarduri;
	}
	
	public void afiseazaCarduri() {
		for (Card c : carduri) {
			System.out.println(c.toString());
		}
	}
	
}
