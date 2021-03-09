package bank;
import bank.Cont;
import java.util.ArrayList;
import bank.Imprumut;
import bank.Depozit;
import java.util.Hashtable;

/*
 * Am doar doua tipuri de servicii, imprumuturi si depozite, toate cu valori prestabilite
 * Nu pot profita de doua servicii in acelasi timp
 */
public class Banca { /// Am facut o singura banca intre care se pot face toate tranzactiile
	private String nume;
	private int nrConturi;
	private float suma;
	private float sumaDepozite;
	private ArrayList<Cont> conturi;	
	private static ArrayList <String> numeValide = new ArrayList<String>();
	private Imprumut imprumuta;
	private Depozit depoziteaza;
	private Hashtable <Cont, Float> ver;
	
	private boolean isValid(String nume) {
		for (String n : numeValide) {
			if (nume.equals(n))
				return false;
		}
		return true;
	}
	
	private String GoodName(String nume) {
		while (!this.isValid(nume)) {
			nume += 1;
		}
		return nume;
	}
	
	public Banca(String nume) {
		this.nume = this.GoodName(nume);
		nrConturi = 0;
		conturi =new ArrayList<Cont>();
		numeValide.add(this.nume);
		suma = 0;
		imprumuta = Imprumut.getImprumut();
		depoziteaza = Depozit.getDepozit();
		sumaDepozite = 0;
		ver = new  Hashtable <Cont, Float>();
	}
	
	public float getSuma() {
		return suma;
	}
	
	public void addMoney(float money) {
		if (money < 0)
			money = 0;
		suma += money;
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
		return money;
	}
	
	public void setNume(String Nume) {
		if (!this.isValid(Nume))
			return;
		numeValide.remove(this.nume);
		nume = Nume;
		numeValide.add(this.nume);
	}
	
	public String Name() {
		return nume;
	}
	
	public void addCont(String Detinator) {
		nrConturi++;
		Cont cont =  new Cont(Detinator,this);
		conturi.add(cont);
	}
	
	public void addCont(Cont cont) {
		conturi.add(cont);
	}
	
	public Cont getCont(int id) {
		for (Cont cont : conturi) {
			if (cont.getId() == id)
				return cont;
		}
		return null;
	}
	
	public void removeCont(int id) {
		Cont cont = this.getCont(id);
		if (cont != null) {
			nrConturi --;
			conturi.remove(cont);
		}
	}
	
	public Cont getCont(String iban) {
		for (Cont cont : conturi) {
			if (cont.IBAN() == iban)
				return cont;
		}
		return null;
	}
	
	public void impumuta(Cont cont,float val) { /// pot imprumuta doar daca este aceasi banca
		if (cont.getBanca().nume.equals(this.nume)) {
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
	
}
