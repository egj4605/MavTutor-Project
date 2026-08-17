package people;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import rating.Rating;
import rating.Rateable;

public class Person implements Rateable
{
    private String name;
    private String email;
    private ArrayList<Rating> ratings = new ArrayList<>();
    public Person(String name, String email)
    {
        if (name == null || email == null || name.isEmpty() || email.isEmpty())
        {
            throw new IllegalArgumentException("FAIL: Name or email is empty");
        }
        this.name = name;
        this.email = email;
    } 
    public Person(Scanner in)
    {
        this.name = in.nextLine();
        this.email = in.nextLine();
    }
    public void save(PrintStream out)
    {
        out.println(name);
        out.println(email);
    }
    public String getName()
    {
        return this.name;
    }
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }
        Person person = (Person) o;
        return this.name.equals(person.name) && this.email.equals(person.email); 
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(name, email);
    }
    @Override
    public String toString()
    {
        return name + " (" + email + ")";
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
        return (double) totalStarRatings / ratings.size();
    }
    @Override
    public Rating[] getRatings() 
    {
        return this.ratings.toArray(new Rating[ratings.size()]); 
    }

}
