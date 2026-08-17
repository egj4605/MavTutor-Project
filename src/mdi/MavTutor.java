package mdi;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;

import menu.Menu;
import menu.MenuItem;
import people.Student;
import people.Person;
import people.Tutor;
import rating.Rateable;
import rating.Rating;
import rating.Comment;
import session.Session;
import session.Course;
import session.InvalidCourseException;

public class MavTutor
{
    private Menu menu;
    private List view;
    private File file;

    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    private ArrayList<Tutor> tutors;
    private ArrayList<Session> sessions;

    private static void showSplashScreen() {
    String[] splash = {
        "==============================================================",
        "",
        "        __  __              _____       _",
        "       |  \\/  |            |_   _|     | |",
        "       | \\  / | __ ___   __ _| |_ _   _| |_ ___  _ __",
        "       | |\\/| |/ _` \\ \\ / / _` | | | | | __/ _ \\| '__|",
        "       | |  | | (_| |\\ V / (_| | | |_| | || (_) | |",
        "       |_|  |_|\\__,_| \\_/ \\__,_|_|\\__,_|\\__\\___/|_|",
        "",
        "                    M A V   T U T O R",
        "",
        "             =====================================",
        "              Foster learning and academic growth",
        "             =====================================",
        "",
        "                 Starting application...",
        "",
        "=============================================================="
    };

    try {
        // Start the splash screen low on the screen
        for (int position = 10; position >= 0; position--) {

            // Clear the terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Add blank lines to position the splash screen
            for (int i = 0; i < position; i++) {
                System.out.println();
            }

            // Print splash screen
            for (String line : splash) {
                System.out.println(line);
            }

            Thread.sleep(150);
        }

        // Keep splash screen visible for another 2 seconds
        Thread.sleep(2000);

    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
    public MavTutor()
    {
        this.view = new ArrayList<>();;
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.tutors = new ArrayList<>();
        this.sessions = new ArrayList<>();

        String clearScreen = "\n".repeat(80);  
        String title = "---- MavTutor Menu ----";            
        title += '\n' + "=".repeat(title.length()) + '\n'; 

        MenuItem item0 = new MenuItem("Quit\n", this::quit);
        MenuItem item1 = new MenuItem("Add New Course", this::newCourse);
        MenuItem item2 = new MenuItem("Add New Student", this::newStudent);
        MenuItem item3 = new MenuItem("Add New Tutor", this::newTutor);
        MenuItem item4 = new MenuItem("Add New Session", this::newSession);
        MenuItem item5 = new MenuItem("Select View", () -> this.selectView());
        MenuItem item6 = new MenuItem("Clear", this::newz);
        MenuItem item7 = new MenuItem("Save", this::save);
        MenuItem item8 = new MenuItem("Save As", this::saveAs);
        MenuItem item9 = new MenuItem("Open", this::open);
        MenuItem item10 = new MenuItem("Review a Student",() -> this.review(students));
        MenuItem item11 = new MenuItem("Review a Tutor", () -> this.review(tutors));
        MenuItem item12 = new MenuItem("Review a Session", () -> this.review(sessions));
        menu = new Menu(new Object[] {clearScreen, title}, new Object[] {this, "\nEnter a menu selection:"}, item0, item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12);
        menu.run(); 
        this.file = null;
    }
    public static void main(String[] args)
    {
        showSplashScreen();
        new MavTutor();
    }
    @Override
    public String toString()
    {
        return Menu.listToString("List of Selected View\n=====================\n" , view, '•');
    }
    private void quit()
    {
        menu.result = null;
    }
    private void selectView()
    {
        String[] dataViews = new String[]{"View Courses", "View Students", "View Tutors", "View Sessions"};
        Integer index = Menu.selectItemFromArray("Select a data view: ", dataViews);
        if(index == 0)
        {
            view = this.courses;
        }
        else if(index == 1)
        {
            view = this.students;
        }
        else if(index == 2)
        {
            view = this.tutors;
        }
        else if(index == 3)
        {
            view = this.sessions;
        }
    }
    private void newCourse()
    {
        try 
        {
            this.courses.add(new Course(Menu.getString("Enter Course Department name: "), 
            Menu.getInt("Enter Course Number: ")));  

        } catch (InvalidCourseException e) 
        {
            menu.result.append("FAIL: entry of course is invalid");
        }
    }
    private void newTutor()
    {
        if (this.courses == null || this.courses.isEmpty())
        {
            menu.result.append("FAIL: no courses have been defined");
        }
        this.tutors.add(new Tutor(Menu.getString("Enter Tutor's name: "),
        Menu.getString("Enter Email address of Tutor: "), 
        Menu.getInt("Enter Social Security Number(SSN) of Tutor: "),
        Menu.getString("Enter bio of Tutor: "),
        this.courses.get(Menu.selectItemFromList("Select a Course from the List: ", this.courses))));
    }
    private void newStudent()
    {
        if (this.courses == null || this.courses.isEmpty())
        {
            menu.result.append("FAIL: no courses have been defined");
        }
        Student student = new Student(Menu.getString("Enter Student's name: "),
        Menu.getString("Enter Email address of Student: "));

        boolean loop = true;
        while(loop)
        {
            Integer inum = (Menu.selectItemFromList("Select the Course(s) from the List for the Student (Enter -1 to Exit): ", this.courses));
            if(inum == -1)
            {
                loop = false;
            }
            else
            {
                student.addCourse(this.courses.get(inum));
            }
        }
        this.students.add(student);
    }   
    private void newSession()
    {
        if (this.tutors == null || this.students == null || this.tutors.isEmpty() || this.students.isEmpty())
        {
            menu.result.append("FAIL: no tutors or no students have been defined");
        }
        Session session = new Session(this.courses.get(Menu.selectItemFromList("Select a Course from the List: ", this.courses)), 
        this.tutors.get(Menu.selectItemFromList("Select a Tutor from the List: ", this.tutors)));

        session.setSchedule(Menu.getString("Enter the Date of the Session (YYYY-MM-DD): "), 
        Menu.getString("Enter the Starting Time of the Session (ex. 10:00): "),
        Menu.getInt("Enter the duration of the Session (in Minutes - ex. 90): "));
        boolean sloop = true;
        while(sloop)
        {
            Integer snum = Menu.selectItemFromList("Select the Student(s) from the List for the Session (Enter -1 to Exit): ", this.students);
            if(snum == -1)
            {
                sloop = false;
            }
            else
            {
                session.addStudent(this.students.get(snum));
            }
        }
        this.sessions.add(session);
    }
    private void newz()
    {
        courses.clear();
        students.clear();
        tutors.clear();
        sessions.clear();
        file = null;
    }
    private void save()
    {
        if (file == null)
        {
            file = Menu.selectFile("Select an option to save: ", null, null);
        }
        if (file != null)
        {
            try(PrintStream out = new PrintStream(file)) 
            {
                out.println(courses.size());                                 
                for(Course course : courses) course.save(out);           

                out.println(students.size());
                for(Student student : students) student.save(out);         

                out.println(tutors.size());                                  
                for(Tutor tutor : tutors) tutor.save(out);                 

                out.println(sessions.size());                                 
                for(Session session : sessions) session.save(out);          

                menu.result.append("Save was successful!");
            } catch(Exception e) 
            {
                System.err.println("Save failed: " + e);
            }
        }
    }
    private void saveAs()
    {
        file = null;
        save();
    }
    private void open()
    {
        file = Menu.selectFile("Select an option to open: ", null, null);

        if (file != null)
        {
            try(Scanner in = new Scanner(file)) 
            {
                int courseSize = in.nextInt();  in.nextLine();                        
                for(int i = 0; i < courseSize; i++) 
                {
                    courses.add(new Course(in)); 
                }         

                int studentSize = in.nextInt(); in.nextLine();                         
                for(int i = 0; i < studentSize; i++) 
                {
                    students.add(new Student(in)); 
                }              

                int tutorSize = in.nextInt(); in.nextLine();                           
                for(int i = 0; i < tutorSize; i++) 
                {
                    tutors.add(new Tutor(in)); 
                }                     

                int sessionSize = in.nextInt(); in.nextLine();                           
                for(int i = 0; i < sessionSize; i++) 
                {
                    sessions.add(new Session(in)); 
                }              

                menu.result.append("Open was successful!");
            } catch(Exception e) 
            {
                System.err.println("Open failed: " + e);
                newz();
            }
        }
    }
    private void review(List <? extends Rateable> list)
    {
        Integer index = Menu.selectItemFromList("Select which one you want to review: ", list);
        
        Double avgRating = list.get(index).getAverageRating();
        if(Double.isNaN(avgRating))
        {
            System.out.println("\nNo ratings have yet been defined\n");
        }
        else
        {
            System.out.println(list.get(index) + "'s average rating is: " + avgRating); 
        }
        Person user = login();
        if(user != null)
        {
            Integer starRating = Menu.getInt("How many stars from 1 to 5 would you rate this person (type -1 to quit): ");
            if (starRating < 1 || starRating > 5)
            {
                return;
            }
            String reviewString = Menu.getString("Write a comment over how your experience was with this person (type 'q' to quit): ");
            if (reviewString == "q" )
            {
                return;
            }
            Comment reviewComment = new Comment(reviewString, user, null);
            Rating userRating = new Rating(starRating, reviewComment);
            list.get(index).addRating(userRating);
        }
        Rating[] ratingList = list.get(index).getRatings();
        Integer selectedRating = Menu.selectItemFromArray("Select a rating: ", ratingList);
        Comment comment = ratingList[selectedRating].getReview();

        printExpandedComments(comment, 0);
        while(true)
        {
            ArrayList<String> options = new ArrayList<>();
            options.add( "Reply"); 
            if(comment.getInReplyTo() != null)
            {
                options.add( "Up");
            }
            if(comment.numReplies() != 0)
            {
                options.add( "Down");
            }
            options.add( "Main menu"); 

            Integer optionPick = Menu.selectItemFromArray("Choose what you would like to do: ", options.toArray());
            if(options.get(optionPick).equals("Reply"))
            {
                if(user == null)
                {
                    login();
                }
                String reply = Menu.getString("Reply to the current comment: ");
                comment.addReply(reply, user);
            }
            else if(options.get(optionPick).equals("Up"))
            {
                if(comment.getInReplyTo() != null)
                {
                    comment = comment.getInReplyTo();
                }
            }
            else if(options.get(optionPick).equals("Down"))
            {
                if(comment.numReplies() != 0)
                {
                    Integer selectedInteger = Menu.getInt("Select a reply: "); 
                    comment = comment.getReply(selectedInteger);
                }
            }
            else if(options.get(optionPick).equals("Main menu"))
            {
                return;
            }
            printExpandedComments(comment, 0);
        }
    }
    private Person login() 
    {
        String[] items = new String[] {"Student", "Tutor", "(Not logging in)"};
        Integer index = Menu.selectItemFromArray("Choose what to login as: ", items);
        if(index != null)
        {
            System.out.println("Login choice:  " + items[index]); 
        }
        Integer selection = 0;
        if(index == 0)
        {
            selection = Menu.selectItemFromList("Select which " + items[index] + " you are logging in as:", students);
            return (Person) students.get(selection);
        }
        else if(index == 1)
        {
            selection = Menu.selectItemFromList("Select which " + items[index] + " you are logging in as:", tutors);
            return (Person) tutors.get(selection);
        }
        return null;
    }
    private void printExpandedComments(Comment c, int level)
    {
        printIndented(c.toString(), level);
        System.out.println("\n");
        for(int i=0; i<c.numReplies(); ++i) 
        {
            printExpandedComments(c.getReply(i), level+1);
        }
    }
    private void printIndented(String multiline, int level)
    {
        String[] strings = multiline.split("\n");
        for(String s : strings) 
        {
            System.out.println(" ".repeat(level) + s);
        }

    }
}