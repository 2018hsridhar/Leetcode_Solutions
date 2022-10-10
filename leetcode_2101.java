/*
2101. Detonate the Maximum Bombs
URL = https://leetcode.com/problems/detonate-the-maximum-bombs/

*/
class Solution {
    
    // Class members for speed
    Map<Integer,Set<Integer>> detSeq;
    Set<Integer> visited;
    public int maximumDetonation(int[][] bombs) {
        int maxBombs = 1;
        int n = bombs.length;
        detSeq = new HashMap<Integer,Set<Integer>>();
        visited = new HashSet<Integer>();
        for(int parentBombIdx = 0; parentBombIdx < n; parentBombIdx++){
            Set<Integer> parentSeq = new HashSet<Integer>();
            int[] parentBomb = bombs[parentBombIdx];
            if(!detSeq.containsKey(parentBombIdx)) {
                parentSeq.add(parentBombIdx);
                helper(parentSeq, bombs,parentBombIdx);
                detSeq.put(parentBombIdx, parentSeq);
            }
            maxBombs= Math.max(maxBombs, detSeq.get(parentBombIdx).size());
            visited.clear();
        }
        return maxBombs;
    }
    
    private void helper(Set<Integer> parentSeq,int[][] bombs, int startIdx)
    {
        // Avoid recursion : do iteratively
        int n = bombs.length;
        Stack<Integer> dfs = new Stack<Integer>();
        dfs.add(startIdx);
        parentSeq.add(startIdx);
        while(!dfs.isEmpty()){
            int parentBombIdx = dfs.pop();
            visited.add(parentBombIdx);
            int[] parentBomb = bombs[parentBombIdx];
            int parentR = parentBomb[2];
            for(int childBombIdx = 0; childBombIdx < n; ++childBombIdx){
                int[] childBomb = bombs[childBombIdx];
                if(!visited.contains(childBombIdx) && getDist(parentBomb,childBomb) <= (double)parentR) {
                    if(!parentSeq.contains(childBombIdx)) {
                        parentSeq.add(childBombIdx);
                        dfs.push(childBombIdx);
                    }
                }
            }
        }
    }
    
    
    private double getDist(int[] bombOne, int[] bombTwo){
        // Note : avoid lossy conversion between incompatible types.
        // Data overflow case : gaaah
        return Math.sqrt(
            Math.pow(bombOne[0] - bombTwo[0],2) 
            + Math.pow(bombOne[1] - bombTwo[1],2));
    }
    
}
