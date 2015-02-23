
public abstract class Entry {
	
	protected String forename;
	protected String familyName;
	protected String school;
	protected String division;
	protected String extention;
	
	public Entry () {
		
	}
	
	public Entry (String forename, String familyName, String school, String division, String extention) {
		this.forename = forename;
		this.familyName = familyName;
		this.school = school;
		this.division = division;
		this.extention = extention;
	}

	public String getForename() {
		return forename;
	}
	
	public String getFamilyName () {
		return familyName;
	}
	
	public String getSchool() {
		return school;
	}
	
	public String getDivision() {
		return division;
	}
	
	public String getExtention() {
		return extention;
	}
	
	public abstract String getTitle();
	
	public abstract String getYear();
}
