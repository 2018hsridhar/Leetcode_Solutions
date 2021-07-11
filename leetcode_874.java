
/*

URL = https://leetcode.com/problems/walking-robot-simulation/
874. Walking Robot Simulation

THOUGHT PROCESS 
- Working on an infinite XY-plane ( 2D plane ) : can we really have an int[][] plane matrix set up here?





Computational Complexity : 
Time = O(N) where N := number of moves
Space = O(1) or O(M) where M := number of obstacles

Edge case testing : 
(1) No obstacles - run command list as expected
(2) Single commands - north, south, east, west { single movements } 
(3) A couple of obstacles here and there


Commands reasonably bounded : move only [1-9] but never 0 units too
No double or weird obstalces : only one cell at a time too

Dealing with XY planes - what quadrants ( I, II, III, IV ) ?
Dealing with distances - are they Manhattan, Euclidean, or both?
-{ ( traversals => Manhattan ) as  ( total_distance => Euclidean ) }

Commands list and obstacles list can both be equally stupidly large 
Worst case : every cell is an obstacle!
Answerde guaranteed to fit within INTEGER data type too


Initial distance = north ( use modulo trick of arrays ) 
Obstacles = issue : representation ( grid can be infinite here ) 
Utilize hashmap : first, check if (x-axis) locatino has obstacle -> then check each of the y-axis values too!



*/

class Solution 
{
    /// Return max distance as commands get processed
    public int robotSim(int[] commands, int[][] obstacles) 
    {
        int maxEuclideanDist = 0;

        // N,E,W,S directionality ( clockwise : right = +, left = - )
        int myDir = 0;
        int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}}; 
        int numDirs = 4;
        int[] robotPos = new int[]{0,0};
        
        // [1] Collect obstacles information into a hashmap
        HashMap<Integer,List<Integer>> columnObstacles = new HashMap<Integer,List<Integer>>();
        for(int i = 0; i < obstacles.length; ++i)
        {
            int[] myObst = obstacles[i];
            int x = myObst[0];
            int y = myObst[1];
            List<Integer> column;
            if(!columnObstacles.containsKey(x))
                column = new LinkedList<Integer>();
            else
                column = columnObstacles.get(x);
            column.add(y);
            columnObstacles.put(x, column);
        }
        
        // [2] Process the commands list
        for(int i = 0; i < commands.length; ++i)
        {
            int curCommand = commands[i];
            if(curCommand == -2)
                myDir = (( myDir - 1 ) + numDirs ) % numDirs;
            else if ( curCommand == -1)
                myDir = (( myDir + 1 ) + numDirs ) % numDirs;
            else // move
            {
                int steps = curCommand;
                for(int j = 0; j < steps; ++j)
                {
                    int[] nextStep = new int[]{robotPos[0] + dirs[myDir][0], robotPos[1] + dirs[myDir][1]};
                    boolean hitObstacle = false;
                    if(columnObstacles.containsKey(nextStep[0]))
                    {
                        List<Integer> columnObjs = columnObstacles.get(nextStep[0]);
                        Iterator<Integer> itr = columnObjs.iterator();
                        while(itr.hasNext())
                        {
                            int myY = itr.next();
                            if(myY == nextStep[1])
                            {
                                hitObstacle=true;
                                break;
                            }
                        }
                    }
                    if(hitObstacle == true)
                        continue; // go to next iteration of the for loop
                    else
                    {
                        int curEuclideanDist = ( (int)Math.pow(nextStep[0],2) + (int)Math.pow(nextStep[1],2));
                        maxEuclideanDist = Math.max(maxEuclideanDist, curEuclideanDist);
                        robotPos[0] = nextStep[0];
                        robotPos[1] = nextStep[1];
                    }
                }
            }
        }
        
        
        return maxEuclideanDist;
    }
}
