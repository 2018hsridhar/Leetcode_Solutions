/*

Is more trivial than it seems
The destination is only a secondary element, but is never a primary element
We can keep visited sets here and check each city for being in strictly the DEST set, but not the SOURCE set. As we entertain three distinct combinations for a city 

inSource = T, inDest = F
inSource = T, inDest = T
intSource = F, intDest = T

1436. Destination City
URL = https://leetcode.com/problems/destination-city/

Complexity
Let N := number cities
let M := number paths
Time = O(M)
Space = O(N)

Worst case : must traverse each path ( as if we started at "A" and went as follows : 
    A->B->C->D->E
)

It can not just be one set add/remove : how to distinguish the beginning from the end here then?
We are really working with a small key set here
Challenge - code strictly via iterators 

Come up with brute force solution and then optimize for it afterwards


*/
class Solution 
{
    public String destCity(List<List<String>> paths) 
    {
        String destCity = "";
        Iterator<List<String>> pathsItr = paths.iterator();
        HashMap<String,Boolean> hasDest = new HashMap<String,Boolean>();
        while(pathsItr.hasNext())
        {
            List<String> path = pathsItr.next();
            Iterator<String> nodes = path.iterator();
            String src = nodes.next();
            String dst = nodes.next();
            if(!hasDest.containsKey(src))
                hasDest.put(src,true);
            else
                hasDest.put(src,true);
            if(!hasDest.containsKey(dst))
                hasDest.put(dst,false);
        }
        
        // Iterate over hm keys and check now
        Set<Map.Entry<String,Boolean>> pairs = hasDest.entrySet();
        for(Map.Entry<String,Boolean> pair : pairs)
            if(pair.getValue() == false)
                return pair.getKey();
        
        return destCity;
    }
}
