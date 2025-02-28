import java.util.*;

/**
 * Write a description of class graphics here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class graphics
{
    // instance variables - replace the example below with your own
    private HashMap<String, Double> drawAns = new HashMap <String, Double>();
    private double weight0;
    private double weight1;
    private double threshW;
    private double output;
    private double sample;

    /**
     * Constructor for objects of class graphics
     */
    public graphics()
    {
        
    }
    
    public void drawLine(){
        //0 = drawAns.get("Weight0")*x+drawAns.get("Weight1")*y+drawAns.get("threshW")
        //y = (-drawAns.get("threshW")-drawAns.get("Weight0")*x)/drawAns.get("Weight1")
        if (output == 0){
            StdDraw.setPenColor(StdDraw.RED);
            if(sample == 0){
                StdDraw.filledCircle(50, 50, 7); //0
            }
            else if (sample == 1){
                StdDraw.filledCircle(500, 50, 7); //1
            }
            else if (sample == 2){
                StdDraw.filledCircle(50, 500, 7); //2
            }
            else if (sample == 3){
                StdDraw.filledCircle(500, 500, 7); //3
            }
        }
        else if (output == 1){
            StdDraw.setPenColor(StdDraw.GREEN);
            if(sample == 0){
                StdDraw.filledCircle(50, 50, 7); //0
            }
            else if (sample == 1){
                StdDraw.filledCircle(500, 50, 7); //1
            }
            else if (sample == 2){
                StdDraw.filledCircle(50, 500, 7); //2
            }
            else if (sample == 3){
                StdDraw.filledCircle(500, 500, 7); //3
            }
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        int x1 = -5;
        int x2 = 5;
        double y1 = (-threshW-weight0*x1)/weight1;
        double y2 = (-threshW-weight0*x2)/weight1;
        double scale = 1;
        StdDraw.setPenRadius(0.002);
        StdDraw.line(0, scale*y1, 600, scale*y2);
        StdDraw.show();
    }
    
    public void draw(){
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(600,600);
        StdDraw.setXscale(0,600);
        StdDraw.setYscale(0,600);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.line(50, 50, 50, 550);
        StdDraw.line(50, 50, 550, 50);
        StdDraw.setPenRadius(0.005);
        StdDraw.filledCircle(50, 50, 7); //0
        StdDraw.filledCircle(500, 50, 7); //1
        StdDraw.filledCircle(50, 500, 7); //2
        StdDraw.filledCircle(500, 500, 7); //3
    }
    
    public void drawFinal(){
        StdDraw.setPenColor(StdDraw.BLUE);
        int x1 = -5;
        int x2 = 5;
        double y1 = (-threshW-weight0*x1)/weight1;
        double y2 = (-threshW-weight0*x2)/weight1;
        double scale = 1;
        StdDraw.setPenRadius(0.002);
        StdDraw.line(0, scale*y1, 600, scale*y2);
        StdDraw.show();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setInfo(double thW, double W0, double W1, double out, int j)
    {
        threshW = thW;
        weight0 = W0;
        weight1 = W1;
        output = out;
        sample = j;
    }
}
