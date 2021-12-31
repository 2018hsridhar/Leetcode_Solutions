/*


Ideas : 
Knapsack is not doable greedy, but we can try to be greedy her and sort match sticks by their lengths
N := 15, so 2^15 = 65,356  : subsets via bit manipulation or bitmasks are not fully gone
Seems like knapsack repeated with (sum/4), as a square's perimeter = 4*s = sum(matchstick lengths ) 
Smaller numbers always fit into spaces occupyabe by larger nums ( e.g. 1+1+1+2 = 3 + 2 = 5), and we can have a side len of 7,3,2 over 7,2,1,1,1
and use said remainder lengths elsewhere. 
Can we come up with examples or counter examples as well too?

Complexity
Let N := #-matching
Time = O()
Space = O()

*/
class Solution {
    public boolean makesquare(int[] matchsticks) {
        
    }
}
