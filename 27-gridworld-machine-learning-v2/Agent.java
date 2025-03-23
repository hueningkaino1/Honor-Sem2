
/**
 * Write a description of class Agent here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Agent
{
    private static Agent m_instance;
    private int[][]maze;
    private boolean isWall;
    private int direction;
    private int right;
    private int [] loc;
    private int x;
    private int y;
    private int [] gold;
    private int gx;
    private int gy;
    private boolean atGoal;
    private static int[] tar = {0, 1, 1, 1};
    private int[][] orSamples = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 }};
    private int[][] dirSamples = {};
    
    private double weight0;
    private double weight1;
    private double threshW;
    private double threshX = -1.0;
    private double learnRate;
    private double target;
    
    public Agent()
    {
        maze = new int [][] {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        
        isWall = false;
        x = 26;
        y = 28;
        loc = new int [] {x,y};
        //1 - grid right, 2 - grid up, 3 - grid left, 4 grid down
        
        //priority order: up, right, down, left
        right = 4;
        gx = 1;
        gy = 1;
        gold = new int[] {gx, gy};
        atGoal = false;
        
        //perceptron
        threshW = Math.random()-0.5;
        weight0 = Math.random()-0.5;
        weight1 = Math.random()-0.5;
        learnRate = 0.03;
        
    }

    public static Agent getInstance(){
        if(m_instance==null){
            m_instance = new Agent();
        }
        
        return m_instance;
    }
    
    public int [][] getMaze(){
        return maze;
    }
    
    public void setLoc(int a, int b){
        loc[0] = a;
        loc[1] = b;
    }
    
    public int locX(){
        return loc[0];
    }
    
    public int locY(){
        return loc[1];
    }
    
    
    public void setG(int a, int b){
        gold[0] = a;
        gold[1] = b;
    }
    
    public int goldX(){
        return gold[0];
    }
    
    public int goldY(){
        return gold[1];
    }
    
    public boolean getGoal(){
        return atGoal;
    }
    
    public int[][] getOrData(){
        int x = loc[1];
        int y = loc[0];
        
        int[][] orData = {{maze[x][y+1], maze[x+1][y+1]} , {maze[x+1][y], maze[x+1][y-1]} , {maze[x][y-1], maze[x-1][y-1]} , {maze[x-1][y], maze[x-1][y+1]}};
        
        return orData;
        
    }
    
    public int[][] setSample(){
        
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
    
    public void perOR(){
        //surrounding Data
        int[][] surData = getOrData();
        double output = -1;
        double val = 0;
        for (int i = 0; i<5; i++){
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
    
    
    public void perDIR(){
        
    }
    
    /*public void perUP(){
        
    }
    
    public void perRIGHT(){
        
    }
    
    public void perDOWN(){
        
    }
    
    public void perLEFT(){
        
    }*/
    
    public void sense() {
        int x = loc[1];
        int y = loc[0];
        
        //1000;0111;0100;1011;0010;1101;0001;1110;1100;0011;0110;1001;1010;0101;0000;1111
        //u0100001001000110
        //r1001000000011000
        //d0010010010000000
        //l0000100100100000
        
        
        // 1 - grid right, 2 - grid up, 3 - grid left, 4 - grid down
        if (right == 1) {
            // Check if x+1 is within bounds
            if (x + 1 < maze.length && maze[x + 1][y] == 0) {
                right = 4;
            }
            // Check if x+1 and y+1 are within bounds for further checks
            else if (x + 1 < maze.length && y + 1 < maze[0].length && maze[x + 1][y] == 1 && maze[x][y + 1] == 1) {
                right = 2;
            }
            else if (y + 1 < maze[0].length && maze[x][y + 1] == 0) {
                right = 1;
            }
        }
        else if (right == 2) {
            // Check if y+1 is within bounds
            if (y + 1 < maze[0].length && maze[x][y + 1] == 0) {
                right = 1;
            }
            // Check if x-1 and y+1 are within bounds
            else if (y + 1 < maze[0].length && x - 1 >= 0 && maze[x][y + 1] == 1 && maze[x - 1][y] == 1) {
                right = 3;
            }
            else if (x - 1 >= 0 && maze[x - 1][y] == 0) {
                right = 2;
            }
        }
        else if (right == 3) {
            // Check if x-1 is within bounds
            if (x - 1 >= 0 && maze[x - 1][y] == 0) {
                right = 2;
            }
            // Check if x-1 and y-1 are within bounds
            else if (x - 1 >= 0 && y - 1 >= 0 && maze[x - 1][y] == 1 && maze[x][y - 1] == 1) {
                right = 4;
            }
            else if (y - 1 >= 0 && maze[x][y - 1] == 0) {
                right = 3;
            }
        }
        else if (right == 4) {
            // Check if y-1 is within bounds
            if (y - 1 >= 0 && maze[x][y - 1] == 0) {
                right = 3;
            }
            // Check if x+1 and y-1 are within bounds
            else if (y - 1 >= 0 && x + 1 < maze.length && maze[x][y - 1] == 1 && maze[x + 1][y] == 1) {
                right = 1;
            }
            else if (x + 1 < maze.length && maze[x + 1][y] == 0) {
                right = 4;
            }
        }
    }
    
    public boolean decide(){
        if (loc[1]==gold[0] && loc[0]==gold[1]){
            atGoal = true;
        }
        else{
            atGoal = false;
        }
        return atGoal;
    }
    
    public void act(){
        //1 - grid right, 2 - grid up, 3 - grid left, 4 grid down
        if (right == 1){
            //if (grid[loc[0]][loc[1]+1]==0){
                loc[0] = loc[0]+1;
            //}
        }
        else if (right == 2){
            //if (grid[loc[0]-1][loc[1]]==0){
                loc[1] = loc[1]-1;
            //}
        }
        else if (right == 3){
            //if (grid[loc[0]][loc[1]-1]==0){
                loc[0] = loc[0]-1;
            //}
        }
        else if (right == 4){
            //if (grid[loc[0]+1][loc[1]]==0){
                loc[1] = loc[1]+1;
            //}
        }
    }
}
