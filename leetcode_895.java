/*
URL := https://leetcode.com/problems/maximum-frequency-stack/description/
895. Maximum Frequency Stack

Tracking of the most frequent element in stock
Need to track positioning of stack els, based on frequency too

Two behaviors
(A) Remove most freq el ( at the top ) 
(B) Remove the topmost el

Storing {el, index, count} too
We have "stack-like" behavior -> may not implement "like a " stack

Complexity
Let N := #-elements pushed onto the Freq Stack
N = 20,000 ( 10e4 callz ) 
Time := O(NlgN)
Space := O(N) ( EXP ) O(1) ( IMP ) 

Woooh multiple data strucvture problem
All pos values

UUID positiona counting works :-)

30 mins to solutioning

*/
class Record implements Comparable<Record> {
    public int val;
    public int freq;
    public int pos;

    public Record(int val, int freq, int pos) {
        this.val = val;
        this.freq = freq;
        this.pos = pos;
    }

    // @override
    // Comparable interface single parameter in `compareTo(...)` method
    // Sort order : by highest freq -> by highest position ( maxHeap )
    public int compareTo(Record other) {
        if(this.freq < other.freq) {
            return 1;
        } else if (this.freq > other.freq) {
            return -1;
        } else {
            if(this.pos < other.pos) {
                return 1;
            } else if ( this.pos > other.pos ) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

class FreqStack {

    PriorityQueue<Record> pq;
    Map<Integer,Integer> freqMap;
    int posCounter = 0;
    public FreqStack() {
        this.pq = new PriorityQueue<Record>();
        this.freqMap = new HashMap<Integer,Integer>();
    }
    
    public void push(int val) {
        // [1] Update frequency
        if(!freqMap.containsKey(val)){
            freqMap.put(val,0);
        }
        int newFreq = freqMap.get(val) + 1;
        freqMap.put(val,newFreq);
        int newPos = this.posCounter;
        this.posCounter++;
        Record rn = new Record(val,newFreq, newPos);
        this.pq.add(rn);
    }
    
    public int pop() {
        int valToRemove = -1; // sanity check return
        if(this.pq.size() > 0) { // sanity guarding good code signal
            Record mostFreqRec = this.pq.remove();
            valToRemove = mostFreqRec.val;
            if(freqMap.containsKey(valToRemove)){
                freqMap.put(valToRemove,freqMap.get(valToRemove) - 1);
            } else {
                return -1;
            }
        } 
        return valToRemove;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
