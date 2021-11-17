package tuition;

/**
 * Extends Student class, includes specific data and operations to non-resident students.
 * @author Sarah McNey, Cynthia Zhu
 */

public class NonResident extends Student {
    public static final int TUITION = 29737;
    public static final int CREDIT_HOUR_RATE = 966;
    
    /**
     * A constructor that creates a new NonResident object given a student's name,
     * major (represented as String), and number of credit hours.
     * @param name: the student's name.
     * @param major: the student's major.
     * @param creditHours: the number of credit hours the student is enrolled in.
     */
    public NonResident(String name, String major, int creditHours) {
        super(name, major, creditHours);
    }
    
    /**
     * A constructor that creates a new NonResident object given a student's name,
     * major (represented as Major object), and number of credit hours.
     * @param name: the student's name.
     * @param major: the student's major.
     * @param creditHours: the number of credit hours the student is enrolled in.
     */
    public NonResident(String name, Major major, int creditHours) {
        super(name, major, creditHours);
    }
    
    /**
     * A method that overrides the Student class's tuitionDue() method to calculate 
     * tuition due for a non-resident student.
     */
    @Override
    public void tuitionDue() {
        this.setTuitionDue(0.0);
        if(this.isFullTime()) {
            this.addTuitionDue(TUITION + FULLTIME_UNIVERSITY_FEE);
        } else {
            this.addTuitionDue(PARTTIME_UNIVERSITY_FEE);
        }
        this.addTuitionDue(this.getCreditHourCost(CREDIT_HOUR_RATE));
        this.subtractTuitionDue(this.getTotalPayment());
    }
    
    /**
     * Represents a NonResident object as a String.
     * @return a String that contains information about the non-resident student.
     */
    @Override
    public String toString() {
        return super.toString() + ":non-resident";
    }
}