
/*
359. Logger Rate Limiter
https://leetcode.com/problems/logger-rate-limiter/

THOUGHT PROCESSES : 

Classes = blueprint for objects : they encode the state and methods of said objects
If just one message type ( e.g. foo ) - akin to earlier, just track a scalar
Timestamps are guaranteed a bound in the closed interval of [0, 10^9]
Timestamps passed in non-decreasing ( chronological ) ordering
Idea : utilize a HashTable here

Typical : when working with pairs of data => utilize hashmap or array data structures
Can easily obtain the intervals (t+10) and (t-10)


*/


class Logger
{
    HashMap<String,Integer> timestamps;

    /** Initialize your data structure here. */
    public Logger() 
    {
        timestamps = new HashMap<String,Integer>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) 
    {
        boolean status = false;
        if(!timestamps.containsKey(message))
        {
            timestamps.put(message, timestamp);
            status = true;
        }
        else
        {
            int curTime = timestamps.get(message);
            if(curTime <= timestamp - 10 )
            {
                timestamps.put(message, timestamp);
                status = true;
            }
            else
            {
                status = false;
            }
        }
        return status;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
