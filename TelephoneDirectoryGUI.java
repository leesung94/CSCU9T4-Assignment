// Skeleton GUI and main program for the Telephone Directory
// processes files of the form
// 	forename, family name, job title, school, division, extension number, year.

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import java.io.File;
import java.lang.Number;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TelephoneDirectoryGUI extends JFrame implements ActionListener {
	// a small number of text fields and buttons given
	// you will have to add your own, depending on your design

	// Text fields for users to add their own entries to the directory
	private JTextField forename = new JTextField(20);
	private JTextField familyname = new JTextField(20);
	private JTextField title = new JTextField(20);
	private JTextField school = new JTextField(20);
	private JTextField division = new JTextField(20);
	private JTextField extn = new JTextField(4);
	private JTextField year = new JTextField(4);

	// Labels for each text field
	private JLabel labForename = new JLabel(" Forename:");
	private JLabel labFamilyname = new JLabel(" Family Name:");
	private JLabel labTitle = new JLabel(" Title:");
	private JLabel labSchool = new JLabel(" School:");
	private JLabel labDivision = new JLabel(" Divsion:");
	private JLabel labExtn = new JLabel(" Extension Number:");
	private JLabel labYear = new JLabel(" Year:");

	private JButton addP = new JButton("Add Entry");
	private JButton lookupP = new JButton("Find Entry");

	private JTextArea outputArea = new JTextArea(10, 60);
	private TelephoneDirectory td = new TelephoneDirectory();
	private File inputFile;
	private FileWriter outputFile;
	private PrintWriter output;
	private Scanner fileRead;
	private String inFile;

	public static void main(String[] args) {
		TelephoneDirectoryGUI applic = new TelephoneDirectoryGUI();
		applic.inFile = "";
		int files = 0;
		for (int i = 0; i < args.length; i++) {
			files++;
			if (files == 1) {
				applic.inFile = args[i];
			}
		}
		if (files != 1) {
			System.out.println("Incorrect number of files given");
			return;
		}
		// Attempts to parse the CSV file in the command line 
		try {
			applic.inputFile = new File(applic.inFile);
			applic.fileRead = new Scanner(applic.inputFile);
			while (applic.fileRead.hasNextLine()) {
				String line = applic.fileRead.nextLine();
				String csvSplit = ",";
				String[] wholeLine = line.split(csvSplit);
				if (wholeLine[2].equals("Research Student")) {
					applic.td.addStudentEntry(wholeLine[0], wholeLine[1],
							wholeLine[2], wholeLine[3], wholeLine[4],
							wholeLine[5], wholeLine[6]);
				} else {
					applic.td.addStaffEntry(wholeLine[0], wholeLine[1],
							wholeLine[2], wholeLine[3], wholeLine[4],
							wholeLine[5], wholeLine[6]);
				}
			}
			applic.fileRead.close();
		} catch (Exception e) {
			System.out.println("Cannot find the input file please check arguements");
		}
	} // main

	// set up the GUI
	public TelephoneDirectoryGUI() {
		super("CSC9T4 Telephone Directory");
		setLayout(new FlowLayout());
		add(labForename);
		add(forename);
		forename.setEditable(true);
		add(labFamilyname);
		add(familyname);
		familyname.setEditable(true);
		add(labTitle);
		add(title);
		title.setEditable(true);
		add(labSchool);
		add(school);
		school.setEditable(true);
		add(labDivision);
		add(division);
		division.setEditable(true);
		add(labExtn);
		add(extn);
		extn.setEditable(true);
		add(labYear);
		add(year);
		year.setEditable(true);

		add(addP);
		addP.addActionListener(this);
		add(lookupP);
		lookupP.addActionListener(this);
		add(outputArea);
		outputArea.setEditable(false);
		setSize(720, 200);
		setVisible(true);
		blankDisplay();

	} // constructor

	// listen for and respond to GUI events
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == addP) {
			try {
				outputFile = new FileWriter(inFile, true);
				output = new PrintWriter(outputFile, true);
			} catch (Exception e) {
				System.out.println("Cannot find the output file please check arguements");
			}
			String toAddFirst = forename.getText();
			String toAddSecond = familyname.getText();
			String toAddTitle = title.getText();
			String toAddSchool = school.getText();
			String toAddDivision = division.getText();
			String toAddExtn = extn.getText();
			String toAddYear = year.getText();
			boolean isStudent = false;
			if (toAddTitle.toLowerCase().equals("research student")) {
				isStudent = true;
			}
			if (!(isName(toAddFirst))) {
				outputArea.setText("Invalid first name entry, cannot add this entry to record");
				return;
			} else if (!(isName(toAddSecond))) {
				outputArea.setText("Invalid second name entry, cannot add this entry to record");
				return;
			} else if (!(isWordOrWords(toAddTitle))) {
				outputArea.setText("Invalid title entry, cannot add this entry to record");
				return;
			} else if (!(isWordOrWords(toAddSchool))) {
				outputArea.setText("Invalid school entry, cannot add this entry to record");
				return;
			} else if (!(isWordOrWords(toAddDivision))) {
				outputArea.setText("Invalid division entry, cannot add this entry to record");
				return;
			} else if (!(isExtn(toAddExtn))) {
				outputArea.setText("Invalid extension entry, cannot add this entry to record");
				return;
			} else if (!(isYear(toAddYear, isStudent))) {
				outputArea.setText("Invalid year entry, cannot add this entry to record");
				return;
			} else if (td.isDiplicate(toAddFirst, toAddSecond, toAddTitle,
					toAddSchool, toAddDivision, toAddExtn, toAddYear)) {
				outputArea.setText("Duplicate record cannot add");
				return;
			}
			if (isStudent) {
				td.addStudentEntry(toAddFirst, toAddSecond, toAddTitle,
						toAddSchool, toAddDivision, toAddExtn, toAddYear);
			} else {
				td.addStaffEntry(toAddFirst, toAddSecond, toAddTitle,
						toAddSchool, toAddDivision, toAddExtn, toAddYear);
			}
			output.append("\n" + toAddFirst + "," + toAddSecond + ","
					+ toAddTitle + "," + toAddSchool + "," + toAddDivision
					+ "," + toAddExtn + "," + toAddYear);
			output.close();
			outputArea.setText("Record Successfully added");

		}
		if (event.getSource() == lookupP) {
			// Checks if the familyname textfield contains a valid search
			// request
			if ((!(familyname.getText().equals("")))
					|| (familyname.getText().matches("[a-zA-Z]+") 
					|| familyname.getText().contains("-"))) {
				String familyNameSearch = familyname.getText();
				ArrayList<String> result = new ArrayList<String>();
				result = td.lookupAllFamily(familyNameSearch);
				outputArea.setText("");
				for (String record : result) {
					outputArea.append(record + "\n");
				}
				// Else checks if division textfield contains a valid search
				// request
			} else if ((!(division.getText().equals("")))
					|| division.getText().matches("[a-zA-Z]+")) {
				String divisionSearch = division.getText();
				ArrayList<String> result = new ArrayList<String>();
				result = td.lookupAllDivision(divisionSearch);
				outputArea.setText("");
				for (String record : result) {
					outputArea.append(record + "\n");
				}
			} else {
				outputArea.setText("Not a valid search request");
			}
		}
		blankDisplay();
	} // actionPerformed

	public void blankDisplay() {
		forename.setText("");
		familyname.setText("");
		title.setText("");
		school.setText("");
		division.setText("");
		extn.setText("");
		year.setText("");
	}// blankDisplay

	private boolean isName(String toCheck) {
		if (toCheck.matches("[a-zA-Z- ]+"))
			return true;
		else if (toCheck.matches("[a-zA-Z]+"))
			return true;
		return false;
	}

	/*
	 * Checks to see if string passed in contains only letters and spaces or
	 * just letters
	 */
	private boolean isWordOrWords(String toCheck) {
		if (toCheck.matches("[a-zA-Z ]+"))
			return true;
		return false;
	}

	private boolean isExtn(String toCheck) {
		if (toCheck.matches("[0-9]+") && toCheck.length() == 4)
			return true;
		else if (toCheck.length() == 0)
			return true;
		return false;
	}

	private boolean isYear(String toCheck, boolean isStudent) {
		if (toCheck.matches("[0-9]+") && toCheck.length() == 4) {
			int year = Integer.parseInt(toCheck);
			if (isStudent) {
				if (year >= 2014)
					return true;
			} else {
				if (year < 2014 && year >= 1950)
					return true;
			}
		}
		return false;
	}

} // TelephoneDirectoryGUI

