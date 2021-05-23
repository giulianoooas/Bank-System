package bank;
import java.util.ArrayList;
import java.util.Hashtable;
import proiect.DatabaseConection;

/*
 * Am doar doua tipuri de servicii, imprumuturi si depozite, toate cu valori prestabilite
 * Nu pot profita de doua servicii in acelasi timp
 */
public class Banca { /// Am facut o singura banca intre care se pot face toate tranzactiile
	private int id;
	private String nume;
	private float suma;
	private float sumaDepozite;
	private Imprumut imprumuta;
	private Depozit depoziteaza;
	private DatabaseConection db = DatabaseConection.getConection();
	private Hashtable <Cont, Float> ver;
	//private AuditWriter a = AuditWriter.getInstance();
	
	
	public Banca(String nume, int iD) {
		//a.WriteData("Adaugam o banca");
		this.nume = nume;
		this.nume = nume;
		suma = 0;
		imprumuta = Imprumut.getImprumut();
		depoziteaza = Depozit.getDepozit();
		sumaDepozite = 0;
		id = iD;
		ver = new  Hashtable <Cont, Float>();
	}
	
	public int Id() {
		return id;
	}
	
	public float getSuma() {
		return suma;
	}
	
	public void addMoney(float money) {
		if (money < 0)
			money = 0;
		suma += money;
		db.actualizeazaBaniBanca(id, suma);
	}
	
	public float giveMoney(float money, Cont cont) {
		if (money < 0)
			money = 0;
		if (money > suma)
			money = suma;
		if (money == 0)
			return 0;
		suma -= money;
		cont.addMoney(money);
		db.actualizeazaBaniBanca(id, suma);
		return money;
	}
	
	public void setNume(String Nume) {
		if  (db.setNumeBanca(id, Nume))
				nume = Nume;
	}
	
	public String Name() {
		return nume;
	}
	
	public void addCont(String Detinator) {
		db.addCont(Detinator, id);
	}
	
	public void removeCont(int id) {
		db.removeCont(id, this.id);
	}
	
	public void removeCont(String Iban) {
		db.removeCont(Iban, this.id);
	}
	
	public void impumuta(Cont cont,float val) { /// pot imprumuta doar daca este aceasi banca
		if (cont.getBanca().nume.equals(this.nume) && suma != 0) {
			imprumuta.Aplica(cont,val);
		}
	}
	
	public boolean sentToDepozit(Cont cont, float val) {
		if (cont.getSuma() != 0 && nume.equals(cont.getBanca().Name())) {
			if (val > cont.getSuma())
				val = cont.getSuma();
			if(val < 0)
				val = 0;
			ver.put(cont, val);
			sumaDepozite += val;
			return true;
		} 
		return false;
	}
	
	public void sentFromDepozit(Cont cont) {
		if (ver.containsKey(cont)) {
			float val = ver.get(cont);
			cont.addMoney(val);
			sumaDepozite -= val;
			ver.remove(cont);
			this.giveMoney(val/100,cont);
		}
	}
	
	public void makeDepozit(Cont cont,float val) {
		depoziteaza.Aplica(cont,val);
	}
	
	@Override
	public String toString() {
		String res = "Numele bancii este " + nume + " si are " + suma;
		return res;
	}
}
