package tuition;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

/**
 * A class containing methods that connect the GUI to the rest of
 * the code. Many of these methods are executed after a certain
 * button is clicked within the GUI.
 * @author Sarah McNey, Cynthia Zhu
 *
 */
public class Controller {
	@FXML
	private RadioButton addResidentRadioButton;
	
	@FXML
	private RadioButton addNonResidentRadioButton;
	
	@FXML
	private RadioButton addTriStateRadioButton;
	
	@FXML
	private RadioButton addInternationalRadioButton;
	
	@FXML
	private RadioButton addYesRadioButton;
	
	@FXML
	private RadioButton addNoRadioButton;
	
	@FXML
	private RadioButton addCTRadioButton;
	
	@FXML
	private RadioButton addNYRadioButton;
	
	@FXML
	private TextField addNameTextField;
	
	@FXML
	private RadioButton addCSRadioButton;
	
	@FXML
	private RadioButton addITRadioButton;
	
	@FXML
	private RadioButton addBARadioButton;
	
	@FXML
	private RadioButton addEERadioButton;
	
	@FXML
	private RadioButton addMERadioButton;
	
	@FXML
	private Spinner addCreditsSpinner;
	
	@FXML
	private TextArea addOutputTextArea;
	
	@FXML
	private VBox addStateVBox;
	
	@FXML
	private VBox addStudyAbroadVBox;
	
	@FXML
	private TextField rmNameTextField;
	
	@FXML
	private RadioButton rmCSRadioButton;
	
	@FXML
	private RadioButton rmITRadioButton;
	
	@FXML
	private RadioButton rmBARadioButton;
	
	@FXML
	private RadioButton rmEERadioButton;
	
	@FXML
	private RadioButton rmMERadioButton;
	
	@FXML
	private TextArea rmOutputTextArea;
	
	@FXML
	private VBox addMajorVBox;
	
	@FXML
	private VBox rmMajorVBox;
	
	@FXML
	private VBox tuitionMajorVBox;
	
	@FXML
	private VBox abroadMajorVBox;
	
	@FXML
	private VBox abroadVBox;
	
	@FXML
	private VBox paymentMajorVBox;
	
	@FXML
	private TextArea tuitionOutputTextArea;
	
	@FXML
	private TextField tuitionNameTextField;
	
	@FXML
	private TextField abroadNameTextField;
	
	@FXML
	private TextArea abroadOutputTextArea;
	
	@FXML
	private TextField paymentNameTextField;
	
	@FXML
	private TextField paymentAmountTextField;
	
	@FXML
	private DatePicker paymentDatePicker;
	
	@FXML
	private TextArea paymentOutputTextArea;
	
	@FXML
	private TextArea printOutputTextArea;
	
	@FXML
	private TextField finAidNameTextField;
	
	@FXML
	private VBox finAidMajorVBox;
	
	@FXML
	private TextField finAidAmountTextField;
	
	@FXML
	private TextArea finAidOutputTextArea;
	
	private Roster roster = new Roster();
	
	private static final int CS_INDEX = 0;
	private static final int IT_INDEX = 1;
	private static final int BA_INDEX = 2;
	private static final int EE_INDEX = 3;
	private static final int ME_INDEX = 4;
	private static final double MAXIMUM_FINANCIAL_AID = 10000.0;
	
	/**
	 * Disables study abroad and state radio buttons for resident and
	 * non-resident students under the Add Student tab. 
	 * @param event: mouse click (clicking on the resident or non-resident 
	 * radio buttons under the Add Student tab will call this method).
	 */
	@FXML
	void toggleVisibilityDefault(ActionEvent event) {
		addYesRadioButton.setDisable(true);
		addNoRadioButton.setDisable(true);
		addCTRadioButton.setDisable(true);
		addNYRadioButton.setDisable(true);
	}
	
	/**
	 * Disables study abroad, enables state radio buttons for tri-state
	 * students under the Add Student tab. 
	 * @param event: mouse click (clicking on the tri-state 
	 * radio button under the Add Student tab will call this method).
	 */
	@FXML
	void toggleVisibilityTriState(ActionEvent event) {
		addYesRadioButton.setDisable(true);
		addNoRadioButton.setDisable(true);
		addCTRadioButton.setDisable(false);
		addNYRadioButton.setDisable(false);
	}
	
	/**
	 * Enables study abroad, disables state radio buttons for international
	 * students under the Add Student tab. 
	 * @param event: mouse click (clicking on the international 
	 * radio button under the Add Student tab will call this method).
	 */
	@FXML
	void toggleVisibilityInternational(ActionEvent event) {
		addYesRadioButton.setDisable(false);
		addNoRadioButton.setDisable(false);
		addCTRadioButton.setDisable(true);
		addNYRadioButton.setDisable(true);
	}
	
