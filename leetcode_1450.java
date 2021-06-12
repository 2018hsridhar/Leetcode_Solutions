1450. Number of Students Doing Homework at a Given Time
https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time/

class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) 
    {
        int numStudents = 0;
        for(int i = 0; i < startTime.length; ++i)
        {
            int sTime = startTime[i];
            int eTime = endTime[i];
            if(sTime <= queryTime && queryTime <= eTime) ++numStudents;
        }
        return numStudents;
    }
}
