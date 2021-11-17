package tuition;

import java.text.DecimalFormat;

/**
 * Extends Student class, includes specific data and operations to resident students.
 * @author Sarah McNey, Cynthia Zhu
 *
 */
public class Resident extends Student {
    private boolean finAidApplied;
    private double finAidAwarded;

    private static final int TUITION = 12536;
    private static final int CREDIT_HOUR_RATE = 404;
    
    /**
     * A constructor that creates a new Resident object given a student's name,
     * major, and number of credit hours.
     * @param name: the student's name.
     * @param major: the student's major.
     * @param creditHours: the number of credit hours the student is enrolled in.
     */
    public Resident(String name, String major, int creditHours) {
        super(name, major, creditHours);
        this.finAidAwarded = 0.0;
    }
    
    /**
     * Constructor that creates a Resident with given name, major String, and financial aid amount.
     * @param name The name of the resident
     * @param major The major of the resident
     * @param finAidAwarded The financial aid award of the resident
     */
    public Resident(String name, String major, double finAidAwarded) {
        super(name, major);
        this.finAidAwarded = finAidAwarded;
    }
    
    /**
     * Constructor that creates a Resident with given name, major (Major object), and financial aid amount.
     * @param name The name of the resident
     * @param major The major of the resident
     * @param finAidAwarded The financial aid award of the resident
     */
    public Resident(String name, Major major, double finAidAwarded) {
        super(name, major);
        this.finAidAwarded = finAidAwarded;
    }
    
    /**
     * Constructor that creates a Resident with given name, major (Major object), and num credits.
     * @param name The name of the resident
     * @param major The major of the resident
     * @param finAidAwarded The financial aid award of the resident
     */
    public Resident(String name, Major major, int creditHours) {
    	super(name, major, creditHours);
    	this.finAidAwarded = 0.0;
    }
    
    /**
     * Gets the financial aid status of this student.
     * @return True if student has received financial aid, false if student has not received financial aid
     */
    public boolean getFinAidApplied() {
        return this.finAidApplied;
    }
    
    /**
     * Gets the financial aid amount of this student.
     * @return The financial aid amount of this student
     */
    public double getFinAidAwarded() {
        return this.finAidAwarded;
    }
    
    /**
     * A method that overrides the Student class's tuitionDue() method to calculate 
     * tuition due for a resident student.
     */
    @Override
    public void tuitionDue() {
        this.setTuitionDue(0.0);
        if(this.isFullTime()) {
            this.addTuitionDue(TUITION + FULLTIME_UNIVERSITY_FEE);
        } else { //part-time
            this.addTuitionDue(PARTTIME_UNIVERSITY_FEE);
        }
        this.addTuitionDue(this.getCreditHourCost(CREDIT_HOUR_RATE)); 
        this.subtractTuitionDue(this.getTotalPayment());
        this.subtractTuitionDue(this.finAidAwarded);
    }
    
    /**
     * A method to apply financial aid to a resident student's account.
     * @param amount: the amount of financial aid awarded.
     */
    public void applyFinAid(double amount) {
        this.finAidApplied = true;
        this.finAidAwarded = amount;
        this.subtractTuitionDue(amount);
    }
    
    /**
     * Represents a Resident object as a String.
     * @return a String that contains information about the resident student.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$###,##0.00");
        String residentString = super.toString() + ":resident";
        if(finAidApplied) {
            residentString += ":financial aid " + df.format(finAidAwarded);
        }
        return residentString;
    }
    
}