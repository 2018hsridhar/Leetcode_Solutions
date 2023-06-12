/*
https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
We can avoid queue operations in their entirety or a need for a simulation
Just sort both the students and the sandwhiches, and check if there is a portion off
We have an infinite loop case here :-) 
    -> wait : this is the all or nothing case. Hang on

1700. Number of Students Unable to Eat Lunch

*/
func countStudents(students []int, sandwiches []int) int {
    countZero := 0
    countOne := 0
    n := len(students)
    for i := 0; i < n; i++ {
        if students[i] == 0 {
            countZero++
        } else {
            countOne++
        }
    }    
    numRemStudents := n
    for i := 0; i < n; i++ {
        if sandwiches[i] == 0 {
            if countZero > 0 {
                countZero--
                numRemStudents--
            } else {
                break
            }
        } else if sandwiches[i] == 1 {
            if countOne > 0 {
                countOne--
                numRemStudents--
            } else {
                break
            }
        }
    }
    return numRemStudents
}
