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
	private int pocetStudentuVDB;
	
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
	
	public void removeFromDb() {
		for (Student student : prvkyDatabaze) {
			System.out.println(student.getInfo());
		}
		System.out.println("Zadejte ID studenta, kterého chcete odstranit z paměti:");
	    int idToRemove = Main.pouzeCelaCisla(sc);

	    Student toRemove = null;
	    for (Student s : prvkyDatabaze) {
	        if (s.getID() == idToRemove) {
	            toRemove = s;
	            break;
	        }
	    }

	    if (toRemove != null) {
	        prvkyDatabaze.remove(toRemove);
	        pocetStudentu--;

	        for (int i = 0; i < prvkyDatabaze.size(); i++) {
	            prvkyDatabaze.get(i).setID(i);
	        }

	        System.out.println("Student s ID " + idToRemove + " byl odstraněn ID byla přečíslována.");
	    } else {
	        System.out.println("Student s tímto ID nebyl nalezen v paměti.");
	    }
	}
	
	// DOVEDNOST STUDENTA PODLE ID
	public void applySkill(int ID) {
	    for (Student student : prvkyDatabaze) {
	        if (student.getID() == ID) {
	            System.out.println("Jmeno: " + student.getName());
	            System.out.println("Prijmeni: " + student.getSurname());
	            
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
		
		if (pocetStudentu == pocetStudentuVDB) {
			for (Student student : db.prvkyDatabaze) {
				updateStudent(student);
			}
			System.out.println("Studenti byli aktualizovani, pocet studentu zustal");
		} else if (pocetStudentu > pocetStudentuVDB) {
			for (int i = 0; i < pocetStudentuVDB; i++) {
				updateStudent(db.prvkyDatabaze.get(i));
		    }
		    for (int i = pocetStudentuVDB; i < pocetStudentu; i++) {
		        insertStudent(db.prvkyDatabaze.get(i));
		    }
		} else {
		    for (int i = 0; i < pocetStudentu; i++) {
		        updateStudent(db.prvkyDatabaze.get(i));
		    }

		    for (int i = pocetStudentu; i < pocetStudentuVDB; i++) {
		        deleteStudent(i);
		    }
		}
		
		try {
			conn.createStatement().executeUpdate("DELETE FROM marks");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String insertMark = "INSERT INTO marks (mark, students_idstudents) VALUES (?, ?);";
		try (PreparedStatement pstmt = conn.prepareStatement(insertMark)){
			for (Student student : db.prvkyDatabaze) {
				for (int mark : student.getMarks()) {
					pstmt.setInt(1, mark);
					pstmt.setInt(2, student.getID());
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean insertStudent(Student student) {
		String insertStudent = "INSERT INTO students (idstudents, name, surname, birth, specs) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement pstInsertStudent = conn.prepareStatement(insertStudent)){
			pstInsertStudent.setInt(1, student.getID());
			pstInsertStudent.setString(2, student.getName());
			pstInsertStudent.setString(3, student.getSurname());
			pstInsertStudent.setInt(4, student.getBirth());
			pstInsertStudent.setString(5, student instanceof Telecommunications ? "t" : "c");
			int rowsAffected =  pstInsertStudent.executeUpdate();
			if (rowsAffected > 0) {
	            System.out.println("Student s ID " + student.getID() + " byl vlozen.");
	            return true;
	        } else {
	            System.out.println("Student s ID " + student.getID() + " nebyl vlozen.");
	            return false;
	        }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	public boolean updateStudent(Student student) {
		String updateStudent = "UPDATE students SET name = ?, surname = ?, birth = ?, specs = ? WHERE idstudents = ?;";
		try (PreparedStatement pstUpdateStudent = conn.prepareStatement(updateStudent)){
			pstUpdateStudent.setString(1, student.getName());
			pstUpdateStudent.setString(2, student.getSurname());
			pstUpdateStudent.setInt(3, student.getBirth());
			pstUpdateStudent.setString(4, student instanceof Telecommunications ? "t" : "c");
			pstUpdateStudent.setInt(5, student.getID());
			int rowsAffected =  pstUpdateStudent.executeUpdate();
			if (rowsAffected > 0) {
	            System.out.println("Student s ID " + student.getID() + " byl aktualizován.");
	            return true;
	        } else {
	            System.out.println("Student s ID " + student.getID() + " nebyl aktualizovan.");
	            return false;
	        }
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean deleteStudent(int id) {
		String deleteStudent = "DELETE FROM students WHERE idstudents = ?";
	    try (PreparedStatement pstDeleteStudent = conn.prepareStatement(deleteStudent)) {
	    	pstDeleteStudent.setInt(1, id);
	    	int rowsAffected =  pstDeleteStudent.executeUpdate();
			if (rowsAffected > 0) {
	            System.out.println("Student s ID " + id + " byl odstranen.");
	            return true;
	        } else {
	            System.out.println("Student s ID " + id + " nebyl ostranen.");
	            return false;
	        }
	    } catch (SQLException e) {
	        System.out.println("Chyba při mazání: " + e.getMessage());
	        return false;
	    }
	}
	
	public boolean loadFromDB(){
		if (conn == null) {
	        System.out.println("Databaze neni pripojena.");
	        return false;
	    }

	    String sql = "SELECT * FROM students";

	    try (Statement stmt = conn.createStatement();
	    	ResultSet rs = stmt.executeQuery(sql)) {

	        prvkyDatabaze.clear();

	        while (rs.next()) {
	        	int ID = rs.getInt("idstudents");
	            String name = rs.getString("name");
	            String surname = rs.getString("surname");
	            int birth = rs.getInt("birth");
	            String specs = rs.getString("specs");

	            Student student;
	            if (specs.equalsIgnoreCase("t")) {
	                student = new Telecommunications(ID, name, surname, birth);
	            } else if (specs.equalsIgnoreCase("c") || specs.equalsIgnoreCase("k")) {
	                student = new Cybersecurity(ID, name, surname, birth);
	            } else {
	                System.out.println("Neznamy obor: " + specs + " pro studenta s ID: " + pocetStudentu++);
	                continue;
	            }

	            prvkyDatabaze.add(student);
	            pocetStudentu++;
	            pocetStudentuVDB++;
	            
	            System.out.println("Nacten student: " + name + " " + surname + ", obor: " + specs);
	            
	            if (!loadMarksFromDB(pocetStudentu, student)) {
	                System.out.println("Nepodarilo se nacist znamky studenta s ID " + pocetStudentu);
	            }
	        }
	        System.out.println(pocetStudentuVDB);
	        
	        return true;

	    } catch (SQLException e) {
	        System.out.println("Chyba pri nacitani studentu: " + e.getMessage());
	        return false;
	    }
		
	}
	
	public boolean loadMarksFromDB(int studentId, Student student) {
	    String sql = "SELECT mark FROM marks WHERE students_idstudents = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, pocetStudentu-1);
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
