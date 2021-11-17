package tuition;

/**
 * Extends NonResident class, includes specific data and operations to students living
 * in New York or Connecticut.
 * @author Sarah McNey, Cynthia Zhu
 */

public class TriState extends NonResident {
    private String state; 
    
    public static final String NEW_YORK = "NY";
    public static final String CONNECTICUT = "CT";
    private static final int NEW_YORK_DISCOUNT = 4000;
    private static final int CONNECTICUT_DISCOUNT = 5000;

    /**
     * A constructor that creates a new TriState object given a student's name,
     * major (represented as a String), number of credit hours, and state.
     * @param name: the student's name.
     * @param major: the student's major.
     * @param creditHours: the number of credit hours the student is enrolled in.
     * @param state: the state the student is from.
     */
    public TriState(String name, String major, int creditHours, String state) {
        super(name, major, creditHours);
        this.state = state;
    }
    
    /**
     * A constructor that creates a new TriState object given a student's name,
     * major (represented as a Major object), number of credit hours, and state.
     * @param name: the student's name.
     * @param major: the student's major.
     * @param creditHours: the number of credit hours the student is enrolled in.
     * @param state: the state the student is from.
     */
    public TriState(String name, Major major, int creditHours, String state) {
        super(name, major, creditHours);
        this.state = state;
    }
    
    /**
     * A method that overrides the Student class's tuitionDue() method to calculate 
     * tuition due for a tri-state student.
     */
    @Override
    public void tuitionDue() {
        this.setTuitionDue(0.0);
        if(this.isFullTime()) {
            this.addTuitionDue(TUITION + FULLTIME_UNIVERSITY_FEE);
            if(this.state.equals(NEW_YORK)) {
                this.subtractTuitionDue(NEW_YORK_DISCOUNT);
            } else if(this.state.equals(CONNECTICUT)) {
                this.subtractTuitionDue(CONNECTICUT_DISCOUNT);
            }
        } else {
            this.addTuitionDue(PARTTIME_UNIVERSITY_FEE);
        }
        this.addTuitionDue(this.getCreditHourCost(CREDIT_HOUR_RATE)); 
        this.subtractTuitionDue(this.getTotalPayment());
    }
    
    /**
     * Represents a TriState object as a String.
     * @return a String that contains information about the tri-state student.
     */
    @Override
    public String toString() {
        return super.toString() + "(tri-state):" + this.state;
    }
}