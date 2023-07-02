/*
URL := https://leetcode.com/problems/validate-stack-sequences/description/
946. Validate Stack Sequences

TEST CASES :
    PUSHED      , POPPED
Each of these should pass 
(A) [1,2,3,4,5], [1,2,3,4,5]
(B) [1,2,3,4,5], [5,4,3,2,1]
(C) [1,2,3,4,5], [1,3,5,4,2]

*/
func validateStackSequences(pushed []int, popped []int) bool {
    pushPtr := 0
    popPtr := 0
    var n = len(pushed)
    myCurStack := []int{}
    // popStack := make([]int, len(popped))
    // copy(popStack, popped)
    // first step check
    for pushPtr < n {
        myElToPush := pushed[pushPtr]
        myCurStack = append(myCurStack, myElToPush)
        myCurStackTopEl := myCurStack[len(myCurStack) -1]
        myElToPop := popped[popPtr]
        for myCurStackTopEl == myElToPop {
            // sadly must be a `re-slice` operation
            // no easy eviction
            // should do length check?
            myCurStack = myCurStack[:len(myCurStack) - 1]
            popPtr++
            if len(myCurStack) > 0 && popPtr < n {
                myCurStackTopEl = myCurStack[len(myCurStack) -1]
                myElToPop = popped[popPtr]
            } else {
                break
            }
        }
        pushPtr++
    }
    // second step check
    // reverse the slice here : check a zipper-merge works as expected
    // no good lib built-in for reversing slices in Go :-(
    if(len(myCurStack)) == 0 {
        return true
    } else if (len(myCurStack) > 0 ) {
        var curStackPtr = len(myCurStack) - 1
        for popPtr < n {
            topEl := popped[popPtr]
            curStackEl := myCurStack[curStackPtr]
            if topEl != curStackEl {
                return false
            } else if topEl == curStackEl {
                curStackPtr--
                popPtr++
            }
        }
    }
    return true
}
