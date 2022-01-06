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

> depends on which character is under deletion here as well
> is it an 'a' or a 'b'?
> if we do not delete a 'b' : count #-a's deleted to right
> if we do not delete a 'a' : count #-deletions to make the right balanced
> if we delete either 'a' or 'b' : count #-deletions to make right balanced as well
> we just concatenate after deletion anyways
> see for 'b', the minima is # of a's on RHS to chuck out


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
    count_a_right = 0
    if s[n-1] == 'a'
        count_a_right += 1
    for each index in range(n-2,-1,-1):
        subproblem_dels = 0
        char curEl = s[index]
        if curEl == a :                     // rendered a NOP anyways
            subproblem_dels = minDels   // just continue
            count_a_right += 1
        elif curEl == b:
            subproblem_dels= min(1 + minDels,count_a_right)
        minDels = subproblem_dels
    ret minDels
    
TEST CASES
(A) "ababaaabbbabababbbbbabababbaabaaaabbbbbababab" => 17 
(B) "ababaaabbbabababbbbbabababababbaabababababbbababaaaabababababbbbbbbbbaaaaabababaaaaababababbbababababababbbababababbbbbbbbbaaaabbbaaabbaaaaabbbaaababbaabaaaabbbbbababab"  => 76

Yeah you were right :-)

*/

class Solution 
{
    public int minimumDeletions(String s) 
    {
        int minDels = 0;
        if(s == null || s.length() <= 1)
        {
            return 0;
        }
        int n = s.length();
        int count_a_right = 0;
        if(s.charAt(n-1) == 'a')
        {
            count_a_right = 1;
        }
        for(int i = n-2; i >= 0; --i)
        {
            int subproblem_dels = 0;
            char curEl = s.charAt(i);
            if(curEl == 'a')                     // rendered a NOP anyways
            {
                subproblem_dels = minDels;   // just continue
                count_a_right += 1;
            }
            else if(curEl == 'b')
            {
                subproblem_dels= Math.min(1 + minDels,count_a_right);
            }
            minDels = subproblem_dels;
        }
        return minDels;
    }
}
