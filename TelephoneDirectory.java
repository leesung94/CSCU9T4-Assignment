import java.util.*;

public class TelephoneDirectory {
    
	private List<Entry> telephoneDirectory;
	
	public TelephoneDirectory() {
		telephoneDirectory = new ArrayList<Entry>();
	}
    
	public void addStudentEntry(String forename, String familyName, String studentTitle,
			String school, String division, String extention,
			String completionYear) {
		Entry record = new Student(forename, familyName, studentTitle, school, division, extention, completionYear);
		telephoneDirectory.add(record);
		
    }
	
	public void addStaffEntry(String forename, String familyName, String jobTitle,
			String school, String division, String extention,
			String appointmentYear) {
		Entry record = new StaffMember(forename, familyName, jobTitle, school, division, extention, appointmentYear);
		telephoneDirectory.add(record);		
	}
    
	public ArrayList<String> lookupAllFamily (String familyName) {
		ListIterator<Entry> iter = telephoneDirectory.listIterator();
        ArrayList<String> result = new ArrayList<String>();
        if (iter.hasNext()) {
            while (iter.hasNext()) {
            	Entry current = iter.next();
            	if (current.getFamilyName().toLowerCase().contains(familyName.toLowerCase())) {
            		if (current.getTitle().toLowerCase().equals("research student")) {
            			result.add(current.getForename() + " " + current.getFamilyName() + " : " + current.getTitle() 
            			+ " School: " + current.getSchool() + " Division: " + current.getDivision() + " Extention Number: "
            			+ current.getExtention() + " Expected Completion Year: " + current.getYear());
      			
            		} else {
            			result.add(current.getForename() + " " + current.getFamilyName() + " : " + current.getTitle() 
                    	+ " School: " + current.getSchool() + " Division: " + current.getDivision() + " Extention Number: "
                   		+ current.getExtention() + " Appointment Year: " + current.getYear());
            		}
            	}
            }
        } else {
        	result.add("No entries found");
        }
        return result;		
	}
	
	public ArrayList<String> lookupAllDivision (String division) {
		ListIterator<Entry> iter = telephoneDirectory.listIterator();
        ArrayList<String> result = new ArrayList<String>();
        if (iter.hasNext()) {
            while (iter.hasNext()) {
            	Entry current = iter.next();
            	if (current.getDivision().toLowerCase().contains(division.toLowerCase())) {
            		if (current.getTitle().toLowerCase().equals("research student")) {
            			result.add(current.getForename() + " " + current.getFamilyName() + " : " + current.getTitle() 
            			+ " School: " + current.getSchool() + " Division: " + current.getDivision() + " Extention Number: "
            			+ current.getExtention() + " Expected Completion Year: " + current.getYear());
      			
            		} else {
            			result.add(current.getForename() + " " + current.getFamilyName() + " : " + current.getTitle() 
                    	+ " School: " + current.getSchool() + " Division: " + current.getDivision() + " Extention Number: "
                   		+ current.getExtention() + " Appointment Year: " + current.getYear());
            		}
            	}
            }
        } else {
        	result.add("No entries found");
        }        
        return result;		
	}
	
	public boolean isDiplicate(String forename, String familyName, String title,
			String school, String division, String extention, String year) {
		ListIterator<Entry> iter = telephoneDirectory.listIterator();
        if (iter.hasNext()) {
            while (iter.hasNext()) {
            	Entry current = iter.next();
            		if (current.getForename().toLowerCase().equals(forename.toLowerCase())
            			&& current.getFamilyName().toLowerCase().equals(familyName.toLowerCase())
            			&& current.getTitle().toLowerCase().equals(title.toLowerCase())
            			&& current.getSchool().toLowerCase().equals(school.toLowerCase())
            			&& current.getDivision().toLowerCase().equals(division.toLowerCase())
            			&& current.getExtention().equals(extention) && current.getYear().equals(year))
            			return true;          	
            }
        }		
		return false;
	}
}
 
