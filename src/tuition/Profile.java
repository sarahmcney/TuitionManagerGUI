package tuition;

/**
 * A class to create a Profile for a student consisting of the student's name and major.
 * @author Sarah McNey, Cynthia Zhu
 *
 */
public class Profile {
    private String name;
    private Major major;
    
    /**
     * A constructor to create a Profile object given a student's name and major,
     * both represented as Strings.
     * @param name: the student's name.
     * @param major: the student's major.
     */
    public Profile(String name, String major) { 
        this.name = name;
        this.major = getMajorFromString(major);
    }
    
    public Profile(String name, Major major) {
    	this.name = name;
    	this.major = major;
    }
    
    /**
     * Gets the name of this profile.
     * @return The name of this profile
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * A helper method to obtain a student's major as a String, given the student's
     * major represented as a Major object.
     * @return the String representation of the student's major.
     */
    public String getStringFromMajor() {
        if(this.major == Major.CS) {
            return "CS";
        } else if(this.major == Major.IT) {
            return "IT";
        } else if(this.major == Major.BA) {
            return "BA";
        } else if(this.major == Major.EE) {
            return "EE";
        } else if(this.major == Major.ME) {
            return "ME";
        } else {
            return "NA";
        }
    }
    
    /**
     *A helper method to assign the proper major given its String representation. 
     * @param majorString: the string representation of the student's major.
     * @return the corresponding Major object.
     */
    private static Major getMajorFromString(String majorString) {
        majorString = majorString.toUpperCase();
        switch(majorString) {
            case "CS":
                return Major.CS;
            case "IT":
                return Major.IT;
            case "BA":
                return Major.BA;
            case "EE":
                return Major.EE;
            case "ME":
                return Major.ME;
            default:
                throw new IllegalArgumentException("Invalid Major."); //did i do this right
        }
    }

    /**
     * Represents a Profile object as a String.
     * @return a String that contains a student's name and major, separated by ":".
     */
    @Override
    public String toString() {
        return this.name + ":" + this.getStringFromMajor();
    }

    /**
     * Determines whether two Profiles are equal.
     * @return True if the two Profiles are equal, false if the two Profiles are not equal
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Profile) {
            Profile profile = (Profile) obj;
            if(this.name.equals(profile.name) && this.major == profile.major) {
                return true;
            }
        }
        return false;
    }
}