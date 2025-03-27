package project_BPC2;
import java.util.Scanner;

public class Databaze {
	private Scanner sc;
	private Student [] prvkyDatabaze;
	private int posledniStudent;
	
	public Databaze(int count)
	{
		prvkyDatabaze = new Student[count];
		sc = new Scanner(System.in);
	}
	
	public void setStudent(int tmp2)
	{
		System.out.println("Zadejte jmeno studenta, prijmeni a datum narozeni: ");
		
		String name = sc.next();
		String surname = sc.next();
		int birth = Main.pouzeCelaCisla(sc);
		int branch = tmp2;
		int ID = 0;
		prvkyDatabaze[posledniStudent++] = new Student(ID, name, surname, birth, branch);
	}
	
}
