package bank;
import java.util.concurrent.TimeUnit;

class Actiune extends Thread{
	private Cont cont;
	private float suma;
	public Actiune(float val, Cont cont) {
		suma = val;
		this.cont = cont;
	}
	
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(5);
			cont.sentMoney(2*suma);
			cont.reactiveazaServiciile();
			System.out.println("Returnarea banilor bancii " +cont.getBanca().Name()+ " de catre contul "+ cont.IBAN()+ " a mers bine.");
		} catch (InterruptedException e) {
			cont.sentMoney(2*suma);
			cont.reactiveazaServiciile();
			System.out.println("Returnarea banilor bancii " +cont.getBanca().Name()+ " de catre contul "+ cont.IBAN()+ " a mers bine.");
		}
		
	}
}

public class Imprumut extends Serviciu { /// Voi face clasa tip singleton
	
	static private Imprumut imp = null;
	
	public static Imprumut getImprumut() {
		if (imp == null)
			imp = new Imprumut();
		return imp;
	}
	
	private Imprumut() {
		descriere = "Vom face un imprumut de 10% din banii bancii si dupa vom trimite banii inapoi dublu.";
	}
	
	@Override
	public void Aplica(Cont cont, float val) { 
		val = cont.getBanca().giveMoney(val, cont);
		System.out.println("Imprumutul din banca " +cont.getBanca().Name()+ " catre contul "+ cont.IBAN()+ " a mers bine.");
		/// cu ajutorul unui thread vom lua banii inapoi
		Actiune retur = new Actiune(2*val, cont);
		Thread th = new Thread(retur);
		th.start();
	}
	
}
