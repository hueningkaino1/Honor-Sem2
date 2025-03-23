import java.util.concurrent.TimeUnit;

/**
 * Write a description of class Grid here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Grid
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private int z;
    private int w;

    public Grid()
    {
       x = Agent.getInstance().locX();
       y = Agent.getInstance().locY();
       z = Agent.getInstance().goldX();
       w = Agent.getInstance().goldY();
    }

    public void maze(){
        int row =0;
        int col =0;
        int [][] maze = Agent.getInstance().getMaze(); 
        for (int number [] : maze) {
            for (int num: number){
                if (num == 1){
                    StdDraw.setPenRadius(0.0005);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    //StdDraw.filledSquare(row+2,col+4,0.5);
                    StdDraw.filledSquare(row,col,0.5);
                }
                else if (num ==0){
                    StdDraw.setPenRadius(0.0005);
                    StdDraw.setPenColor(StdDraw.WHITE);
                    //StdDraw.filledSquare(row+2,col+4,0.5);
                    StdDraw.filledSquare(row,col, 0.5);
                    //StdDraw.square(row+2,col+3,0.5);
                }
                row++;
            }
            row = 0;
            col++;
        }
        StdDraw.show();
    }
    
    public void objMov(){
        x = Agent.getInstance().locX();
        y = Agent.getInstance().locY();
        StdDraw.setPenColor(StdDraw.CYAN);
        //StdDraw.filledSquare(x+2, y+3, 0.5);
        StdDraw.filledSquare(x, y, 0.5);
        StdDraw.show();
        //System.out.println(x +" "+ y + " right:");
    }
    
    public void showGold(){
        StdDraw.setPenColor(StdDraw.MAGENTA);
        //StdDraw.filledSquare(w+0.8, z-0.2, 0.5);
        StdDraw.filledSquare(w, z, 0.5);
        StdDraw.show();
    }
    
    

    // code for your Gridworld
    public void start(){
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize();
        StdDraw.setXscale(-3,32);
        StdDraw.setYscale(-3,32);
        while (true){
            maze();
            objMov();
            showGold();
            try{
                TimeUnit.MILLISECONDS.sleep(250);
            }
            catch (InterruptedException ie){
                ie.printStackTrace();
            }
            Agent.getInstance().sense();
            if(Agent.getInstance().decide()){
                break;
            }
            Agent.getInstance().act();
        }
    }
}
