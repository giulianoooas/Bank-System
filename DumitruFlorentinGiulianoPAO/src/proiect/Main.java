package proiect;
import bank.*;
import java.util.*;


/*
 * 
 * Actiunile primei etape:
 * 1) Adaugarea unei banci
 * 2) Schimbarea pinului unui card.
 * 3) Adaugarea unui cont
 * 4) Stergerea unui cont
 * 5) Adaugarea unui card
 * 6) Stergerea unui card
 * 7) Creerea unui tranzactii
 * 8) Imprumutul de bani
 * 9) Depozitarea de bani
 * 10) Sa vezi extrasul.
 * 11) Sa trimitem bani
 * 12) Afisam carduri.
 * 
 */


public class Main {
	
	
	//static AuditWriter audit = AuditWriter.getInstance();
	static DatabaseConection db = DatabaseConection.getConection();
	static Scanner sc =  new Scanner(System.in);
	static Scanner names = new Scanner(System.in); // scanner pentru stringuri
	
	static public void ShowAllInfoCont(int id) {
		ArrayList<Cont> conturi = db.getAllConts(id);
		if (conturi.isEmpty())
			System.out.println("Banca nu are conturi!");
		for (Cont cont : conturi) {
			System.out.println(cont.toString());
			cont.Extras();
			cont.afiseazaCarduri();
			System.out.println();
		}
	}
	
	static public void ShowAllInfoBanci() {
		ArrayList<Banca> banci = db.getAllBanci();
		for (Banca banca : banci) {
			System.out.println(banca.toString() + " iar conturile ei sunt:\n");
			ShowAllInfoCont(banca.Id());
		}
	}
	
	public static void AdaugaOBanca() {
		System.out.print("\nNumele bancii: ");
		String Nume = names.next();
		db.addBanca(Nume);
	}
	
	public static void AdaugaUnCard() {
		/// Vom afisa mai intai banca si id -ul ei si vom lasa user-ul sa aleaga in care banca vrea sa-si faca cont
		ArrayList<Banca> banci = db.getAllBanci();
		System.out.println("Bancile in care iti poti adauga contul:\n");
		for (Banca banca : banci) {
			System.out.println(banca.Name() + " " + banca.Id());
		}
		System.out.print("Banca aleasa: "); 
		int id = sc.nextInt();
		ArrayList<Cont> conturi = db.getAllConts(id);
		if(conturi.isEmpty())return;
		System.out.println("Conturile disponibile:\n");
		for (Cont cont : conturi) {
			System.out.println(cont.IBAN() + " " + cont.getId());
		}
		System.out.print("Contul ales: "); 
		id = sc.nextInt();
		System.out.print("Pinul:");
		String Pin = names.next();
		db.addCard(id, Pin);
	}
	
	public static void AdaugaUnCont() {
		/// Vom afisa mai intai banca si id -ul ei si vom lasa user-ul sa aleaga in care banca vrea sa-si faca cont
		ArrayList<Banca> banci = db.getAllBanci();
		System.out.println("Bancile in care iti poti adauga contul:\n");
		for (Banca banca : banci) {
			System.out.println(banca.Name() + " " + banca.Id());
		}
		System.out.print("Banca aleasa: "); 
		int id = sc.nextInt();
		System.out.print("Detinator: ");
		String nume = names.next();
		db.addCont(nume, id);
	}
	
	public static void StergeOBanca() {
			ArrayList<Banca> banci = db.getAllBanci();
			System.out.println("Bancile pe care le poti sterge:\n");
			for (Banca banca : banci) {
				System.out.println(banca.Name() + " " + banca.Id());
			}
			System.out.print("Idul bancii de sters: ");
			int idBanca = sc.nextInt();
			db.removeBanca(idBanca);
	}
	
	public static void StergeUnCont() {
		ShowAllInfoBanci();
		System.out.print("Contul ales: "); 
		int idCont = sc.nextInt();
		db.removeCont(idCont);
	}
	
	public static void SchimbaNumeleBancii() {
		ArrayList<Banca> banci = db.getAllBanci();
		System.out.println("Bancile pe care le poti modifica:\n");
		for (Banca banca : banci) {
			System.out.println(banca.Name() + " " + banca.Id());
		}
		System.out.print("Idul bancii de modificat: ");
		int idBanca = sc.nextInt();
		System.out.print("\nNumele bancii: ");
		String Nume = names.next();
		db.setNumeBanca(idBanca, Nume);
	}
	
