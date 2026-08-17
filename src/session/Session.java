package session;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import rating.Rating;
import rating.Rateable;
import people.Student;
import people.Tutor;
/**
 * Class Session composites a DateRange for a tutoring session for an aggregate Course
 * 
 * @author              Evan Jorge-Munoz
 * @version             1.0
 * @since               1.0
 * @license.agreement   CC BY-SA International 4.0
 */
public class Session implements Rateable
{
    private Course course;
    private DateRange dates;
    private Tutor tutor;
    private ArrayList<Student> students;
    private ArrayList<Rating> ratings;
    /**
     * Specifies the Course and Tutor
     * 
     * @param course        the course department and number
     * @param tutor         the tutor person for the specified course
     * @since               1.0
     */
    public Session(Course course, Tutor tutor)
    {
        this.course = course;
        this.tutor = tutor;
        this.students = new ArrayList<>();
    }
    public Session(Scanner in)
    {
        this.course = new Course(in);
        this.dates = new DateRange(in);
        this.tutor = new Tutor(in);
      
        this.students = new ArrayList<>();
        int size = in.nextInt(); in.nextLine();
        while(size-- > 0) 
        {
           this.students.add(new Student(in));
        }
    }
    public void save(PrintStream out)
    {
        course.save(out);
        dates.save(out);
        tutor.save(out);

        out.println(students.size()); 
        for(Student student : students) 
        {
            student.save(out);  
        }

    }
    /**
     * To construct DateRange object for field dates
     * 
     * @param date          specified year, month and day for the session
     * @param startTime     the number in military time of when the session begins
     * @param duration      the amount of time that occurs after the startTime
     * @return              void
     * @since               1.0
     */
    public void setSchedule(String date, String startTime, long duration)
    {
        this.dates = new DateRange(date, startTime, duration);
    }
    /**
     * To add the session's student to the students list
     * 
     * @param student       the student person for the session
     * @return              void
     * @since               1.0
     * 
     */
    public void addStudent(Student student)
    {
        students.add(student);
    }
    /**
     * To create a string including each element of the session
     * 
     * @return              the listed date, time and details of the session
     * @since               1.0
     */
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("Session on ").append(this.course).append(" at ").append(this.dates.toString());
        s.append("\n");
        s.append("Tutor: ").append(this.tutor);
        s.append("\n");
        s.append("Students: ");
        for(int i = 0; i < students.size(); i++)
        {
            s.append(students.get(i));

            if (i < students.size() - 1) 
            {
                s.append(", ");
            }
        }
        return s.toString();
    }
    @Override
    public void addRating(Rating rating) 
    {
        this.ratings.add(rating);
    }
    @Override
    public double getAverageRating() 
    {
        int totalStarRatings = 0;
        for (int i = 0; i < ratings.size(); i++)  
        {
            totalStarRatings += ratings.get(i).getStars(); 
        }
        if (this.ratings.isEmpty())
        {
            return Double.NaN;
        }
        return (double) totalStarRatings / ratings.size();
    }
    @Override
    public Rating[] getRatings() 
    {
        return this.ratings.toArray(new Rating[ratings.size()]); 
    }
}
