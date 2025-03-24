
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
    
    //perceptron share
    private double learnRate;
    private double threshX = -1.0; 
    
    // perceptron OR
    private static int[] tar = {0, 1, 1, 1};
    private int[][] orSamples = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 }};
    private int[] orResults = new int [4];
    private double weight0;
    private double weight1;
    private double threshW;
    private double target;
    private boolean[] run;
    
    //1000;0111;0100;1011;
    //0010;1101;0001;1110;
    //1100;0011;0110;1001;
    //1010;0101;0000;1111
    //u: 0100 0010 0100 0110
    //r: 1001 0000 0001 1000
    //d: 0010 0100 1000 0000
    //l: 0000 1001 0010 0000
    
    //perceptron DIRECTION
    private double weight0_dir;
    private double threshW_dir;
    private double target_dir;
    private int[] dirResults = new int [4];
    private static int[][] tar_dir = {{0,1,0,0}, {1,0,0,0}, {0,0,1,0}, {0,1,0,0}, 
                                      {0,0,0,1}, {0,0,1,0}, {1,0,0,0}, {0,0,0,1}, 
                                      {0,0,1,0}, {1,0,0,0}, {0,0,0,1}, {0,1,0,0},
                                      {0,1,0,0}, {1,0,0,0}, {1,0,0,0}, {0,0,0,0}};
                                  
    private int[][] dirSamples = {{1,0,0,0}, {0,1,1,1}, {0,1,0,0}, {1,0,1,1}, 
                                  {0,0,1,0}, {1,1,0,1}, {0,0,0,1}, {1,1,1,0}, 
                                  {1,1,0,0}, {0,0,1,1}, {0,1,1,0}, {1,0,0,1},
                                  {1,0,1,0}, {0,1,0,1}, {0,0,0,0}, {1,1,1,1}};
    
    
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
        
        // or perceptron
        threshW = Math.random()-0.5;
        weight0 = Math.random()-0.5;
        weight1 = Math.random()-0.5;
        learnRate = 0.03;
        run = new boolean[]{false, false, false, false};
        
        
        //direction perceptron
        weight0_dir = Math.random()-0.5;
        threshW_dir = Math.random()-0.5;
        
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
    
    public void getOrData(double threshW, double weight0, double weight1){
        //0 = drawAns.get("Weight0")*x+drawAns.get("Weight1")*y+drawAns.get("threshW")
        int x = loc[1];
        int y = loc[0];
        int[][] orData = {{maze[x][y+1], maze[x+1][y+1]} , {maze[x+1][y], maze[x+1][y-1]} , {maze[x][y-1], maze[x-1][y-1]} , {maze[x-1][y], maze[x-1][y+1]}};   
        for (int i = 0; i< orData.length; i++){
            double val = (weight0*orData[i][0] + weight1*orData[i][1] + threshW*threshX);
            if (val > 0){
                orResults[i] = 1;
            }
            else if (val < 0){
                orResults[i] = 0;
            }
            System.out.println(val);
        }
        
        System.out.println("Weight0: " + weight0 + " Weight1: " + weight1 + " ThreshW: " + threshW);
        
        for(int i = 0; i< orData.length; i++){
            System.out.println(i + ": " + orData[i][0] +  "   " + orData[i][1]);
            System.out.println(orResults[i]);
        }
    }
    
    /*public int[] getSample(){
        int[] target = {};
        for (int i = 0; i<4; i++){
            if (orSamples[i][0] == 0 && orSamples[i][1]==0){
                target[i] = 0;
            } else{
                target[i] = 1;
            }
        }
        return target;
    }*/
    
    
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
        //private static int[] tar = {0, 1, 1, 1};
        //private int[][] orSamples = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 }};
        double output = -1;
        double val = 0;
        for(int i = 0; i<1000; i++){
            if (!(run[0] && run [1] && run[2] && run[3])){
                for(int j = 0; j<4; j++){
                    target = tar[j];
                    int[] xInt = orSamples[j];
                    val = weight1* xInt[1] + weight0*xInt[0] + threshW*threshX;
                    //System.out.println(j);
                    if (val > 0){
                        output = 1;
                    }
                    else if (val < 0 ){
                        output = 0;
                    }
                    if (output != target){
                        deltaW(weight0, weight1, threshW, output, xInt[0], xInt[1]);
                        run[j] = false;
                        /*System.out.println("Output: " + output + " Target: " + target);
                        System.out.println("Val: " + val);
                        System.out.println("Weight0: " + weight0 + " Weight1: " + weight1 + " ThreshW: " + threshW);
                        System.out.println();*/
                        
                    }else{
                        run[j] = true;
                        /*System.out.println("Output: " + output + " Target: " + target);
                        System.out.println("Val: " + val);
                        System.out.println("Weight0: " + weight0 + " Weight1: " + weight1 + " ThreshW: " + threshW);
                        System.out.println();*/
                        
                    }
                    //gs.setInfo(threshW, weight0, weight1, output, j);
                    //gs.drawLine();
                    //System.out.println(drawAns);
                    //drawAns.clear();
                }
            }
            else{
                    getOrData(threshW, weight0, weight1);
                    return;
            }
        }
        getOrData(threshW, weight0, weight1);
    }
    
    public void deltaW_dir(double W0, double threshW, double output, double X0){
        //deltaW(weight0, weight1, threshW, output, xInt[0], xInt[1]);
        double dWeight0 = learnRate*(target_dir-output)*X0;
        weight0_dir = W0 + dWeight0;
        double dThreshW = learnRate*(target_dir-output)*threshX;
        threshW_dir = threshW_dir + dThreshW;
    }
    
    public void getDirData(double threshW, double weight0){
        for (int i = 0; i < 4; i++){
            double val = (weight0 * orResults[i] - threshW*threshX);
            if (val > 0){
                dirResults[i] = 1;
            }
            else if (val < 0){
                dirResults[i] = 0;
            }
            System.out.println(val);
        }
        
        for(int i = 0; i< orResults.length; i++){
            System.out.println(i + ": " + orResults[i]);
            System.out.println(dirResults[i]);
        }
    }
    
    public void perDIR(){
        //one weight matches all four targets so only one weight needed
        double output = -1;
        double val = 0;
        for (int i = 0; i<20; i++){
            for (int k = 0; k < 4; k++){
                for(int j = 0; j < 16; j++){
                    target_dir = tar_dir[j][k];
                    int xInt = dirSamples[j][k];
                    val = weight0_dir* xInt + threshW_dir*threshX;
                    //System.out.println(j);
                    if (val > 0){
                        output = 1;
                    }
                    else if (val < 0 ){
                        output = 0;
                    }
                    if (output != target_dir){
                        deltaW_dir(weight0_dir, threshW_dir, output, xInt);                    
                    }else{
                        
                    }
                }
                System.out.println("Output: " + output + " Target: " + target_dir);
                System.out.println("Val: " + val);
                System.out.println("Weight0: " + weight0_dir + " ThreshW: " + threshW_dir);
                System.out.println();
            }
        }     
        getDirData(threshW_dir, weight0_dir);
    }
    
    /*public void perUP(){
        //index 0
        double output = -1;
        double val = 0;
        //for(int i = 0; i<15; i++){
            for (int j = 0; j<16; j++){
                target_dir = tar_dir[j][0];
                int xInt = dirSamples[j][0];
                val = weight0_dir* xInt + threshW_dir*threshX;
                //System.out.println(j);
                if (val > 0){
                    output = 1;
                }
                else if (val < 0 ){
                    output = 0;
                }
                if (output != target_dir){
                    deltaW_dir(weight0_dir, threshW_dir, output, xInt);                    
                }else{
                    
                }
            }
        //}
        System.out.println("Output: " + output + " Target: " + target_dir);
        System.out.println("Val: " + val);
        System.out.println("Weight0: " + weight0_dir + " ThreshW: " + threshW);
        System.out.println();
    } 
    
    public void perRIGHT(){
        //index 1
        double output = -1;
        double val = 0;
        for (int j = 0; j<16; j++){
            target_dir = tar_dir[j][1];
            int xInt = dirSamples[j][1];
            val = weight0_dir* xInt + threshW_dir*threshX;
            //System.out.println(j);
            if (val > 0){
                output = 1;
            }
            else if (val < 0 ){
                output = 0;
            }
            if (output != target_dir){
                deltaW_dir(weight0_dir, threshW_dir, output, xInt);

                
            }else{
                
            }
        }
        System.out.println("Output: " + output + " Target: " + target_dir);
        System.out.println("Val: " + val);
        System.out.println("Weight0: " + weight0_dir + " ThreshW: " + threshW);
        System.out.println();
    }
    
    public void perDOWN(){
        //index 2
        double output = -1;
        double val = 0;
        for (int j = 0; j<16; j++){
            target_dir = tar_dir[j][2];
            int xInt = dirSamples[j][2];
            val = weight0_dir* xInt + threshW_dir*threshX;
            //System.out.println(j);
            if (val > 0){
                output = 1;
            }
            else if (val < 0 ){
                output = 0;
            }
            if (output != target_dir){
                deltaW_dir(weight0_dir, threshW_dir, output, xInt);

                
            }else{
                
            }
        }
        System.out.println("Output: " + output + " Target: " + target_dir);
        System.out.println("Val: " + val);
        System.out.println("Weight0: " + weight0_dir + " ThreshW: " + threshW);
        System.out.println();    
    }
    
    public void perLEFT(){
        //index 3
        double output = -1;
        double val = 0;
        for (int j = 0; j<16; j++){
            target_dir = tar_dir[j][3];
            int xInt = dirSamples[j][3];
            val = weight0_dir* xInt + threshW_dir*threshX;
            //System.out.println(j);
            if (val > 0){
                output = 1;
            }
            else if (val < 0 ){
                output = 0;
            }
            if (output != target_dir){
                deltaW_dir(weight0_dir, threshW_dir, output, xInt);
                
            }else{
                
            }
        }
        System.out.println("Output: " + output + " Target: " + target_dir);
        System.out.println("Val: " + val);
        System.out.println("Weight0: " + weight0_dir + " ThreshW: " + threshW);
        System.out.println();
    }*/
    
    public void sense() {
        perOR();
        perDIR();
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
        if (dirResults[0] == 1){
            //if (grid[loc[0]][loc[1]+1]==0){
                loc[0] = loc[0]+1;
            //}
        }
        else if (dirResults[1] == 1){
            //if (grid[loc[0]-1][loc[1]]==0){
                loc[1] = loc[1]-1;
            //}
        }
        else if (dirResults[2] == 1){
            //if (grid[loc[0]][loc[1]-1]==0){
                loc[0] = loc[0]-1;
            //}
        }
        else if (dirResults[3] == 1){
            //if (grid[loc[0]+1][loc[1]]==0){
                loc[1] = loc[1]+1;
            //}
        }
    }
}
