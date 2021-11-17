package tuition;

import java.text.DecimalFormat;

/**
 * A class that defines the common data items and operations for all student instances,
 * such as a profile that includes name and major, and a method to calculate the tuition
 * due.
 * @author Sarah McNey, Cynthia Zhu
 *
 */
public class Student {
    private Profile profile;
    private int creditHours;
    private double tuitionDue;
    private double paymentAmount;
    private Date lastPayment;
    private double totalPayment;

    public static final int FULLTIME_UNIVERSITY_FEE = 3268;
    public static final double PARTTIME_UNIVERSITY_FEE = FULLTIME_UNIVERSITY_FEE * 0.8;
    public static final int FULLTIME_MIN_CREDIT_HOURS = 12;
    private static final int FULLTIME_MAX_CREDIT_HOURS = 16;

    /**
     * Constructor that creates a Student with given name and major (String).
     * @param name The name of the student
     * @param major The major of the student
     */
    public Student(String name, String major) {
        this.profile = new Profile(name, major);
    }
    
    /**
     * Constructor that creates a Student with given name and major (Major object).
     * @param name The name of the student
     * @param major The major of the student
     */
    public Student(String name, Major major) {
    	this.profile = new Profile(name, major);
    }
    
    /**
     * A constructor that creates a Student object given Strings that indicate the student's
     * name and major, as well as an integer indicating the number of credits a student is
     * taking.
     * @param name: The name of the student.
     * @param major: The student's major, represented as a String.
     * @param creditHours: The number of credit hours the student is enrolled in.
     */
    public Student(String name, String major, int creditHours) {
        profile = new Profile(name, major);
        this.creditHours = creditHours;
        totalPayment = 0.0;
    }
    
    /**
     * A constructor that creates a Student object given a String that indicates the student's
     * name, their major as a Major object, as well as an integer indicating the number of credits a student is
     * taking.
     * @param name: The name of the student.
     * @param major: The student's major, represented as a Major obj.
     * @param creditHours: The number of credit hours the student is enrolled in.
     */
    public Student(String name, Major major, int creditHours) {
    	profile = new Profile(name, major);
    	this.creditHours = creditHours;
    	totalPayment = 0.0;
    }
    
    /**
     * Constructor that creates a Student with given name, major (String), payment amount, and payment date.
     * @param name The name of the student
     * @param major The major of the student
     * @param paymentAmount The payment amount of the student
     * @param lastPayment The payment date of the student
     */
    public Student(String name, String major, double paymentAmount, Date lastPayment) {
        this.profile = new Profile(name, major);
        this.paymentAmount = paymentAmount;
        this.lastPayment = lastPayment;
    }
    
    /**
     * Constructor that creates a Student with given name, major (Major obj), payment amount, and payment date.
     * @param name The name of the student
     * @param major The major of the student
     * @param paymentAmount The payment amount of the student
     * @param lastPayment The payment date of the student
     */
    public Student(String name, Major major, double paymentAmount, Date lastPayment) {
        this.profile = new Profile(name, major);
        this.paymentAmount = paymentAmount;
        this.lastPayment = lastPayment;
    }
    
    /**
     * A do-nothing method for calculating tuition due -- all subclasses override this method.
     */
    public void tuitionDue() {
        
    }
    
    /**
     * A helper method that increases a student's tuition due by a given amount.
     * @param amount: the amount to increase the student's tuition due by.
     */
    public void addTuitionDue(double amount) {
        tuitionDue += amount;
    }

    /**
     * A helper method that decreases a student's tuition due by a given amount.
     * @param amount: the amount to decrease the student's tuition due by.
     */
    public void subtractTuitionDue(double amount) {
        tuitionDue -= amount;
    }

    /**
     * A method to set a student's tuition due.
     * @param amount: the amount to set the student's tuition due to.
     */
    public void setTuitionDue(double amount) {
        this.tuitionDue = amount;
    }
    
    /**
     * A helper method to retrieve a student's tuitionDue value.
     * @return the student's tuitionDue value.
     */
    public double getTuitionDue() {
        return this.tuitionDue;
    }
    
    /**
     * Gets the payment amount of this student.
     * @return The payment amount of this student
     */
    public double getPaymentAmount() {
        return this.paymentAmount;
    }
    
    /**
     * Gets last payment date of this student.
     * @return Date of last payment
     */
    public Date getLastPayment() {
        return this.lastPayment;
    }
    
    /**
     * Sets last payment date of this student to given last payment date.
     * @param lastPayment Date of last payment
     */
    public void setLastPayment(Date lastPayment) {
        this.lastPayment = lastPayment;
    }
    
    /**
     * A helper method to retrieve a student's number of credit hours.
     * @return the student's number of credit hours.
     */
    public int getCreditHours() {
        return this.creditHours;
    }
    
    /**
     * Sets number of credit hours of this student to given number of credit hours.
     * @param creditHours Number of credit hours
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    /**
     * A helper method to retrieve a student's profile.
     * @return the student's profile.
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * A helper method to retrieve the total amount that a student has paid
     * @return the total amount the student has paid.
     */
    public double getTotalPayment() {
        return this.totalPayment;
    }
    
    /**
     * Sets total payment amount of this student to given total payment amount.
     * @param totalPayment Total payment amount
     */
    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    /**
     * A helper method to calculate the amount that a student needs to pay by credit hour.
     * If a student is part-time, the method returns the student's number of credit hours
     * multiplied by the given credit hour rate. If a student is full-time, the method
     * calculates the number of credits exceeding 16 that the student is taking, and 
     * multiplies that quantity by the given credit hour rate.
     * @param creditHourRate: the rate that the student is charged per credit hour.
     * @return the amount that the student needs to pay by credit hour.
     */
    public int getCreditHourCost(int creditHourRate) {
        if(this.isFullTime()) {
            int additionalCredits = this.creditHours - FULLTIME_MAX_CREDIT_HOURS;
            if(additionalCredits <= 0) {
                return 0;
            } else {
                return additionalCredits * creditHourRate;
            }
        } else {
            return this.creditHours * creditHourRate;
        }
    }

    /**
     * A helper method to determine whether a student is full-time or not depending
     * on the number of credit hours they are enrolled in.
     * @return a boolean value indicating if the student is full-time (true) or part-time (false)
     */
    public boolean isFullTime() {
        if(this.creditHours >= FULLTIME_MIN_CREDIT_HOURS) {
            return true;
        }
        return false;
    }
    
    /**
     * A method to pay a student's tuition.
     * @param amount: the amount of the payment the student made.
     * @param date: the date of the payment.
     */
    public void payTuition(double amount, Date date) {
        lastPayment = date;
        this.totalPayment += amount;
        this.subtractTuitionDue(amount);
    }

    /**
     * Represents a Student object as a String.
     * @return a String that contains information about the student.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String studentString = this.profile.toString() + ":" + this.creditHours + 
                " credit hours:tuition due:" + df.format(this.tuitionDue) + ":total payment:" + df.format(this.totalPayment) +
                ":last payment date: ";
        if(this.lastPayment == null) {
            studentString += "--/--/--";
        } else {
            studentString += this.lastPayment;
        }
        return studentString;   
    }
}