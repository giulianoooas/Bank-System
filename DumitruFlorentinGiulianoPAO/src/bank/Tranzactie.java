package bank;
import proiect.DatabaseConection;

public class Tranzactie {
	private Cont From; /// cine trimite banii
	private Cont To; /// catre cine se trimit
	private float suma;
	private boolean deLaBanca;
	private boolean catreBanca;
	private DatabaseConection db = DatabaseConection.getConection();
	/// Tranzactie (idTranzactie#, Cont1, Cont2, Banca, suma)
	
	
	public Tranzactie(Cont From, Cont To, float suma) { /// de la cont la cont
		this.From = From;
		this.To = To;
		if (suma < 0) 
			suma = 0;
		this.suma = suma;
		deLaBanca = false;
		catreBanca = false;
		db.addTranzactie(From, To, suma);
	}
	
	public Tranzactie(Cont From,float suma) {
		this.From = From;
		if (suma < 0) 
			suma = 0;
		this.suma = suma;
		deLaBanca = false;
		catreBanca = true;
		db.addTranzactie(From, suma);
	}
	
	public Tranzactie(Cont From,float suma,int a) {
		this.From = From;
		this.suma = suma;
		deLaBanca = true;
		catreBanca = false;
		db.addTranzactie(From, suma,a);
	}
	
	public Cont getTo() {
		return To;
	}
	
	public Cont getFrom() {
		return From;
	}
	
	public float getSuma() {
		return suma;
	}

	
	@Override
	public String toString() {
		String a = "\n";
		if (!catreBanca && !deLaBanca) {
			a =a +  "O Tanzactie este:\nIban From " + this.From.IBAN() +
				" din banca " + this.From.getBanca().Name()+ 
				" \nIban To " + this.To.IBAN() +" din banca " + this.To.getBanca().Name()+ 
				"\nSuma " + this.suma + "\n";
		} 
		if (catreBanca) {
			a = a + "O Tanzactie este:\nIban From " + this.From.IBAN() +
					" catre banca " + this.From.getBanca().Name()+ 
					"\nSuma " + this.suma + "\n";
		} 
		if (deLaBanca){
			a = "O Tanzactie este:\nSpre Iban  " + this.From.IBAN() +
					"\nSuma " + this.suma + "\n";
		}
		a= a + "\n";
		return a;
	}
}
