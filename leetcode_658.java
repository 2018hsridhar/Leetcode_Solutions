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

It is finnicy due to dumb bounds here for sure -> really have to go with the generalized binary search
Also having to account for original element is a bit of a PITA!

[1,2,3,4,5]
3
17

*/

class encap
{
    public int val;
    public int dist;
    public encap(){}
    public encap(int val, int dist){this.val = val; this.dist = dist;}
}

class Solution 
{
    public List<Integer> findClosestElements(int[] arr, int k, int x) 
    {
        LinkedList<Integer> kClosest = new LinkedList<Integer>();
        if(x < arr[0])
        {
            for(int i = 0; i < k; ++i)
            {
                if(i >= arr.length)
                    break;
                kClosest.add(arr[i]);
            }
            return kClosest;
        }
        // These k closest do not have to be sorted themselves by their distance of closeness ( e.g. given target == 6, return {5,4,3})
        // but rather, but ascending order ( e..g {3,4,5} for target = 6)
        else if(x > arr[arr.length - 1])
        {
            int kCount = 0;
            for(int i = arr.length - 1; i >= 0; --i)
            {
                kClosest.addFirst(arr[i]);
                ++kCount;
                if(kCount == k) break;
            }
            return kClosest;
        }
        
        // Now for the case where target is guaranteed in bounds of [low,high] here
        // That is, arr[0] <= target <= arr[arr.length - 1]
        PriorityQueue<encap> maxHeap = new PriorityQueue<encap>(new Comparator<encap>() {
            @Override
            public int compare(encap a, encap b) {
                return b.dist - a.dist; 
            }
        }); 
        
        // Max heap can maintain a notion of distance ... but not the elements themselves, UNLESS, we have some pairwise comparison stuff 
        // taking place here as well
        
        for(int i = 0; i < arr.length; ++i)
        {
            int val = arr[i];
            int dist = Math.abs(val - x);
            encap ec = new encap(val,dist);
            if(maxHeap.size() >= k)
            {
                encap curMaxEncap = maxHeap.peek();
                int curMax = curMaxEncap.dist;
                if(dist < curMax)
                {
                    maxHeap.poll();
                    maxHeap.add(ec);    
                }
            }
            else
                maxHeap.add(ec);    
        }
        
        while(maxHeap.size() > 0)
        {
            encap ec = maxHeap.poll();
            kClosest.add(ec.val);
        }
        Collections.sort(kClosest);
        
        return kClosest;
    }
}
