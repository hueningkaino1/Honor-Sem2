
/**
 * Write a description of class tryout here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class tryout
{
    // instance variables - replace the example below with your own
    private int x;
    private int[][] xSamples = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 }};

    /**
     * Constructor for objects of class tryout
     */
    public tryout()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void sampleMethod(int y)
    {
        
    }
    
    public static void main (String [] args) {
       double weight0 = 0;
       double weight1 = 0;
       for (int i = 0; i<10;i++){
           weight0 = Math.random()-0.5;
           weight1 = Math.random()-0.5;
           System.out.println(weight0);
           System.out.println(weight1);
           System.out.println();
       }
       //Perceptron per = new Perceptron();
       //per.start();
    }
    
    public double pertron(){
        double output = -1;
        double val = 0;
        for(int i = 0; i<iteration; i++){
            for(int j = 0; j<4; j++){
                xInt = xSamples[j];
                val = weight1* xInt[1] + weight0*xInt[0] + threshW*threshX;
                if (val > 0){
                    output = 1;
                }
                else{
                    output = 0;
                }
                if (output != target){
                    deltaW(weight0, weight1, threshW, output, xInt[0], xInt[1]);
                    error = true;
                }else{
                    error = false;
                    System.out.println("Output: " + output + " Target: " + target);
                    System.out.println("Val: " + val);
                    System.out.println("Error: " + error);
                    System.out.println("Weight0: " + weight0 + " Weight1: " + weight1 + " ThreshW: " + threshW);
                    System.out.println();
                    drawAns.put("Output", output);
                    drawAns.put("Weight0", weight0);
                    drawAns.put("Weight1", weight1);
                    drawAns.put("Error", 0);
                    return output;
                }
                System.out.println("Output: " + output + " Target: " + target);
                System.out.println("Val: " + val);
                System.out.println("Error: " + error);
                System.out.println("Weight0: " + weight0 + " Weight1: " + weight1 + " ThreshW: " + threshW);
                System.out.println();
                drawAns.put("Output", output);
                drawAns.put("Weight0", weight0);
                drawAns.put("Weight1", weight1);
                drawAns.put("Error", 1);
            
            }
            error = true;
            return output;
        }
    }
}
