package project_BPC2;
import java.util.Scanner;

public class Main {
	
	// Test celych cisel pri vyberu moznosti ukradnuto ze cvik
	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			// System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("Chybne zadane cislo, zadejte prosim cele cislo");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
	

	public static void main(String[] args) {
		
		System.out.println("\nAPLIKACE DATABAZE STUDENTU");
		
		Scanner sc = new Scanner(System.in);
		Databaze newDatabase = new Databaze(1);
		
		boolean run = true;
		while (run) {
			System.out.println("\n====Vyberte moznost====");
			System.out.println("1 - Vytvoreni nove databaze");
			System.out.println("2 - Vlozeni noveho studenta");
			System.out.println("3 - Vypsani studenta\n");
			System.out.println("0 - Ukonceni programu");
			
			int tmp = pouzeCelaCisla(sc);
			switch(tmp) {
				case 1:
					System.out.println("Databaze byla vytvorena!");
					break;
				case 2:
					System.out.println("\n====Vyberte do jakeho oboru student patri====");
					System.out.println("1 - Telekomunikace");
					System.out.println("2 - To druhy");
					
					int tmp2 = sc.nextInt();
					switch(tmp2) {
						case 1:
							System.out.println("VYBRANO: TELEKOMUNIKACE");
							newDatabase.setStudent(tmp2);
							break;
						case 2:
							System.out.println("VYBRANO: KYBERBEZPECNOST");
							newDatabase.setStudent(tmp2);
							break;
					}
					break;
					
				case 3:
					System.out.println("Databaze byla vytvorena!");
					break;
				
				
				
				
				case 0:
					System.out.println("==== APLIKACE UKONCENA ====");
					run = false;
					break;
					
				default:
					System.out.println("Výběr neexistuje, zadej cislo znovu");
			}
		}
		
	}

}
