import java.awt.Color;

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
    private double weight0;
    private double weight1;
    private double threshW;
    private double output;
    
    /**
     * Constructor for objects of class graphics
     */
    public graphics()
    {

    }
    
    public void draw(){
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(600,600);
        StdDraw.setXscale(-75,75);
        StdDraw.setYscale(-70,70);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.filledCircle(0 , 0, 1);
    }
    
    public void drawPoints(){
        StdDraw.setPenRadius(0.005);
        if (tag.equals("top")){
            StdDraw.setPenColor(StdDraw.RED);
        }else if (tag.equals("bottom")){
            StdDraw.setPenColor(StdDraw.BLUE);
        }else if (tag.equals("neutral")){
            StdDraw.setPenColor(StdDraw.BLACK);
        }
        StdDraw.filledCircle(x , y, 0.5);
        StdDraw.pause(5);
        StdDraw.show();
    }
    
    public void drawLine(){
        StdDraw.setPenColor(new Color(0, 0, 0, 90));
        double x1 = -75;
        double x2 = 75;
        double y1 = (threshW-weight0*x1)/weight1;
        double y2 = (threshW-weight0*x2)/weight1;
        double scale = 1;
        StdDraw.setPenRadius(0.0005);
        StdDraw.line(x1, scale*y1, x2, y2*scale);
        StdDraw.show();
    }
    
    public void drawFinal(){
        //0 = drawAns.get("Weight0")*x+drawAns.get("Weight1")*y+drawAns.get("threshW")
        StdDraw.setPenColor(new Color(194, 151, 252));
        double x1 = -75;
        double x2 = 75;
        double y1 = (threshW-weight0*x1)/weight1;
        double y2 = (threshW-weight0*x2)/weight1;
        double scale = 1;
        StdDraw.setPenRadius(0.003);
        StdDraw.line(x1, scale*y1, x2, scale*y2);
        StdDraw.show();
        StdDraw.pause(150);
    }
    
    public void setInfo(double [] xy, String tags){
        x = xy[0];
        y = xy[1];
        tag = tags;
    }
    
    public void setIn(double thW, double W0, double W1, double out)
    {
        //gs.setInfo(threshW, weight0, weight1, output, j);
        threshW = thW;
        weight0 = W0;
        weight1 = W1;
        output = out;
    }
}
