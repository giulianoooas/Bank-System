package bank;
import java.util.LinkedList;
import proiect.DatabaseConection;
/*
 * Daca Tranzactionez intre alte banci, atunci isi va pastra si banca un comision de 2 la suta
 * 
 */
public class Cont {
	private String Detinator;
	private int id;
	private String Iban;
	private Extras extras;
	private float suma;
	private Portofel portofel;
	private Banca banca;
	private boolean areUnServiciuActiv;
	private DatabaseConection db = DatabaseConection.getConection();
//	private AuditWriter a = AuditWriter.getInstance();
	
	/*
	 * Supraincarc hashcode-ul si equals deoarece in Banca am o structura de date ver, ce este un hashmap pentru a verifica daca un cont
	 * are un serviciu activ in momentul respectiv 
	 */
	
	@Override
	public boolean equals(Object o) {
		  Cont c = (Cont)o;
	      return this.id == c.id;
	  }
	
	@Override
	public int hashCode() {
		return id;
	}
	
	private String nameCorector(String nume) {
		if(nume.split(" ").length <= 1){
			return "Dumitru Giuliano";
		}
		return nume;
	}
	
	public Cont(String Detinator,Banca banca,int ID,String Iban) {
		//a.WriteData("Adaugam un cont");
		id = ID;
		this.Detinator = nameCorector(Detinator);
		this.Iban = Iban;
		extras = new Extras(this);
		suma = 0;
		portofel = new Portofel(this);
		this.banca = banca;
		areUnServiciuActiv = false;
	}
	
	public String getDetinator() {
		return Detinator;
	}
	
	public void setDetinator(String Detinator) {
		this.Detinator = nameCorector(Detinator);
		db.setNumeDetinatorCont(this.Detinator, id);
	}
	
	public int getId() {
		return id;
	}
	
	public void Extras() {
		//a.WriteData("Afisam extrasul unui cont");
		extras.showExtras();
	}
	
	public String IBAN() {
		return Iban;
	}
	
	public float getSuma() {
		//a.WriteData("Afisam suma de banu dintr-un cont");
		return suma;
	}
	
	public void addMoney(float val) {
		if (val < 0)
			val = 0;
		if (val == 0)
			return;
		//Tranzactie tr = new Tranzactie(this,val,1);
		//extras.addTranz(tr);
		//a.WriteData("Facem o tranzactie noua");
		suma += val;
		db.updateMoneyCont(suma, id);
	}
	
	public void takeMoney(float val) {
		if (val < 0)
			val = 0;
		if (val > suma)
			val = suma;
		if (val == 0)
			return;
		Tranzactie tr = new Tranzactie(this,-val,1);
		//a.WriteData("Facem o tranzactie noua");
		suma -= val;
		db.updateMoneyCont(suma, id);
	}
	
	public Card card(int id) {
		return portofel.getCard(id);
	}
	
	public void setPin(int index, String Pin) {/// setez un nou pin pentru card
		portofel.getCard(index).setPin(Pin);
		//a.WriteData("Setam un pin nou");
	}
	
	public void addCard(String pin) {
		portofel.addCard(pin);
		//a.WriteData("Adaugam un card nou");
	}
	
	public void removeCard(int index) {
		portofel.removeCard(index);
		//a.WriteData("Scoatem un card");
	}
	
	public void sentMoney(String iban, float value) {
		Cont cont = db.getContByIban(iban);
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
			if (!cont.banca.Name().equals(this.banca.Name())) {
				float comision = value%2;
				if (comision > suma)
					return;
				suma -= comision;
				banca.addMoney(comision);
				db.updateMoneyCont(suma, id);
				db.updateMoneyCont(cont.suma, cont.id);
				db.actualizeazaBaniBanca(banca.Id(), banca.getSuma());
				//a.WriteData("Adaugam o tranzactie noua");
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
		suma -= value;
		banca.addMoney(value);
		db.updateMoneyCont(suma, id);
		db.actualizeazaBaniBanca(banca.Id(), banca.getSuma());
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
			if (!cont.banca.Name().equals(this.banca.Name())) {
				float comision = value%2;
				if (comision > suma)
					return;
				suma -= comision;
				banca.addMoney(comision);
				db.updateMoneyCont(suma, id);
				db.updateMoneyCont(cont.suma, cont.id);
				db.actualizeazaBaniBanca(banca.Id(), banca.getSuma());
				//a.WriteData("Adaugam o tranzactie noua");
			}
			
		}
	}
	
	public Banca getBanca() {
		return banca;
	}
	
	public void reactiveazaServiciile() {
		areUnServiciuActiv = false;
		//a.WriteData("Activam serviciile contului");
	}
	
	public void dezactiveazaServiciile() {
		areUnServiciuActiv = true;
		//a.WriteData("Dezactivam serviciile contului");
	}
	
	public void imprumuta(float suma) {
		if (areUnServiciuActiv == false) {
			this.dezactiveazaServiciile();
			banca.impumuta(this,suma);
		}
	}
	
	public void depoziteaza(float suma) {
		if (areUnServiciuActiv == false && suma != 0) {
			this.dezactiveazaServiciile();
			banca.makeDepozit(this,suma);
		}
	}
	
	public void afiseazaCarduri() {
		System.out.print("Contul meu are cardurile:\n");
		portofel.afiseazaCarduri();
		//a.WriteData("Afisam cardurile contului");
	}
	
	@Override
	public String toString() {
		return "Contul este al propietarului: " + Detinator + " si are Iban: " + Iban + " id: " + id; 
	}
	
}
