/**
 * @param {number} numerator
 * @param {number} denominator
 * @return {string}
 */
 /*
 URL := https://leetcode.com/problems/fraction-to-recurring-decimal/

 Ensure to track if a numerator hit again or not -> append parantheses here
 Push numbers into an array and keep going
 Concat to string later at the end
 Capture different criteria

 Complexity
 Time = Until numerator hit again ( this is an enumerative algo ) 
 Space = O(enumerative) (EXP) O(1) ( IMP ) 

 Is good for building calculators :-) 
 Any repeating decimal must repeat from beginning
    - no 0.00234123123123... permissibility

  20 mins here

Givens
a. 1,2
b. 2,1
c. 4,333

My test cases
d. 1000,1
e. 22,7 XX
f. 4,40
g.234235, 123
h. 0, 123
i. 634,687
j. 634, 634
k. 1,100
l. 1,1
m. 593, 53
n.593, 53
o. 7,12 XXX
p. 2,3
q. 5, 74

Mostly right .. handle the negatives as well GAAAH

Getting there

Test case : 420, 226
 */
var fractionToDecimal = function(numerator, denominator) {
    if(numerator == 0){
      return "0"
    }
    numSigns = 0
    if(numerator < 0) {
      numSigns++
      numerator *= -1
    }
    if(denominator < 0 ) {
      numSigns++
      denominator *= -1
    }
    let myDotStatus = false
    myDotStatus = (numerator < denominator)
    let myRepeatStatus = false
    let myNumerals = []
    // Set() needs new
    let myHitNumerators = new Map() // MDN Web Docs
    let myRepNum = -1
    let idx = 0;
    // Provided numerator is to right of decimal place as well
    while(numerator != 0) {
        if(myHitNumerators.has(numerator)) {
            myRepNum = numerator
            myRepeatStatus = true
            break
        } else {
            if(myDotStatus) {
              myHitNumerators.set(numerator,idx)
            }
            let rem = numerator % denominator
            // No explicit int here
            let quotient = Math.floor(numerator / denominator)
            myNumerals.push(quotient) // append to right
            if(rem == 0) {
              break
            }
            if(rem != 0) {
              myDotStatus = true
            } 
            numerator = rem * 10
        }
        idx++
    }
    // Splicing adds array els :-) 
    if(myDotStatus) { // always after the decimal point as well
        myNumerals.splice(1,0,'.') // splice to add els
        // Guarantee of a repeat followig a dot only should hold true as well
        if(myRepeatStatus){ // not always true :-) ---> must notice position of num hit!
            let leftIdxRepNum = myHitNumerators.get(myRepNum)
            myNumerals.splice(leftIdxRepNum+1,0,'(')
            myNumerals.push(')')
        }
    }
    if(numSigns == 1) {
      myNumerals.splice(0,0,"-")
    }
    let myDecimal = myNumerals.join('')
    return myDecimal
};