	/**
	 * Attempts to add a student to the roster -- if input is valid,
	 * add the student and display the corresponding message. If not,
	 * display the corresponding error message.
	 * @param event: mouse click (clicking on the Add Student button
	 * under the Add Student tab will call this method).
	 */
	@FXML
	void addStudent(ActionEvent event) {
		boolean validInput = checkValidInput(addNameTextField, addMajorVBox, addOutputTextArea);
		if(!validInput) { 
			return;
		}
		
		String name = addNameTextField.getText();
		Major major = getMajorFromRadioButton(addMajorVBox);
		int credits = (Integer) addCreditsSpinner.getValue();
		boolean addSuccess = false;

		if(addResidentRadioButton.isSelected()) {
			Resident resident = new Resident(name, major, credits);
			addSuccess = roster.add(resident);
		} else if(addNonResidentRadioButton.isSelected()) {
			NonResident nonresident = new NonResident(name, major, credits);
			addSuccess = roster.add(nonresident);
		} else if(addTriStateRadioButton.isSelected()) {
			boolean stateSelected = false;
			for(Node child : addStateVBox.getChildren()) {
				RadioButton radioButton = (RadioButton) child;
				if(radioButton.isSelected()) {
					stateSelected = true;
					break;
				}
			}
			
			if(!stateSelected) {
				addOutputTextArea.appendText("Missing state\n");
				return;
			}
			
			String state;
			if(addCTRadioButton.isSelected()) {
				state = TriState.CONNECTICUT;
			} else {
				state = TriState.NEW_YORK;
			}
			
			TriState tristate = new TriState(name, major, credits, state);
			addSuccess = roster.add(tristate);
		} else if(addInternationalRadioButton.isSelected()) {
			if(credits < 12) {
				addOutputTextArea.appendText("International students must enroll at least 12 credits\n");
				return;
			}
			
			boolean abroadSelected = false;
			for(Node child : addStudyAbroadVBox.getChildren()) {
				RadioButton radioButton = (RadioButton) child;
				if(radioButton.isSelected()) {
					abroadSelected = true;
					break;
				}
			}
			
			if(!abroadSelected) {
				addOutputTextArea.appendText("Missing study abroad\n");
				return;
			}
			
			boolean studyAbroad;
			if(addYesRadioButton.isSelected()) {
				studyAbroad = true;
			} else {
				studyAbroad = false;
			}
			
			International international = new International(name, major, credits, studyAbroad);
			addSuccess = roster.add(international);
		} else { //none selected
			addOutputTextArea.appendText("Missing student type\n");
			return;
		}
		
		if(addSuccess) {
			addOutputTextArea.appendText("Student added\n");
		} else {
			addOutputTextArea.appendText("Student already in the roster\n");
		}
	}
	
	/**
	 * Attempts to remove a student from the roster -- if input is valid,
	 * remove the student and display the corresponding message. If not,
	 * display the corresponding error message.
	 * @param event: mouse click (clicking on the Remove Student button
	 * under the Remove Student tab will call this method).
	 */
	@FXML
	void removeStudent(ActionEvent event) {
		boolean validInput = checkValidInput(rmNameTextField, rmMajorVBox, rmOutputTextArea);
		if(!validInput) { 
			return;
		}
				
		String name = rmNameTextField.getText();
		Major major = getMajorFromRadioButton(rmMajorVBox);
		Student student = new Student(name, major);
		
		if(roster.remove(student)) {
			rmOutputTextArea.appendText("Student removed from the roster\n");
		} else {
			rmOutputTextArea.appendText("Student is not in the roster\n");
		}
	}
	
	/**
	 * Calculates balance for all students in the roster.
	 * @param event: mouse click (clicking on the All Students button
	 * under the Calculate Tuition tab will call this method).
	 */
	@FXML
	void calculateBalance_all(ActionEvent event) {
		roster.calculateBalance();
		tuitionOutputTextArea.appendText("Calculation completed\n");
	}
	
