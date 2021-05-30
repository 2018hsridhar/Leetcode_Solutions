
/*
Benefits of OOP methods
- More secure : class variables are now member variables, and are not exposed out 
- Can manipulate specific objects ( their instances ) or all objects ( via static variables ) 
- Forces programmer to think more in terms of objects manipulation
- Somewhat forces programmers to think more in terms of typsetting : e.g object.length() -> int data type. Types are konwn ahead of time! Wait what!!!

    Notice how a max to the number of calls ir provided as a potential input too
    Alphabet range : [a-z] = 26 children max = max number of characters ( or the . character )
    Meant to match wildcard matching ( as performed by UNIX tools ) 
    
    Resources : 
    
    https://www.youtube.com/watch?v=zIjfhVPRZCg&t=268s
    
    Make sure to understand hashmap versus array implmenetation of the children of a tree!
    In recursive methods, how can a caller pass the state, updated over time, to the calle?
        - seems only in the method signature!
        - helper methods needed to properly pass in the state too!
    Not how hashmap reduces space, and entails quick lookup of children. 
    - Hashmap will be preferred to ArrayLists, for setting the children of trees too!

 */
public class Node
{
    HashMap<Character, Node> children;
    Character c;
    boolean isWord;
    
    public Node()
    {
        children = new HashMap<Character,Node>();
        c = (char)0; // empty character
        isWord = false; // not a word if empty character only!
    }
}

class WordDictionary 
{
    Node root;
    /** Initialize your data structure here. */
    public WordDictionary() 
    {
        root = new Node();
    }
    
    // Ensure that add word is actually working as expected!
    // Forgot about this!
    
    public void addWord(String word) 
    {
        Node curNode = root;
        for(char c : word.toCharArray())
        {
            if(curNode.children.containsKey(c))
            {
                curNode = curNode.children.get(c);
            }
            else
            {
                Node newChild = new Node();
                newChild.c = c;
                curNode.children.put(c,newChild);
                curNode = newChild;
            }
        }        
        // add end : set isWord status of current node to True
        curNode.isWord = true;
    }
    
    // Not as trivial as the other problem - the [.] can indicate matching ANY word!
    // Must iterate over all of those search strings then!
    // Idea : create helper function to index into the string word - pass state from each caller to the callee
    
    public boolean search(String word) 
    {
        Node curNode = root;
        boolean isFound = false;
        int searchIndex = 0;
        isFound = searchHelperUtil(root, word, searchIndex);
        return isFound;
    }
        
    // Exploit property of booleans : ( true || false ) = true
    // remember that your search starts at an empty string root node! You can not just check your current node ( it will be a null node! ) 
    public boolean searchHelperUtil(Node curNode, String word, int searchIndex)
    {
        boolean isFound = false;
        // check if cur Node character = c, and if isWord = true
        if(searchIndex == word.length())
        {
            return curNode.isWord;             
        }
        char c = word.charAt(searchIndex);
        if(c == '.')
        {
            boolean InitSearch = false;
            HashMap<Character,Node> children = curNode.children;
            searchIndex += 1;
            for(Character x : children.keySet() )
            {
                Node child = children.get(x);
                InitSearch = InitSearch || searchHelperUtil(child,word,searchIndex);
            }
            isFound = InitSearch;
        }
        else
        {
            if(curNode.children.containsKey(c))
            {
                Node child = curNode.children.get(c);
                isFound = searchHelperUtil(child,word,++searchIndex);
            }
            else
                return false; // do not contain Key
        }
        return isFound;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

