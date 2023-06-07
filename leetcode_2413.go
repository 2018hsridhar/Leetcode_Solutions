/*
URL = https://leetcode.com/problems/smallest-even-multiple/
2413. Smallest Even Multiple
Conditional logic variable value/expression assignment -> ternary operator preferred
*/
func smallestEvenMultiple(n int) int {
    sem := 0
    if n % 2 == 0 && n % n == 0 {
        sem = n
    } else {
        sem = 2 * n
    }
    // Go design choice: no to ternary operators.
    // sem = (n % 2 == 0 && n % n == 0) ? n : 2 * n
    return sem
}
