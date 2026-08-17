package session;
/**
 * This class throws an InvalidCourseException if Course is invalid inherited by IllegalArgumentException
 * 
 * @author              Evan Jorge-Munoz
 * @version             1.0
 * @since               1.0
 * @license.agreement   CC BY-SA International 4.0
 * 
 */
public class InvalidCourseException extends IllegalArgumentException
{
    /**
     * Indicates that the department portion of the Course name is invalid
     * 
     * @param dept          department type of course
     * @since               1.0
     */
    public InvalidCourseException(String dept)
    {
        super("Invalid dept in new Course: " + dept);
    }
    /**
     * Indicates the course number is invalid
     * 
     * @param dept          department type of course
     * @param number        number of specified course
     * @since               1.0
     */
    public InvalidCourseException(String dept, int number)
    {
        super("Invalid course number in new Course: " + dept + " " + number);
    }
}