package bank;

public class Card {
	private String Pin;
	private Cont Propietar;
	private int id;
	
	private boolean isValid(String pin) {
		boolean verificare = true;
		
		if (pin.length() != 4) return false;
		for (int i = 0; i < 4; i ++) {
			if ((pin.charAt(i) == '0') || (pin.charAt(i) == '1') || (pin.charAt(i) == '2')
					|| (pin.charAt(i) == '4') || (pin.charAt(i) == '5') || (pin.charAt(i) == '3')||
					(pin.charAt(i) == '6') || (pin.charAt(i) == '7') || (pin.charAt(i) == '8') ||
					(pin.charAt(i) == '9'))
			{
				continue;
			} else {
				verificare = false;
			}
		}
		
		return verificare;
	}
	
	public String getPin() {
		return Pin;
	}
	
	public Card(Card c, int id) {
		this.id = id;
		this.Pin = c.Pin;
		this.Propietar = c.Propietar;
		
	}
	
	public Card(Cont propietar, String pin,int id) {
		Propietar = propietar;
		if (!this.isValid(pin)) {
			pin = "1234";
		}
		Pin = pin;
		this.id = id;
	}
	
	public Card() {
		Pin = "";
		Propietar = null;
		id = -1;
	}
	
	public int Id() {
		return id;
	}
	
	public Cont getCont() {
		return Propietar;
	}
	
	public float getMoney() {
		return Propietar.getSuma();
	}
	
	public void addMoney(float val) {
		Propietar.addMoney(val);
	}
	
	public void takeMoney(float val) {
		Propietar.takeMoney(val);
	}
	
	public void setPin(String pin) {
		if (this.isValid(pin))
			Pin = pin;
	}
	
	public Card copy(Card c) {
		return new Card(c.Propietar,c.Pin,c.id);
	}
	
	@Override
	public String toString() {
		String s = "Cardul cu pinul " + Pin + " id : " + id;
		return s;
	}
}
