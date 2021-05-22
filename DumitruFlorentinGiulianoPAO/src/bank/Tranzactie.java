package bank;

public class Tranzactie {
	private Cont From; /// cine trimite banii
	private Cont To; /// catre cine se trimit
	private int id;
	private float suma;
	private boolean deLaBanca;
	private boolean catreBanca;
	
	public Tranzactie(Cont From, Cont To, float suma,int i) {
		this.From = From;
		this.To = To;
		if (suma < 0) 
			suma = 0;
		this.suma = suma;
		id = i;
		deLaBanca = false;
		catreBanca = false;
	}
	
	public Tranzactie(Cont From,float suma,int i) {
		this.From = From;
		if (suma < 0) 
			suma = 0;
		this.suma = suma;
		id = i;
		deLaBanca = false;
		catreBanca = true;
	}
	
	public Tranzactie(Cont From,float suma,int a, int i) {
		this.From = From;
		this.suma = suma;
		id = i;
		deLaBanca = true;
		catreBanca = false;
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
	
	public int ID() {
		return id;
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
