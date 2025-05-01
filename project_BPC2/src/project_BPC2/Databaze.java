package project_BPC2;
import java.util.Scanner;

public class Databaze {
	private Scanner sc;
	private Student [] prvkyDatabaze;
	private int posledniStudent = 0;
	
	public Databaze(int count)
	{
		prvkyDatabaze = new Student[count];
		sc = new Scanner(System.in);
	}
	
	
	// VYTVORENI STUDENTA
	public void setStudent()
	{
		System.out.println("Zadejte jmeno studenta, prijmeni a datum narozeni: ");
		
		String name = sc.next();
		String surname = sc.next();
		int birth = Main.pouzeCelaCisla(sc);
		int ID = 0;
		prvkyDatabaze[posledniStudent++] = new Telecommunications(0, "Pepa0Morse", "Zdepa", 5);
		prvkyDatabaze[posledniStudent++] = new Cybersecurity(1, "Kohout", "Umyla", 6);
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
	
}
