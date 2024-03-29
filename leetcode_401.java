
/*
URL = https://leetcode.com/problems/binary-watch/
401. Binary Watch


Total in-memory storage for a perfect binary tree, of depth = 6 nodes, is : 756Bytes
(12B/node)*[(2^6)-1] nodes = 630B + 126B

Benefits of passing objects into recursive methods : very low memory footprint in functions due to addressing notation ( size(ptr) at max here ) 
Adding to beginning of a linked list is easy too!
Avoid string : consier fixed size array in place instead? 
Fill in the digits from left to right, and read from there too ( via StringBuilders )?
    - need deep copying here for sure
*/

class Solution 
{
    public List<String> readBinaryWatch(int turnedOn) 
    {
        List<String> results = new LinkedList<String>();
        if(turnedOn >= 9)
            return results;
        HashMap<Integer, HashSet<Integer>> hours = new HashMap<Integer,HashSet<Integer>>();
        for(int i = 0; i <= 3; ++i)
            hours.put(i,new HashSet<Integer>());
        HashMap<Integer, HashSet<Integer>> minutes = new HashMap<Integer,HashSet<Integer>>();
        for(int i = 0; i <= 5; ++i)
            minutes.put(i,new HashSet<Integer>());
        
        // for minutes : for hours, it is 3 here instead
        // default of level 0 here too
        int maxDepth = 5; 
        int curDepth = 0;
        int oneCount = 0;
        ArrayList<Integer> combo = new ArrayList<Integer>();
        generateCombinations(hours, minutes, curDepth, maxDepth, combo, oneCount);
        

        int numHours = turnedOn;
        int numMins = 0;
        while(numHours >= 0)
        {
            // Check toggle here properly too
            if(numHours <= 3 && numMins <= 5)
            {
                Set<Integer> hourVals = hours.get(numHours);    
                Set<Integer> minVals = minutes.get(numMins);
                for(int hour : hourVals)
                {
                    for(int min : minVals)
                    {
                        StringBuilder sb = new StringBuilder("");
                        sb.append(hour);
                        sb.append(":");
                        if(min < 10)
                            sb.append("0");
                        sb.append(min);        
                        results.add(sb.toString());
                    }
                }
                
            }
            --numHours;
            ++numMins;
        }
        return results;
    }
    
    /*
     * Denote as following too
     * zero => left
     * one => right
     * hour as no trailing zero
     * minutes is a two digit val : take note of this!
     */
    public void generateCombinations(HashMap<Integer, HashSet<Integer>> hours, HashMap<Integer, HashSet<Integer>> minutes, 
                                     int curDepth, int maxDepth,
                                     ArrayList<Integer> combo, int oneCount)
    {
        if(curDepth > maxDepth+1)
            return;
        else if ( curDepth == maxDepth+1)
        {
            int val = 0;
            for(int i = 0; i < combo.size(); ++i)
                val += (int)Math.pow(2,i) * combo.get(i);
            if(val <= 59)
            {
                Set<Integer> myEncountered = minutes.get(oneCount);
                myEncountered.add(val);
            }
        }
        else if ( curDepth == 4) // for hours 
        {
            int val = 0;
            for(int i = 0; i < combo.size(); ++i)
                val += (int)Math.pow(2,i) * combo.get(i);
            if(val <= 11)
            {
                Set<Integer> myEncountered = hours.get(oneCount);
                myEncountered.add(val);
            }
        }
        ArrayList<Integer> combo_zero = new ArrayList<Integer>();
        ArrayList<Integer> combo_one = new ArrayList<Integer>();
        for(int i : combo)
        {
            combo_zero.add(i);
            combo_one.add(i);
        }
        combo_zero.add(0);
        combo_one.add(1);
        generateCombinations(hours, minutes, curDepth + 1, maxDepth, combo_zero, oneCount);       
        generateCombinations(hours, minutes, curDepth + 1, maxDepth, combo_one, oneCount + 1);       
    }
    
}
