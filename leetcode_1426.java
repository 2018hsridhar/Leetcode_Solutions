/*
URL = https://leetcode.com/problems/counting-elements/
1426. Counting Elements

Thought processes : 
Sort the array - incurs O(NlogN) performance in best case and worst case ( via merge sort )
Needs typical consecutive element checker : a[i] vs a[i+1]
Need to store a temporary count, in the case of consecutive elements too!
No need to check final element case
Array length may be equal to 1 : exert caution here
Array values bounded by the closed interval of integers in the range [0,1000]

Ideal performance ( after sorting ) : [T,S] = [O(n), O(1)] where n := number of elements in array
Do we have  [O(NLogN), O(1)] = [T,S] algorithm?

Test cases : 
[1]
[0, 0, 0, 0, 0, 0]
[1,2,3,4,5]
[5, 4, 3, 2, 1]
[1, 3, 5, 7, 9, 10, 11, 12]

Failing test case : 
[1,5,6,9,4,2,2,0]

This concept of tracking the current frequency of an element, in a sorted array, is denoted as "runLength", as
observed in RLE - Run Length Encoding!

The benefit of sorting an array, is that we can apply run lengths as a tracking mechanism! Avoids hashsets! 

*/
class Solution 
{
    public int countElements(int[] arr) 
    {
        int elCount = 0;
        if(arr.length <= 1)
            return elCount; 
        
        Arrays.sort(arr);
        int runLength = 1;
        for(int i = 0; i < arr.length - 1; ++i)
        {
            int cur = arr[i];
            int next = arr[i+1];
            if(cur == next)
                ++runLength;
            else if ( cur == next - 1)
            {
                elCount += runLength;
                runLength = 1;
            }
            else
                runLength = 1;
            
        }
        return elCount;
    }
}
