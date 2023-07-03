/*
URL := https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/description/
2610. Convert an Array Into a 2D Array With Conditions
woah 8 mins to solution
*/
func findMatrix(nums []int) [][]int {
    resultMatrix := [][]int{}
    // idea 1 : sort by number of elements here
    // Then obtain the count of each elements?
    // Better : make array of array dynamically. 
    // Avoid sort operation to begin with
    countNumbers := make(map[int]int)
    for _, el := range nums {
        countNumbers[el]++
        newCount := countNumbers[el]
        if len(resultMatrix) < newCount {
            resultMatrix = append(resultMatrix, []int{})
        }
        resultMatIndex := newCount - 1
        resultMatrix[resultMatIndex] = append(resultMatrix[resultMatIndex], el) 
    }
    return resultMatrix
}
