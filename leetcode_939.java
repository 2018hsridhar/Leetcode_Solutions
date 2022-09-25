/*
939. Minimum Area Rectangle
URL = https://leetcode.com/problems/minimum-area-rectangle/

Edge Caess
(A) [[2,4],[6,45],[5,1],[10,234],[23,123]] 
    => 0
(B) [[1,1],[1,2],[1,3],[1,4],[1,5]]
    => 0
(C) [[1,1],[1,2],[1,3],[1,4],[1,5],[3,1],[3,3]]
    => 4
(D) 


*/
class Solution {
    
    public class customComparator implements Comparator<int[]>
    {
        public int compare(int[] one, int[] two)
        {
            if(one[0] < two[0]) {
                return -1;
            } else if ( one[0] > two[0]) {
                return 1;
            } else {
                if(one[1] < two[1]){
                    return -1;
                } else if ( one[1] > two[1]) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
    
    public int minAreaRect(int[][] points) {
        
        // [1] Sort points : incr(x) then incr(y) here
        // [2] Create hashmaps : x -> what is its y here too.
        // [3] Do pairwise tests here as the `x`'s are affixed too ( so )
        // Goal : reduce space state to search for the 4 points under comparisons here.
        // Leverage the diagonal instead
        // Can constrain to a single direction test too.
        // Leverage hasmaps<int,hashset<int>> type of logic here too!
        int minArea = Integer.MAX_VALUE;      
        Arrays.sort(points, new customComparator());
        // It is not an ordered map yet, so take note of this!
        // We could order the map!
        Map<Integer,HashSet<Integer>> hm = new TreeMap<Integer,HashSet<Integer>>();
        for(int i = 0; i < points.length; ++i){
            int[] point = points[i];
            if(hm.containsKey(point[0])){
                hm.get(point[0]).add(point[1]);
            } else {
                hm.put(point[0], new HashSet<Integer>());
                hm.get(point[0]).add(point[1]);
            }
        }
        
        boolean hitRect = false;
        Set<Map.Entry<Integer,HashSet<Integer>>> entries = hm.entrySet();
        for(Map.Entry<Integer,HashSet<Integer>> bottomLeft : entries) {
            // NavigableMap<Integer,HashsSet<Integer>> greaterPoints = hm.tailMap(bottomLeft.getKey(),false); // not inclusive here 
            for(Map.Entry<Integer,HashSet<Integer>> topRight : entries) {
                int leftX = bottomLeft.getKey();
                int rightX = topRight.getKey();
                Set<Integer> bottomLeftYs = bottomLeft.getValue();
                Set<Integer> topRightYs = topRight.getValue();
                if(topRight.getKey() > bottomLeft.getKey()){
                    for(int by : bottomLeftYs) {
                        for(int tr : topRightYs) {
                            if(by < tr) {
                                if(hm.get(leftX).contains(tr) && hm.get(rightX).contains(by)){
                                    int curArea = (rightX-leftX) * (tr-by);
                                    minArea = Math.min(minArea,curArea);
                                    hitRect = true;
                                }                                
                            }
                        }
                    }
                }
            }
        }
        if(!hitRect){
            minArea = 0;
        }
        return minArea;
    }
}
