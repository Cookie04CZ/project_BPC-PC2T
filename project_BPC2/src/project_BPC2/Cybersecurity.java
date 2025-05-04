package project_BPC2;

class Cybersecurity extends Student {

	public Cybersecurity(int ID, String name, String surname, int birth) {
		super(ID, name, surname, birth);
	}

	@Override
	public void skill() {
		System.out.println("Hash: " + (getName() + " " + getSurname()).hashCode());
	}

}
