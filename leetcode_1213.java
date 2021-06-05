
/*

1213. Intersection of Three Sorted Arrays
https://leetcode.com/problems/intersection-of-three-sorted-arrays/

THOUGHT PROCESSES : 
What somone might naively think : we need 3 points, and need to do a bunch of condition checking for all 3
Better - let's just do two arrays, two times. So (arr1,arr2) yields (arr_comb), then (arr_comb,arr3) = result
Profit here
Oh and our inputs are already sorted anyways
SO make a helper method to handle this instead!

Not all array lengths may be the same too

*/

class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) 
    {
        List<Integer> results = new ArrayList<Integer>();
        ArrayList<Integer> firstTwoRes = twoArrsIntersection(arr1, arr2);
        int[] firstTwo = new int[firstTwoRes.size()];
        for(int i = 0; i < firstTwoRes.size(); ++i)
        {
            firstTwo[i] = firstTwoRes.get(i);
        }
        results = twoArrsIntersection(firstTwo, arr3);
        return results;
    }
    
    // Due to the sort in strictly incraesing order - we may not have to worry about the edge cases as much here
    // if we max out of a single array - then in the array we are currently at, this ( and remainder elements ) were never in array 1
    public ArrayList<Integer> twoArrsIntersection(int[] arr1, int[] arr2)
    {
        int i = 0;
        int j = 0;
        ArrayList<Integer> result = new ArrayList<Integer>();
        while(i < arr1.length && j < arr2.length )
        {
            if(arr1[i] == arr2[j])
            {
                result.add(arr1[i]);
                ++i;
                ++j;
            }
            else if ( arr1[i] < arr2[j])
                ++i;
            else if ( arr2[j] < arr1[i])
                ++j;
        }
        // check remainder of elements
        return result;   
    }
    
}
