package project_BPC2;
import java.util.ArrayList;

public class Student {
	private int ID;
    private String name;
    private String surname;
    private int birth;
    private ArrayList<Integer> marks;

    public Student(int ID, String name, String surname, int birth) {
    	this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.marks = new ArrayList<Integer>();
    }
    
    public int getID() {
		return ID;
	}
    
    public void setID(int newID) {
    	this.ID = newID;
    }
    
    public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public int getBirth() {
		return birth;
	}
	
	public ArrayList<Integer> getMarks(){
		return marks;
	}
	
	public void setMark(int mark) {
		marks.addLast(mark);
	}
	
	public String getInfo() {
		return ("ID: " + ID + " Name: " + name + " Surname: " + surname + " Birth: " + birth + " Studijni prumer: " + getAvg());
	}
	
	public void skill() {
	}
	
	public float getAvg() {
		float markSum = 0f;
		for (float mark : marks) {
			markSum += mark;
		}
		return markSum/marks.size();
	}
	
}