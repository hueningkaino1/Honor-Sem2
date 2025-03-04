import java.util.Scanner;

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
        Scanner kb = new Scanner(System.in);
        System.out.println("Gate type: and, nand, or, nor");
        String tarType = kb.nextLine().toLowerCase();
        System.out.println("Enter learning rate 0.03 ~ 0.08");
        double learnRate = kb.nextDouble();
        System.out.println("Enter iteration number 50 ~ 200");
        int iteration = kb.nextInt();
        
        perceptron per = new perceptron(learnRate, iteration, tarType);
        per.start();
    }
}
