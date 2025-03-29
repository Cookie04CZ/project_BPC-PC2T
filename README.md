# project_BPC-PC2T
Projekt do BPC-PC2T z JAVY

Zadani projektu:

Předpokládejme databázi studentů technické univerzity, kde každý student má svoje identifikační číslo,
jméno, příjmení a datum narození. Každý student může dostat libovolný počet známek (standardní 
známkování 1 až 5), z nějž je počítán studijní průměr. Existují dvě skupiny studentů lišící se dle 
dovednosti: 

	a) Studenti oboru telekomunikací, kteří dokážou převést svoje jméno a příjmení na Morseovu abecedu
	b) Studenti oboru kyberbezpečnosti, kteří dokážou vyjádřit svoje jméno a příjmení ve formě hashe (použití hashovací funkce) 

Při přijetí na univerzitu, je každý student zařazen do jedné z výše uvedených skupin. V průběhu studia
není možné studenta přesunout do jiné skupiny. 
Vytvořte v programovacím jazyce JAVA ve vývojovém prostředí Eclipse databázový program, který 
umožní uživateli následující:

	a) Přidávat nové studenty - uživatel vždy provede výběr skupiny, do které chce studenta přiřadit, zadá jeho jméno a příjmení a rok narození. Následně je studentovi přiděleno identifikační číslo odvozené dle celkového pořadí přijímaných studentů. 
	b) Zadat studentovi novou známku – uživatel vybere studenta podle jeho ID a zadá požadovanou známku. 
	c) Propuštění studenta z univerzity – uživatel zadá ID studenta, který je odstraněn z databáze. 
	d) Nalezení jednotlivých studentů dle jejich ID a výpis ostatních informací (jméno, příjmení, rok narození, studijní průměr). 
	e) Pro vybraného studenta (dle ID) spustit jeho dovednost (viz rozdělení studentů dle oborů). 
	f) Abecedně řazený výpis všech studentů (dle příjmení) v jednotlivých skupinách (ID, jméno, příjmení, rok narození, studijní průměr). 
	g) Výpis obecného studijního průměru v obou oborech (společný průměr všech studentů v daném oboru). 
	h) Výpis celkového počtu studentů v jednotlivých skupinách. 
	i) Vymazání vybraného studenta ze souboru. 
	j) Načtení vybraného studenta ze souboru. 
	k) Při ukončení programu se uloží veškeré informace do SQL databáze. 
	l) Při spuštění programu se veškeré informace načtou z SQL databáze.
 
Pozn. SQL databáze bude využívána pouze při spuštění a ukončení programu.

Program musí dále obsahovat následující: 
- Efektivní využití základních vlastností OOP. 
- Alespoň jednu abstraktní třídu nebo rozhraní 
- Alespoň jednu dynamickou datovou strukturu
