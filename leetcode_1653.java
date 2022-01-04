/*
Akin to typical DP problems
Choice makeable : to delete or not delete a character

COMPLEXITY :
n = 10^5 : POLY time algos fine here
Let S := len(String)
TIME = O()
SPACE = O()

TEST CASES :
(A) "aaaababa"
    -> delete 'a's or delete b's, but NOT the first four a's
    -> 2
(B) "bbabbaa"
    -> delete all 'a's, but not all 'b's here yields optimal
    -> 3
(C)
(D)
(E)

Leverage property : are we in a range of all a's, or all b's, at a given index too?
Cuz at a certain point, the problem can simplify massively too!

We can maintain a seperate array to count number of a's, following a given index, as well ( propagate that too ) 
Can do sliding window magic too and pass in something akin to count_a, count_b as well

Note edge case : a 'b' early on could also indicate just deleting all b's too, and it theoretically works!

Base case of single character -> 0 deletions always
Leading a's really do not impact algo
Deleting the 'b' here means a new subproblem to solve

E.g. s = 'aababbab' =>
    f('aababbab') => '' + 1 + f('abbab') really
    Miniizing it based on whether to preserve the 'b' letter here or NOT really
    

PSEUDOCODE : 

    minDels = 0
    if s points to null or len(s) <= 1
        ret 0
    n = len(s)
    for each index in range(n-1,-1,-1):
        

    ret minDels
    
*/

class Solution 
{
    public int minimumDeletions(String s) 
    {
        int minDeletions = 0;
        return minDeletions;
        
        
        
    }
}
