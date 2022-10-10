/*
1496. Path Crossing
URL = https://leetcode.com/problems/path-crossing/

*/
class Solution {
    public boolean isPathCrossing(String path) {
        boolean status = false;
        Map<Integer,Set<Integer>> coordsHit = new HashMap<Integer,Set<Integer>>();
        int[] curCoord = new int[]{0,0};
        coordsHit.put(0, new HashSet<Integer>());
        // I like how one can invoke set.add() without asserting el existence :-)
        coordsHit.get(0).add(0); 
        for(int i= 0; i < path.length(); ++i){
            char dir = path.charAt(i);
            switch(dir) {
                case 'N':
                    curCoord[1] += 1;
                    break;
                case 'E':
                    curCoord[0] += 1;
                    break;
                case 'S':
                    curCoord[1] -= 1;
                    break;
                case 'W':
                    curCoord[0] -= 1;
                    break;
                default:
                    break;
            }
            // Hit new point : check if a repeat exists
            // Nested conditional logic
            if(coordsHit.containsKey(curCoord[0])){
                if(coordsHit.get(curCoord[0]).contains(curCoord[1])){
                    status = true;
                    break;
                }
            }
            // Go add the coordinate
            if(!coordsHit.containsKey(curCoord[0]))
                coordsHit.put(curCoord[0],new HashSet<Integer>());
            Set<Integer> firstCoordX = coordsHit.get(curCoord[0]);
            firstCoordX.add(curCoord[1]);
            
        }
        return status;
    }
}
