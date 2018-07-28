public class Solution {
    // 1. uyse a visited set, to determine if u terminate or not
    // keep looping, over the number ... rep it as a string, and take a summation each time
    // can always ASSUME that n is a positive number !
    public boolean isHappy(int n)
    {
        boolean term = true;
        if(n == 1)
        {
            return true;
        }
        else
        {
            Set<Integer> visited = new HashSet<Integer>();
            term = helper(n,visited);
        }
        return term;
    }
    
    public boolean helper(int n, Set<Integer> visited)
    {
        boolean stat = false;
        if(visited.contains(n))
        {
            stat = false;
        }
        else if ( n == 1)
        {
            stat = true;
        }
        else
        {
            visited.add(n);
            // [2] construc tthy new number ... via characters
            String s = "" + n;
            char[] cArr = s.toCharArray();
            int newN = 0;
            for(int i = 0; i < cArr.length; i++)
            {
                int x = (int)(cArr[i] - '0');
                newN += (x * x);
            }
            stat = helper(newN,visited);
        }
        return stat;
    }
    
}

// https://leetcode.com/problems/happy-number/

