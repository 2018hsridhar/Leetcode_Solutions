/*
848. Shifting Letters
url = https://leetcode.com/problems/shifting-letters/
*/
class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        // [1] Calculate the total shift
        int n = s.length();
        int maxShift = 0;
        int[] elShifts = new int[n];
        for(int i = shifts.length - 1; i >= 0; --i) {
            int el = shifts[i];
            maxShift += el;
            maxShift = maxShift % 26;
            elShifts[i] = maxShift;
        }
        
        // [2] Apply the actual shift
        char[] c_arr = s.toCharArray();
        for(int i = 0; i < c_arr.length; ++i) {
            int int_form = (int)(c_arr[i] - 'a');
            // System.out.printf("inf form = %d\n", int_form);
            int_form = (int_form + elShifts[i] ) % 26;
            // System.out.printf("inf form = %d\n", int_form);
            char res = (char)(int_form + (int)'a');
            c_arr[i] = res;
        }
        
        String result = new String(c_arr);
        return result;
    }
}
