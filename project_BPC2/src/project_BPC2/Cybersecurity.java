package project_BPC2;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Cybersecurity extends Student{

	public Cybersecurity(int ID, String name, String surname, int birth) {
		super(ID, name, surname, birth);
		// TODO Auto-generated constructor stub
	}

	//TODO napsat kod na hashovani
	public void skill() {
		System.out.println("Hash: " + (getName() + " " + getSurname()).hashCode());
	}

}
