package tuition;

/**
 * Extends NonResident class, includes specific data and operations to international students.
 * @author Sarah McNey, Cynthia Zhu
 *
 */

public class International extends NonResident {
    private boolean studyAbroad;

    private static final int ADDITIONAL_FEE = 2650;

    /**
     * A constructor that creates a new International object given a student's name,
     * major (represented as a String), number of credit hours, and whether or 
     * not they are part of the study abroad program.
     * @param name: the student's name.
     * @param major: the student's major.
     * @param creditHours: the number of credit hours the student is enrolled in.
     * @param studyAbroad: a boolean indicating if a student is part of the study abroad
     * program (true) or not (false).
     */
    public International(String name, String major, int creditHours, boolean studyAbroad) {
        super(name, major, creditHours);
        this.studyAbroad = studyAbroad;
    }
    
    /**
     * A constructor that creates a new International object given a student's name,
     * major (represented as a Major object, number of credit hours, and whether 
     * or not they are part of the study abroad program.
     * @param name: the student's name.
     * @param major: the student's major.
     * @param creditHours: the number of credit hours the student is enrolled in.
     * @param studyAbroad: a boolean indicating if a student is part of the study abroad
     * program (true) or not (false).
     */
    public International(String name, Major major, int creditHours, boolean studyAbroad) {
        super(name, major, creditHours);
        this.studyAbroad = studyAbroad;
    }
    
    /**
     * A method that overrides the Student class's tuitionDue() method to calculate 
     * tuition due for an international student.
     */
    @Override
    public void tuitionDue() {
        this.setTuitionDue(0.0);
        this.addTuitionDue(FULLTIME_UNIVERSITY_FEE + ADDITIONAL_FEE);
        if(!this.studyAbroad) {
            this.addTuitionDue(TUITION);
        }
        this.addTuitionDue(this.getCreditHourCost(CREDIT_HOUR_RATE));
        this.subtractTuitionDue(this.getTotalPayment());
    }
    
    /**
     * A method to obtain a student's studyAbroad value.
     * @return the student's studyAbroad value (true = in study
     * abroad, false = not in study abroad)
     */
    public boolean getStudyAbroad() {
    	return this.studyAbroad;
    }
    
    /**
     * A method to set a student's studyAbroad value.
     * @param isStudyAbroad: a boolean indicating whether a student is part of the study
     * abroad program (true) or not (false).
     */
    public void setStudyAbroad(boolean isStudyAbroad) {
        this.studyAbroad = isStudyAbroad;
    }
    
    /**
     * Represents an International object as a String.
     * @return a String that contains information about the international student.
     */
    @Override
    public String toString() {
        String internationalString = super.toString() + ":international";
        if(this.studyAbroad) {
            internationalString += ":study abroad";
        }
        return internationalString;
    }
}