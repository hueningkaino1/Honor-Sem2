
/**
 * Write a description of class SetPoint here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SetPoint
{
    // instance variables - replace the example below with your own
    private double x;
    private double y;
    private String tag;

    /**
     * Constructor for objects of class SetPoint
     */
    public SetPoint(double xx, double yy, String tags)
    {
        x = xx;
        y = yy;
        tag = tags;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public String getTag()
    {
        return tag;
    }
    
    public void setTag(String s)
    {
        tag = s;
    }
    
}
