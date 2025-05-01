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
		Databaze newDatabase = new Databaze(2);
		
		boolean run = true;
		while (run) {
			System.out.println("\n====Vyberte moznost====");
			System.out.println("1 - Vytvoreni nove databaze");
			System.out.println("2 - Zadani znamky studentovi");
			System.out.println("3 - Terminace studenta");
			System.out.println("4 - Vypis informaci o studentovi podle ID");
			System.out.println("5 - Studentova dovednost");
			System.out.println("6 - Vypis vsech studentu razeny dle prijmeni");
			System.out.println("7 - Vypis obecneho studijniho prumeru");
			System.out.println("8 - Vypis poctu studentu ve skupinach");
			System.out.println("9 - Nacteni studenta ze souboru");
			System.out.println("0 - Odstraneni studenta ze souboru");
			System.out.println("neco jineho - Ukonceni programu\n");
			
			
			int choice = pouzeCelaCisla(sc);
			switch (choice) {
				case 1: //TODO Vytvoreni studenta
					System.out.println("1 - Vytvoreni noveho studenta");
					System.out.println("Je tento student oboru telekomunikace [t] nebo kyberbezpecnosti? [k]: ");
					newDatabase.setStudent();
					break;

				case 2: //TODO Zadani znamky studentovi+
					System.out.println("2 - Zadani znamky studentovi");
					break;
					
				case 3: //TODO Terminace studenta z databaze
					System.out.println("3 - Terminace studenta");
					break;
				
				case 4: //TODO Vypis podle ID
					System.out.println("4 - Vypis informaci o studentovi podle ID");
					newDatabase.vypisDatabazi();
					break;
					
				case 5: //TODO Dovednost studetna podle ID
					System.out.println("5 - Spusteni dovednosti studenta");
					System.out.print("Zadejte ID studenta: ");
					int ID = sc.nextInt();
					newDatabase.applySkill(ID);
					break;
					
				case 6: //TODO Vypis vsech se razenim
					System.out.println("6 - Vypis vsech studentu razeny dle prijmeni");
					break;
					
				case 7: //TODO Vypis studijnich prumeru
					System.out.println("7 - Vypis obecneho studijniho prumeru");
					break;
					
				case 8: //TODO Vypis poctu studentu
					System.out.println("8 - Vypis poctu studentu ve skupinach");
					break;
					
				case 9: //TODO Nacitani ze souboru
					System.out.println("9 - Nacteni studenta ze souboru");
					break;
					
				case 0: //TODO Odstraneni ze souboru
					System.out.println("0 - Odstraneni studenta ze souboru");
					break;
					
				default:
					System.out.println("==== APLIKACE UKONCENA ====");
					run = false;
					break;
			}
		}
		
	}

}
