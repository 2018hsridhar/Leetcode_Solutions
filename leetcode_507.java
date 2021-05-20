class Solution {
    public boolean checkPerfectNumber(int num) 
    {
        // Squareroot is the max number here to test out ( x = sqrt(x) * sqrt(x) )
        // so instead of [1,x], we check through [1,sqrt(x)]
        // the case of "6" will trip you up : (2,3) and (3,2) are your divisor pairings! Avoid double counting here
        // make sure FLOOR function is used to prevent double counting ( CEIL function will not prevent doubel counting of divisors and quotients )
        
        boolean isPerfNum = false;
        int sum = 0; // solve over time
        if(num == 1 ) 
            return false;

        // (int) case is not known if it goes lower or goes higher : thus apply the Math.floor function here
        int sqrt = (int)(Math.floor(Math.sqrt(num)));

        System.out.printf("Sqrt = [%d]\n", sqrt);
        for(int i = 2; i <= sqrt; ++i)
        {
            int quotient = i;            
            if(num % quotient == 0)
            {
                int divisor = num / quotient;    
                System.out.printf("divisor = [%d] \t quotient = [%d]\n", divisor, quotient);
                sum += divisor;
                sum += quotient;
            }
        }
        
        // 1 is everywhere ( includes the divisor itself ) 
        // Hence, avoiding it in our for loop!
        sum += 1; 
        if(sum == num)
            isPerfNum = true;
        
        return isPerfNum;
    }
}
