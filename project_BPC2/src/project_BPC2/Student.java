package project_BPC2;

public class Student {
	private int ID;
    private String name;
    private String surname;
    private int birth;
    private int branch;

    public Student(int ID, String name, String surname, int birth, int branch) {
    	this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.branch = branch;
    }
    
    public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public int getBranch()
	{
		return branch;
	}
}