	/**
	 * Attempts to calculate balance for a single student. If input is
	 * valid, calculate the balance and display the corresponding 
	 * message, otherwise display the corresponding error message. 
	 * @param event: mouse click (clicking on the Single Student button
	 * under the Calculate Tuition tab will call this method).
	 */
	@FXML
	void calculateBalance_individual(ActionEvent event) {
		boolean validInput = checkValidInput(tuitionNameTextField, tuitionMajorVBox, tuitionOutputTextArea);
		if(!validInput) { 
			return;
		}
		
		String name = tuitionNameTextField.getText();
		
		Major major = getMajorFromRadioButton(tuitionMajorVBox);
		Student student = new Student(name, major);
		int studentIndex = roster.find(student);
		boolean calculated = roster.calculateBalanceIndividual(student);
		
		if(!calculated) {
			tuitionOutputTextArea.appendText("Student not in roster\n");
		} else {
			tuitionOutputTextArea.appendText("Calculation complete\n");
		}
	}
	
	/**
	 * Attempts to update study abroad status for international students:
	 * if input is valid, enroll student in study abroad and display 
	 * the corresponding message. If not, display the corresponding 
	 * error message.
	 * @param event: mouse click (clicking on the Enroll in Study Abroad button
	 * under the Study Abroad tab will call this method).
	 */
	@FXML
	void enrollStudyAbroad(ActionEvent event) {
		boolean validInput = checkValidInput(abroadNameTextField, abroadMajorVBox, abroadOutputTextArea);
		if(!validInput) { 
			return;
		}
		
		String name = abroadNameTextField.getText();
		Major major = getMajorFromRadioButton(abroadMajorVBox);
		Student student = new Student(name, major);
		int studentIndex = roster.find(student);
		
		if(studentIndex == Roster.NOT_FOUND) {
			abroadOutputTextArea.appendText("Student not in the roster\n");
			return;
		}
		
		student = roster.getStudentFromIndex(studentIndex);
		
		boolean statusSelected = false;
		if(!roster.isInternational(student)) {
			abroadOutputTextArea.appendText("Student is not international\n");
			return;
		} else {
			boolean success = roster.enrollInStudyAbroad(student);
			
			if(success) {
				abroadOutputTextArea.appendText("Study abroad status updated\n");
			} else {
				abroadOutputTextArea.appendText("Student not found in roster\n");
			}
		}
		
	}
	
	/**
	 * Attempts to process tuition payment amounts and dates for a single
	 * student. If input is valid, update the corresponding student's tuition due,
	 * payments made, and payment date, then display the appropriate message.
	 * If not, display the corresponding error message.
	 * @param event: mouse click (clicking the Make Payment button under
	 * the Make Payment tab will call this method).
	 */
	@FXML
	void makePayment(ActionEvent event) {
		boolean validInput = checkValidInput(paymentNameTextField, paymentMajorVBox, paymentOutputTextArea);
		if(!validInput) { 
			return;
		}
		
		String name = paymentNameTextField.getText();
		Major major = getMajorFromRadioButton(paymentMajorVBox);
		Student student = new Student(name, major);
		int studentIndex = roster.find(student);
		
		if(studentIndex == Roster.NOT_FOUND) {
			paymentOutputTextArea.appendText("Student not in the roster\n");
			return;
		}
		
		student = roster.getStudentFromIndex(studentIndex);
		
		String amountString = paymentAmountTextField.getText();
		if(amountString == null || amountString.isEmpty()) {
			paymentOutputTextArea.appendText("Missing amount\n");
			return;
		}
		
		double amount;
		try {
			amount = Double.parseDouble(amountString);
		} catch(NumberFormatException e) {
			paymentOutputTextArea.appendText("Invalid amount\n");
			return;
		}
		
		if (!isValidPaymentAmount(student, amount)) {
			paymentOutputTextArea.appendText("Invalid amount\n");
            return;
        }
		
		String date;
		try {
			date = paymentDatePicker.getValue().toString();
		} catch(NullPointerException e) {
			paymentOutputTextArea.appendText("Missing date\n");
			return;
		}
		
		String[] dateArray = date.split("-");
		int year;
		int month;
		int day;
		try {
			year = Integer.parseInt(dateArray[0]);
			month = Integer.parseInt(dateArray[1]);
			day = Integer.parseInt(dateArray[2]);
		} catch(NumberFormatException e) {
			paymentOutputTextArea.appendText("Invalid date\n");
			return;
		}
		
		Date paymentDate = new Date(month, day, year);
		if (!paymentDate.isValid()) {
            paymentOutputTextArea.appendText("Payment date invalid\n");
            return;
        }
		
		Student s = new Student(name, major, amount, paymentDate);
        if (roster.makePayment(s)) {
            paymentOutputTextArea.appendText("Payment applied.\n");
        }
	}
	
	@FXML
	void printRoster(ActionEvent event) {
		printOutputTextArea.appendText(roster.print() + "\n");
	}
	
	@FXML
	void printRosterAlphabetical(ActionEvent event) {
		printOutputTextArea.appendText(roster.printByName() + "\n");
	}
	
