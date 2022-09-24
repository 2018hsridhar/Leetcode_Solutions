/*
https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/
1893. Check if All the Integers in a Range Are Covered

Test Cases
(A) [[4,5]] 4 5 => true
(B) [[4,5]] 4 4 => true
(C) [[4,5]] 5 5 => true
(D) 
(E) 
(F) [[15,36],[15,23],[15,44],[30,49],[2,19],[27,36],[7,42],[12,41]]
19
47

*/
class Solution {
    
    // I prefer a `nested class` containing our Comparator of interest.
    // Templated off of one type too.
    class RangeComparator implements Comparator<int[]>
    {
        public int compare(int[] range_one, int[] range_two){
            if(range_one[0] < range_two[0]) {
                return -1;
            } else if ( range_one[0] > range_two[0]) {
                return 1;
            } else {
                if(range_one[1] < range_two[1]) {
                    return -1;
                } else if ( range_one[1] > range_two[1]) {
                    return 1;
                }
                return 0;                
            }
        }
    }
    
    //  We could have tried merging our ranges here too.
    // Impose a single direction constraint on the check too.
    public boolean isCovered(int[][] ranges, int left, int right) {
        int n = ranges.length;
        boolean isCovered = true;
        // scope object in method
        // Avoid other parts of function accessing said object too.
        Arrays.sort(ranges, new RangeComparator());
        int start = left;
        int ptr1 = 0; // ptr to ranges array
        boolean hitARange = false;
        // While loop terminates if ranges[ptr1][1] >= start here
        if(ranges[0][0] > start) {
            return false;
        }
        while(ptr1 < n) {
            if(ranges[ptr1][1] < start) {
                ptr1++;
            } else {
                hitARange = true;
                break;
            }
        }
        if(!hitARange) { 
            return false;
        }
        int curLeft = ranges[ptr1][1]; // stop here
        // ptr1++;
        // You just check a gap of one too ( as you have sorted the ranges here )
        while(ptr1 < n && curLeft <= right){
            int rangeLeft = ranges[ptr1][0];
            int rangeRight = ranges[ptr1][1];
            if(rangeLeft <= curLeft && curLeft <= rangeRight) {
                curLeft = rangeRight + 1; // append a (+1) here given use of sort
            } 
            ptr1++;
        }
        // System.out.printf("Cur left = %d\n", curLeft);
        if(curLeft <= right) {
            isCovered = false;
            // break; // no break outside switch EXPR or loop EXPR
        }
        return isCovered;
    }
}
