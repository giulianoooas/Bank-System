package proiect;
import java.util.*;
import java.io.*;

public final class CsvReader {
	
	private String file;
	private boolean valid;
	private BufferedReader bf;
	private FileReader fl;
	
	
	public CsvReader(String file) {
		
		this.file = file;
		try {
			
			this.valid = true;
			fl = new FileReader(file);
			bf = new BufferedReader(fl);
		} catch (Exception e) {
			System.out.println(e.toString());
			this.bf = null;
			this.valid = false;
		}
	}
	
	public List<String> CitireDate(){
		List<String> res = new ArrayList<String> ();
		try {
			if (this.valid) {
				
				String line = "";
				while ((line = bf.readLine()) != null) {
					res.add(line);
				}
				
			}
		} catch(Exception e) {
			/// nimic
		}finally {
			try {
				bf.close();
			} catch(Exception e) {
				/// nimic
			}
		}
		return res;
	}
	
	
}
