/*
URL := https://leetcode.com/problems/maximum-product-after-k-increments/
2233. Maximum Product After K Increments
Converted to doubles - handles modulo - works GAAAAH precision erroring
*/
class Solution {
    public int maximumProduct(int[] nums, int k) {
        double maxProd = 1;
        double mod = (double)(Math.pow(10,9)) + 7;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
        }
        for(int a = 0; a < k; a++) {
            int min = pq.poll();
            int nextMin = min + 1;
            pq.add(nextMin);
        }
        while(!pq.isEmpty()){
            int el = pq.poll();
            maxProd *= (double)el;
            maxProd %= mod;
        }
        return (int)(maxProd % mod);
    }
}
