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
(C) [[1,7,13],[2,6,2],[3,4,3],[9,10,2],[12,15,3]] { 3 non-intersecting rectangle groups } 
    => 
(D) [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,6],[19,24,8],[19,24,10]]
    => to confirm nested buildings heights ( yes they do grow here ) 
(E)
(F) 
(G) 

Is it also akin to the intervals problem, by chance?





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
        List<List<Integer>> results = new ArrayList<List<Integer>>(); 
        // you can NOT instantiate an abstract class ( must be an obj ), but an object may contain type of abstract class :-) Hence, following above be valid syntax.
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
                        return -1;  // lefter, and then the taller buildings, as we go leftwards too
                    }
                    else if ( one.height > two.height)
                    {
                        return 1;   // put the shorter, right buildings as we go here
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
        
        while(!skyline.isEmpty())
        {
            Building rightmost = skyline.poll();
            System.out.printf("Printing building = [%d,%d,%d]\n", rightmost.left, rightmost.right, rightmost.height );
        }
        
        
        return results;        
    }
}
