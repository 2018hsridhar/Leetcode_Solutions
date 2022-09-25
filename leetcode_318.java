
class Solution {
    public int maxProduct(String[] words) {
        // Lengths retrievable from worsd itself
        // Leverage an array of sets here
        int maxProd = 0;
        List<Set<Character>> maps = new ArrayList<Set<Character>>();
        for(int i = 0; i < words.length; ++i) {
            Set<Character> myChars = new HashSet<Character>();
            String word = words[i];
            for(char c : word.toCharArray()){
                myChars.add(c);
            }
            maps.add(myChars);
        }
        
        for(int i = 0; i < words.length; ++i) {
            String word = words[i];
            Set<Character> myChars = maps.get(i);
            for(int j = 1; j < words.length; ++j) {
                String wordTwo = words[j];
                Set<Character> myCharsTwo = maps.get(j);
                boolean intersect = false;
                for(Character x : myChars){
                    if(myCharsTwo.contains(x)){
                        intersect = true;
                    }
                }
                if(!intersect){
                    int lenOne = word.length();
                    int lenTwo = wordTwo.length();
                    maxProd = Math.max(maxProd, lenOne*lenTwo);
                }
            }
        }        
        
        return maxProd;
    }
}
