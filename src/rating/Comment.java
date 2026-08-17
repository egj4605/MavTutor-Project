package rating;
import java.util.ArrayList;
import people.Person;

public class Comment
{
    private String text;
    private Person author;
    private Comment inReplyTo;
    private ArrayList<Comment> replies = new ArrayList<>();

    public Comment(String text, Person author, Comment inReplyTo)
    {
        if(text == null || author == null || text.isEmpty()) 
        {
            throw new IllegalArgumentException("FAIL: Text or author is null or Empty.");
        }
        this.text = text;
        this.author = author;
        this.inReplyTo = inReplyTo;     
    }
    public void addReply(String text, Person author)
    {
        if(text == null || author == null || text.isEmpty())
        {
            throw new IllegalArgumentException("FAIL: Text or author is null or empty");
        }
        else
        {
            Comment comment = new Comment(text, author, this);
            this.replies.add(comment);
        }
    }
    public int numReplies()
    {
        return this.replies.size();    
    }
    public Comment getReply(int index)
    {
        return this.replies.get(index); 
    }
    public Comment getInReplyTo()
    {
        return this.inReplyTo;
    }
    @Override
    public String toString() 
    {
        StringBuilder s = new StringBuilder("Comment by " + this.author);
        if(this.inReplyTo != null)
        {
            s.append(" in reply to " + this.inReplyTo.author);
        }
        if (this.replies != null && !this.replies.isEmpty())
        {
            s.append("\nReplies: ");
        
            for(int index = 0; index < this.replies.size(); index++) 
            {
                s.append(" (" + index + ") ").append(this.replies.get(index).author.getName()).append("     ");
            }
        }
        s.append("\n" + text);
        return s.toString();
    }
}