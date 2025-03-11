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
        //double rad1, double rad2,  double dis, double LR, double iter
        Scanner kb = new Scanner(System.in);
        System.out.println("radius 1 (smaller 20 ~ 30): ");
        double rad1 = kb.nextDouble();
        System.out.println("radius 2 (bigger 40 ~ 50): ");
        double rad2 = kb.nextDouble();
        System.out.println("distance 1 (-10 ~ 20): ");
        double dis = kb.nextDouble();
        System.out.println("learning rate (0.01 ~ 0.05): ");
        double LR = kb.nextDouble();
        doughnut dn = new doughnut(rad1, rad2, dis, LR, 7);
        dn.start();
    }
}
