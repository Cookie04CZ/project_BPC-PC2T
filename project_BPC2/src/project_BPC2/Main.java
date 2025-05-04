package project_BPC2;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("\n┳┓┏┓┏┳┓┏┓┳┓┏┓┏┓┏┓  ┏┓┏┳┓┳┳┳┓┏┓┳┓┏┳┓┳┳");
		System.out.println("┃┃┣┫ ┃ ┣┫┣┫┣┫┏┛┣   ┗┓ ┃ ┃┃┃┃┣ ┃┃ ┃ ┃┃");
		System.out.println("┻┛┛┗ ┻ ┛┗┻┛┛┗┗┛┗┛  ┗┛ ┻ ┗┛┻┛┗┛┛┗ ┻ ┗┛");

		System.out.println("\nTesty uspesnosti:");
		Scanner sc = new Scanner(System.in);
		Databaze newDatabase = new Databaze();

		if (newDatabase.connect()) {
			if (newDatabase.createTable()) {
				System.out.println("Tabulky byly uspesne vytvoreny/uz existuji");
			} else {
				System.out.println("Chyba pri vytvareni tabulek.");
			}

			if (newDatabase.loadFromDB()) {
				System.out.println("Cteni z databaze probehlo uspesne.");
			} else {
				System.out.println("Nepodarilo se nacist studenty z databaze.");
			}
		} else {
			System.out.println("Chyba pri pripojeni k databazi.");
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
			case 1:
				System.out.println("1 - Vytvoreni noveho studenta");
				newDatabase.newStudent();
				break;

			case 2:
				System.out.println("2 - Zadani znamky studentovi");
				newDatabase.addMarks();
				break;

			case 3:
				System.out.println("3 - Terminace studenta");
				newDatabase.removeFromDb();
				break;

			case 4:
				System.out.println("4 - Vypis informaci o studentovi podle ID");
				newDatabase.vypisDatabazi();
				break;

			case 5:
				System.out.println("5 - Spusteni dovednosti studenta");
				System.out.print("Zadejte ID studenta: ");
				int ID5 = sc.nextInt();
				newDatabase.applySkill(ID5);
				break;

			case 6:
				System.out.println("6 - Vypis vsech studentu razeny dle prijmeni");
				newDatabase.studentSorting();
				break;

			case 7:
				System.out.println("7 - Vypis obecneho studijniho prumeru");
				newDatabase.studPrumer();
				break;

			case 8:
				System.out.println("8 - Vypis poctu studentu ve skupinach");
				newDatabase.numInSpec();
				break;

			case 9:
				System.out.println("9 - Prace se souborem");
				newDatabase.filet();
				break;

			case 0:
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

	public static int pouzeCelaCisla(Scanner sc) {
		int cislo = 0;
		try {
			cislo = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Chybne zadane cislo, zadejte prosim cele cislo");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}

}
