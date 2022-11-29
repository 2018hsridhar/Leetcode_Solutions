/*
Extend to the k-cases via a priority queue.
281. Zigzag Iterator
URL = https://leetcode.com/problems/zigzag-iterator/

8 mins to solution
*/
class ZigzagIterator {
private:
    int ptr1 = 0;
    int ptr2 = 0;
    int sizeOne = 0;
    int sizeTwo = 0; // default-initialized class safety
    bool toggle = true; // set to read from first vector : true -> v1 AND false -> v2
    vector<int> myv1;
    vector<int> myv2;
public:
    // They can have 0 lengths, but are never null
    // Len hits 2K max
    ZigzagIterator(vector<int>& v1, vector<int>& v2) {
        sizeOne = v1.size();
        sizeTwo = v2.size();
        myv1 = v1; // deep-copy here!
        myv2 = v2;
    }

    int next() {
        int res = -1;
        if(hasNext()){ // invoke internal method
            if(ptr1 < sizeOne && ptr2 < sizeTwo){
                if(toggle){
                    toggle = !toggle;
                    res = myv1.at(ptr1);
                    ++ptr1;
                } else {
                    toggle = !toggle;
                    res = myv2.at(ptr2);
                    ++ptr2;
                }
            } else if ( ptr1 < sizeOne ) {
                res = myv1.at(ptr1);
                ++ptr1;
            } else if ( ptr2 < sizeTwo ){
                res = myv2.at(ptr2);
                ++ptr2;
            }
        }
        return res;
    }

    bool hasNext() {
        return (ptr1 < sizeOne || ptr2 < sizeTwo);
    }
};

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i(v1, v2);
 * while (i.hasNext()) cout << i.next();
 */
