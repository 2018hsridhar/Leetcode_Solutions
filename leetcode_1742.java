/*
1742. Maximum Number of Balls in a Box

*/

class Solution {
    public int countBalls(int lowLimit, int highLimit) 
    {
        // In some contexts, we prefer Integer object support ( Integer objects support quick notion of MIN_VALUE or MAX_VALUE ). Could use zero here - up to you!
        // Given how useful OOP is in storing fields inside nice constructs, it remains no surprise that OOP prevails as choice of a programming paradigm
        
        int maxBallCount = Integer.MIN_VALUE;
        HashMap<Integer,Integer> boxes = new HashMap<Integer,Integer>();
        for(int i = lowLimit; i <= highLimit; ++i)
        {
            int val = i;
            int digSum = computeDigitSum(val);
            if(!boxes.containsKey(digSum))
            {
                boxes.put(digSum, 1);
                maxBallCount = Math.max(maxBallCount, 1); // Skip conditional here anyways :-) 
            }
            else
            {
                boxes.put(digSum, boxes.get(digSum) + 1);
                maxBallCount = Math.max(maxBallCount, boxes.get(digSum)); // Skip conditional here anyways :-) 
            }
        }
        return maxBallCount;
    }
    
    public int computeDigitSum(int val)
    {
        int digSum = 0;
        while(val >= 10)
        {
            digSum += (val % 10 );
            val /= 10;
        }
        digSum += val;
        return digSum;
    }
}
