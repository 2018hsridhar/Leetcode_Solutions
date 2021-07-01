
/*
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

The trivial answer - just use a hashmap and serch for words in "top k" frequency"
- O(nl) space, O(nlogn) time for sort operations too!, where l = lenth of longest string
Any answers when using a trie structure though?



*/

class TrieNode
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
    
    public String getWord()
    {
        return this.word;
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
    TrieNode root;
    
    public void insert(String word)
    {
        TrieNode curr = root;
        for(char c : word.toCharArray())
        {
            if(curr.children.containsKey(c))
            {
                curr = curr.children.get(c);
            }
            else
            {
                TrieNode newChild = new TrieNode();
                newChild.c = c;
                curr.children.put(c,newChild);
                curr = newChild; 
            }
        }
        curr.frequency++;
    }
    
    // Reduce work from having to insret non-existent entries
    // Enables quick frequency checking too
    // Return node found, if word exists to - update its frequency along the way :-)
    public TrieNode checkIsWord(String word)
    {
        TrieNode curr = root;
        for(char c : word.toCharArray())
        {
            if(curr.children.containsKey(c))
            {
                curr = curr.children.get(c);
            }
            else
            {
                return null;
            } 
        }
        return curr;
        
    }
    // We can have repeated nodes in our piriority queue : e.g ("leetcode",1) and ("leetcode",2)
    // It should get taken care of correctly anyways?
    
    public List<String> topKFrequent(String[] words, int k) 
    {
        root = new TrieNode();
        List<String> topKFrequent = new ArrayList<String>();
        PriorityQueue<PQ_Node> pq = new PriorityQueue<PQ_Node>();
        for(String s : words)
        {
            TrieNode exists = checkIsWord(s);
            PQ_Node newWFP;
            if(exists != null)
            {
                // System.out.printf("Trie contains the word = %s\n", s);
                exists.frequency += 1; // update please
                newWFP = new PQ_Node(s,exists.frequency);
                
            }
            else
            {
                insert(s); // we know at insertion, frequency will be 1!
                newWFP = new PQ_Node(s,1);
            }
            pq.add(newWFP);
            // if(pq.size() > k)
                // pq.remove();
                
        }
        
        while(pq.size() > 0)
        {
            PQ_Node myCur = pq.remove();
            topKFrequent.add(myCur.getWord());
            System.out.printf("Adding (%s, %d)\n", myCur.getWord(), myCur.freq);
        }
        
        return topKFrequent;
    }
}


/*

692. Top K Frequent Words
URL = https://leetcode.com/problems/top-k-frequent-words/
Resources = https://en.wikipedia.org/wiki/Bucket_sort#Worst-case_analysis

EDGE CASES / TESTING : 
1. Akin to typical cases of using buckets, break with a clustered input case
2. Uniform distribution
3. Same values
3. 


Assume inputs are reasonable and strictly lowercase letters
Assume k is a valid natural number

Reccurring patterns
- Compute the maximum_key ahead of time in a one-pass linear scan

Types of binds to think about for strings - two come up in mind only : 
1. Bin by the first letter of each string [ maximal_key = 26 ]
2. Bin by the length of each string [ maximal_key = max_len(strings)]
3. Frequency of occurance [ 0,k]. We know max possible frequency = length of input array!
4. The strings themselves ( but soo many strings be possible in a dictionary! ) 

Difficulty of binning by frequency of occurence, is that we have to migrate over elements ( say, "i","i" ) -> migrate from bin_1, to bin_2. Seems like too much effort!

When given this ( two keys same frequency, given key in lower order ) => think of stable sorting!
OFC if you apply the hasmap ahead of time : construct bins can be quick ( but si the lexicographic ordering  preserved here )?

You have to return the sort in the end anyways - hence, why binning can help ( sort by frequency, from highest => lowest ) 

*/


class Solution 
{
    public List<String> topKFrequent(String[] words, int k) 
    {
        return null;
    }
}
