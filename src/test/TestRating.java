package test;
import people.Person;
import rating.Rating;
import rating.Comment;

public class TestRating
{
    public static void main(String[] args)
    {
        // TEST VECTOR #1: getStars and toString check
        Rating rating;
        String[] starSet = {"\u2605\u2606\u2606\u2606\u2606", 
        "\u2605\u2605\u2606\u2606\u2606",
        "\u2605\u2605\u2605\u2606\u2606",
        "\u2605\u2605\u2605\u2605\u2606",
        "\u2605\u2605\u2605\u2605\u2605"};
        
        for(int i = 1; i <= 5; i++)
        {
            
            rating = new Rating(i, null);
            if(rating.getStars() != i)
            {
                System.err.println("FAIL: Returns the incorrect number from the getStars method");
            }
            if(!rating.toString().equals(starSet[i-1]))
            {
                System.err.println("FAIL: Returns the incorrect string from the toString method");
            }
        }

        // TEST VECTOR #2: getReview check
        Person PersonTest = new Person("Person", "Person@gmail.com");
        
        Comment review = new Comment("Outstanding Service! *(getReview check)*", PersonTest, null);
        rating = new Rating(5, review);
        if(!rating.getReview().equals(review))
        {
            System.err.println("FAIL: review was not returned by the getReview method");
        }
        
    }
}