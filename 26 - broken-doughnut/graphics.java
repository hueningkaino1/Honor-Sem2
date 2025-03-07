
/**
 * Write a description of class graphics here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class graphics
{
    // instance variables - replace the example below with your own
    double x;
    double y;
    String tag;
    
    /**
     * Constructor for objects of class graphics
     */
    public graphics()
    {

    }
    
    public void draw(){
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(600,600);
        StdDraw.setXscale(-50,50);
        StdDraw.setYscale(-50,50);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
    }
    
    public void drawPoints(){
        StdDraw.setPenRadius(0.001);
        if (tag.equals("top")){
            StdDraw.setPenColor(StdDraw.RED);
        }else if (tag.equals("bottom")){
            StdDraw.setPenColor(StdDraw.BLUE);
        }else if (tag.equals("neutral")){
            StdDraw.setPenColor(StdDraw.BLACK);
        }
        StdDraw.filledCircle(x , y, 0.03);
        StdDraw.show();
    }
    
    public void setInfo(double [] xy, String tags){
        x = xy[0];
        y = xy[1];
        tag = tags;
    }
}
