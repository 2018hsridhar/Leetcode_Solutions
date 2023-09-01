*
URL := https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/
1605. Find Valid Matrix Given Row and Column Sums

Let M, N := dims(row_sum),dims(col_sum)
Time := O(MN)
Space := O(MN) ( EXP ) O(1) ( IMP ) 

woah 83/84 passing
you're close
Approach is correct
It's probably a precision issue coming up
*/
impl Solution {

    // publish function syntax
    // & vs &mut
    pub fn getRowSum(r:usize, matrix: &Vec<Vec<i128>>) -> i128 {
        let mut rowSum = 0;
        let n = matrix[0].len();
        for c in 0..=n-1 {
            rowSum += matrix[r][c];
        }
        return rowSum; 
    }

    pub fn restore_matrix(row_sum: Vec<i32>, col_sum: Vec<i32>) -> Vec<Vec<i32>> {
        let m = row_sum.len();
        let n = col_sum.len();
        let mut matrix: Vec<Vec<i128>> = Vec::with_capacity(m * n);
        for _ in 1..=m { // inclusive range of 1..m
            let mut vecRow = Vec::with_capacity(n);
            vecRow.resize(n,0);
            matrix.push(vecRow);
        }
        // woooh throw away values
        // array indexing for vectors too?
        // preserve column sums, BUT, fix the row sums as we go
        // maximal at top too -> never an issue going downards
        for c in 0..=n-1 as usize {
            matrix[0][c] = col_sum[c] as i128;
        }
        for r in 0..m-1 as usize {
            let expectedRowSum = row_sum[r] as i128; 
            // name resolution part of scoping here
            // Woah tells copy move trait : pass as reference only 
            // pass by reference to avoid mutations
            let mut actualRowSum = Solution::getRowSum(r,&matrix);
            let nextRow = r + 1;
            while(actualRowSum > expectedRowSum) {
                for c in 0..=n-1{
                    if(actualRowSum - matrix[r][c] > expectedRowSum) {
                        let delta = matrix[r][c];
                        matrix[r][c] -= delta;
                        matrix[nextRow][c] += delta;
                        actualRowSum -= delta;
                    } else {
                        let delta = (actualRowSum - expectedRowSum);
                        matrix[r][c] -= delta;
                        matrix[nextRow][c] += delta;
                        actualRowSum -= delta;
                        break;
                    }
                }
            }
        }
        // gaaah Display printing
        // also the heck :? printing?
        // Type annotation strictness in language
        let mut matrix32: Vec<Vec<i32>> = Vec::with_capacity(m * n);
        for _ in 1..=m { // inclusive range of 1..m
            let mut vecRow = Vec::with_capacity(n);
            vecRow.resize(n,0);
            matrix32.push(vecRow);
        }
        // langauge itself lacking in implicit conversions
        // only explicit conversions : makes for stronger type safety too!
        // gaaaah = vs non equals here
        // gaaah integer overflowing too :-(.
        for r in 0..m as usize {
            for c in 0..n as usize {
                matrix32[r][c] = matrix[r][c] as i32;
            }
        }
        return matrix32;
    }
}