	static public String getPosesorNou() {
		System.out.print("Numele nou: ");
		String Nume = names.nextLine();
		return Nume;
	}
	
	public static void SchimbaNumelePosesorului() {
		ShowAllInfoBanci();
		System.out.print("Contul ales: "); 
		int idCont = sc.nextInt();
		String Nume = getPosesorNou();
		db.setNumeDetinatorCont(Nume, idCont);
	}
	
	
	static public int getInstr() {
		System.out.print("Astepatam instructiunea: ");
		int i = sc.nextInt();
		return i;
	}
	
	static public void AfisatiToateCardurile() {
		System.out.println("Cardurile sunt:");
		ArrayList<Card> carduri = db.getAllCards();
		for (Card card : carduri) {
			System.out.println(card.toString());
		}
	}
	
	static public void StergeCard() {
		AfisatiToateCardurile();
		System.out.println("Cardul de sters:");
		int id = sc.nextInt();
		db.removeCard(id);
	}
	
	static public void SchimbaPinCard() {
		AfisatiToateCardurile();
		System.out.println("Cardul de actualizat:");
		int id = sc.nextInt();
		System.out.println("Pinul nou:");
		String pin = names.next();
		db.changePinCard(id, pin);
	}
	
	static public void AdaugaBaniInBanca() {
		ArrayList<Banca> banci = db.getAllBanci();
		System.out.println("Bancile disponibile sunt:");
		for (Banca banca : banci) {
			System.out.println(banca.Name() + " " + banca.Id());
		}
		System.out.print("Banca in care vrei sa audaugi bani este: ");
		int idBanca = sc.nextInt();
		Banca banca = db.getBanca(idBanca);
		if 
		(banca!=null) {
			System.out.print("Suma de adaugat este: ");
			float suma = sc.nextFloat();
			if (suma <= 0)return;
			banca.addMoney(suma);
		}
	}
	
	static public void TrimiteBaniCont() {
		ArrayList<Banca> banci = db.getAllBanci();
		System.out.println("\"Bancile disponibile sunt:");
		for (Banca banca : banci) {
			System.out.println(banca.Name() + " " + banca.Id());
		}
		System.out.print("Banca din care vrei sa trimiti bani este: ");
		int idBanca = sc.nextInt();
		Banca banca = db.getBanca(idBanca);
		if 
		(banca!=null) {
			System.out.print("Suma posibile de trimis este " + banca.getSuma() + ", iar tu trimiti: \n");
			float suma = sc.nextFloat();
			if (suma <= 0 || suma > banca.getSuma())return;
			ArrayList<Cont> conturi = db.getAllConts(idBanca);
			for (Cont cont : conturi) {
				System.out.println(cont.getId() + " " + cont.IBAN());
			}
			System.out.print("Trimiti in contul: ");
			int id = sc.nextInt();
			Cont cont = db.getContById(id);
			if (cont != null) {
				banca.giveMoney(suma, cont);
			}
		}
	}
	
	static public void TrimiteBaniCatreBanca() {
		ArrayList<Cont> conturi = db.getAllConts();
		System.out.println("Conturile sunt: ");
		for(Cont cont : conturi) {
			System.out.println(cont.toString()+ "\n");
		}
		System.out.print("Alege contul care trimite: ");
		int id = sc.nextInt();
		Cont cont = db.getContById(id);
		if (cont != null) {
			System.out.println("Maximul de bani pe care il poti trimite este "+ cont.getSuma() + " iar tu trimiti: ");
			float suma = sc.nextFloat();
			if (suma <=0 || suma > cont.getSuma())return;
			cont.sentMoney(suma);
		}
	}
	
	static public void TrimiteContLaCont() {
		ArrayList<Cont> conturi = db.getAllConts();
		System.out.println("Conturile sunt: ");
		for(Cont cont : conturi) {
			System.out.println(cont.toString()+ "\n");
		}
		System.out.print("Alege contul care trimite: ");
		int id1 = sc.nextInt();
		System.out.print("Alege contul care primeste: ");
		int id2 = sc.nextInt();
		Cont cont = db.getContById(id1);
		Cont cont1 = db.getContById(id2);
		if (cont != null && cont1 != null) {
			System.out.println("Maximul de bani pe care il poti trimite este "+ cont.getSuma() + " iar tu trimiti: ");
			float suma = sc.nextFloat();
			if (suma <=0 || suma > cont.getSuma())return;
			cont.sentMoney(cont1, suma);
		}
	}
	
