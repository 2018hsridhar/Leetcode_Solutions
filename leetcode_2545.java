/*
This is an array of arrays
Can we treat the input as array(int[] arr) : each element int[] arr
Lambda function for safety ( why create a class accessible by other classes in this file )?

URL = https://leetcode.com/problems/sort-the-students-by-their-kth-score/description/
2545. Sort the Students by Their Kth Score

*/
class Solution {
    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                // Intentional: Reverse order for this demo
                int cmp = Integer.compare(o2[k], o1[k]); // in Java 7
                // Avoid object creatoin
                // Beware integer overflow for a - b or a + b\
                return cmp;
            }
        });
        return score;
    }
}
