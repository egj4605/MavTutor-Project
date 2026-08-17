package test;
import people.Person;

public class TestPerson
{
    public static void main(String[] args)
    {
        // TEST VECTOR #1: toString check
        Person person = new Person("Evan", "Evan@gmail.com");
        if (!person.toString().contains("Evan")) 
        {
            System.err.println("FAIL: the toString method did not include the name and email fields specified"); 
        
        } 

        // TEST VECTOR #2: getName check
        person = new Person("Dante", "Dante@gmail.com");
        if(!person.getName().equals("Dante"))
        {
            System.err.println("FAIL: the getName method does not return the name field"); 
        }
        // TEST VECTOR #3: equals method check
        Person person1 = new Person("Elizabeth", "Elizabeth@gmail.com");
        Person person2 = new Person("Elizabeth", "Elizabeth@gmail.com");
        if(!person1.equals(person1))
        {
            System.err.println("FAIL: Returns false since the objects are different");
        }
        if (person1.equals(null))
        {
            System.err.println("FAIL: Returns false for comparing to a null object"); 
        }
        if (person1.equals("FalseStringTest"))
        {
            System.err.println("FAIL: Returns false for comparing to a String object"); 
        }
        if (!person1.equals(person2))
        {
            System.err.println("FAIL: Returns false for name and email are not identical"); 
        }

    }

}
