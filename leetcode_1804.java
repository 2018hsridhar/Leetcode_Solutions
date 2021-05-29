
/*

    LEETCODE 1804 : IMPLEMENT TRIE II ( PREFIX TREE ) 
    URL = https://leetcode.com/problems/implement-trie-ii-prefix-tree/
    
    THOUGHT PROCESSES FOR IMPLEMENTING A TRIE ( PREFIX TREE ) 
    Wait ... does this later lead down to prefix arrays?
    
    In general, make another class to set up a minor "helper" data structure
    - nest the class, if privacy is desirable
    - the nested data structrues can have their own public and private variables too!
    - utilizes objects enables own "toString()" methods - they re quick for printing and debugging purposes
    - should we use a fixed sizes array for the children, or an array list? Both will require iteration anyways!
    - objects allow us to set up accessible member variables with ease ( which are not part of our current class ) 
    
    Top of trie = blank root node ( no character ) with ArrayList<Node> initialized, and numInstances = 0;
    
 */

public class Node
{
    public char c;
    public int numInstances;
    public int charCount;
    public Node[] children;
    
    public Node()
    {
        c = 0; // can not initialize a null character ! wow! Must be of width - 1 hre!
        numInstances = 0;
        charCount = 0;
        children = new Node[26];
        // initialize children array
        for(int i = 0; i < children.length; ++i)
            children[i] = null; 
    }
  
    // Issue here : if you make a new Node() constructor - you must initialize that children array again! dang!
    // public Node(char myC, int numInstances)
    // {
    //     c = myC; // can not initialize a null character ! wow! Must be of width - 1 hre!
    //     this.numInstances = numInstances;
    // }
    
    public String toString()
    {
        String result = String.format("char = %s\t instances = %d\t charCount = %d \n", c, numInstances, charCount);
        return result;
    }
}

class Trie 
{
    private Node rootNode;

    public Trie() 
    {
        rootNode = new Node();
        
    }
    
    // Insertion must always commence at the root node of a tree!
    public void insert(String word) 
    {
        Node curNode = rootNode;
        char[] c_arr = word.toCharArray();
        for(int i = 0; i < c_arr.length; ++i)
        {
            char myC = c_arr[i];
            int cIdx = myC - 'a' + 1;
            // System.out.printf("For char = %s \t cIDx = %d\n", myC, cIdx);
            // System.out.println(curNode.children.length);
            if(curNode.children[cIdx] == null) // make new node in the tree!
            {
                Node newNode = new Node();
                newNode.c = myC;
                newNode.charCount = 1;
                curNode.children[cIdx] = newNode;
                curNode = newNode; // go shift here!
            }
            else // continue on tree path
            {
                curNode.charCount += 1; // update as we go
                curNode = curNode.children[cIdx];
            }
        }
        // now at the end of the tree : increment the index value!
        curNode.numInstances++;
        
    }
    
    public int countWordsEqualTo(String word) 
    {
        Node curNode = rootNode;
        char[] c_arr = word.toCharArray();
        for(int i = 0; i < c_arr.length; ++i)
        {
            char myC = c_arr[i];
            int cIdx = myC - 'a' + 1;
            if(curNode.children[cIdx] == null) // word does not exist - break
            {
                return 0;
            }
            else // continue on tree path
            {
                curNode = curNode.children[cIdx];
            }
        }
        // now at the end of the tree : increment the index value!
        return curNode.numInstances;
    }
    
    // Issue here : so <app> is not a valid word - but <apple> is, with inst num = 2
    // keep going down the trees once the root/leaf is hit - make sure thy state space explores all children!
    
    public int countWordsStartingWith(String prefix) 
    {
        Node curNode = rootNode;
        char[] c_arr = prefix.toCharArray();
        for(int i = 0; i < c_arr.length; ++i)
        {
            char myC = c_arr[i];
            int cIdx = myC - 'a' + 1;
            if(curNode.children[cIdx] == null) // word ( or its other prefixes ) does not exist
            {
                return 0;
            }
            else // continue on tree path
            {
                curNode = curNode.children[cIdx];
            }
        }
        // now at the end of the tree : check if we still have leaves here
        // Recurse and increment count with subTree values after each recursive call in the recursvie stack
        // System.out.println(curNode.toString());
        int prefixCount = countWordswithPrefix(curNode); // assuming cur Node proves correct here
        return prefixCount;
    }

    // FORGOT TO HANDLE NULL CASE OF CHILDREN HERE - WOAH!
    public int countWordswithPrefix(Node root)
    {
        int count = 0;
        if(root == null)
            return 0;
        count += root.numInstances;
        for(int i = 0; i < root.children.length; ++i)
        {
            int subTreeCount = countWordswithPrefix(root.children[i]);
            count += subTreeCount;
        }
        return count;
    }
    
    // Either decrease the number of instances; OR; eliminate the entire word, if instance count < 1
    // But do not go eliminate the nodes yet
    // Do not leave dangling nodes either ( e.g. "apple" - don't leave an "appl" if you added only "apple" from thy dictionary!)
    // Maybe store another frequency with each node and decrement it as we go?
    // Can we auto-decrement if we know at some point, the frequency is 0? Only if the word actually exists! 
    
    public void erase(String word) 
    {
        int countOfWord = countWordsEqaulTo(word);
        if(countOfWord > 1)
        {
            
        }
        else if ( countOfWord == 1)
        {
            
        }
        return; // no point if word does not even exist in our dictionary!
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */
