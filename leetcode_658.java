/*
Wait - wasn't this provided on the interviewing.io platform already?
658. Find K Closest Elements
URL = https://leetcode.com/problems/find-k-closest-elements/


Distance minimization based on absolute values to a set of closest elements.
Notice the lexicographic ordering as well : Given a,b,x \in Z, if norm(a,x) =norm(a,b) AND a < b , then a is closer to x compared to b
-> Thus, ensure results sorted in STRICTLY acending order

Handle duplicates as wel too
utilizes arr.binarySearch(k) method too, to expedite problem solving

Guaranteed proper bounds and sorted order as well here\
Note : x can be below target OR above target as well here

*/
class Solution 
{
    public List<Integer> findClosestElements(int[] arr, int k, int x) 
    {
        LinkedList<Integer> kClosest = new LinkedList<Integer>();
        int tIdx = Arrays.binarySearch(arr, x);
        if(x < arr[0])
            tIdx = -1;
        else if ( x > arr[arr.length - 1])
            tIdx = arr.length;
        int leftPtr = tIdx - 1;
        int rightPtr = tIdx + 1; 
        int n = arr.length;
        System.out.printf("Target idx = %d \t arr length = %d\n", tIdx, arr.length);
        // Test out seperate iteration order here as well
        int i = 0;
        if(tIdx == -1)
        {
            kClosest.add(arr[0]);
            ++i;
        }
        if(0 <= tIdx && tIdx < arr.length)
        {
            // boolean expr1 = (0 <= tIdx);
            // boolean expr2 = (tIdx < arr.length);
            // System.out.println(expr1);
            // System.out.println(expr2);
            System.out.printf("Adding initial elements\n");
            kClosest.add(x);
            ++i;
        }
        while(i < k)
        {
            if(leftPtr >= 0 && rightPtr < n)
            {
                // System.out.printf("Going both ways");
                int leftVal = arr[leftPtr];
                int rightVal = arr[rightPtr];
                if(leftVal < rightVal)
                {
                    kClosest.addFirst(leftVal);
                    --leftPtr;
                }
                else
                {
                    kClosest.add(rightVal);
                    ++rightPtr;
                }
            }
            else if ( leftPtr >= 0 && rightPtr >= n)
            {
                // System.out.printf("Going left\n");
                int leftVal = arr[leftPtr];
                kClosest.addFirst(leftVal);
                --leftPtr;
            }
            else if ( leftPtr < 0 && rightPtr < n-1)
            {
                int rightVal = arr[rightPtr];
                kClosest.add(rightVal);
                ++rightPtr;
            }
            else
                break;
            ++i;
        }
        
        return kClosest;
    }
}
