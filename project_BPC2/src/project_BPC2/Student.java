package project_BPC2;

public abstract class Student {
	private int ID;
    private String name;
    private String surname;
    private int birth;

    public Student(String name, String surname, int birth) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
    }
    
    public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public void applySkill(){
	}
}