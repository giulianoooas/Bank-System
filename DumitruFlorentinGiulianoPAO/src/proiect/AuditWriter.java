package proiect;
import java.io.*;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

/*
 *
 * O voi face o clasa de tip singleton
 * 
 */
public class AuditWriter {
	
	private boolean merge = false;
	static private AuditWriter instance = null;
	private FileWriter file;
	private String filePath = "C:\\Users\\Florentin-Giuliano D\\Desktop\\DumitruFlorentinGiulianoPAO\\src\\proiect\\CSV_Files\\Audit.csv";
	
	private AuditWriter() {
		try {
			file = new FileWriter(filePath);
			merge = true;
		} catch(Exception e) {
			
		}
	}
	
	static public AuditWriter getInstance() {
		if (instance == null)
			instance = new AuditWriter();
		return instance;
	}
	
	
	public void WriteData(String Action) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		    LocalDateTime now = LocalDateTime.now();  
			file.append(Action);
			file.append(",");
			file.append("" + dtf.format(now));
			file.append("\n");
			
		} catch (Exception e) {
			
		}
	}
	
	public void close() {
		try {
			
			file.close();
			System.out.println("Auditul a fost inchis.");
			
		} catch(Exception e) {
			
		}
	}
	
}
