package proiect;
import java.sql.*;
import java.util.*;
import bank.*;

public class DatabaseConection {
	
	private static DatabaseConection db = null;
	private static Connection conn = null;
	
	private void addConstraints() {
		try {
			
			Statement stm = conn.createStatement();
			String sql = "alter table banca add constraint primary key(idBanca);";
			try {
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			
			sql = "alter table Cont add constraint primary key(idCont);";
			try {
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			sql = "alter table Card add constraint primary key(idCard);";
			try {
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			sql = "alter table Tranzactie add constraint primary key(idTranzactie);";
			try {
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			sql = "alter table Cont add constraint foreign key (idBanca) references Banca(idBanca) on delete cascade;";
			try {
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			sql = "alter table Card add constraint foreign key (idCont) references Cont(idCont) on delete cascade;";
			try {
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			sql = "alter table Tranzactie add constraint foreign key (cont1) references Cont(idCont) on delete cascade;";
			try {
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			sql = "alter table Tranzactie add constraint foreign key (cont2) references Cont(idCont) on delete cascade;";
			try {
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			sql = "alter table Tranzactie add constraint foreign key (banca) references Banca(idBanca) on delete cascade;";
			try {
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			sql = "alter table Cont add constraint unic1 unique (Iban);";
			try {
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			sql = "alter table Banca add constraint unic2 unique (Nume);";
			try {
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			
			System.out.println("Totul a mers bine.");
		} catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	
	private void createTables() {
		/*
		 * Baza mea de date are 4 tabele:
		 * 		-1) Banca(#idBanca, Nume, Suma)
		 * 		-2) Cont(#idCont, Detinator, idBanca, suma, Iban)
		 * 		-3) Card(#idCard, idCont,Pin)
		 * 		-4) Tranzactie (#idTranzactie, Cont1,Cont2,Banca1,Banc2, suma)
		 * Tranzactia este de forma aceasta, deoarece se pot transfera bani de la cont la cont sau de la cont la banca si invers
		 */
		try {
			
    		Statement stm = conn.createStatement();
    		boolean ok = true;
    		try {
    			
    			stm.executeUpdate("create table Banca (idBanca int, Nume Varchar(30), suma float);");
    			
    		} catch(Exception e) {
    			ok = false;
    		}
			
    		try {
    			
    			stm.executeUpdate("create table Cont (idCont int,idBanca int, Detinator Varchar(30), suma float,Iban varchar(30));");
    			
    		} catch(Exception e) {
    			ok = false;
    		}
    		
    		try {
    			
    			stm.executeUpdate("create table Card (idCard int, idCont int, pin varchar(4));");
    			
    		} catch(Exception e) {
    			ok = false;
    		}
    		try {
    			stm.executeUpdate("create table Tranzactie (idTranzactie int, suma float, cont1 int, cont2 int, banca int);");
    			
    		} catch(Exception e) {
    			ok = false;
    		}
    		if (ok) /// adaug constrangeri doar daca nu am tabelele create
    			addConstraints();
		}catch(Exception e) {
			System.out.println("Nu s-a putut generata statmentul!");
		}
	}
	
	private DatabaseConection() {
			String URL = "jdbc:mysql://localhost:3306/Proiect";
		    String USER = "root";
		    String PASS = "giuli";
		    try {
		    	Class.forName("com.mysql.jdbc.Driver");
		    	if (conn == null)
		    		conn = DriverManager.getConnection(URL, USER, PASS);
		    	createTables();
		    	try {
		    		
		    		Statement stm = conn.createStatement();
		    		stm.executeUpdate("create database Proiect;");
		    		
		    	} catch (Exception e) {
		    	}
	    	}
	    	catch(Exception e) {
	    	   System.out.println(e.toString());
	    	}
	}
	
	static public DatabaseConection getConection() {
		if (db == null)
			db = new DatabaseConection();
		return db;
	}
	
	static public void close() {
		try {
			conn.commit();
			conn.close();
		} catch ( Exception e) {
			
		}
	}
	
	/// acum voi face operatiile crud pentru fiecare dintre tabele
	///pentru Banca
	/// Banca(#idBanca, Nume, Suma)
	///read by name
	static private boolean existaBanca(String nume) {
		
		try {
			Statement stm = conn.createStatement();
			ResultSet res = stm.executeQuery("select 1 from Banca where nume = '" + nume + "';");
			res.afterLast();
			return false;
		} catch(Exception e) {
			return true;
		}
	}
	
	/// create
	static public void addBanca(String nume) {
		int id = 0;
		if (!existaBanca(nume))return;
		for (int i = 0; i < 10000; i ++) {
			try {
				id = i;
				String sql = "insert into Banca values(" + i + ",'" + nume + "',0);";
				Statement stm = conn.createStatement();
				stm.executeUpdate(sql);
				break;
			} catch(Exception e){
			}
		}
	}
		/// read
		static public Banca getBanca(int id) {
			try {
				Statement stm = conn.createStatement();
				ResultSet res = stm.executeQuery("select * from Banca where idBanca = " + id + ";");
				res.next();
				Banca r = new Banca(res.getString("Nume"),res.getInt("idBanca"));
				r.addMoney(res.getFloat("suma"));
				return r;
				
			} catch(Exception e) {
				return null;
			}
		}
		
		static public Banca getBanca(String nume) {
			try {
				Statement stm = conn.createStatement();
				ResultSet res = stm.executeQuery("select * from Banca where nume = '" + nume + "';");
				res.next();
				Banca r = new Banca(res.getString("Nume"),res.getInt("idBanca"));
				r.addMoney(res.getFloat("suma"));
				return r;
			} catch(Exception e) {
				return null;
			}
		}
		
		static public boolean setNumeBanca(int id,String Nume) {
			try {
				Statement stm = conn.createStatement();
				stm.executeUpdate("update  banca set nume = '" + Nume + "' where idBanca =" + id +";"  );
				return true;
			} catch(Exception e) {
				return false;
			}
		}
		
		static public void actualizeazaBaniBanca(int id, float money) {
			try {
				Statement stm = conn.createStatement();
				stm.executeUpdate("update  banca set suma = " + money + "where idBanca =" + id +";"  );
				
			} catch(Exception e) {
			}
		}
		
		static public void removeBanca(int idBanca) {
			try {
				Statement stm = conn.createStatement();
				String sql = "delete from banca where idBanca = " + idBanca + ";";
				stm.executeUpdate(sql);
			} catch (Exception e) {
				
			}
		}
		
		static public void removeBanca(String nume) {
			try {
				Statement stm = conn.createStatement();
				String sql = "delete from banca where nume = '" + nume + "';";
				stm.executeUpdate(sql);
			} catch (Exception e) {
				
			}
		}
		
		static public ArrayList<Banca> getAllBanci(){
			ArrayList<Banca> rez =new  ArrayList<Banca>();
			try {
				Statement stm = conn.createStatement();
				String sql = "select * from banca;";
				ResultSet res = stm.executeQuery(sql);
				while(res.next()) {
					Banca r = new Banca(res.getString("Nume"),res.getInt("idBanca"));
					r.addMoney(res.getFloat("suma"));
					rez.add(r);
				}
				
			} catch(Exception e) {
				
			}
			return rez;
		}
		
		/*
		 * Cont(#idCont, Detinator, idBanca, suma, Iban)
		 */
		
		static public Cont getContByIban(String Iban) {
			try {
				
				Statement stm = conn.createStatement();
				ResultSet res = stm.executeQuery("select * from Cont where Iban = '" + Iban + "';");
				res.next();
				Cont r = new Cont(res.getString("Detinator"),getBanca(res.getInt("idBanca")), res.getInt("idCont"),res.getString("Iban"));
				r.addMoneyFromDb(res.getFloat("suma"));
				return r;
			} catch(Exception e) {
				return null;
			}
		}
		
		static public Cont getContById(int id) {
			try {
				
				Statement stm = conn.createStatement();
				ResultSet res = stm.executeQuery("select * from Cont where idCont = " + id + ";");
				res.next();
				Cont r = new Cont(res.getString("Detinator"),getBanca(res.getInt("idBanca")), res.getInt("idCont"),res.getString("Iban"));
				r.addMoneyFromDb(res.getFloat("suma"));
				return r;
			} catch(Exception e) {
				return null;
			}
		}
		
		static public void clearConts() {
			try {
				Statement stm = conn.createStatement();
				String sql = "delete from cont;"; 
				stm.executeUpdate(sql);
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		static public void addCont(String detinator, int idBanca) {
			/// functia cu care adaug un nou cont
			
			for (int i = 1; i <= 10000; i ++)
			try {
				Statement stm = conn.createStatement();
				String sql = "insert into cont(idCont,Detinator,idBanca,suma,Iban) values (" + i + ",'" + detinator + "'," + idBanca + " ," + 0 +",'" +  (detinator + i) +"');"; 
				stm.executeUpdate(sql);
				break;
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		static public void removeCont(int idCont, int idBanca) {
			try {
				Statement stm = conn.createStatement();
				String sql = "delete from cont where idCont = " + idCont + " and idBanca = " +idBanca + ";";
				stm.executeUpdate(sql);
			} catch(Exception e) {
				
			}
		}
		
		static public void removeCont(int idCont) {
			try {
				Statement stm = conn.createStatement();
				String sql = "delete from cont where idCont = " + idCont + ";";
				stm.executeUpdate(sql);
			} catch(Exception e) {
				
			}
		}
		
		static public void removeCont(String Iban, int idBanca) {
			try {
				Statement stm = conn.createStatement();
				String sql = "delete from cont where Iban = '" + Iban + "' and idBanca = " +idBanca + ";";
				stm.executeUpdate(sql);
			} catch(Exception e) {
				
			}
		}
		
		static public void setNumeDetinatorCont(String Nume, int id) {
			try {
				Statement stm = conn.createStatement();
				String sql = "update cont set detinator = '" + Nume+ "' where idCont = " +id + ";";
				stm.executeUpdate(sql);
			} catch(Exception e) {
				
			}
		}
		
		static public void updateMoneyCont(float suma, int id) {
			try {
				Statement stm = conn.createStatement();
				String sql = "update cont set suma = " + suma+ "where idCont = " +id + ";";
				stm.executeUpdate(sql);
			} catch(Exception e) {
				
			}
		}
		
		static public ArrayList<Cont> getAllConts(int id){
			ArrayList<Cont> rez =new  ArrayList<Cont>();
			try {
				Statement stm = conn.createStatement();
				String sql = "select * from cont where idBanca = " + id+";";
				ResultSet res = stm.executeQuery(sql);
				while(res.next()) {
					Cont r = new Cont(res.getString("Detinator"),getBanca(res.getInt("idBanca")), res.getInt("idCont"),res.getString("Iban"));
					r.addMoneyFromDb(res.getFloat("suma"));
					rez.add(r);
				}
				
			} catch(Exception e) {
				
			}
			return rez;
		}
		
		static public ArrayList<Cont> getAllConts(){
			ArrayList<Cont> rez =new  ArrayList<Cont>();
			try {
				Statement stm = conn.createStatement();
				String sql = "select * from cont;";
				ResultSet res = stm.executeQuery(sql);
				while(res.next()) {
					Cont r = new Cont(res.getString("Detinator"),getBanca(res.getInt("idBanca")), res.getInt("idCont"),res.getString("Iban"));
					r.addMoneyFromDb(res.getFloat("suma"));
					rez.add(r);
				}
				
			} catch(Exception e) {
				
			}
			return rez;
		}
		
		/// Card(#idCard,pin,idCont)
		static public Card getCardById(int id) {
			try {
				Statement stm = conn.createStatement();
				String sql = "select * from card where idCard = " + id + ";";
				ResultSet res = stm.executeQuery(sql);
				res.next();
				Card c = new Card(getContById(res.getInt("contId")), res.getString("pin"), id);
				return c;
			} catch(Exception e) {
				return null;
			}
		}
		
		static public Card getCardById(int id,int idCont) {
			try {
				Statement stm = conn.createStatement();
				String sql = "select * from card where idCard = " + id + " and idCont = "+ idCont + ";";
				ResultSet res = stm.executeQuery(sql);
				res.next();
				Card c = new Card(getContById(res.getInt("contId")), res.getString("pin"), id);
				return c;
			} catch(Exception e) {
				return null;
			}
		}
		
		static public boolean changePinCard(int id, String Pin) {
			try {
				
				Statement stm = conn.createStatement();
				stm.executeUpdate("update card set pin = '" + Pin + "' where idCard = " + id + ";");
				
				return true;
			} catch(Exception e) {
				return false;
			}
		}
		
		static public void removeCard(int id) {
			try {
				Statement stm = conn.createStatement();
				stm.executeUpdate("delete from card where idCard = " + id + ";");
			} catch(Exception e) {
			}
		}
		
		static public void clearCards() {
			try {
				Statement stm = conn.createStatement();
				stm.executeUpdate("delete from card;");
			} catch(Exception e) {
			}
		}
		
		static public void removeCard(int id, int idCont) {
			try {
				Statement stm = conn.createStatement();
				stm.executeUpdate("delete from card where idCard = " + id + " and idCont = " + idCont + ";");
			} catch(Exception e) {
			}
		}
		
		static public void addCard(int idCont, String pin) {
			for (int i = 0; i < 10000; i ++)
			try {
				Statement stm = conn.createStatement();
				Card c = new Card(getContById(idCont),pin,i);
				if (c.getCont() == null)
					return ;
				stm.executeUpdate("insert into Card(idCard,idCont,pin) values (" + i + "," + idCont + ",'" + c.getPin() + "');");
				break;
			} catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		
		static public ArrayList<Card> getAllCards(int id){
			ArrayList<Card> rez =new  ArrayList<Card>();
			try {
				Statement stm = conn.createStatement();
				String sql = "select * from Card where idCont = " + id+";";
				ResultSet res = stm.executeQuery(sql);
				while(res.next()) {
					Card c = new Card(getContById(res.getInt("idCont")), res.getString("pin"), res.getInt("idCard"));
					rez.add(c);
				}
				
			} catch(Exception e) {
				
			}
			return rez;
		}
		
		static public ArrayList<Card> getAllCards(){
			ArrayList<Card> rez =new  ArrayList<Card>();
			try {
				Statement stm = conn.createStatement();
				String sql = "select * from Card where pin is not null;";
				ResultSet res = stm.executeQuery(sql);
				while(res.next()) {
					Card c = new Card(getContById(res.getInt("idCont")), res.getString("pin"), res.getInt("idCard"));
					rez.add(c);
				}
				
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			return rez;
		}
		
		/// Tranzactie (#idTranzactie, Cont1,Cont2,Banca, suma)
		/// La tranzactie nu am nevoie de update, deoarece o tranzactie facuta ramane neschimbata
		
		static public ArrayList<Tranzactie> getAllTranzactii(int id){
			ArrayList<Tranzactie> rez =new  ArrayList<Tranzactie>();
			try {
				Statement stm = conn.createStatement();
				String sql = "select * from Tranzactie where Cont1 = " + id+" or Cont2 = "+ id + ";";
				ResultSet res = stm.executeQuery(sql);
				while(res.next()) {
					Integer to = res.getInt("Cont1"), from = res.getInt("Cont2");
					float suma = res.getFloat("suma");
					Cont To = getContById(to);
					Cont From = getContById(from);
					if (To != null && From != null) {
						Tranzactie t = new Tranzactie(To,From,suma,false);
						rez.add(t);
						continue;
					} 
					if (From == null) {
						Tranzactie t = new Tranzactie(To,suma,1,false);
						rez.add(t);
						continue;
					} 
					if (To == null) {
						Tranzactie t = new Tranzactie(From,suma,false);
						rez.add(t);
					}
				}
				
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			return rez;
		}
		/// pentru fiecare constructor de Tranzactie voi face cate o functie separata
		static public void addTranzactie(Cont From, Cont To, float suma) {
			for (int i = 0; i < 100000; i ++) {
				try {
					Statement stm = conn.createStatement();
					stm.executeUpdate("insert into Tranzactie(idTranzactie, suma,Cont1,Cont2) values (" + 
								i + "," + suma + "," + From.getId() + "," + To.getId() + ")");
					break;
				} catch(Exception e) {
				}
			}
		}
		
		static public void addTranzactie(Cont To, float suma) {
			for (int i = 0; i < 100000; i ++) {
				try {
					Statement stm = conn.createStatement();
					stm.executeUpdate("insert into Tranzactie(idTranzactie, suma,Cont1,Banca) values (" + 
								i + "," + suma + "," + To.getId() + "," + To.getBanca().Id() + ")");
					break;
				} catch(Exception e) {
				}
			}
		}
		
		static public void addTranzactie(Cont From, float suma,int a) {
			for (int i = 0; i < 100000; i ++) {
				try {
					Statement stm = conn.createStatement();
					stm.executeUpdate("insert into Tranzactie(idTranzactie, suma,Cont2,Banca) values (" + 
								i + "," + suma + "," + From.getId() + "," + From.getBanca().Id() + ")");
					break;
				} catch(Exception e) {
				}
			}
		}
		
		static public void removeTranzactie(int id) {
			try {
				Statement stm = conn.createStatement();
				stm.executeUpdate("delete from Tranzactie where idTranzactie = " + id + ";");
			} catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		
		public static void clearTranzactii() {
			try {
				Statement stm = conn.createStatement();
				stm.executeUpdate("delete from Tranzactie;");
			} catch(Exception e) {
				System.out.println(e.toString());
			}
		}
}
	

