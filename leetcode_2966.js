
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[][]}
 */
 /*
 URL := https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/
 2966. Divide Array Into Arrays With Max Difference

Complexity
Let N := len(nums)
Time := O(nlgn)
Space := O(N) ( EXP ) O(1) ( IMP ) 

Sort and greedy
Return any valid answer here
Size of 3 limitation -> we know a limitation here
We know each num must be used as well
Sorting tells us what index to look at next

Utilize min-max property

 */
var divideArray = function(nums, k) {
    myArrays = []
    // implicit lambda here ( is a sort in the hiding )
    // Convert to number form
    // Has to be a minus ( not a <> symbol ) WHATT
    nums.sort((a,b) => Number(a) - Number(b)) 
    console.log(nums)
    let n = nums.length
    for(let i = 0; i < n; i += 3) {
        let numOne = nums[i]
        let numTwo = nums[i+1]
        let numThree = nums[i+2]
        if(numThree - numOne <= k) {
            myArrays.push([numOne,numTwo,numThree])
        } else {
            return []
        }
    }
    return myArrays
};
