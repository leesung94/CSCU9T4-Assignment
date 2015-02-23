
public class StaffMember extends Entry {
	
	private String jobTitle;
	private String appointmentYear;
	
	public StaffMember(String forename, String familyName, String jobTitle,
			String school, String division, String extention,
			String appointmentYear) {
		super(forename, familyName, school, division, extention);
		this.jobTitle = jobTitle;
		this.appointmentYear = appointmentYear;
	}

	public String getTitle() {
		return jobTitle;
	}
	
	public String getYear() {
		return appointmentYear;
	}

}
