/*
973. K Closest Points to Origin
URL = https://leetcode.com/problems/k-closest-points-to-origin/

Solved problem on Interviewing.Io on 0924/2021 from 6:00 p.m. - 7:00 p.m.
Coded this from 7:00-7:15 p.m. and 8:00 p.m. - 8:15 p.m. ( 30 mins again ) 

Complexity : 

Time = O(nlog(k))
Space = O(Math.min(k,n))

Edge cases : 
(1) Negatives to Positives
(2) Strictly positives

Answer guaranteed uniqueness as well here
Answer in ANY order as well
Challenge problem : scale to n-dimensions and REDUCE the total computatinoa here
Challenge #2 : Generalize target to more than just the origin of (0,0) here

We have both Divide-and-Conquer, as well as maxHeap here too!

Trip up here : points may be <int,int>, BUT, distances can be DOUBLEs! How they intend to trip you up here as well!
https://stackoverflow.com/questions/11003155/change-priorityqueue-to-max-priorityqueue

*/
class Solution 
{
    public class myPoint
    {
        public int x;
        public int y;
        public double dist;
        public myPoint(){};
        public myPoint(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        public myPoint(int x, int y, double dist)
        {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    // We should NOT have to test equality here as well
    public int[][] kClosest(int[][] points, int k) 
    {
        PriorityQueue<myPoint> kClosest = new PriorityQueue<myPoint> (
          new Comparator<myPoint> () {
            public int compare(myPoint a, myPoint b) {
               if(b.dist < a.dist)
                   return -1;
               else if(b.dist > a.dist)
                    return 1;
                return 0;
            }
          }
        );
        
        for(int i = 0; i < points.length; ++i)
        {
            double curDist = getDist(points[i]);
            int x = points[i][0];
            int y = points[i][1];
            myPoint curPoint = new myPoint(x,y,curDist);
            if(kClosest.size() >= k)
            {
                double myHeapMaxDist = kClosest.peek().dist;
                if(curDist <= myHeapMaxDist)
                {
                    kClosest.poll();
                    kClosest.add(curPoint);
                }
            }
            else
            {
                kClosest.add(curPoint);
            }
        }
        
        int num_points = kClosest.size();
        // System.out.printf("Num points = %d\n", num_points);
        int[][] results = new int[num_points][2];
        for(int i = 0; i < num_points; ++i)
        {
            myPoint cur = kClosest.poll();
            int[] newPair = new int[2];
            results[i][0] = cur.x;
            results[i][1] = cur.y;
        }
        return results;            
    }
    
    public double getDist(int[] point)
    {
        double y_diff = point[1] - 0;
        double x_diff = point[0] - 0;
        double totalDist = (x_diff*x_diff) + (y_diff*y_diff);
        return Math.sqrt(totalDist);            
    }
    
}



