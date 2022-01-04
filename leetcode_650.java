/*

URL = https://leetcode.com/problems/2-keys-keyboard/
650. 2 Keys Keyboard

Only one character - 'A' - on screen fo notepad
Two ops -> 
    "copy ALL"
    "paste"
    
    Given int n here in range [1,1000] : get min #-ops to get 'A' exacty n times
    Immediate thought process : isn't this just logarithmic anyways?
    
    Always start at 'A'
    Any mappings to FIB sequences too?
    The copy all is like a preserved 'buffer', if that makes sense too
    Oh ... the "copy" step, in itself, is an operation as well! Take note of that!
    
    Need a reccurence relationship and OSP as well
    Two choices at each step -> copy all and paste, or paste with current sized buffer
    Char len decreases and size of copy buffer parallely increases too
    
Complexity for DP
Let N := len
Time =O(N^2)
Space = O(N^2)
    
TEST CASES
(A) n = 4,6 => 3, 4
(B) n = 5 => 5  ( more due to odd length here as well ) 
(C) n = 24 => 11 ish operations 

notice sequencing issues as well :: 


    XXXXXXXX,buffersize = 3 , for len = 24 : not possible to fill in 16 remaining slots with three here
    but X is filled up to 8 here, somehow.
    copy 8 now
    XXXXXXXX buffersize = 8 , for len = 24 : possible to fill up remainder in just two operations now
    Do NOT terminate earlier due to these cases as well! 
    
    
A copy operation MUST be followed by an IMMEDIATE paste as well. Do not just skip                                         Where the heck exist the overlapping subproblems here as well?
Can not be just binary due to weird copy-paste taking place
5-1 = 4 : 4 = 2 + 2 : 
Copy buffer size can grow later too!

Start n larger, paste and make copy buffers from there as you proceed instead?
Consider hashmap in lieu of array/matrix?

WE know from the longest length, the copy can be at min one character, at max, based on however more pasted as well
We have to check each one too
1000 => 999
    -1 = 998 : now copy 'AA" and solve rest
        -2 = 996 : now copy = 'AAAA' and solve rest
            ^ here is where the overlap exists, in terms of size(copy) and in remainder length possible
    -2 = 997 : now copy 'AAA' and solve rest
    -3 = 996 : now copy 'AAAA"' and solve rest
    
    Notice same problem showing up here : but capturing state is a bit trickier : thus use hash or use array instead
    

    PSEUDOCODE : 
    
        minSteps(1000, 1000, 1, memo):
    
        // but fill in teh base case too ( how to ID that ) . 
        memo[n+1][n+1]; // sure alloc extra space ( why not ) ?
        memo[1][0] = 0   // only one base case TBH here
        int minSteps(int init, int n, int bufferSize, int[][] memo)
        {
            if memo[n][bufferSize] != 0
                ret memo[n][bufferSize]
            minSteps = MAX_VAL  
            # write a max val, if it be an impossiblity as well ( write a 0 if not impossible though )
            # number pastes, with current copy buffer size : there could be an optimization, but so be it for now
            for num_pastes_ops in range(1,n,1):
                numRemain = n - num_pastes_ops*bufferSize
                if numRemain > 0 :
                   newBufferSize = init - numRemain
                   curOps = 1 + num_pastes_ops
                   minSteps = min(minSteps, curOps - 1 + minSteps(init,newRemain,bufferSize, memo))
                   minSteps = min(minSteps, curOps + minSteps(init,newRemain,newBufferSize, memo))
                else if numRemain equals 0
                    curOps = 1 + i
                    minSteps = min(minSteps, curOps)
            
            memo[n][bufferSize] = minSteps
            ret minsteps            
        }
    
*/



class Solution {
    public int minSteps(int n) 
    {
        // There exists only one case which COULD theoretically zero out anyways. All other cases some strictly positive integer
        if(n <= 1)
        {
            return 0;
        }
        int copyBufferSize = 1;
        int[][] memo = new int[n+1][n+1]; // sure alloc extra space ( why not ) ?
        memo[1][0] = 0;   // only one base case TBH here
        int steps = minSteps(n, n-1, 1, memo); // Should be (n-1) for init, as we always include one character 'de-facto'
        return steps;
    }
    
    private int minSteps(int init, int n, int bufferSize, int[][] memo)
    {
        if(memo[n][bufferSize] != 0)
        {
            return memo[n][bufferSize];
        }
        int minSteps = Integer.MAX_VALUE >> 1; // data overflow here
        // # write a max val, if it be an impossiblity as well ( write a 0 if not impossible though )
        // # number pastes, with current copy buffer size : there could be an optimization, but so be it for now
        for(int num_paste_ops = 1; num_paste_ops <= n; num_paste_ops++)
        {
            int curOps = 1;
            int numRemain = n - num_paste_ops*bufferSize;
            if(numRemain > 0)
            {
               int newBufferSize = init - numRemain;
               curOps += num_paste_ops;
               minSteps = Math.min(minSteps, curOps - 1 + minSteps(init,numRemain,bufferSize, memo));
               minSteps = Math.min(minSteps, curOps + minSteps(init,numRemain,newBufferSize, memo));
            }
            else if(numRemain == 0)
            {
                curOps += num_paste_ops;
                minSteps = Math.min(minSteps, curOps);
            }
        }
        memo[n][bufferSize] = minSteps;
        return memo[n][bufferSize];
    }        
        
}
