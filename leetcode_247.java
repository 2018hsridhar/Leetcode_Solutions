/*
246. Strobogrammatic Number
https://leetcode.com/problems/strobogrammatic-number/

Test cases
(a) N = 1
(b) N = 2
(c) N = 3

*/
class Solution {

    // Number sof of length n : so they go from Math.pow(10,n-1) to Math.pow(10,n) = 1
    // Can we avoid iterating over all numbers ( well we go to 10^14 at max ).
    // We have to return the list of numbers ( it is ineluctable )
    // Just : [0,1,8,6,9] here
    // The [6,9] are the trickier numbers too! -> it depends on their placement in the digits system
    // Brute force execs a function for 100,000,000,000,000 inputs ( 100T )!
    
    public List<String> findStrobogrammatic(int n) 
    {
        List<String> results = new ArrayList<String>();
        results = genStrob(n); // Function applicable to given types only ( candidate signatures evaluated ). Functions are also symbols in your compiler.
        Collections.sort(results);
        return results;
    }
    
    // Set up recursive base cases
    private List<String> genStrob(int n)
    {
        List<String> results = new ArrayList<String>();
        if(n == 1)
        {
            results.add("0");
            results.add("1");
            results.add("8");
        } else if ( n == 2 ) {
            results.add("11");
            results.add("69");
            results.add("88");
            results.add("96");
        } else {
            results = helper(n);
            // This is a bit atrocious, but it works :-)
            for(int i = 0; i < results.size(); ++i){
                if(results.get(i).charAt(0) == '0')
                    results.remove(i);
            }
        }
        return results;
    }
    
    private List<String> helper(int n)
    {
        // Helper iterative containers
        String[] prefixes = new String[]{"0","1","6","8","9"};
        String[] suffixes = new String[]{"0","1","9","8","6"};
        List<String> results = new ArrayList<String>();
        if(n == 1)
        {
            results.add("0");
            results.add("1");
            results.add("8");
        } else if ( n == 2 ) {
            // Note the weird case of double 0 too!
            results.add("00");
            results.add("11");
            results.add("69");
            results.add("88");
            results.add("96");
        } else {
            List<String> interior_min_2 = helper(n-2);
            for(int i = 0; i < interior_min_2.size(); ++i)
            {
                String interior = interior_min_2.get(i);
                for(int j = 0; j < prefixes.length; ++j) {
                    String prefix = prefixes[j];
                    String suffix = suffixes[j];
                    StringBuilder sb = new StringBuilder("");
                    sb.append(prefix);
                    sb.append(interior);
                    sb.append(suffix);
                    results.add(sb.toString());
                }
            }
        }
        return results;
    }

    public boolean isStrobogrammatic(String num) 
    {
        HashMap<Character,Character> hm = new HashMap<Character,Character>();
        hm.put('0','0');
        hm.put('1','1');
        hm.put('2','X');
        hm.put('3','X');
        hm.put('4','X');
        hm.put('5','X');
        hm.put('6','9');
        hm.put('7','X');
        hm.put('8','8');
        hm.put('9','6');
        StringBuilder sb = new StringBuilder("");
        char[] c_arr = num.toCharArray();
        for(char c : c_arr)
        {
            sb.append(hm.get(c));
        }
        String flipped = sb.reverse().toString();
        if(flipped.indexOf('X') != -1)
               return false;
        return flipped.equals(num);
    }
}
