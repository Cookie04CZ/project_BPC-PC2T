package project_BPC2;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Databaze {
	private Scanner sc;
	private Student [] prvkyDatabaze;
	private int pocetStudentu = 0;
	
	public Databaze(int count)
	{
		prvkyDatabaze = new Student[count];
		sc = new Scanner(System.in);
	}
	
	
	// VYTVORENI STUDENTA
	public void newStudent()
	{
		System.out.println("Zadejte jmeno studenta, prijmeni a datum narozeni: ");
		String name = sc.next();
		String surname = sc.next();
		int birth = Main.pouzeCelaCisla(sc);
		System.out.println("Je tento student oboru telekomunikace [t] nebo kyberbezpecnosti? [k]: ");
		String spec = sc.next();
		switch (spec) {
			case "t":
			case "T":
				prvkyDatabaze[pocetStudentu] = new Telecommunications(pocetStudentu, name, surname, birth);
				pocetStudentu++;
				break;
				
			case "k":
			case "K":
				prvkyDatabaze[pocetStudentu] = new Cybersecurity(pocetStudentu, name, surname, birth);
				pocetStudentu++;
				break;
				
			default:
				System.out.println("Vas vyber oboru neprobehl spravne (student nebyl vytvoren), zkuste to znovu");
				break;
		}
	}
	
	
	// VYPIS CELE DATABAZE
	public void vypisDatabazi()
	{
		for (int i=0;i<prvkyDatabaze.length;i++){
			if (prvkyDatabaze[i]!=null)
				System.out.println(i+". "+prvkyDatabaze[i].getName() +" " + prvkyDatabaze[i].getSurname());
			else
				System.out.println(i+". "+"null");
		}
	}
	
	
	// DOVEDNOST STUDENTA PODLE ID
	public void applySkill(int ID) {
	    for (Student student : prvkyDatabaze) {
	        if (student.getID() == ID) {
	            System.out.println("Jmeno: " + student.getName());
	            System.out.println("Prijmeni: " + student.getSurname());
	            //System.out.println("Rok narození: " + student.getBirth());
	            //System.out.printf("Studijní průměr: %.2f\n", student.getStudijniPrumer());
	            
	            if (student instanceof Telecommunications) {
	            	Telecommunications TelecommunicationsStudent = (Telecommunications) student;
	            	TelecommunicationsStudent.skill();
	            }
	            if (student instanceof Cybersecurity) {
	            	Cybersecurity CybersecurityStudent = (Cybersecurity) student;
	            	CybersecurityStudent.skill();
	            }
	            	
	            return;
	        }
	     
	    }
	    System.out.println("Student s ID " + ID + " nebyl nalezen.");
	}
	
	// prace s databazema
	
	private Connection conn;
	public boolean connect() { 
		conn= null; 
	    try {
	    	conn = DriverManager.getConnection("jdbc:sqlite:databazeStudentu.db");                       
	    } 
	    catch (SQLException e) { 
	        System.out.println(e.getMessage());
	        return false;
	    }
	    return true;
	}
	public void disconnect() { 
		if (conn != null) {
			try {
			   conn.close();  
		    } 
	        catch (SQLException ex) {
	        	System.out.println(ex.getMessage());
	        }
		}
	}

	public boolean createTable() {
		if (conn==null)
			return false;
	    String studentstable = "CREATE TABLE IF NOT EXISTS students (" + "idstudents integer PRIMARY KEY," + "name varchar(45) NOT NULL,"+ "surname VARCHAR(45) NOT NULL, " + "birth INT NOT NULL, " + "specs TEXT CHECK(specs IN ('t', 'c')) NOT NULL" + ");";
	    String markstable = "CREATE TABLE IF NOT EXISTS students (" + "idmarks INT PRIMARY KEY, " + "mark INT CHECK (mark BETWEEN 1 AND 5), "+ "students_idstudents INTEGER NOT NULL, " + "FOREIGN KEY (students_idstudents) REFERENCES students(idstudents) ON DELETE CASCADE " + ");";
	    try{
	    	Statement stmt1 = conn.createStatement(); 
	        stmt1.execute(studentstable);
	        Statement stmt2 = conn.createStatement(); 
	        stmt2.execute(markstable);
	        return true;
	    }
	    catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	    return false;
	}
	
}
