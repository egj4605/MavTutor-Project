package people;
import java.io.PrintStream;
import java.util.Scanner;
import session.Course;

public class Tutor extends Person
{
    private String bio;
    private int ssn;
    private Course course;

    public Tutor(String name, String email, int ssn, String bio, Course course)
    {
        super(name, email); 
        if (ssn < 001_01_0001 || ssn > 999_99_9999)
        { 
            throw new IllegalArgumentException("FAIL: ssn is out of range. Must be between 001_01_0001 and 999_99_9999.");
        }
        this.ssn = ssn;
        this.bio = bio;
        this.course = course;
    }
    public Tutor(Scanner in)
    {
        super(in);
        this.ssn = in.nextInt(); in.nextLine();
        this.bio = in.nextLine();
        this.course = new Course(in);
    }
    public void save(PrintStream out)
    {
        super.save(out);
        out.println("" + ssn);
        out.println(bio);
        course.save(out);
    }
    public int getSSN()
    {
        return this.ssn;
    }
    public String getBio()
    {
        return this.bio;
    }
    public Course getCourse()
    {
        return this.course;
    }
}