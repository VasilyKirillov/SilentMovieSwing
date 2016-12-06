package model;

public class Director {

	private static int start = 13;
	private int id;
	private String firstName;
	private String midName;
	private String lastName;
	private String about;
	// constructor 1
	public Director() {
	}
	// constructor 2 run when create new director
	public Director(String firstName, String midName, String lastName, String about) {
		this.id = ++start;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.about = about;
	}
	// constructor 3 run when reading from database
	public Director(int id, String firstName, String midName, String lastName, String about) {
		this.id = id;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.about = about;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getMidName() {
		return midName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getAbout() {
		return about;
	}

	@Override
	public String toString() {
		if (midName==null) return id + " " + firstName + " " + lastName;
		else return id + " " + firstName + " " + midName + " " + lastName;
	}
	public String[] toArray(){
		String[] ar = {firstName,midName,lastName,about};
		return  ar;
	}

}
