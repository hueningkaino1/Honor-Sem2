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
                StdDraw.filledCircle(0, 0, 0.03); //0
            }
            else if (sample == 1){
                StdDraw.filledCircle(1, 0, 0.03); //1
            }
            else if (sample == 2){
                StdDraw.filledCircle(0, 1, 0.03); //2
            }
            else if (sample == 3){
                StdDraw.filledCircle(1, 1, 0.03); //3
            }
        }
        else if (output == 1){
            StdDraw.setPenColor(StdDraw.GREEN);
            if(sample == 0){
                StdDraw.filledCircle(0, 0, 0.03); //0
            }
            else if (sample == 1){
                StdDraw.filledCircle(1, 0, 0.03); //1
            }
            else if (sample == 2){
                StdDraw.filledCircle(0, 1, 0.03); //2
            }
            else if (sample == 3){
                StdDraw.filledCircle(1, 1, 0.03); //3
            }
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        double x1 = -0.5;
        double x2 = 2;
        double y1 = (threshW-weight0*x1)/weight1;
        double y2 = (threshW-weight0*x2)/weight1;
        double scale = 1;
        StdDraw.setPenRadius(0.002);
        StdDraw.line(-0.5, scale*y1, 2, y2*scale);
        StdDraw.show();
        StdDraw.pause(150);
    }
    
    public void drawFinal(){
        //0 = drawAns.get("Weight0")*x+drawAns.get("Weight1")*y+drawAns.get("threshW")
        StdDraw.setPenColor(StdDraw.CYAN);
        double x1 = -0.5;
        double x2 = 2;
        double y1 = (threshW-weight0*x1)/weight1;
        double y2 = (threshW-weight0*x2)/weight1;
        double scale = 1;
        StdDraw.setPenRadius(0.0045);
        StdDraw.line(-0.5, scale*y1, 2, scale*y2);
        StdDraw.show();
        StdDraw.pause(150);
    }
    
    public void draw(){
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(600,600);
        StdDraw.setXscale(-0.5,2);
        StdDraw.setYscale(-0.5,2);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(0, 0, 0, 1.25);
        StdDraw.line(0, 0, 1.25, 0);
        StdDraw.setPenRadius(0.001);
        StdDraw.filledCircle(0, 0, 0.03); //0
        StdDraw.filledCircle(1, 0, 0.03); //1
        StdDraw.filledCircle(0, 1, 0.03); //2
        StdDraw.filledCircle(1, 1, 0.03); //3
    }
    

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setInfo(double thW, double W0, double W1, double out, int j)
    {
        //gs.setInfo(threshW, weight0, weight1, output, j);
        threshW = thW;
        weight0 = W0;
        weight1 = W1;
        output = out;
        sample = j;
    }
}
