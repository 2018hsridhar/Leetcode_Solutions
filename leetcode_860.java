
/*
860. Lemonade Change
https://leetcode.com/problems/lemonade-change/

THOUGHT PROCESS : 

[T,S] = [O(N), O(1)] ideally : linear-scan approach
No change initially : current_state = 0
Change value set = {5,10,20}

Many greedy problems seem to be based off of linear-scans, along arrays
And also entail a standard sorting approach

If customer provides $5.00 for transaction ( 1 lemonade ) => just collect as we go!


Edge tests : 
n = [0,1000]10000 max ( fine for linear scan ) 
bills[i] are reasonable values only

Handle null or empty array case ( set status initially equal to true ) 
Singleton cases : [5] = true : [10,20] = false

[5,5,5]
[10,10,10] or [15,15,15] should break
[15,10,5] should break ( strictly monotonically decreasing ) 
[5,10,15] should fail ( strictly monotonically increasing ) 

Change is always divisble by 5 too :-) 
Problem tougher because a $20 can be broken by two $10 bills too!

[5,5,5,10,20]
[5,5,5,20]

Not passing test case : [5,5,10,10,20]


*/


class Solution 
{
    public boolean lemonadeChange(int[] bills) 
    {
        int curChange = 0;
        boolean status = true;
        
        // Initialize hashmap with 0 amount of change per bill
        HashMap<Integer,Integer> change = new HashMap<Integer,Integer>();
        change.put(5,0);
        change.put(10,0);
            
        for(int i = 0; i < bills.length; ++i)
        {
            if(bills[i] == 5)
                change.put(5,change.get(5) + 1);
            else if ( bills[i] == 10)
            {
                if(change.get(5) >= 1)
                {
                    change.put(5, change.get(5) - 1);
                    change.put(10, change.get(10) + 1);
                 }
                else
                    return false;
            }
            // Careful : want greediness, but do not evaluate those other else-ifs
            // Hence the continue statement
            else if ( bills[i] == 20 )
            {
                // First, try to break into 10s ( decrease 10s as you go ) 
                if(change.get(10) >= 1 && change.get(5) >= 1 )
                {
                    change.put(10, change.get(10) - 1);
                    change.put(5, change.get(5) - 1 );
                }
                else if ( change.get(5) >= 3 )
                {
                    change.put(5, change.get(5) - 3);   
                }
                else
                    return false;
         
            }
        }
        return status;
    }
}
