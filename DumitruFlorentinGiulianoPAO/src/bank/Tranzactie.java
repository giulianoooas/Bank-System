package bank;

public class Tranzactie {
	private Cont From; /// cine trimite banii
	private Cont To; /// catre cine se trimit
	private static int cont = 0;
	private int id;
	private float suma;
	
	public Tranzactie(Cont From, Cont To, float suma) {
		this.From = From;
		this.To = To;
		if (suma < 0) 
			suma = 0;
		this.suma = suma;
		id = ++cont;
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
		String a = "O Tanzactie este:\nIban From " + this.From.IBAN() +
				" din banca " + this.From.getBanca().Name()+ 
				" \nIban To " + this.To.IBAN() +" din banca " + this.To.getBanca().Name()+ 
				"\nSuma " + this.suma + "\n";
		return a;
	}
}
