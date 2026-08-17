package people;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import session.Course;

public class Student extends Person
{
    private static int nextStudentID = 0;
    private int studentID;
    private ArrayList<Course> courses;

    public Student(String name, String email)
    {
        super(name, email); 
        this.studentID = nextStudentID++;
        this.courses = new ArrayList<>();
    }
    public Student(Scanner in)
    {
        super(in);
        this.nextStudentID = in.nextInt(); in.nextLine();
        this.studentID = in.nextInt(); in.nextLine();
        
        this.courses = new ArrayList<>();
        int size = in.nextInt(); in.nextLine();
        while(size-- > 0) 
        {
           this.courses.add(new Course(in));
        }
    }
    public void save(PrintStream out)
    {
        super.save(out);
        out.println("" + nextStudentID);
        out.println("" + studentID);

        out.println(courses.size()); 
        for(Course course : courses)
        {
            course.save(out); 
        } 
    }
    public void addCourse(Course course)
    {
        courses.add(course);
    }
    public Course[] getCourses()
    {
        return courses.toArray(new Course[0]);
    }
    @Override
    public String toString()
    {
        String superString = super.toString();
        String stringre = ", #" + this.studentID + ")";
        return superString.replace(")", stringre);
    }
}