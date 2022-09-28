/*
2138. Divide a String Into Groups of Size k
URL = https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/
*/
class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        int numGroups = (int)Math.ceil((double)n / (double)k);
        String[] partitions = new String[numGroups];
        int i = 0;
        int wIdx = 0;
        while(i < n && i+k <= n) {
            partitions[wIdx++] = s.substring(i,i+k);
            i += k;
        }
        int diff = (n - i);
        if(diff != 0)
            diff = k - diff;
        if(diff > 0) {
            StringBuilder repeat = new StringBuilder("");
            for(int a = 0; a < diff; a++){
                repeat.append(fill);
            }
            // Notice use of String.format and format specificer syntax for concatenation.
            String lastGroup = String.format("%s%s",s.substring(i), repeat);
            partitions[wIdx] = lastGroup;
        }
        return partitions;
    }
}
