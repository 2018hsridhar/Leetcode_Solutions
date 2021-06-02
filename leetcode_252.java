/*

252. Meeting Rooms
https://leetcode.com/problems/meeting-rooms/

THOUGT PROCESSES : 
- AKIN TO INTERVAL MERGE QUESTIONS
- Sort the time intervals array ahead of schedule
--> Needs own compareTo and class ... again ... :-(
- Then figure out if bounded or not too, from there :-)


*/



class Solution {
    public boolean canAttendMeetings(int[][] intervals) 
    {
        if(intervals == null || intervals.length == 0)
            return true;
        
        boolean status = true;
        Arrays.sort(intervals, new Comparator<int[]>()
            {
                @Override
                public int compare(int[] e1, int[] e2)
                {
                    if(e1[0] < e2[0])
                        return -1;
                    else if ( e1[0] > e2[0])
                        return 1;
                    return 0;
                }
            });
        int curEndTime = intervals[0][1];
        // start at second interval and execute check
        for(int i = 1; i < intervals.length; ++i)
        {
            int[] myInt = intervals[i];
            int curStartTime = myInt[0];
            if(curStartTime >= curEndTime)
            {
                curEndTime = myInt[1];
            }
            else
            {
                status = false;
                break;
            }
            // System.out.printf("Processing interval = [%d,%d]\n", myInt[0], myInt[1]);
        }
        
        return status;
    }
}
