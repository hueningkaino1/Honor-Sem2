import java.util.Map;
import java.util.*;

/**
 * Write a description of class perceptron here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class perceptron
{
    private double learnRate;
    private double weight0;
    private double weight1;
    private double threshW;
    private double threshX = -1.0;
    private int iteration;
    private boolean error;
    private int[][] xSamples = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 }};
    private boolean[] run;
    private int[] xInt;
    private int target;
    private HashMap<String, Double> drawAns = new HashMap <String, Double>();
    //error = 0 false, error = 1 true
    //and
    private static int[] tar = {0, 0, 0, 1};
    //nand
    //private static int[] tar = {1, 1, 1, 0};
    //or
    //private static int[] tar = {0, 1, 1, 1};
    //nor
    //private static int[] tar = {1, 0, 0, 0};
    private graphics gs = new graphics();

    /**
     * Constructor for objects of class perceptron
     */
    public perceptron(double LR, int interTimes)
    {
        learnRate = LR;
        threshW = Math.random()-0.5;
        weight0 = Math.random()-0.5;
        weight1 = Math.random()-0.5;
        iteration = interTimes;
        error = false;
        target = -1;
        xInt = new int[2];
        run = new boolean[]{false, false, false, false};
    }

    
    public void start(){
        gs.draw();
        pertron();
        gs.drawFinal();
    }
    
    public void deltaW(double W0, double W1, double tW, double output, double X0, double X1){
        double dWeight0 = learnRate*(target-output)*X0;
        weight0 = W0 + dWeight0;
        double dWeight1 = learnRate*(target-output)*X1;
        weight1 = W1 + dWeight1;
        double dThreshW = learnRate*(target-output)*threshX;
        threshW = threshW + dThreshW;
    }
    
    public void pertron(){
        double output = -1;
        double val = 0;
        for(int i = 0; i<iteration; i++){
            for(int j = 0; j<4; j++){
                target = tar[j];
                xInt = xSamples[j];
                val = weight1* xInt[1] + weight0*xInt[0] + threshW*threshX;
                System.out.println(j);
                if (val > 0){
                    output = 1;
                }
                else{
                    output = 0;
                }
                if (output != target){
                    deltaW(weight0, weight1, threshW, output, xInt[0], xInt[1]);
                    error = true;
                    System.out.println("Output: " + output + " Target: " + target);
                    System.out.println("Val: " + val);
                    System.out.println("Error: " + error);
                    System.out.println("Weight0: " + weight0 + " Weight1: " + weight1 + " ThreshW: " + threshW);
                    System.out.println();
                }else{
                    error = false;
                    run[j] = true;
                    System.out.println("Output: " + output + " Target: " + target);
                    System.out.println("Val: " + val);
                    System.out.println("Error: " + error);
                    System.out.println("Weight0: " + weight0 + " Weight1: " + weight1 + " ThreshW: " + threshW);
                    System.out.println();
                    
                }
                gs.setInfo(threshW, weight0, weight1, output, j);
                gs.drawLine();
                //System.out.println(drawAns);
                //drawAns.clear();
            }
        }
        error = true;
    }
}
