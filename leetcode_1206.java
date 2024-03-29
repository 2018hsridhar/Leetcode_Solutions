/*
URL = https://leetcode.com/problems/design-skiplist/
Code needs to handle duplicates in the skiplist

Complexity
Let N := #-elements in the skip list


Operation       Time           Space
Search          O(lgN)         O(N)
Add             O(lgN)         O(N)
Erase           O(lgN)         O(N)

TEST CASES :
(A) ["Skiplist","add","add","add","add","add","add","add","add","add","add","add","add"]
[[],[1],[2],[3],[4],[5],[6],[1],[2],[3],[4],[5],[6]]
=> seems ok, but not too meaningful : needs search too!
=> numLevels seems fine too : test for that as well!
=> incorporate search() into removal as well : lever this!
=> DLL makes list removal so easy too! Now I see :-(
-> is doable via SLL, but will prove cumbersome a bit :-(
(B) ["Skiplist","add","add","add","add","add","add","add","add","add","add","add","add","search","search"]
[[],[1],[2],[3],[4],[5],[6],[1],[2],[3],[4],[5],[6],[0],[3]]
    => search seems fine
    => erasure entails difficulty
(C)
(D)
(E)
(F) [30,40,50,60,70,90] insert(80)

If multiple instances of a value -> removing ANY of them is fine!
But in this case, seems removing all be needed here.
Length is reasonably bounded : not too many func calls/procedure overhead either
Ideally, algorithms can be iterative too

In skip list, all lists point to null
HEAD element appears across all levels too

Think of the member fields here too
Notice when the coin flip occurs for insertion too!

Set by "coin-flip" probability of 0.5 as well


*/

class Skiplist 
{
    Node HEAD;
    int numLevels;
    double prob = 0.5; // a constant
    boolean hasFirstElem;
    
    // Node member class for linked lists
    // we need to point up or down a node here as well -> point down?
    public class Node
    {
        int val;
        Node next;
        Node bellow;
        
        public Node()
        {
            val = 0;
            next = null;
            bellow = null;
        }
        
        public Node(int val, Node next, Node bellow)
        {
            this.val = val;
            this.next = next;
            this.bellow = bellow;
        }
    }

    public Skiplist() 
    {
        // can we set to a sentinel, or something else? 
        // Special logic if head points to null anyways ( the singleton case ) 
        HEAD = new Node();
        numLevels = 1;
        hasFirstElem = false;
    }
    
    public boolean search(int target) 
    {
        Node cur = HEAD;
        boolean foundElem = false;
        while(cur != null)
        {
            if(target == cur.val)
            {
                foundElem = true;
                break;
            }
            else
            {
                if(cur.next == null)
                {
                    if(cur.bellow != null)
                        cur = cur.bellow;
                    else
                        break;
                }
                else
                {
                    if(target < cur.next.val)   // Careful on the inequality here to
                    {
                        cur = cur.bellow;
                    }
                    else if ( target >= cur.next.val )
                    {
                        cur = cur.next;
                    }
                }
            }
        }
        return foundElem;
    }
    
    // Incorporate randomness here
    // Add method can track the heads of the previous levels as well
    // Build up via the Doubly-Linked List too! ( or maybe can still work as SLL, as long as we keep trac )?
    // Keep track of a number of levels, in case helpful too!
    // REMEMBER :-> we must always hit rock bottom in the isnertion as well!
    public void add(int num) 
    {
        if(hasFirstElem == false)
        {
            HEAD.val = num;
            hasFirstElem = true;
            return;
        }
        else
        {
            List<Node> dropHeads = new ArrayList<Node>();
            Node cur = HEAD;
            while(cur != null)  // See this : eval CUR = null before EVAL foc cur.next = null logic!
            {
                // CONDITIONAL LOGIC preceding POINTER iteration. Notice this ordering
                // Hang on -> what if truly at last element insertion too!
                // For ANY drop below, a comparison to the NEXT element is still a must too! ( of said below ) 
                if(cur.next == null)
                {
                    dropHeads.add(cur);
                    if(cur.bellow != null)
                    {
                        cur = cur.bellow;
                    }
                    else
                    {
                        break;
                    }
                }
                else if ( cur.next != null)
                {
                    if(num <= cur.next.val)
                    {
                        dropHeads.add(cur);
                        if(cur.bellow != null)
                        {
                            cur = cur.bellow;
                        }
                        else    // careful : you may be at the bottom here too!
                        {
                            break;
                        }
                    }
                    else
                    {
                        cur = cur.next; // Must go right words here
                    }
                }
            }
            // Now have set of all heads to perform randomization on as well :-)
            // We need to ARTIFICIALLY halt this randomization too! ( set to numLevels + 1 here for potential increment )
            // Decrease exponentially be the ideal too
            // Each probability event also remains independent of one another
            // Fill in the base level too : THEN all remaining levels
            Node toAdd = new Node(num,null,null);
            toAdd.next = cur.next;
            cur.next = toAdd;
            int numLevels = dropHeads.size();
            boolean hitCeil = true;
            for(int a = 1; a < numLevels; ++a)
            {
                // We have a stopping condition as well too!
                double coinToss = (int)Math.round(Math.random());
                if(coinToss == 0)
                {
                    hitCeil = false;
                    break;
                }
                else if ( coinToss == 1)
                {
                    Node myHead = dropHeads.get(numLevels - 1 - a);
                    Node novel = new Node(num,null,toAdd);
                    novel.next = myHead.next;
                    myHead.next = novel;
                    toAdd = novel;
                }
                
            }
            if(hitCeil == true)
            {
                Node newHEAD = new Node(HEAD.val,null,HEAD);
                HEAD = newHEAD;
                numLevels++;
            }
            // System.out.printf("For the add operation, numLevels = [%d]\n", numLevels);
        }
    }
    
    // This the harder one too
    public boolean erase(int num) 
    {
        return false;
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
