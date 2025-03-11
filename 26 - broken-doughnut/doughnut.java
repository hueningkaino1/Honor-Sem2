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
    private double width;
    private double distance;
    private String tag;
    private double weight0;
    private double weight1;
    private double threshW;
    private double threshX = -1.0;
    private double learnRate;
    private double target;
    private double iteration;
    private int numPoints;
    private ArrayList<SetPoint> trainSet = new ArrayList<SetPoint>();
    private ArrayList<SetPoint> neutralSet = new ArrayList<SetPoint>();
    private graphics gs = new graphics();

    /**
     * Constructor for objects of class doughtnut
     */
    public doughnut(double rad1, double rad2,  double dis, double LR, double iter)
    {
        gs.draw();
        r1 = rad1;
        r2 = rad2;
        width = rad2-rad1;
        distance = dis;
        threshW = Math.random()-0.5;
        weight0 = Math.random()-0.5;
        weight1 = Math.random()-0.5;
        learnRate = LR;
        iteration = iter;
        numPoints = 1000;
    }
    
    public void setTPoints(){
         for (int i = 0; i < numPoints; i++){
            double r = Math.random()*(r2-r1)+r1;
            double theta = Math.random()*((2*Math.PI));
            double[] coordinates = {r*Math.cos(theta), r*Math.sin(theta)};
            if (0 <= theta && theta <= Math.PI){
                tag = "top";
                coordinates[1] = coordinates[1]+(distance/2);
                coordinates[0] = coordinates[0]-(r2/2)+(width/4);
            } else if (Math.PI <= theta && theta <= 2*Math.PI){
                tag = "bottom";
                coordinates[1] = coordinates[1]-(distance/2);
                coordinates[0] = coordinates[0]+(r2/2)-(width/4);
            }
            trainSet.add(new SetPoint(coordinates[0], coordinates[1], tag));
            gs.setInfo(coordinates, tag);
            gs.drawPoints();
        }
        printList(trainSet);
    }
    
    public void setNeutralPoints(){
         for (int i = 0; i < numPoints; i++){
            double r = Math.random()*(r2-r1)+r1;
            double theta = Math.random()*((2*Math.PI));
            double[] coordinates = {r*Math.cos(theta), r*Math.sin(theta)};
            if (0 <= theta && theta <= Math.PI){
                coordinates[1] = coordinates[1]+(distance/2);
                coordinates[0] = coordinates[0]-(r2/2)+(width/4);
            } else if (Math.PI <= theta && theta <= 2*Math.PI){
                coordinates[1] = coordinates[1]-(distance/2);
                coordinates[0] = coordinates[0]+(r2/2)-(width/4);
            }
            tag = "neutral";
            neutralSet.add(new SetPoint(coordinates[0], coordinates[1], tag));
            gs.setInfo(coordinates, tag);
            gs.drawPoints();
        }
        //printList(trainSet);
    }
    
    public void points (ArrayList<SetPoint> set){
        for(SetPoint i: set){
            double[] coordinates = {i.getX(), i.getY()};
            gs.setInfo(coordinates, i.getTag());
        }
    }
    
    public void printList(ArrayList<SetPoint> set){
        int count = 0;
        for(SetPoint i: set){
           count++;
           System.out.println("x: " + i.getX() + " , " + "y: " + i.getY() + " , count " + count);
        }
    }
    
    public void start(){
        setTPoints();
        perceptron();
        StdDraw.clear();
        gs.drawFinal();
        setNeutralPoints();
        setTag();
    }
    
    public void setTag(){
        //ax+by+c=0
        for(SetPoint i: neutralSet){
            double[] coor = {i.getX(), i.getY()};
            if((weight0*coor[0]+weight1*coor[1]+threshW)>0){
                tag = "top";
            }
            else if ((weight0*coor[0]+weight1*coor[1]+threshW)<0){
                tag = "bottom";
            }
            i.setTag(tag);
            gs.setInfo(coor, tag);
            gs.drawPoints();
        }
    }

    public void deltaW(double W0, double W1, double tW, double output, double X0, double X1){
        //deltaW(weight0, weight1, threshW, output, xInt[0], xInt[1]);
        double dWeight0 = learnRate*(target-output)*X0;
        weight0 = W0 + dWeight0;
        double dWeight1 = learnRate*(target-output)*X1;
        weight1 = W1 + dWeight1;
        double dThreshW = learnRate*(target-output)*threshX;
        threshW = threshW + dThreshW;
    }
    
    public void perceptron(){
        double output = -1;
        double val = 0;
        for (int i = 0; i<iteration; i++){
            for(int j = 0; j<numPoints; j++){
                val = weight0 * trainSet.get(j).getX() + weight1*trainSet.get(j).getY() + threshW*threshX;
                if (val > 0){
                    output = 1;
                }
                else if (val < 0 ){
                    output = 0;
                }
                if (trainSet.get(j).getTag().equals("top")){
                    target = 1;
                }
                else if (trainSet.get(j).getTag().equals("bottom")){
                    target = 0;
                }
                if (output != target){
                    deltaW(weight0, weight1, threshW, output, trainSet.get(j).getX(), trainSet.get(j).getY());
                    System.out.println("Output: " + output + " Target: " + target);
                    System.out.println("Val: " + val);
                    System.out.println("Weight0: " + weight0 + " Weight1: " + weight1 + " ThreshW: " + threshW);
                    System.out.println();
                        
                    }else{
                    System.out.println("Output: " + output + " Target: " + target);
                    System.out.println("Val: " + val);
                    System.out.println("Weight0: " + weight0 + " Weight1: " + weight1 + " ThreshW: " + threshW);
                    System.out.println("Reach target");
                    System.out.println();
                        
                }
                gs.setIn(threshW, weight0, weight1, output);
                gs.drawLine();
            }
        }
        gs.drawFinal();
    }
}
