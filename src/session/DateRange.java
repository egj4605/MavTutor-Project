package session;
import java.io.PrintStream;
import java.util.Scanner;
/**
 * Class DateRange tracks a period of time for a specified date
 * 
 * @author              Evan Jorge-Munoz
 * @version             1.0
 * @since               1.0
 * @license.agreement   CC BY-SA International 4.0
 */
public class DateRange 
{
    private String date;
    private String startTime;
    private String endTime;
    /**
     * Assigns its parameters to each corresponding field
     * 
     * @param date          the specified year, month and day
     * @param startTime     the number in military time of the beginning time of the specified date
     * @param endTime       the number in military time of the ending time of the specified date
     * @since               1.0
     */
    public DateRange(String date, String startTime, String endTime)
    {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    /**
     * Calculates field endTime
     * 
     * @param date          the specified year, month and day
     * @param startTime     the number in military time of the beginning time of the specified date
     * @param duration      the number in military time of the ending time of the specified date
     * @since               1.0
     */
    public DateRange(String date, String startTime, long duration)
    {
        this.date = date;
        this.startTime = startTime;
        String numHr = startTime.substring(0, 2);   
        String numMin = startTime.substring(3, 5); 
        int startHr = Integer.parseInt(numHr);
        int startMin = Integer.parseInt(numMin);
        long totalStartMins = (startHr * 60) + startMin; 

        long totalEndMins = totalStartMins + duration;

        long endHr = (totalEndMins / 60) % 24;
        long endMin = totalEndMins % 60;

        this.endTime = String.format("%02d:%02d", endHr, endMin);
    }
    public DateRange(Scanner in)
    {
        this.date = in.nextLine();
        this.startTime = in.nextLine();
        this.endTime = in.nextLine();
    }
    public void save(PrintStream out)
    {
        out.println(date);
        out.println(startTime);
        out.println(endTime);
    }
    /**
     * Calculates the duration by finding the difference in minutes between startTime and endTime
     * 
     * @return              the duration (difference in minutes between startTime and endTime)
     * @since               1.0
     */
    public long duration()
    {
        
        int startHr = Integer.parseInt(this.startTime.substring(0, 2));
        int startMin = Integer.parseInt(this.startTime.substring(3, 5));
        long totalStartMins = (startHr * 60) + startMin; 

        int endHr = Integer.parseInt(this.endTime.substring(0, 2));
        int endMin = Integer.parseInt(this.endTime.substring(3, 5));
        long totalEndMins = (endHr * 60) + endMin; 

        return totalEndMins - totalStartMins;

    }
    /**
     * To return a string of the 3 fields (date, startTime, endtime) AND the duration
     * 
     * @return              a string of the 3 fields (date, startTime, endtime) AND the duration
     * @since               1.0
     */
    @Override
    public String toString()
    {
        return this.date + " " + this.startTime + " - " + this.endTime + " (" + duration() + " minutes" + ")";
    }
}
