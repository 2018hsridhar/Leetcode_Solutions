/*
URL := https://leetcode.com/problems/determine-the-minimum-sum-of-a-k-avoiding-array/
2829. Determine the Minimum Sum of a k-avoiding Array

Approach : Greedy <- always go for the minimum
Take note of special case with same elements : 3 + 3 = 6 type of thing : still need the 3 here, but, not the other 3 
Leverage sets to avoid adding duplicate elements
Variation on the 2SUM problem.
*/

use std::collections::HashSet;

impl Solution {
    pub fn minimum_sum(n: i32, k: i32) -> i32 {
        let mut minSum = 0;
        let mut keysHit = HashSet::new();
        let mut count = 0;
        let mut step = 1;
        while(count < n) {
            // println!("Step = {}\n", step);
            if keysHit.contains(&step) {
                step += 1;
            } else if !keysHit.contains(&step) {
                let diff = k - step;
                if(diff >= 0) {
                    // Insert bigger of 2 : { step,diff } = {1,4} or {2,3} type of thinking
                    if(diff > step) {
                        keysHit.insert(diff);
                    }
                } 
                minSum += step;
                step += 1;
                count = count + 1;
            }
        }
        return minSum;
    }
}
