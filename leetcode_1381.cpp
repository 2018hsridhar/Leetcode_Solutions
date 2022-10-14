/*
1381. Design a Stack With Increment Operation
URL = https://leetcode.com/problems/design-a-stack-with-increment-operation/
*/
class CustomStack {
private:
    std::vector<int> stk;
    int sp;
    int n;
public:
    CustomStack(int maxSize) {
        // stk = vector<int>(maxSize,0);
        sp = 0;
        n = maxSize;
        stk.resize(maxSize,0);
    }
    
    void push(int x) {
        if(sp < n){
            stk[sp++] = x;
        }
    }
    
    int pop() {
        if(sp <= 0){
            return -1;
        } else {
            int res= stk[sp-1];
            sp--;
            return res;
        }
    }
    
    void increment(int k, int val) {
        for(int a = 0; a < std::min(k,sp); a++){
            stk[a] += val;
        }
    }
};

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack* obj = new CustomStack(maxSize);
 * obj->push(x);
 * int param_2 = obj->pop();
 * obj->increment(k,val);
 */
