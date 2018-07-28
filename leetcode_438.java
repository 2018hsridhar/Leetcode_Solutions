// https://leetcode.com/problems/find-all-anagrams-in-a-string/

public class Solution {
    public List<Integer> findAnagrams(String s, String p)
    {
        if(s == null || p == null)
        {
            return null;
        }
        LinkedList<Integer> results = new LinkedList<Integer>();
        if ( s.length() < p.length())
        {
            return results;
        }
        else
        {
            int[] sMap = new int[26];
            int[] pMap = new int[26];
            
            char[] sArr = s.toCharArray();
            char[] pArr = p.toCharArray();
            
            for(int i = 0; i < pArr.length; i++)
            {
                pMap[(int)(pArr[i] - 'a')]++;
            }
            
            // set up initial sMap ... to pArr-1, # of characters, initially!
            // s: "cbaebabacd" p: "abc" ... so iterate over "cb", tbh. then start @ a
            // keep updating this, over time!
            // in a sense, I'm doing a "sliding window" esque processing ! ... thus, a bit more difficult to analyze
            // also ... working with arrays, instead of HM.
            int elimIdx = 0;
            for(int i = 0; i < pArr.length - 1; i++)
            {
                //sMap[(int)(pArr[i] - 'a')]++;
                sMap[(int)(sArr[i] - 'a')]++;
            }
            
            // I expect first case to pass, @ least
            //for(int i = pArr.length - 1; i < sArr.length - pArr.length; i++) ... also was a bug !
            for(int i = pArr.length - 1; i < sArr.length; i++)
            {
                sMap[(int)(sArr[i] - 'a')]++;
                // for(int x = 0; x < 26; x++)
                // {
                //     System.out.println(x + "\t" + sMap[x] + "\t" + pMap[x]);
                // }
                if(overlapMaps(sMap,pMap))
                {
                    results.add(i - (pArr.length - 1));
                }
                    // get rid of the 1st idx, in sMap!
                    sMap[(int)(sArr[elimIdx] - 'a')]--;
                    elimIdx++;
                // } ... also a bug
            }
            
        }
        return results;
    }
    
    // algo performance :: O(s) time, O(1) space
    // O(1) = o(26) # of comparisons
    public boolean overlapMaps(int[] sMap, int[] pMap)
    {
        boolean stat = true;
        for(int i = 0; i < pMap.length; i++)
        {
            if(pMap[i] > sMap[i])
            {
                stat = false;
                break;
            }
            // clearly, sMap has an insufficient # of characters
        }
        return stat;
    }
    
}
