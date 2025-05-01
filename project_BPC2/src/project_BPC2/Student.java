package project_BPC2;


public class Student {
	private int ID;
    private String name;
    private String surname;
    private int birth;

    public Student(int ID, String name, String surname, int birth) {
    	this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
    }
    
    public int getID() {
		return ID;
	}
    
    public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	
	public void skill() {
	}
	
}