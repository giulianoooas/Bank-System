package bank;
import bank.Serviciu;
import java.util.concurrent.TimeUnit;

class Actiune1 extends Thread{
	private Cont cont;
	
	public Actiune1(Cont cont) {
		this.cont = cont;
	}
	
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(5);
			cont.getBanca().sentFromDepozit(cont);
			cont.reactiveazaServiciile();
			System.out.println("Returnarea banilor din banca " +cont.getBanca().Name()+ " in contul "+ cont.IBAN()+ " a mers bine.");
		} catch (InterruptedException e) {
			cont.getBanca().sentFromDepozit(cont);
			cont.reactiveazaServiciile();
			System.out.println("Returnarea din banca " +cont.getBanca().Name()+ " in contul "+ cont.IBAN()+ " a mers bine.");
		}
		
	}
}

public class Depozit extends Serviciu { /// Voi face clasa tip singleton
	
	static private Depozit dep = null;
	
	public static Depozit getDepozit() {
		if (dep == null)
			dep = new Depozit();
		return dep;
	}
	
	private Depozit() {
		descriere = "Vom face un depozit de 10% din banii contului in banc si dupa vom trimite banii inapoi plus 1% din bannii din banca.";
	}
	
	@Override
	public void Aplica(Cont cont, float val) { 
		boolean ok = cont.getBanca().sentToDepozit(cont,val);
		if (!ok) {
			System.out.println("Depozitul  a fost refuzat!");
			return;
		}
		System.out.println("Depzitul in banca " +cont.getBanca().Name()+ " catre contul "+ cont.IBAN()+ " a mers bine.");
		/// cu ajutorul unui thread vom lua banii inapoi
		Actiune1 retur = new Actiune1(cont);
		Thread th = new Thread(retur);
		th.start();
	}
	
}
