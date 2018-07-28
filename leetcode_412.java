// https://leetcode.com/problems/fizz-buzz/

public class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> results = new LinkedList<String>();
        if(n <= 0)
        {
            return results;    
        }
        for(int i = 1; i <= n; i++)
        {
            if(i % 3 == 0 && i % 5 == 0)
            {
                results.add("FizzBuzz");
            }
            else if (i % 3 == 0)
            {
                results.add("Fizz");
            }
            else if ( i % 5 == 0)
            {
                results.add("Buzz");
            }
            else
            {
                results.add(i + "");
            }
        }
        return results;
    }
}
