
public class Student extends Entry {
	
	private String studentTitle;
	private String completionYear;
	
	public Student(String forename, String familyName, String studentTitle,
			String school, String division, String extention,
			String completionYear) {
		super(forename, familyName, school, division, extention);
		this.studentTitle = studentTitle;
		this.completionYear = completionYear;
		
	}
	public String getTitle() {
		return studentTitle;
	}
	
	public String getYear() {
		return completionYear;
	}

}


