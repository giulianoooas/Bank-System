package bank;

public abstract class Serviciu {
	protected String descriere;
	
	public String Descriere() {
		return descriere;
	}
	
	@Override
	public String toString() {
		return "Serviciul meu face: " + descriere;
	}
	
	public abstract void Aplica(Cont cont, float val);
}
