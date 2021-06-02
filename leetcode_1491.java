/*

1491. Average Salary Excluding the Minimum and Maximum Salary
URL = https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/

*/

class Solution {
    public double average(int[] salary)
    {
        Arrays.sort(salary);
        int n = salary.length;
        double min = salary[0];
        double max = salary[n - 1];
        double sum = 0;
        for(int i = 0; i < salary.length; ++i)
            sum += salary[i];
        sum -= min;
        sum -= max;
        return sum / (n-2);
    }
}
