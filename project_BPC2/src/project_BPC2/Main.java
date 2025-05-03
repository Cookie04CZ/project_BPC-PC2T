package project_BPC2;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("\nAPLIKACE DATABAZE STUDENTU");
		
		Scanner sc = new Scanner(System.in);
		Databaze newDatabase = new Databaze();
		
		
		
		// Připojení k databázi
	    if (newDatabase.connect()) {
	    	// Vytvoření tabulek (pokud ještě neexistují)
		    if (newDatabase.createTable()) {
		        System.out.println("Tabulky byly úspěšně vytvořeny/uz existuji");
		    } else {
		        System.out.println("Chyba při vytváření tabulek.");
		    }
	    	
	    	if (newDatabase.loadFromDB()) {
	            System.out.println("Studenti byli nacteni z databaze.");
	        } else {
	            System.out.println("Nepodarilo se nacist studenty z databaze.");
	        }
	    } else {
	        System.out.println("Chyba při připojování k databázi.");
	        return;
	    }

	    
		
	    
		boolean run = true;
		while (run) {
			System.out.println("\n====Vyberte moznost====");
			System.out.println("[1] Vytvoreni noveho studenta");
			System.out.println("[2] Zadani znamky studentovi");
			System.out.println("[3] Terminace studenta");
			System.out.println("[4] Vypis informaci o studentovi podle ID");
			System.out.println("[5] Studentova dovednost");
			System.out.println("[6] Vypis vsech studentu razeny dle prijmeni");
			System.out.println("[7] Vypis obecneho studijniho prumeru");
			System.out.println("[8] Vypis poctu studentu ve skupinach");
			System.out.println("[9] Prace se souborem");
			System.out.println("[0] Ukonceni programu\n");
			
			int choice = pouzeCelaCisla(sc);
			switch (choice) {
				case 1: //TODO Vytvoreni studenta
					System.out.println("1 - Vytvoreni noveho studenta");
					newDatabase.newStudent();
					break;

				case 2: //TODO Zadani znamky studentovi+
					System.out.println("2 - Zadani znamky studentovi");
					newDatabase.addMarks();
					break;
					
				case 3: //TODO Terminace studenta z databaze
					System.out.println("3 - Terminace studenta");
					newDatabase.removeFromDb();
					break;
				
				case 4: //TODO Vypis podle ID
					System.out.println("4 - Vypis informaci o studentovi podle ID");
					newDatabase.vypisDatabazi();
					break;
					
				case 5: //TODO Dovednost studetna podle ID
					System.out.println("5 - Spusteni dovednosti studenta");
					System.out.print("Zadejte ID studenta: ");
					int ID5 = sc.nextInt();
					newDatabase.applySkill(ID5);
					break;
					
				case 6: //TODO Vypis vsech se razenim
					System.out.println("6 - Vypis vsech studentu razeny dle prijmeni");
					newDatabase.studentSorting();
					break;
					
				case 7: //TODO Vypis studijnich prumeru
					System.out.println("7 - Vypis obecneho studijniho prumeru");
					newDatabase.studPrumer();
					break;
					
				case 8: //TODO Vypis poctu studentu
					System.out.println("8 - Vypis poctu studentu ve skupinach");
					newDatabase.numInSpec();
					break;
					
				case 9: //TODO Prace se souborem
					System.out.println("9 - Prace se souborem");
					newDatabase.filet();
					break;
					
				case 0: //TODO Odstraneni ze souboru
					System.out.println("==== APLIKACE UKONCENA ====");
					newDatabase.saveToDB(newDatabase);
					newDatabase.disconnect();
					run = false;
					break;
				default:
					System.out.println("Chybne zadane cislo");
					break;
			}
		}
		
	}
	
	
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
	
}
