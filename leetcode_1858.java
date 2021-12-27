/*

1858. Longest Word With All Prefixes
URL = https://leetcode.com/problems/longest-word-with-all-prefixes/

Goal : Use a TRIE or a HASHMAP into this problem, for all prefixes
We must do a precomputation stage of inserting all the words into the trie as well
Can limit ourselves to just coding up the trie code as well, and insertion
    -> avoid need for support a deletion operation
Do we have to DFS each valid trie path as well?

We could sort the list in dictionary order as well, and try some hashmap prefix checking, but that entails
O(nlgn) sort ahead of time, even if it exploits properties of subproblems.

COMPLEXITY
Let M := total #-characters in the input
Let N := #-words in the input
Let K := length longest word in the input
TIME = O(NK) insertions + O(NK) search
SPACE = O(NK) ( suppose each word has unique alphabets here ) ( explicit ) 
        O(K)  ( implicit => recursive call stack => length of longest word )  
        
Constraints : 
number of words from 1 to 10e5
word len bounded by 10e5 
Total num chars > INT data type ( 10e5*10e5 > 6e9 ) 
But notice that summation constraint, so do not worry about that as well :-)

Make sure to plan for empty string literal case as well

*/

class Solution 
{
    
    // You typically get tricked as to whether dstructure insert is in the node class itself, or outside the scope
    // Ruminate carefully
    TrieNode root;
    
    class TrieNode
    {
        public String letter;
        public TreeMap<String, TrieNode> children;
        public boolean isWord;
        
        public TrieNode()
        {
            isWord = false;
            letter = "";        // lack support for empty character literal : empty string now
            children = new TreeMap<String,TrieNode>();
        }
    }
    
    // Iterative insertion here
    public void insert(String word)
    {
        TrieNode curr = root;
        char[] c_arr = word.toCharArray();
        for(int i = 0; i < c_arr.length; ++i)
        {
            String ch = c_arr[i] + "";
            if(curr.children.containsKey(ch))
            {
                curr = curr.children.get(ch);
            }
            else
            {
                TrieNode nxtChild = new TrieNode();
                nxtChild.letter = ch + "";
                curr.children.put(ch, nxtChild);
                curr = nxtChild;
            }
        }
        // Set isWord status to true here
        curr.isWord = true;
        return;
    }
    
    public String longestWord(String[] words) 
    {
        root = new TrieNode(); // initialize trie here
        root.isWord = true;     // Empty string literal '' is a default word as well!
        // [1] PRECOMPUTATION PHASE
        for(int i = 0; i < words.length; ++i)
        {
            String token = words[i];
            insert(token);
        }
        
        // [2] Iterate over TRIE phase in a backtracking exhaustive search manner
        StringBuilder sb = new StringBuilder("");
        StringBuilder best = new StringBuilder("");
        searchForLongestWord(root, sb, best);
        return best.toString();
    }

    // Parameterize recursive function, but avoid premature optimizations as well
    // Strings do build up until the end as well
    // Will be in lexicographic ordering, PROVIDED we go left -> right here in the keyset ( which needs ordering GFDI ) 
    // Use TreeMap for now? Go to array based solution later :-( 
    // How to denote a TrieNode which is a leaf as well ( do not iterate further! ) 
    public void searchForLongestWord(TrieNode root, StringBuilder curWord, StringBuilder best)
    {
        if(!root.isWord)
        {
            return;
        }
        else if ( root.isWord)
        {
            // Clearly, all other prefixes must have been here as well ( avoided backtrack ) 
            // [1] Operations on parent -> if word, compare to the global best
            String curEntry = curWord.toString();
            if(curEntry.length() > best.length())
            {
                best.delete(0, best.length());
                best.append(curEntry);
            }
            
            // [2] Operations on children => the recursive portion ( top-down recursive ) 
            TreeMap<String, TrieNode> children = root.children;
            Set<String> keys = children.keySet();
            for(String ch : keys )
            {
                TrieNode child = children.get(ch);
                if(child.isWord == true)
                {
                    StringBuilder novel = new StringBuilder(curWord.toString());
                    novel.append(ch);
                    searchForLongestWord(child, novel, best);
                }
            }
        }
    }
    
    
}
