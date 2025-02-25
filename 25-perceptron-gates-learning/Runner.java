
/**
 * Write a description of class Runner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Runner
{
   
    
    public static void main (String [] args) {
        //double LR, int interTimes, int T, int[] xSample
        perceptron per = new perceptron(0.05, 25);
        per.start();
    }
}
