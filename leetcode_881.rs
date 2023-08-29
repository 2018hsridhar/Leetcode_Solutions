/*
URL := https://leetcode.com/problems/boats-to-save-people/
881. Boats to Save People

Greedy sort in DESC order?
Or not ... hang on
We should sort this ,,, but, can we pack folks in?

N or lgN time only 

E.g. limit = 6 and people = [5,4,3,2,1,1]
    3 boats total : (5,1) (4,1) and (3,2) match
    Coin changing esque?
    Boat carries at most two people at the same time, but infinite.

First LC done in Rust :-) 
23 minutes

*/
impl Solution {
    pub fn num_rescue_boats(people: Vec<i32>, limit: i32) -> i32 {
        let mut numBoats = 0;
        // Unstable sorting ok : faster when equal elements can be re-ordered
        // people not declared as mutable : mutable declaration here
        let mut peopleVec = people.clone();
        peopleVec.sort_unstable(); // vec<T> DerefMut<T> mutable
        // assign once only to an immutable var : not twice
        // bindable mutable var
        let mut lPtr = 0;
        let mut rPtr = peopleVec.len() - 1;
        while lPtr < rPtr {
            let leftEl = peopleVec[lPtr];
            let rightEl = peopleVec[rPtr];
            // println!("format lPtr={lPtr} \t rPtr={rPtr} arguments");
            if (leftEl + rightEl <= limit) {
                numBoats += 1;
                lPtr += 1;
                rPtr -= 1;
            } else { // right is to big here
                numBoats += 1;
                rPtr -= 1;
            }
        }
        if(lPtr == rPtr){
            numBoats += 1;
        }
        return numBoats;
    }
}
