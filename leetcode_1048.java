/*


Word length is high, but the length of each word here be reasonable
URL = https://leetcode.com/problems/longest-string-chain/
1048. Longest String Chain


*/
class Solution 
{
    public int longestStrChain(String[] words) 
    {
        int lsc = 1;
        // [1] Sort operation
        Arrays.sort(words, new Comparator<String>(){
            @Override
            public int compare(String one, String two)
            {
                if(one.length() < two.length())
                    return -1;
                else if ( one.length() > two.length())
                    return 1;
                return 0;
            }
        });
            
        // [2] Create <K,V> store for DP computations
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        String s = "bcad";
        // String temp = s.substring(0,3) + s.substring(4,4);
        // System.out.printf("%s\n", temp);
        for(String word : words)
        {
            if(word.length() == 1)
                hm.put(word,1);
            else
            {
                int curLSC = 1;
                int strLen = word.length();
                for(int i = 0; i < strLen; ++i)
                {
                    String predecessor = word.substring(0,i) + word.substring(i+1,strLen);
                    if(hm.containsKey(predecessor))
                    {
                        curLSC = Math.max(curLSC, 1 + hm.get(predecessor));
                    }
                }
                hm.put(word,curLSC);
                lsc = Math.max(lsc, curLSC);
            }
        }
        
        return lsc;
    }
}
