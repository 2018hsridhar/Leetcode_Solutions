public class Solution {
    
    public int search(int[] A, int T) 
   {
      // HANDLE a const array case (i.e.[5,5,6,6,6,3,3,3,3,4,4,4,])
      if(A == null || A.length == 0) 
      {
         return -1;
      }
      
      // mid idx @ 2
      // low = 0, high = len - 1 = 5
         // case 1 :  [2, 4, 5, 9, 12, 17] - shift by 0, or by multiples of 6
        // case 2 : [9, 12, 17, 2, 4, 5] - shifted by 3 
        // case 3 : [12,17,2,4,5,9] - shifted by 5
      
      // T = 4
      // case 1 : [2,5] ... A[low] = 2 < 4 < A[mid] = 5
      // case 2 : [2,5] ... [mid+1, high]
      // case 3:: [2,9] ... 
      // you seem to be getting disconnected? hello? 
      
      int low = 0;
      int high = A.length - 1;
      while(low <= high) 
      {
         int mid = (low + high) / 2;
         if(A[mid] == T)
         {
            return mid; 
         }
         // range [low,high] is in sorted order
         else if(A[low] <= A[mid] && A[mid] <= A[high])
         {
            if(A[low] <= T && T <= A[mid])
            {
                 high = mid - 1;
            }
            else
            {
               low = mid + 1;
            }
         }
         // higher range is sorted in ord, from mid elem
         else if (A[low] >= A[mid] && A[high] >= A[mid])
         {
            if(A[mid] <= T && T <= A[high])
            {
               low = mid + 1;
            }
            else
            {
               high = mid - 1;
            }
         }
         // low range, up to the mid elem, is sorted in ord
         else if(A[low] <= A[mid] && A[high] <= A[mid] )
         {
            if(A[low] <= T && T <= A[mid])
            {
               high = mid - 1;
            }
            else
            {
               low = mid + 1;
            }
         }
      }
      
      return -1; // not found case
   }
   
}