	@FXML
	void printRosterPaymentDate(ActionEvent event) {
		printOutputTextArea.appendText(roster.printByPaymentDate() + "\n");
	}
	
	@FXML
	void setFinancialAid(ActionEvent event) {
		boolean validInput = checkValidInput(finAidNameTextField, finAidMajorVBox, finAidOutputTextArea);
		if(!validInput) { 
			return;
		}
		
		String name = finAidNameTextField.getText();
		Major major = getMajorFromRadioButton(finAidMajorVBox);
		Student student = new Student(name, major);
		int studentIndex = roster.find(student);
		
		if(studentIndex == Roster.NOT_FOUND) {
			finAidOutputTextArea.appendText("Student not in the roster\n");
			return;
		}
		
		student = roster.getStudentFromIndex(studentIndex);
		
		if(!roster.isResident(student)) {
			finAidOutputTextArea.appendText("Student is not a resident\n");
			return;
		}
		
		String amountString = finAidAmountTextField.getText();
		double amount;
		try {
			amount = Double.parseDouble(amountString);
		} catch(NumberFormatException e) {
			finAidOutputTextArea.appendText("Invalid amount\n");
			return;
		}
		if (!roster.isFullTime(student)) {
            finAidOutputTextArea.appendText("Parttime student doesn't qualify for the award.\n");
            return;
        }
		if (roster.getFinancialAidStatus(student)) {
			finAidOutputTextArea.appendText("Awarded once already.\n");
            return;
        }
		else if (!isValidFinancialAid(amount)) {
			finAidOutputTextArea.appendText("Invalid amount\n");
            return;
		} else {
			Student resident = new Resident(name, major, amount);
	        if (roster.applyFinancialAid(resident)) {
	        	finAidOutputTextArea.appendText("Tuition updated.\n");
	        } else {
	        	finAidOutputTextArea.appendText("Student not in the roster.\n");
	        }
		}
	}
	
	/**
	 * Obtains the proper major selected within a group of radio buttons.
	 * @param majorVBox: a VBox that consists of radio buttons indicating different majors.
	 * @return the Major that corresponds to the selected radio button.
	 */
	private Major getMajorFromRadioButton(VBox majorVBox) {
		int i = 0;
		for(Node child : majorVBox.getChildren()) {
			RadioButton radioButton = (RadioButton) child;
			if(radioButton.isSelected()) {
				switch(i) {
					case CS_INDEX:
						return Major.CS;
					case IT_INDEX:
						return Major.IT;
					case BA_INDEX:
						return Major.BA;
					case EE_INDEX:
						return Major.EE;
					case ME_INDEX:
						return Major.ME;
					default:
						return Major.CS; //default, should never execute though
				}
			}
			i++;
		}
		return Major.CS; //should never execute either
	}
	
	/**
     * Determines whether argument payment amount is valid.
     * @param student The student who is making the payment
     * @param paymentAmount The payment amount of the student
     * @return True if argument payment amount is valid, false if argument payment amount is invalid
     */
    private boolean isValidPaymentAmount(Student student, double paymentAmount) {
        if (paymentAmount <= 0) {
            return false;
        }
        if (paymentAmount > roster.getBalance(student)) {
            return false;
        }
        return true;
    }
    
    /**
     * Determines whether argument financial aid amount is valid.
     * @param financialAid The financial aid amount
     * @return True if argument financial aid amount is valid, false if argument financial aid amount is invalid
     */
    private boolean isValidFinancialAid(double financialAid) {
        if (financialAid < 0 || financialAid > MAXIMUM_FINANCIAL_AID) {
            return false;
        }
        return true;
    }
    
    /**
     * Determines whether name and major input is valid.
     * @param nameTextField: the TextField where the user enters their name.
     * @param majorVBox: the VBox containing the radio buttons for each major.
     * @param outputTextArea: the TextArea to which error messages are 
     * displayed.
     * @return true if name, major are valid, false if not.
     */
    private boolean checkValidInput(TextField nameTextField, VBox majorVBox, TextArea outputTextArea) {
    	boolean majorSelected = false;
    	String name = nameTextField.getText();
		if(name == null || name.isEmpty()) {
			outputTextArea.appendText("Missing data\n");
			return false;
		}
		for(Node child : majorVBox.getChildren()) {
			RadioButton radioButton = (RadioButton) child;
			if(radioButton.isSelected()) {
				majorSelected = true;
				break;
			}
		}
		if(!majorSelected) {
			outputTextArea.appendText("Missing student major\n");
			return false;
		}
		return true;
    }
	
}