	static public void CreazaUnDepozit() {
		ArrayList<Cont> conturi = db.getAllConts();
		System.out.println("Conturile sunt: ");
		for(Cont cont : conturi) {
			System.out.println(cont.toString()+ "\n");
		}
		System.out.print("Alege contul care depoziteaza: ");
		int id = sc.nextInt();
		Cont cont = db.getContById(id);
		if (cont != null) {
			System.out.println("Maximul de bani pe care il poti depozita este "+ cont.getSuma() + " iar tu trimiti: ");
			float suma = sc.nextFloat();
			if (suma <=0 || suma > cont.getSuma())return;
			cont.depoziteaza(suma);
		}
	}
	
	static public void CreazaUnImprumut() {
		ArrayList<Cont> conturi = db.getAllConts();
		System.out.println("Conturile sunt: ");
		for(Cont cont : conturi) {
			System.out.println(cont.toString()+ "\n");
		}
		System.out.print("Alege contul care depoziteaza: ");
		int id = sc.nextInt();
		Cont cont = db.getContById(id);
		if (cont != null) {
			System.out.println("Maximul de bani pe care il poti imprumuta este "+ cont.getBanca().getSuma() + " iar tu ceri: ");
			float suma = sc.nextFloat();
			if (suma <=0 || suma > cont.getBanca().getSuma())return;
			cont.imprumuta(suma);
		}
	}
	
	static public void AfisInitial() {
		String instruciuni = "Instructiunile aplicatiei sunt: \n"+
				"Vei alege unul dintre numerele selectate in functie de comanda dorita si dupa vei respecta pasii de acolo.\n"+
				"1) pentru a afisa toate informatiile\n"+
				"2) pentru a adauga o banca\n" +
				"3) pentru a adauga un cont\n"+
				"4) pentru a adauga un card\n"+
				"5) pentru a sterge o banca\n" +
				"6) pentru a sterge un cont\n" + 
				"7) Schimba numele unei banci\n"+
				"8) Schimba numele unui posesor de cont\n"+
				"9) Sterge un card\n"+
				"10) Schimba pinul unui card\n"+
				"11) Adauga bani intr-o banca\n" +
				"12) Trimite bani intr-un cont\n" + 
				"13) Trimite bani de la cont la banca\n" + 
				"14) Trimite bani de la cont la cont\n" + 
				"15) Creeaza un depozit\n"+ 
				"16) Creaza un imprumut";
		System.out.println(instruciuni);
	}
	
	
	public static boolean isValid(int instr) {
		if (instr == 1) {
			ShowAllInfoBanci();
			return true;
		}
		if (instr == 2) {
			AdaugaOBanca();
			return true;
		} 
		if (instr == 3) {
			AdaugaUnCont();
			return true;
		}
		if (instr == 4) {
			AdaugaUnCard();
			return true;
		}
		if (instr == 5) {
			StergeOBanca();
			return true;
		}
		
		if (instr == 6) {
			StergeUnCont();
			return true;
		}
		
		if (instr == 7) {
			SchimbaNumeleBancii();
			return true;
		}
		
		if (instr == 8) {
			SchimbaNumelePosesorului();
			return true;
		}
		
		if (instr == 9) {
			StergeCard();
			return true;
		}
		
		if (instr == 10) {
			SchimbaPinCard();
			return true;
		}
		
		if (instr == 11) {
			AdaugaBaniInBanca();
			return true;
		}
		
		if (instr == 12) {
			TrimiteBaniCont();
			return true;
		}
		
		if (instr == 13) {
			TrimiteBaniCatreBanca();
			return true;
		}
		
		if (instr == 14) {
			TrimiteContLaCont();
			return true;
		}
		
		if (instr == 15) {
			CreazaUnDepozit();
			return true;
		}
		
		if (instr == 16) {
			CreazaUnImprumut();
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		AfisInitial();
		int instr = getInstr();
		while (isValid(instr))
			instr = getInstr();
		System.out.println("Aplicatia a terminat de rulat!");
		 db.close();
		 sc.close();
		 names.close();
	}

}
