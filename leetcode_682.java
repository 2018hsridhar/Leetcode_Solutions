/*
URL = https://leetcode.com/problems/baseball-game/
Problem 682. Baseball Game
THOUGHT PROCESSES
- Stack operations are useful when processing symbols which deal with adding elements or removing elements from a string
- Good for keeping track of numerical values which update over time - e.g. game scores
- Processing continuous streams of records
- At least one operation will be guaranteed
- Check previous score is guaranteed for specific operations ( a.k.a. an element actually existss ) 
- notice : data_structure<typename>() = constructor syntax! typename is pegged to data structure name - not to variable name!


*/





class Solution {
    public int calPoints(String[] ops) 
    {
        int sum = 0;
        Stack<Integer> points = new Stack<Integer>();
        for(String s : ops )
        {
            String myOp = s;
            if(s.equals("C"))
            {
                points.pop();
            }
            else if ( s.equals("D"))
            {
                Integer doubleScore = 2 * points.peek();
                points.push(doubleScore);
            }
            else if ( s.equals("+"))
            {
                Integer score_1 = points.pop();
                Integer score_2 = points.pop();
                Integer sumScores = score_1 + score_2;
                points.push(score_2);
                points.push(score_1);
                points.push(sumScores);
            }
            else
            {
                Integer score = Integer.parseInt(s);
                points.push(score);
            }
        }
        
        // Assume stack of values fully built up - obtain cummulative sum
        while(!points.isEmpty())
        {
            sum += points.pop();
        }
        return sum;
    }
}
