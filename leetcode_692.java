
/*

PROBLEM : 692. Top K Frequent Words
URL : https://leetcode.com/problems/top-k-frequent-words/

Needs a min-heap/priority queue of size k
Needs own compareTo() method written, which supports sort based on 
- frequency
- dictionary order

Needs same trie data structures as observed in other trie problems!
During each addition, update frequencies dynamically in the tree - and update the priority queue too!

If word does not exist - insert. Otherwise, add to the trie data structure

URLs : 
1. https://java-programming.mooc.fi/part-10/2-interface-comparable#:~:text=The%20Comparable%20interface%20defines%20the%20%60compareTo%60%20method%20used,object%20to%20which%20the%20%22this%22%20object%20is%20compared.
2.
3.

*/

public class TrieNode
{
    Character c;
    boolean isWord; 
    int frequency; // frequency of a full word here, actually! 
    HashMap<Character,TrieNode> children;
    
    public TrieNode()
    {
        c = (int)0;
        isWord = false;
        frequency = 0;
        children = new HashMap<Character,TrieNode>();
    }
}


// Template Comparable abstract clas with datatype compared against. ( the class type, that is )
class PQ_Node implements Comparable<PQ_Node>
{
    String word;
    int freq; 
    
    public PQ_Node()
    {
        word = "";
        freq = 0;
    }
    
    public PQ_Node(String word, int freq)
    {
        this.word = word;
        this.freq = freq;
    }
    
    // Notice how integers [-1,0,1] assist in setting up our comparison order
    // As we compare only two elements at a time
    public int compareTo(PQ_Node e)
    {
        if(this.freq == e.freq)
        {
            return this.word.compareTo(e.word); // utilizes strings built in compareTo() method :-)
        }
        else if ( this.freq < e.freq)
            return -1;
        return 1;
    }
    
    
    
}

class Solution 
{
    public List<String> topKFrequent(String[] words, int k) 
    {
        List<String> topKFrequent = new ArrayList<String>();
        
        return topKFrequent;
    }
}
