/*

873. Length of Longest Fibonacci Subsequence
URL = https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/

Reasonably ounded input length 
No duplicates in the array as well

Recursively, how to get each subsequence as well?
Leverage striclty increasing property : you can not go further back in time


Complexity 
Let N := len(arr)
Time = O(N^2)
Space = O(N)

Base cases : arrays of length = {1,2} here


Edge Case Testing 
(A) [1,2,3,4,5,6,7,8]
(B) [1,3,7,11,12,14,18]
(C)[2,4,5,6,7,8,11,12,13,19]
    llfs = 5
    {2,5,7,12,19}
(D)
(E)
(F)
(G)

*/

class Solution {
    public int lenLongestFibSubseq(int[] arr) 
    {
        int llfs = 0; // default max is 0
        TreeMap<Integer,Integer> subseq_len = new TreeMap<Integer,Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2)
            {
                return (-1*i1.compareTo(i2));   // Nice use of pre-existing Integer obj comparison here :-)
            }
        });
        
        // A best pratice : fill in the keys as we already go
        int n = arr.length;
        for(int i = 0; i < n; ++i)
         subseq_len.put(arr[i],1);   
        
        // No need to consider first two elements here
        for(int i = 2; i < n; ++i)
        {
            int elem = arr[i];
            int elem_llfs = 1;
            // System.out.printf("Elem = %d\n", elem);
            int lowerBound = (elem / 2) + 1;
            int upperBound = elem - 1;
            // Iterate over keys existing
            Set<Integer> keys = subseq_len.keySet();
            Iterator<Integer> itr = keys.iterator();
            while(itr.hasNext())
            {
                int key = itr.next(); // the higher difference : must also be a fib
                // Oh I see why this is wrong : yes 13-7 = 6, but 7 is not part of the sequence here!! No fib property
                // 2, 4, 6, 7, 13 ... ok I see 7 != 4 + 6 here!
                if(lowerBound <= key && key <= upperBound)
                {
                    int difference = elem - key;
                    if(subseq_len.containsKey(difference))
                    {
                        elem_llfs = Math.max(elem_llfs, 2 + subseq_len.get(difference));
                    }
                }
            }
            System.out.printf("[%d,%d]\n", elem, elem_llfs);
            subseq_len.put(elem, elem_llfs);
            llfs = Math.max(llfs, elem_llfs);
        }
        if(llfs == 1)
            return 0;
        return llfs;
    }
}


/*
 Set<Integer> keys = subseq_len.keySet();
 Iterator<Integer> itr = keys.iterator();
 while(itr.hasNext())
     System.out.printf("%d\n", itr.next());
*/       
