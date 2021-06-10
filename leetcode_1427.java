/*

1427. Perform String Shifts
https://leetcode.com/problems/perform-string-shifts/

Can we combine all operations ( somewhat like a transitive/associative relationship ) and just know what the final operation is? 
This will really test your knowledge of the modulus operator!

You must remember how to use the modulus operator, to rotate elements in an array ( and possibly other data structures, such as ArrayLists ) 

*/

class Solution 
{
    public String stringShift(String s, int[][] shift) 
    {
        int n = s.length();
        char[] c_arr = s.toCharArray();
        char[] output_arr = new char[n];
        int finalShift = 0;
        for(int i = 0; i < shift.length; ++i)
        {
            int[] oper = shift[i];
            int dir = oper[0];
            if(dir == 1)
                finalShift += oper[1];
            else
                finalShift -= oper[1];
            finalShift %= n;
        }
        if(finalShift < 0)
            shift(c_arr, output_arr, 0, finalShift * -1 );
        else
            shift(c_arr, output_arr, 1, finalShift);
        return new String(output_arr);
    }
    
    public void shift(char[] c_arr, char[] output_arr, int dir, int offset)
    {
        int n = c_arr.length;
        for(int i = 0; i < c_arr.length; ++i)
        {
            int i_p = ( i + offset ) % n;
            if(dir == 0)
                i_p = ((i - offset) + n ) % n;
            output_arr[i_p] = c_arr[i];
        }
    }
}
