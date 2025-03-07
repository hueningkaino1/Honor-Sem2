import java.util.ArrayList;

/**
 * Write a description of class doughtnut here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class doughnut
{
    // instance variables - replace the example below with your own
    private double r1;
    private double r2;
    private double weight;
    private double distance;
    private String tag;
    private ArrayList<double []> trainSet = new ArrayList<double []>(2);
    private ArrayList<double []> neutralSet = new ArrayList<double []>(2);
    private graphics gs = new graphics();

    /**
     * Constructor for objects of class doughtnut
     */
    public doughnut(double rad1, double rad2, double w, double dis)
    {
        r1 = rad1;
        r2 = rad2;
        weight = w;
        distance = dis;
    }
    
    public void setTPoints(){
         for (int i = 0; i < 1000; i++){
            double r = Math.random()*(r2-r1+1)+r1;
            double theta = Math.random()*((2*Math.PI+1));
            double[] coordinates = {r*Math.cos(theta), r*Math.sin(theta)};
            trainSet.add(coordinates);
            if (0 < theta && theta <= Math.PI){
                tag = "top";
            } else if (Math.PI < theta && theta <= 2*Math.PI){
                tag = "bottom";
            }else{
                tag = "neutral";
            }
            System.out.println("x: " + coordinates[0] + " , " + "y: " + coordinates[1]);
            gs.setInfo(coordinates, tag);
            gs.drawPoints();
        }
    }
    
    public void start(){
        setTPoints();
    }

}
