/*
It is not true that in ops[i] = [a_i,b_i] that a_i = b_i holds true.
Sorting the original input most likely helps here.
We also still have out hashmap of (rows,cols) idea too.
We can always go largest to smallest
We need only a count here too.
But does pure lexicographic ordering even work here?
But can we not leverage inequalities too?

E.g. op on [3,1] => from now on, only in range [1,1] do we ever encounter ANY new maximums too
Because other manipulations => [1,2],[1,3],[2,1],[2,2] => and so on => impact [1,1] `de facto`!
And careful on the `no ops` edge case too
URL = https://leetcode.com/problems/range-addition-ii/
598. Range Addition II

Complexity
Let N := #-elements in the input
Time = O(N)
Space = O(1) ( EXP & IMP ) 

Test Cases
(A) [[3,3]] => 9
(B) [[3,3],[2,2],[1,1]] => 1
(C) [[3,3],[3,2],[2,3],[2,2]] => 4
(D) 3
3
[[3,3],[1,1],[2,2],[1,2],[2,1],[3,2],[2,3]] => 1
(E) [[3,3],[3,1],[1,3]] => 1
(F) 3
3
[[3,2],[3,2],[2,3],[2,3]] => 4

*/
class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        int maxCount = 0;
        if(ops.length == 0) {
            return m * n; // special case here
        }
        // Sort ranges greatest to least here ( both orders decreasing too ) 
        Arrays.sort(ops, new Comparator<int[]>(){
            public int compare(int[] rangeOne, int[] rangeTwo){
                if(rangeOne[0] < rangeTwo[0])
                    return 1;
                else if ( rangeOne[0] > rangeTwo[0])
                    return -1;
                else {
                    if(rangeOne[1] < rangeTwo[1]){
                        return 1;
                    } else if ( rangeOne[1] > rangeTwo[1]){
                        return -1;
                    } return 0;
                }
            }          
        });
        int p = ops.length;
        // Leverage the `top left` corner here
        // break out of while loop properly
        // but keep a notion of a max count correct at least before resetting a current count
        int[] rangeCur = ops[0]; // first range under consid : the original
        int curCount = rangeCur[0] * rangeCur[1]; // de facto start here : assume entire matrix can incr
        int i = 1;        
        while(i < p){
            int[] rangeTwo = ops[i];
            rangeCur[0] = Math.min(rangeCur[0],rangeTwo[0]);
            rangeCur[1] = Math.min(rangeCur[1],rangeTwo[1]);
            curCount = rangeCur[0] * rangeCur[1];
            ++i;
        }
        return curCount;
    }
}
