/*

https://leetcode.com/problems/the-skyline-problem/

- Guaranteed surface flatness
- Return skyline formed

- You will try out a divide-and-conquer technique for this problem
- Goal : create the silhouette

Can we have duplicates as well ( W.R.T. left, right, height ) dimensions?
Can we sort buildings from left -> right
Key points = left endpoints of horizontal segments of skyline ( except last point : mark's termination where rightmost building ends )
Can also conjure up a heap here.

We sure do know the first point and the last point which we draw up as well.

DIVIDE-AND-CONQUER

BASE :

RECURSIVE : 





COMPLEXITY
Let N := #-skyline buildings
N := 10,000, but (left,right) can range from [0,INT_MAX], and height can go up too
Buildings is already pre-sorted in non-decreasing order as well.
TIME = O(NlogN)                   -> could be worse. Do check as well
SPACE = O(1) ( IMP ) O(N) ( EXP ) -> again, objects may have their manipulations too :-). 

TEST CASES
(A) [[1,10,13],[2,6,2],[3,4,3],[8,11,2],[9,10,3]]
    That staircase stack case ( T1->T2->T3, T4->T5 with T1 going across the board ) 
(B) [[0,2,3]]
    => single tower case works
(C) [[1,7,13],[2,6,2],[3,4,3],[9,10,2],[12,15,3]] { 3 non-intersecting rectangle groups } 
    => 
(D) [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,6],[19,24,8],[19,24,10]]
    => to confirm nested buildings heights ( yes they do grow here ) 
(E) [[1,2,1],[3,4,1],[5,6,1]]
    => [[1,1],[3,1],[5,1],[6,0]] 
(F)  [[1,4,1],[3,4,1],[5,6,1]]
    => a non-disjoint case, with equal height buildings on the LHS of our sequence here
(G) [[1,3,1],[2,4,2],[3,5,3],[4,6,4]]
    => ascending buildings ( by height, and by left-right pairings ) 
    => [[1,0],[1,1],[2,2],[3,3],[4,4],[6,0]]
(H) [[1,3,4],[2,4,3],[3,5,2],[4,6,1]]
    => descending buildings ( by height, and by left-right pairings ) 
    => [[1,0],[1,4],[2,3],[3,2],[4,1],[6,0]]
(I) [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]] -> a tougher case
    and assoc subcases
    [[5,12,12],[15,20,10],[19,24,8]]
(J) [[0,2,3],[2,5,3],[5,8,3]]
(K) [[0,2,3],[2,5,3], [5,8,3], [5,12,12]]
    => not passing this case sadly
    
Is it also akin to the intervals problem, by chance?
Ensure to merge buildings of consecutive horizontal lines here as well too.

*/

class Building
{
    public int left;
    public int right;
    public int height;
    
    public Building(){}
    public Building(int left, int right, int height)
    {
        this.left = left;
        this.right = right;
        this.height = height;
    }
}

