/*
1257. Smallest Common Region
URL = https://leetcode.com/problems/smallest-common-region/


*/
class Solution {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        // [1] Set up a hashmap of relationships for child->parent tracking
        Map<String,String> ancestors = new HashMap<String,String>();
        for(List<String> regionListing : regions ) {
            String parent = regionListing.get(0);
            for(int i = 1; i < regionListing.size(); ++i) {
                String child = regionListing.get(i);
                // only once ancestors too!
                if(!ancestors.containsKey(child)){
                    ancestors.put(child,parent);
                } 
            }
        }
        
        // [2] Having HM filled up -> search for the region ( its existence is guaranteed )
        // Note that dept of each path is not known here -> can we avoid generating the list?
        List<String> r1Path = getPathUp(region1,ancestors);
        List<String> r2Path = getPathUp(region2,ancestors);
        
        // [3] Iterate backwards from the lists too
        int ptr1 = r1Path.size() - 1;
        int ptr2 = r2Path.size() - 1;
        while(ptr1 >= 0 && ptr2 >= 0 && r1Path.get(ptr1).equals(r2Path.get(ptr2))){
            ptr1--;
            ptr2--;
        }
        String smallestRegion = r1Path.get(ptr1+1);        
        return smallestRegion;
    }
    
    // NOTE : Generalize your return type ( to an interface ) 
    private List<String> getPathUp(String x, Map<String,String> ancestors)
    {
        List<String> regionsPath = new ArrayList<String>();
        StringBuilder sb = new StringBuilder("");
        sb.append(x);
        regionsPath.add(x);
        while(ancestors.containsKey(sb.toString())){
            String parent = ancestors.get(sb.toString());
            regionsPath.add(parent);
            sb.delete(0,sb.length());
            sb.append(parent);
        }
        return regionsPath;
    }
    
}
