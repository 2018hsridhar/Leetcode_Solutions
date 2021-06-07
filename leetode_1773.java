
/*
1773. Count Items Matching a Rule
https://leetcode.com/problems/count-items-matching-a-rule/

Iterator syntax is topic because it helps return object types easily
Also lends nice support for pointer types

Why is java is string comparison based on the .equals() method? It is becuase the "==" method compares pointers/addreses - not the objects/strings themselves ( you need a DEREFENCING operator here ) !
*/

class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) 
    {
        int numMatches = 0;
        Iterator<List<String>> itr = items.iterator();
        while(itr.hasNext())
        {
            List<String> triplet = itr.next();
            Iterator<String> vals = triplet.iterator();
            String type = vals.next();
            String color = vals.next();
            String name = vals.next();
            if(ruleKey.equals("type") && type.equals(ruleValue))
                ++numMatches;
            else if ( ruleKey.equals("color") && color.equals(ruleValue))
                ++numMatches;
            else if ( ruleKey.equals("name") && name.equals(ruleValue))
                ++numMatches;                
        }
        return numMatches;
    }
}