class Solution 
{
    public List<List<Integer>> getSkyline(int[][] buildings) 
    {
        ArrayList<List<Integer>> results = new ArrayList<List<Integer>>(); 
        if(buildings.length == 1)   // special case handling
        {
            ArrayList<Integer> firstPoint = new ArrayList<Integer>(Arrays.asList(buildings[0][0], buildings[0][2]));
            ArrayList<Integer> secondPoint = new ArrayList<Integer>(Arrays.asList(buildings[0][1], 0));
            results.add(firstPoint);
            results.add(secondPoint);
            return results;
        }
        // you can NOT instantiate an abstract class ( must be an obj ), 
        // but an object may contain type of abstract class :-) Hence, following above be valid syntax.
        PriorityQueue<Building> skyline = new PriorityQueue<Building>(new Comparator<Building>(){
            public int compare(Building one, Building two)
            {
                if(one.right < two.right)
                {
                    return 1;
                }
                else if ( one.right > two.right)
                {
                    return -1;
                }
                else
                {
                    if(one.height < two.height)
                    {
                        return 1;  // lefter, and then the taller buildings, as we go leftwards too
                    }
                    else if ( one.height > two.height)
                    {
                        return -1;   // put the shorter, right buildings as we go here
                    }
                    else
                    {
                        return 0; // the lefts do not matter in this case, honestly.
                    }
                }
            }
        });
        // Fill up our PQ : also check validity of ordering as well
        for(int i = 0; i < buildings.length; ++i)
        {
            int[] meta = buildings[i];
            Building novel = new Building(meta[0], meta[1], meta[2]);
            skyline.add(novel);
        }
        
        // Let's trick it too -> ahh, but your condition may do an accidental evaluatino here, so perhaps not
//         Building sentinel = new Building(buildings[0][0], buildings[0][1], buildings[0][2]);
//         skyline.add(sentinel);
        
        int rightMostX = skyline.peek().right;
        int rightMostY = 0;
        ArrayList<Integer> rightInt = new ArrayList<Integer>();
        rightInt.add(rightMostX);
        rightInt.add(rightMostY);
        results.add(rightInt);
        
        // We are guaranteed the right sort here anyways
        // We can easily test the disjoint case
        // Boolean scoped out of while-loop for continuous updating pattern
        // Can do a pseudo-building trick too ( copy the first building here anyways ) 
        // Failing to merge heights in a disjoint case as well ! Merge may need to take place ( or some comparison as well ) 
        // So do NOT be too quick unto your comparison here as well
        boolean nextDisjoint = false;  // we don't know yet
        while(!skyline.isEmpty() && skyline.size() > 1)
        {
            Building rightMost = skyline.poll();
            Building secondMost = skyline.poll();
            // System.out.printf("Printing building = [%d,%d,%d]\n", rightMost.left, rightMost.right, rightMost.height );
            // Perform the disjoint check ahead of time as well ( need a bool for such a case too ) 
            if(secondMost.right < rightMost.left)
            {
                System.out.printf("Encountered a disjoint case rightMost height = [%d] secondMost height = [%d]\n", rightMost.height, secondMost.height);
                nextDisjoint = true;
                // output rightmost info -> evict from the heap
                ArrayList<Integer> rightTopLeft = new ArrayList<Integer>();

                // Check to add rightmost point of cluster
                // Is is ordering, as well as the first is re-evaluated again from scratch, and hence, confusion here!
    
                // We must add this before the disjoint part
                rightTopLeft.add(rightMost.left);
                rightTopLeft.add(rightMost.height);
                results.add(0, rightTopLeft);
                
                if(nextDisjoint == true && skyline.size() > 1)
                {
                    System.out.printf("At next disjoint eval to true rightmost = [%d]\n", rightMost.right);
                    ArrayList<Integer> rightest = new ArrayList<Integer>();
                    rightest.add(secondMost.right);
                    rightest.add(0);
                    results.add(0, rightest);
                }
                
                // place back secondMost on the head now
                skyline.add(secondMost);
            }
            else if(secondMost.right >= rightMost.left)
            {
                // System.out.printf("secondmost = (%d,%d,%d) right >= rightmost left\n", secondMost.left, secondMost.right, secondMost.height);
                if(nextDisjoint == true)    // but why not check size here? Is it due to height order as well
                {
                    System.out.printf("At next disjoint eval, but secondRight >= rightLeft, to true rightmost = [%d]\n", rightMost.right);
                    ArrayList<Integer> rightest = new ArrayList<Integer>();
                    rightest.add(rightMost.right);
                    rightest.add(0);
                    results.add(0, rightest);
                }
                nextDisjoint = false; // still in cluster here : no hit
                // second rightmost of lower height -> "chop off" the right portion of the smaller
                if(secondMost.height < rightMost.height )
                {
                    System.out.printf("here second most height = [%d] < rightmost height = [%d]\n", secondMost.height, rightMost.height);
                    if(secondMost.left > rightMost.left && secondMost.right < rightMost.right)
                    {
                        continue; // we can effectively "nest" said building. 
                    }
                    else
                    {
                        ArrayList<Integer> leftTop = new ArrayList<Integer>();
                        leftTop.add(rightMost.left);
                        leftTop.add(rightMost.height);
                        results.add(0, leftTop);
                        
                        if(secondMost.left < rightMost.left)
                        {
                            Building repl = new Building(secondMost.left, rightMost.left, secondMost.height);
                            System.out.printf("Adding sub building = [%d,%d,%d]\n", repl.left, repl.right, repl.height);
                            skyline.add(repl);
                        }
                        skyline.add(secondMost); // go add it back here
                  }
                }
                // second rightmost of greater than or equal to height -> "chop off" the 
                else if ( secondMost.height > rightMost.height)
                {
                    System.out.printf("here second most height > rightmost height [%d,%d]\n", secondMost.height, rightMost.height);
                    // Inequality caution strikes again
                    if(rightMost.left > secondMost.left && rightMost.right < secondMost.right)
                    {
                        // nest this case as well : rightmost contained within secondmost
                        // Wait this could be wrong actually!
                    }
                    else
                    {
                        ArrayList<Integer> leftTop = new ArrayList<Integer>();
                        leftTop.add(secondMost.right);
                        leftTop.add(rightMost.height);
                        results.add(0, leftTop);
                        
                        if(rightMost.left < secondMost.left)
                        {
                            Building repl = new Building(rightMost.left, secondMost.left, rightMost.height);
                            System.out.printf("Adding sub building = [%d,%d,%d]\n", repl.left, repl.right, repl.height);
                            skyline.add(repl);
                        }
                        skyline.add(rightMost); // go add it back here
                    }
                }
                else if ( secondMost.height == rightMost.height)
                {
                    Building repl = new Building(Math.min(secondMost.left, rightMost.left), 
                                                 Math.max(secondMost.right, rightMost.right), rightMost.height);
                    skyline.add(repl);
                }
            }
        }
        
        // Now at the final building -> go add it's top left point, de-facto
        ArrayList<Integer> leftest = new ArrayList<Integer>();
        if(leftest != null)
        {
            Building leftist = skyline.poll();
            if(leftist != null)
            {
                if(nextDisjoint == true)
                {
                    ArrayList<Integer> firstright = new ArrayList<Integer>();
                    firstright.add(leftist.right);
                    firstright.add(0);
                    results.add(0, firstright);
                }
                leftest.add(leftist.left);
                leftest.add(leftist.height);
                results.add(0, leftest);
            }
        }
        return results;        
    }
}

// Analysis : we always add the left of a building under consideration with the exception of the disjoint portion here
