package project_BPC2;

import java.util.HashMap;

public class Telecommunications extends Student {

	public Telecommunications(int ID, String name, String surname, int birth) {
		super(ID, name, surname, birth);
	}

	public static HashMap<Character, String> getMorseAbeceda() {
		HashMap<Character, String> morseMap = new HashMap<>();

		morseMap.put('A', ".-");
		morseMap.put('B', "-...");
		morseMap.put('C', "-.-.");
		morseMap.put('D', "-..");
		morseMap.put('E', ".");
		morseMap.put('F', "..-.");
		morseMap.put('G', "--.");
		morseMap.put('H', "....");
		morseMap.put('I', "..");
		morseMap.put('J', ".---");
		morseMap.put('K', "-.-");
		morseMap.put('L', ".-..");
		morseMap.put('M', "--");
		morseMap.put('N', "-.");
		morseMap.put('O', "---");
		morseMap.put('P', ".--.");
		morseMap.put('Q', "--.-");
		morseMap.put('R', ".-.");
		morseMap.put('S', "...");
		morseMap.put('T', "-");
		morseMap.put('U', "..-");
		morseMap.put('V', "...-");
		morseMap.put('W', ".--");
		morseMap.put('X', "-..-");
		morseMap.put('Y', "-.--");
		morseMap.put('Z', "--..");
		morseMap.put('/', "/");
		return morseMap;
	}

	@Override
	public void skill() {
		String text = getName().toUpperCase() + "/";
		text += getSurname().toUpperCase();
		HashMap<Character, String> morse = getMorseAbeceda();
		System.out.print("Preklad jmena do Morseovy abecedy: /");
		for (char znak : text.toCharArray()) {
			String morseZnak = morse.get(znak);
			if (morseZnak != null) {
				System.out.print(morseZnak + "/");
			} else {
				System.out.print("###/");
			}
		}
		System.out.print("\n\n");
	}
}
