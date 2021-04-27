package proiect;
import bank.*;
import java.util.*;

public class Main {

	static List<Banca> Banci = new ArrayList<Banca>();
	static List<Cont> Conturi = new ArrayList<Cont>();
	
	static public void citireBanci() {
		
		System.out.print("Vom citi bancile!\n");
		
		try {
			
			CsvReader c = new CsvReader("C:\\Users\\Florentin-Giuliano D\\Desktop\\DumitruFlorentinGiulianoPAO\\src\\proiect\\CSV_Files\\Banci.csv");
			List<String> numeBanci = c.CitireDate();
			for (String i : numeBanci) {
				System.out.print("Banca " + i + "\n");
				Banci.add(new Banca(i));
			}
			
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void citireConturi() {
		System.out.print("Vom citi conturile!\n");
		try {
			
			CsvReader c = new CsvReader("C:\\Users\\Florentin-Giuliano D\\Desktop\\DumitruFlorentinGiulianoPAO\\src\\proiect\\CSV_Files\\Conturi.csv");
			List<String> conturi = c.CitireDate();
			for (String i : conturi) {
				String[] rezultate = i.split(","); /// imi prelucrez datele
				for (Banca banca : Banci) {
					if (banca.Name().equals(rezultate[1])) {
						Cont cont = new Cont(rezultate[0],banca);
						cont.addMoney(1000); /// ma asigur ca fiecare cont are 1000 de dolari
						Conturi.add(cont);
						System.out.print("Detinator " + rezultate[0] + " banca " + rezultate[1] + "\n");
						break;
					}
				}
			}
			
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void citireCarduri() {
		System.out.print("Vom citi cardurile!\n");
		try {
			
			CsvReader c = new CsvReader("C:\\Users\\Florentin-Giuliano D\\Desktop\\DumitruFlorentinGiulianoPAO\\src\\proiect\\CSV_Files\\Carduri.csv");
			List<String> carduri = c.CitireDate();
			for (String i : carduri) {
				String[] rezultate = i.split(","); /// imi prelucrez datele
				for (Cont cont : Conturi) {
					if (cont.getDetinator().equals(rezultate[0])) {
						cont.addCard(rezultate[1]);
						System.out.print("Detinator " + rezultate[0] + " pin " + rezultate[1] + "\n");
						break;
					}
				}
			}
			
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void citireTranzactii() {
		System.out.print("Vom citi tranzactiile sub forma: From To Money!\n");
		try {
			
			CsvReader c = new CsvReader("C:\\Users\\Florentin-Giuliano D\\Desktop\\DumitruFlorentinGiulianoPAO\\src\\proiect\\CSV_Files\\Tranzactii.csv");
			List<String> tranzactii = c.CitireDate();
			for (String i : tranzactii) {
				String[] rezultate = i.split(","); /// imi prelucrez datele
				for (Cont From : Conturi) {
					if (From.getDetinator().equals(rezultate[0])) {
						for (Cont To : Conturi) {
							if (To.getDetinator().equals(rezultate[1])) {
								
								float suma = Float.parseFloat(rezultate[2]);
								From.sentMoney(To,suma);
								System.out.print("Contul " + From.getDetinator() + " trimite contului " + To.getDetinator() +" " + suma  +" bani.\n");
								break;
							}
						}
						break;
					}
				}
			}
			
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	public static void main(String[] args) {
			
		 citireBanci();
		 citireConturi();
		 citireCarduri();
		 citireTranzactii();
		 System.out.println();
		 for (Cont cont : Conturi) {
			 System.out.print("Contul " + cont.getDetinator() + ":\n");
			 cont.afiseazaCarduri();
		 }
	}

}
