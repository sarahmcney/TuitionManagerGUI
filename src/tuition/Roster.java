package tuition;

/**
 * Roster is a collection of Students.
 * Roster defines an array list data structure to hold the Students and
 * provides operations to manage the collection.
 * @author Sarah McNey, Cynthia Zhu
 */
public class Roster {
    private Student[] roster;
    private int size;
    
    private static final int MAXIMUM_CREDIT_HOURS_INTERNATIONAL_STUDY_ABROAD = 12;
    
    public static final int NOT_FOUND = -1;
    
    /**
     * Constructor that creates a Student array list of capacity 4 and size 0.
     */
    public Roster() {
        roster = new Student[4];
        size = 0;
    }
    
    /**
     * Searches for the argument student in the array list using linear search.
     * @param student The student to be searched for
     * @return The index of the student if found, -1 if not found
     */
    public int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (student.getProfile().equals(roster[i].getProfile())) {
                return i;
            }
        }
        return NOT_FOUND;
    }
    
    /**
     * Increases the capacity of the array list by 4.
     */
    private void grow() {
        Student[] arr = new Student[roster.length + 4];
        for (int i = 0; i < size; i++) {
            arr[i] = roster[i];
        }
        roster = arr;
    }
    
    /**
     * Inserts the argument student at the end of the array list.
     * If capacity is reached, calls the grow() method to
     * increase the capacity by 4.
     * @param student The student to be inserted
     * @return True if the student was inserted, false if the student was found
     */
    public boolean add(Student student) {
        if (find(student) != NOT_FOUND) {
            return false;
        }
        roster[size] = student;
        size += 1;
        if (size == roster.length) {
            grow();
        }
        return true;
    }
    
    /**
     * Deletes the argument student from the array list.
     * Maintains the order of the array list.
     * @param student The student to be deleted
     * @return True if the student was deleted, false if the student was not found
     */
    public boolean remove(Student student) {
        int index = find(student);
        if (index == NOT_FOUND) {
            return false;
        }
        size -= 1;
        for (int i = index; i <= size; i++) {
            roster[i] = roster[i + 1];
        }
        return true;
    }
    
    /**
     * Determines whether the array list contains the argument student.
     * @param student Student
     * @return True if the array list contains the argument student, false if not
     */
    public boolean hasStudent(Student student) {
        if (find(student) != NOT_FOUND) {
            return true;
        }
        return false;
    }
    
    /**
     * Determines whether the argument student is a resident student.
     * @param student Student
     * @return True if the argument student is a resident student, false if not
     */
    public boolean isResident(Student student) {
        int index = find(student);
        if (index == NOT_FOUND) {
            return false;
        }
        if (roster[index] instanceof Resident) {
            return true;
        }
        return false;
    }
    
    public boolean isInternational(Student student) {
    	int index = find(student);
    	if(index == NOT_FOUND) {
    		return false;
    	}
    	if(roster[index] instanceof International) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Determines whether the argument student is a full-time student.
     * @param student Student
     * @return True if the argument student is a full-time student, false if not
     */
    public boolean isFullTime(Student student) {
        int index = find(student);
        if (index == NOT_FOUND) {
            return false;
        }
        if (roster[index].getCreditHours() < Student.FULLTIME_MIN_CREDIT_HOURS) {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the balance of the argument student.
     * @param student Student
     * @return The balance of the argument student
     */
    public double getBalance(Student student) {
        int index = find(student);
        return roster[index].getTuitionDue();
    }
    
    /**
     * Gets the financial aid status of the argument student.
     * @param student Student
     * @return The financial aid status of the argument student
     */
    public boolean getFinancialAidStatus(Student student) {
        int index = find(student);
        if (index == NOT_FOUND) {
            return false;
        }
        if (roster[index] instanceof Resident) {
            return ((Resident) roster[index]).getFinAidApplied();
        }
        return false;
    }
    
    /**
     * Makes a payment for the argument student.
     * @param student The student who is making a payment
     * @return True if the payment is made, false if the student is not found
     */
    public boolean makePayment(Student student) {
        int index = find(student);
        if (index == NOT_FOUND) {
            return false;
        }
        roster[index].payTuition(student.getPaymentAmount(), student.getLastPayment());
        return true;
    }
    
    /**
     * Calculates balance for all students in array list.
     */
    public void calculateBalance() {
        for (int i = 0; i < size; i++) {
            roster[i].tuitionDue();
        }
    }
    
    public boolean calculateBalanceIndividual(Student student) {
    	int index = find(student);
    	if (index == NOT_FOUND) {
            return false;
        }
    	roster[index].tuitionDue();
    	return true;
    }
    
    /**
     * Applies financial aid for the argument student.
     * @param student The student who is receiving financial aid
     * @return True if financial aid is applied, false if the student is not found
     */
    public boolean applyFinancialAid(Student student) {
        int index = find(student);
        if (index == NOT_FOUND) {
            return false;
        }
        double financialAid = ((Resident) student).getFinAidAwarded();
        ((Resident) roster[index]).applyFinAid(financialAid);
        return true;
    }
    
    /**
     * Enrolls international student in study abroad program.
     * @param student The student enrolling in study abroad.
     * @return True if the update is performed successfully, false if not.
     */
    public boolean enrollInStudyAbroad(Student student) {
        int index = find(student);
        if (index == NOT_FOUND) {
            return false;
        }
        if(student instanceof International) {
        	((International) roster[index]).setStudyAbroad(true);
            if (roster[index].getCreditHours() > MAXIMUM_CREDIT_HOURS_INTERNATIONAL_STUDY_ABROAD) {
                roster[index].setCreditHours(MAXIMUM_CREDIT_HOURS_INTERNATIONAL_STUDY_ABROAD);
            }
            roster[index].setTotalPayment(0.0);
            roster[index].setLastPayment(null);
            roster[index].tuitionDue();
            return true;
        }
        
        return false;
    }
    
    /**
     * Given an index, return the student located at that index in the roster.
     * @param index: the index of the student
     * @return the Student object of the student at given index.
     */
    public Student getStudentFromIndex(int index) {
    	return roster[index];
    }
    
    /**
     * Prints the array list.
     * @return a String either containing the list to print or an
     * appropriate error message.
     */
    public String print() {
    	String output = "";
        if (size == 0) {
            return "Student roster is empty!";
        }
        output += "* list of students in the roster **\n";
        for (int i = 0; i < size; i++) {
            output += roster[i].toString() + "\n";
        }
        output += "* end of roster **";
        return output;
    }
    
    /**
     * Sorts the array list by name using selection sort and
     * prints the array list.
     * @return a String either containing the list to print or
     * the appropriate error message.
     */
    public String printByName() {
    	String output = "";
        if (size == 0) {
            return "Student roster is empty!";
        }
        for (int i = 0; i < size - 1; i++) {
            int index = i;
            for (int j = i + 1; j < size; j++) {
                if (roster[index].getProfile().getName().compareTo(roster[j].getProfile().getName()) > 0) {
                    index = j;
                }
            }
            Student temp = roster[i];
            roster[i] = roster[index];
            roster[index] = temp;
        }
        output += "* list of students ordered by name **\n";
        for (int i = 0; i < size; i++) {
            output += roster[i].toString() + "\n";
        }
        output += "* end of roster **";
        return output;
    }
    
    /**
     * Prints only the students who have made payments sorted by payment date.
     * @return a String either containing the list to print or an appropriate
     * error message.
     */
    public String printByPaymentDate() {
    	String output = "";
        if (size == 0) {
            return "Student roster is empty!";
        }
        int length = 0;
        for (int i = 0; i < size; i++) {
            if (roster[i].getLastPayment() != null) {
                length += 1;
            }
        }
        Student[] arr = new Student[length];
        int n = 0;
        for (int i = 0; i < size; i++) {
            if (roster[i].getLastPayment() != null) {
                arr[n] = roster[i];
                n += 1;
            }
        }
        for (int i = 0; i < length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[index].getLastPayment().compareTo(arr[j].getLastPayment()) > 0) {
                    index = j;
                }
            }
            Student temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        output += "* list of students made payments ordered by payment date **\n";
        for (int i = 0; i < length; i++) {
            output += arr[i].toString() + "\n";
        }
        output += "* end of roster **";
        return output;
    }
}