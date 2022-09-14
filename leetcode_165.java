/*

165. Compare Version Numbers
URL = https://leetcode.com/problems/compare-version-numbers/

Approach
- it is a typical string problem with two strings as input
- no need to clean user input
- it need NOT be true : len(v1) = len(v2). Their lengths can differentiate.
- Partition by the `.` delimeter.
- if len = 1 and char = 0 ; this is a "TRUE" zero
- if len > 1 BUT char(i) == char(i+1) and char(i) = 0 -> just continue
- store the start,end index for substring operations later for Integer.ParseInt.
- zipper merge approach needed

TEST CASES ( *** FOCUS HERE *** ) 
(A) "0.1","0.10.10.1" => -1 
(B) "0.1","0.1.0.0" => 0
(C) "0.1.0.0","0.1" => 0"
(D) "4.3.2.1","1.2.3.4" -> 1
(E) "04.03.02.01","4.3.2.1" -> 0
(F) "1.1","000000001.1" -> 0
(G) "0.1.1000000.001"
"0.00001.1000000.000001" -> 0
(H) "000.0.000.0"
"0.00.0.0000" -> 0

Complexity
Let N := #-revisions in a version
TIME = O(N)
SPACE = O(1) ( EXPLICIT ) O(1) ( IMPLICIT )


*/
class Solution {
    public int compareVersion(String version1, String version2) 
    {
        int res = 0; // Assuming v1 = v2
        String delimeter = "\\."; // note use of double backslash - not single backslash - for the escape character sequence
        String[] tokens1 = version1.split(delimeter);
        String[] tokens2 = version2.split(delimeter);
        int ptr1 = 0;
        int ptr2 = 0;
        int m = tokens1.length;
        int n = tokens2.length;
        while(ptr1 < m && ptr2 < n){
            String rev1 = tokens1[ptr1];
            String rev2 = tokens2[ptr2];
            int val1 = parseFromRevision(rev1);
            int val2 = parseFromRevision(rev2);
            if(val1 < val2) {
                return -1;
            } else if ( val1 > val2) {
                return 1;
            }
            ptr1++;
            ptr2++;
        }
        // Can still iterate over Version_1
        while(ptr1 < m) {
            String rev1 = tokens1[ptr1];
            int val1 = parseFromRevision(rev1);
            int val2 = 0;
            if(val1 < val2) {
                return -1;
            } else if ( val1 > val2) {
                return 1;
            }
            ptr1++;
        }
        // Can still iterate over Version_2     
        while(ptr2 < n) {
            String rev2 = tokens2[ptr2];
            int val1 = 0;
            int val2 = parseFromRevision(rev2);
            if(val1 < val2) {
                return -1;
            } else if ( val1 > val2) {
                return 1;
            }
            ptr2++;
        }
        return res; // really res = 0; strings were equal!
    }
    
    // Is one of the revision numbers : may be "0000001" or "0" or "100"
    public int parseFromRevision(String rev)
    {
        int lPtr = 0;
        int rPtr = rev.length() - 1;
        // Find index of first non-zero '0' character
        while(lPtr < rPtr) {
            if(rev.charAt(lPtr) == rev.charAt(lPtr+1) && rev.charAt(lPtr) == '0')
            {
                lPtr++;
            }
            else
                break;
        }
        String desired = rev.substring(lPtr,rPtr + 1); // may need a "one-offset" here
        int parsedVal = Integer.parseInt(desired);
        return parsedVal;
    }
    
}















