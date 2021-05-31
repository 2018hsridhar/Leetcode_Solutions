/*

Problem Statement = 346. Moving Average from Data Stream
URL = https://leetcode.com/problems/moving-average-from-data-stream/

*/
class MovingAverage {

    /** Initialize your data structure here. */
    Queue<Integer> movingAvg;
    double curAvg = 0;
    int size;
    int max_size;
    
    public MovingAverage(int size) 
    {
        this.movingAvg = new LinkedList<Integer>();
        this.max_size = size;
        this.size = 0;
    }
    
    public double next(int val) 
    {
        // where size is not maximized
        double res = 0;
        if(size < max_size)
        {
            size += 1;
            curAvg += val;
            movingAvg.add(val);
            res = curAvg / size;
        }
        else
        {
            // where size is maximized
            movingAvg.add(val);
            int first = movingAvg.remove();
            curAvg -= first;
            curAvg += val;
            res = curAvg / max_size;    
        }
        
        return res;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
