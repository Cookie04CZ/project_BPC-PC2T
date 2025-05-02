package project_BPC2;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Databaze {
	private Scanner sc;
	private ArrayList<Student> prvkyDatabaze;
	private int pocetStudentu = 0;
	
	public Databaze()
	{
		prvkyDatabaze = new ArrayList<Student>();
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
				prvkyDatabaze.add(new Telecommunications(pocetStudentu, name, surname, birth));
				pocetStudentu++;
				break;
				
			case "k":
			case "K":
				prvkyDatabaze.add(new Cybersecurity(pocetStudentu, name, surname, birth));
				pocetStudentu++;
				break;
				
			default:
				System.out.println("Vas vyber oboru neprobehl spravne (student nebyl vytvoren), zkuste to znovu");
				break;
		}
	}
	
	
	// VYPIS CELE DATABAZE
	public void vypisDatabazi(int ID)
	{
		if (prvkyDatabaze.size() == 0) {
			System.out.println("Databaze je prazdna, pro pridani studenta stisknete 1");
		} else if (ID > prvkyDatabaze.size()-1 || ID < 0) {
			System.out.println("Student s timto ID neexistuje, rozsah ID je: 0 - " + (prvkyDatabaze.size()-1));
		} else {
			System.out.println(prvkyDatabaze.get(ID).getInfo());
		}
	}
	
	public void addMarks(int ID) {
		System.out.println("Zadejte znamku: ");
		int mark = sc.nextInt();
		if (mark < 1 || mark > 5) {
			System.out.println("Znamky mohou byt pouze 1 - 5");
		} else {
			prvkyDatabaze.get(ID).setMark(mark);
			System.out.println(prvkyDatabaze.get(ID).getMarks().toString());
		}
	}
	
	//TODO osetrit vstupy
	//TODO dodelat
	//TODO musi se posunout vsichni
	public void removeFromDb() {
		for (Student student : prvkyDatabaze) {
			System.out.println(student.getInfo());
		}
		System.out.println("Zadejte ID studenta, ktereho chcete terminovat");
		int termix = sc.nextInt();
		prvkyDatabaze.remove(termix);
		System.out.println("Zadejte ID studenta, ktereho chcete terminovat");
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
		if (conn==null) {
			return false;
		}
	    String studentstable = "CREATE TABLE IF NOT EXISTS students (idstudents integer PRIMARY KEY, name varchar(45) NOT NULL, surname VARCHAR(45) NOT NULL, birth INT NOT NULL, specs TEXT CHECK(specs IN ('t', 'c')) NOT NULL);";
	    String markstable = "CREATE TABLE IF NOT EXISTS marks (idmarks INTEGER PRIMARY KEY AUTOINCREMENT, mark INTEGER CHECK (mark BETWEEN 1 AND 5), students_idstudents INTEGER NOT NULL, FOREIGN KEY (students_idstudents) REFERENCES students(idstudents) ON DELETE CASCADE);";
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
	
	public boolean saveToDB(Databaze db) {
		if (conn==null) {
			return false;
		}
		String sqls = "INSERT INTO students (idstudents, name, surname, birth, specs) VALUES (?, ?, ?, ?, ?)";
		String sqlm = "INSERT INTO marks (mark, students_idstudents) VALUES (?, ?);";
		try {
			for (int i = 0; i < db.prvkyDatabaze.size(); i++) {
				PreparedStatement pstmts = conn.prepareStatement(sqls);
				pstmts.setInt(1, db.prvkyDatabaze.get(i).getID());
				pstmts.setString(2, db.prvkyDatabaze.get(i).getName());
				pstmts.setString(3, db.prvkyDatabaze.get(i).getSurname());
				pstmts.setInt(4, db.prvkyDatabaze.get(i).getBirth());
				pstmts.setString(5, db.prvkyDatabaze.get(i) instanceof Telecommunications ? "t" : "c");
				pstmts.executeUpdate();
				
				System.out.println("Student byl ulozen do databaze.");
				
				PreparedStatement pstmtm = conn.prepareStatement(sqlm);
				for (int mark : db.prvkyDatabaze.get(i).getMarks()) {
					System.out.println(mark);
					System.out.println(i);
					pstmtm.setInt(1, mark);
					pstmtm.setInt(2, i);
					pstmtm.executeUpdate();
					System.out.println("Znamka znamkuje");
				}
			}
			return true;
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean loadFromDB(){
		if (conn == null) {
	        System.out.println("Databaze neni pripojena.");
	        return false;
	    }

	    String sql = "SELECT * FROM students";

	    try (Statement stmt = conn.createStatement();
	    	ResultSet rs = stmt.executeQuery(sql)) {

	        prvkyDatabaze.clear(); // Vyčisti stávající záznamy v paměti

	        while (rs.next()) {
	            int id = rs.getInt("idstudents");
	            String name = rs.getString("name");
	            String surname = rs.getString("surname");
	            int birth = rs.getInt("birth");
	            String specs = rs.getString("specs");

	            Student student;
	            if (specs.equalsIgnoreCase("t")) {
	                student = new Telecommunications(id, name, surname, birth);
	            } else if (specs.equalsIgnoreCase("c") || specs.equalsIgnoreCase("k")) {
	                student = new Cybersecurity(id, name, surname, birth);
	            } else {
	                System.out.println("Neznamy obor: " + specs + " pro studenta s ID: " + id);
	                continue;
	            }

	            prvkyDatabaze.add(student);

	            System.out.println("Nacten student: " + name + " " + surname + ", obor: " + specs);
	            
	            if (!loadMarksFromDB(id, student)) {
	                System.out.println("Nepodarilo se nacist znamky studenta s ID " + id);
	            }
	        }

	        return true;

	    } catch (SQLException e) {
	        System.out.println("Chyba pri nacitani studentu: " + e.getMessage());
	        return false;
	    }
		
	}
	
	public boolean loadMarksFromDB(int studentId, Student student) {
	    String sql = "SELECT mark FROM marks WHERE students_idstudents = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, studentId);
	        ResultSet rs = pstmt.executeQuery();

	        System.out.print("Znamky: ");
	        boolean isMark = false;
	        while (rs.next()) {
	            isMark = true;
	            int mark = rs.getInt("mark");
	            student.setMark(mark);
	            System.out.print(mark + " ");
	        }
	        if (!isMark) {
	            System.out.print("zadne");
	        }
	        System.out.println();
	        return true;

	    } catch (SQLException e) {
	        System.out.println("Chyba pri nacitani znamky studenta: " + e.getMessage());
	        return false;
	    }
	}
	
}
