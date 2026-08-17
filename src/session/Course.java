package session;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;
/**
 * Class Course contains the name of the course department and the course number
 * 
 * @author              Evan Jorge-Munoz
 * @version             1.0
 * @since               1.0
 * @license.agreement   CC BY-SA International 4.0
 */
public class Course
{
    private String dept;
    private int number;
    /**
     * Assigns the valid parameters (dept and number) to the corresponding fields, or throws InvalidCourseException if invalid
     * 
     * @param dept          department type of course
     * @param number        number of specified course
     * @since               1.0
     */
    public Course(String dept, int number)
    {

        if(dept.length() != 3 && dept.length() != 4)
        {
            throw new InvalidCourseException(dept);
        
        }
        if (number < 1000 || number > 9999) 
        {
            throw new InvalidCourseException(dept, number);
        }
        this.dept = dept;
        this.number = number;
    }
    public Course(Scanner in)
    {
        this.dept = in.nextLine();
        this.number = in.nextInt(); in.nextLine();
    }
    public void save(PrintStream out)
    {
        out.println(dept);
        out.println("" + number);
    }
    /**
     * Returns a string of the concatenated dept and number fields
     * 
     * @return              string of the concatenated dept and number fields
     * @since               1.0
     */
    @Override
    public String toString()
    {
        return this.dept + " " + this.number;

    }
    /**
     * Checks if both dept and number are equal, and both fields are relevant for hashCode
     * 
     * @param o             the object type passed in to be checked 
     * @return              returns true if both dept and number are equal, and both fields are relevant for hashCode. If not, returns false.
     * @since               1.0
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        else if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Course course = (Course) o;
        return this.dept.equals(course.dept) && this.number == course.number;
    }
    /**
     * To return the hashcode integer of either dept and/or number
     * 
     * @return              the hashcode integer of either dept and/or number
     * @since               1.0
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(dept, number);
    }
}