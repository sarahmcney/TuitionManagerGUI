package tuition;

import java.util.StringTokenizer;
import java.util.Calendar;

/**
 * A class to create a Date, consisting of a month, day, and year. 
 * Contains methods to check for validity and compare two dates to each other, along
 * with additional helper methods.
 * @author Sarah McNey, Cynthia Zhu
 * 
 */
public class Date implements Comparable<Date>{ //need to check that we can use StringTokenizer in this class, and that we can use Integer.parseInt()
  private int year;
  private int month;
  private int day;

  //constants
  private static final int QUADRENNIAL = 4;
  private static final int CENTENNIAL = 100;
  private static final int QUATERCENTENNIAL = 400;
  
  private static final int CURRENT_YEAR = 2021;
  
  private static final int JANUARY = 1;
  private static final int FEBRUARY = 2;
  private static final int MARCH = 3;
  private static final int APRIL = 4;
  private static final int MAY = 5;
  private static final int JUNE = 6;
  private static final int JULY = 7;
  private static final int AUGUST = 8;
  private static final int SEPTEMBER = 9;
  private static final int OCTOBER = 10;
  private static final int NOVEMBER = 11;
  private static final int DECEMBER = 12;
  
  private static final int JAN_MAR_MAY_JULY_AUG_OCT_DEC_DAYS = 31;
  private static final int APR_JUNE_SEP_NOV_DAYS = 30;
  
  private static final int FEB_DAYS = 28;
  private static final int FEB_DAYS_LEAPYEAR = 29;
  
  /**
   * A constructor that creates a Date object with today's date.
   */
  public Date() { 
      String todaysDate = Calendar.getInstance().getTime().toString();
      StringTokenizer st = new StringTokenizer(todaysDate, " ");
      st.nextToken(); //skip over day of the week
      month = getMonthNumber(st.nextToken());
      day = Integer.parseInt(st.nextToken());
      st.nextToken(); //skip over timestamp
      st.nextToken(); //skip over time zone
      year = Integer.parseInt(st.nextToken());
  }
  
  /**
   * A constructor that creates a Date object given a String.
   * @param date: a String that contains a date in mm/dd/yyyy format.
   */
  public Date(String date) {
      StringTokenizer st = new StringTokenizer(date, "/");
      month = Integer.parseInt(st.nextToken());
      day = Integer.parseInt(st.nextToken());
      year = Integer.parseInt(st.nextToken());
  }
  
  public Date(int month, int day, int year) {
	  this.month = month;
	  this.day = day;
	  this.year = year;
  }
  
  /**
   * A helper method that converts an abbreviated month name into its corresponding 
   * numerical value.
   * @param monthName: a String containing an abbreviated name of a month.
   * @return the corresponding numerical value for the given month.
   */
  private int getMonthNumber(String monthName) {
      switch(monthName) {
      case "Jan":
          return JANUARY;
      case "Feb":
          return FEBRUARY;
      case "Mar":
          return MARCH;
      case "Apr":
          return APRIL;
      case "May":
          return MAY;
      case "Jun":
          return JUNE;
      case "Jul":
          return JULY;
      case "Aug":
          return AUGUST;
      case "Sep":
          return SEPTEMBER;
      case "Oct":
          return OCTOBER;
      case "Nov":
          return NOVEMBER;
      case "Dec":
          return DECEMBER;
      default:
          return -1;
      }
  }
  
  /**
   * A method to check if the current Date object represents a valid date.
   * In order for a date to be valid, the year must be equal to the current
   * year. The day must be a valid day within the corresponding month, taking leap years
   * into account. The date also cannot be after the current date.
   * @return true if the Date object satisfies the above criteria, false otherwise.
   */
  public boolean isValid() {
      Date todaysDate = new Date();
      if(this.year != CURRENT_YEAR || this.compareTo(todaysDate) > 0) {
          return false;
      }
      if(this.month < JANUARY || this.month > DECEMBER) { //are these considered magic #s
          return false;
      }
      if(!this.validNumDays()) {
          return false;
      }
      return true;
  }
  
  /**
   * A helper method to check if the day field is valid for the current Date object
   * (i.e. checks if the day is lower or higher than possible for the corresponding month).
   * @return true if the day is valid, false otherwise.
   */
  private boolean validNumDays() {
      if(this.day <= 0) { //cannot have negative or zero day
          return false;
      }
      if(this.month == JANUARY || this.month == MARCH || this.month == MAY
                  || this.month == JULY || this.month == AUGUST || this.month == OCTOBER
                  || this.month == DECEMBER) {
          if(this.day <= JAN_MAR_MAY_JULY_AUG_OCT_DEC_DAYS) {
              return true;
          } return false;
      }
      else if(this.month == APRIL || this.month == JUNE || this.month == SEPTEMBER
                  || this.month == NOVEMBER) {
          if(this.day <= APR_JUNE_SEP_NOV_DAYS) {
              return true;
          } return false;
      }
      else { //february
          if(isLeapYear(this.year)) {
              if(this.day <= FEB_DAYS_LEAPYEAR) {
                  return true;
              } return false;
          } else {
              if(this.day <= FEB_DAYS) {
                  return true;
              } return false;
          }
      }
  }
  
  /**
   * A helper method that checks if a given year is a leap year or not.
   * @param year: a year expressed as an integer.
   * @return true if the year is a leap year, false otherwise.
   */
  private static boolean isLeapYear(int year) {
      if(year % QUADRENNIAL == 0) { 
          if(year % CENTENNIAL == 0) {
              if(year % QUATERCENTENNIAL == 0) {
                  return true;
              } else {
                  return false;
              }
          } else {
              return true;
          }
      } else {
          return false;
      }
  }
  
  /**
   * A method that compares two dates to each other.
   * @param date: the Date object that you are comparing to the current Date object.
   * @return 0 if the two dates are the same, -1 if the current Date object is earlier
   * than the date argument, and 1 if the current Date object is later than the date argument. 
   */
  @Override
  public int compareTo(Date date) {
      if(this.year < date.year) {
          return -1;
      } else if(this.year > date.year) {
          return 1;
      } else { //same year
          if(this.month < date.month) {
              return -1;
          } else if(this.month > date.month) {
              return 1; 
          } else { //same year, same month
              if(this.day < date.day) {
                  return -1;
              } else if(this.day > date.day) {
                  return 1; 
              } else { //must be the same date
                  return 0;
              }
          }
      }
  }
  
  /**
   * Represents a Date object as a String.
   * Executes when a Date object is printed to the screen.
   * @return a String that represents the date in mm/dd/yyyy format.
   */
  @Override //double check that i can do this for this class
  public String toString() {
      return this.month + "/" + this.day + "/" + this.year;
  }
  
}