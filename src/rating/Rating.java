package rating;
public class Rating
{
    private int stars;
    private Comment review;

    public Rating(int stars, Comment review)
    {
        if(stars < 1 || stars > 5)
        {
            throw new IllegalArgumentException("FAIL: stars is not between 1 and 5");
        }
        this.stars = stars;
        this.review = review;
    }
    public int getStars()
    {
        return this.stars;
    }
    public Comment getReview()
    {
        return this.review;
    }
    @Override
    public String toString() 
    {
        char filledStar = '\u2605';
        char unfilledStar = '\u2606';
        int totalStars = 5;
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < this.stars; i++)
        {
            s.append(filledStar);
        }
        for(int i = 0; i < (totalStars - this.stars); i++)
        {
            s.append(unfilledStar);
        }
        return s.toString();

    }
}