/*
735. Asteroid Collision
URL = https://leetcode.com/problems/asteroid-collision/
Seems very much a stack problem
You want to track the directions as you go 
    + : push onto stack
    - : compare to the top, and keep popping
Direction of winning asteroid always preserved
Asteroids never have size = 0!
Account for the case of an asteroid plowing its way too!
18 mins to solution

Test Cases
(A) [10,-2,-3,-5]
(B) [10,-2,-3,-10]
(C) [19,15,10,-2,-3,-20]
(D) [1,2,3,4,-3,1,2,-4]
    ^ Remedy this!
(E) [-4,-2,-1,5,3,2,4,-5]
    ^ TLE
(F) 
(G)

*/
class Solution {
public:
    vector<int> asteroidCollision(vector<int>& asteroids) {
        vector<int> finalState;
        stack<int> myAst;
        for(int el : asteroids) { // compiler optimizes this :-)
            if(el > 0 ) {
                myAst.push(el);
            } else if ( el < 0 ) {
                bool insertNeg = true;
                while(!myAst.empty()){
                    int topEl = myAst.top();
                    if(topEl < 0){
                        myAst.push(el);
                        insertNeg = false;
                        break;
                    } else if ( topEl > 0) {
                        if(topEl > abs(el)){
                            insertNeg = false;
                            break;
                        } else if ( topEl == abs(el)){
                            myAst.pop();
                            insertNeg = false; // to avoid a push, pretend as if inserted
                            break;
                        } else if ( topEl < abs(el)) {
                            myAst.pop();
                        }
                    }
                }
                if(insertNeg){ // if we have to insert the neg asteroid.
                    myAst.push(el);
                }
            }
        } 
        // Reversed ordering.
        while(!myAst.empty()){
            finalState.insert(finalState.begin(),myAst.top());
            myAst.pop();
        }
        return finalState;
    }
};
