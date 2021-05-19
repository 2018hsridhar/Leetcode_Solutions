// NOT A WORKING SOLUTION!

class Solution {
    public boolean isLongPressedName(String name, String typed) 
    {
        boolean isLPN = true;
        // CHECK BASE CASES
        if(name == null || typed == null ) 
            return true;
        if(name.length() == 0 && typed.length() == 0) 
            return true;
        
        char[] n_arr = name.toCharArray();
        char[] t_arr = typed.toCharArray();
        
        if(n_arr.length == 1)
        {
            if(t_arr.length == 2)
            {
                if(n_arr[0] == t_arr[0] && n_arr[0] == t_arr[1])    
                    return true;
            }
            if(t_arr.length == 1)
            {
                if(n_arr[0] == t_arr[0])
                    return true;
            }
                
        }
        
        // NOW THE NORMAL CASES
        
        int i = 0;
        int j = 0;
        
        
        while(i < n_arr.length && j < t_arr.length ) 
        {
            System.out.printf("i = %d \t j = %d \n", i, j);
            if((j+1) < t_arr.length && (i+1) < n_arr.length && n_arr[i] == t_arr[j] && n_arr[i+1] == t_arr[j+1])
            {
                ++i;
                ++j;
            }
            else if((j+1) < t_arr.length && n_arr[i] == t_arr[j] && n_arr[i] == t_arr[j+1])
            {
                ++i;
                ++j;
                ++j;
            }
            else if ( n_arr[i] == t_arr[j])
            {
                ++i;
                ++j;
            }
            else
            {
                isLPN = false;
                return isLPN;
            }
        }
        // if J still is not equal to end of str len case
        // System.out.printf("i = %d \t j = %d \n", i, j);
        if(i < n_arr.length || j < t_arr.length)
            return false;
        
        return isLPN;
    }
}
