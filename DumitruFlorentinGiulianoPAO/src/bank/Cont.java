package bank;
import bank.Extras;
import bank.Tranzactie;
import bank.Portofel;
import bank.Card;
import bank.Banca;
import java.util.LinkedList;
/*
 * Daca Tranzactionez intre alte banci, atunci isi va pastra si banca un comision de 2 la suta
 * 
 */
public class Cont {
	private static int cont = 0;
	private String Detinator;
	private int id;
	private String Iban;
	private Extras extras;
	private float suma;
	private Portofel portofel;
	private Banca banca;
	private boolean areUnServiciuActiv;
	private static LinkedList<Cont> conturi = new LinkedList<Cont>();
	
	private Cont getContById(int id) {
		for (Cont a : conturi) {
			if (a.getId() == id)
				return a;
		}
		return null;
	}
	
	private Cont getContByIBan(String Iban) {
		for (Cont a : conturi) {
			if (a.IBAN().equals(Iban))
				return a;
		}
		return null;
	}
	
	private String nameCorector(String nume) {
		if(nume.split(" ").length <= 1){
			return "Dumitru Giuliano";
		}
		return nume;
	}
	
	public Cont(String Detinator,Banca banca) {
		id = ++cont;
		this.Detinator = nameCorector(Detinator);
		Iban = " ";
		String []g = this.Detinator.split(" ");
		for (String a : g) {
			Iban += a;
		}
		Iban += id;
		extras = new Extras(this);
		suma = 0;
		portofel = new Portofel(this);
		this.banca = banca;
		banca.addCont(this);
		conturi.add(this);
		areUnServiciuActiv = false;
	}
	
	public String getDetinator() {
		return Detinator;
	}
	
	public void setDetinator(String Detinator) {
		this.Detinator = nameCorector(Detinator);
	}
	
	public int getId() {
		return id;
	}
	
	public void Extras() {
		extras.showExtras();
	}
	
	public void addTranzactie(Tranzactie a) {
		extras.addTranz(a);
	}
	
	public String IBAN() {
		return Iban;
	}
	
	public float getSuma() {
		return suma;
	}
	
	public void addMoney(float val) {
		if (val < 0)
			val = 0;
		if (val == 0)
			return;
		Tranzactie tr = new Tranzactie(this,val,1);
		extras.addTranz(tr);
		suma += val;
	}
	
	public void takeMoney(float val) {
		if (val < 0)
			val = 0;
		if (val > suma)
			val = suma;
		if (val == 0)
			return;
		Tranzactie tr = new Tranzactie(this,-val,1);
		extras.addTranz(tr);
		val -= suma;
	}
	
	public Card card(int id) {
		return portofel.getCard(id);
	}
	
	public void addCard(String pin) {
		portofel.addCard(pin);
	}
	
	public void removeCard() {
		portofel.removeCard();
	}
	
	public void sentMoney(String iban, float value) {
		Cont cont = this.getContByIBan(iban);
		if (cont == null)
			return;
		if (value > suma)
			value = suma;
		if (value < 0)
			value = 0;
		if (cont != null && suma != 0) {
			cont.suma += value;
			suma -= value;
			Tranzactie tr = new Tranzactie(this,cont, value);
			this.extras.addTranz(tr);
			cont.addTranzactie(tr);
			if (!cont.banca.Name().equals(this.banca.Name())) {
				float comision = value%2;
				if (comision > suma)
					return;
				suma -= comision;
				banca.addMoney(comision);
				
			}
		}
	}
	
	public void sentMoney(float value) {
		if (value > suma)
			value = suma;
		if (value < 0)
			value = 0;
		if (value == 0)
			return;
		Tranzactie tr = new Tranzactie(this,value);
		extras.addTranz(tr);
		suma -= value;
		banca.addMoney(value);
	}
	
	public void sentMoney(Cont cont, float value) {
		if (value > suma)
			value = suma;
		if (value < 0)
			value = 0;
		if (cont != null && suma != 0) {
			cont.suma += value;
			suma -= value;
			Tranzactie tr = new Tranzactie(this,cont, value);
			this.extras.addTranz(tr);
			cont.addTranzactie(tr);
			if (!cont.banca.Name().equals(this.banca.Name())) {
				float comision = value%2;
				if (comision > suma)
					return;
				suma -= comision;
				banca.addMoney(comision);
				
			}
			
		}
	}
	
	public Banca getBanca() {
		return banca;
	}
	
	public void reactiveazaServiciile() {
		areUnServiciuActiv = false;
	}
	
	public void dezactiveazaServiciile() {
		areUnServiciuActiv = true;
	}
	
	public void imprumuta(float suma) {
		if (areUnServiciuActiv == false) {
			this.dezactiveazaServiciile();
			banca.impumuta(this,suma);
		}
	}
	
	public void depoziteaza(float suma) {
		if (areUnServiciuActiv == false) {
			this.dezactiveazaServiciile();
			banca.makeDepozit(this,suma);
		}
	}
}
