/*


Leetcode 1441 : Build an Array With Stack Operations
URL = https://leetcode.com/problems/build-an-array-with-stack-operations/
*/

class Solution {

    
    /*
    
    Read from numbers list = {1,2,3,...,n}
    Givevn array "target" - tell if we equal it alraedy or not!
    Performed for each iteration - we know our bounds here ( O(N) = T, O(N) = S )?
    "target" is guaranteed to be strictly increasing - [1,2,5,3] will never occur!
    target values are guaranteed a bound from [1,n] too! So if we go [1,4] = target where n = 4 .. that is it! 
    target array is only till 100 values max - not too many operations either!
    Is a stack even needed for this problem? Seems to be I/O mostly! 
    We are always guaranteed an answer too!
    
    */
    
    public List<String> buildArray(int[] target, int n) 
    {
        int tIdx = 0; // index into the target array
        List<String> ops = new ArrayList<String>();
        int maxTVal = target[0];
        for(int i = 1; i <= n; ++i)
        {
            if(i != target[tIdx])
            {
                ops.add("Push");
                ops.add("Pop");
            }
            else
            {
                ops.add("Push");
                ++tIdx;
                if(tIdx >= target.length)
                    break;
            }
        }
        return ops;        
    }
}
