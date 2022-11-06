/*

1094. Car Pooling

Complexity
Let N := len(trips)
TIME = O(NLGN)
SPAACE = O(N) ( EXP ) O(1) ( IMP ) 

Cases
(A) [[1,1,4],[1,2,5],[1,3,6],[1,4,7]] => PASS
(B) [[2,1,4],[1,2,5],[2,3,6],[1,4,7]] => PASS
(C) [[2,1,4]] => PASS
(D)
(E)
(F)
(G)

*/
class Solution {
public:
    bool carPooling(vector<vector<int>>& trips, int capacity) {
        bool status = true;
        int n = trips.size();
        // time got spent debugging a damn lambda function more than the code : heads up!
        // GAAAH has to be a damn boolean!
        std::sort(trips.begin(), trips.end(), [](const vector<int>& intv1, const vector<int>& intv2) -> bool {
            if(intv1.at(1) < intv2.at(1)){
                return true;
            } else if ( intv1.at(1) > intv2.at(1)){
                return false;
            } else {
                if(intv1.at(2) < intv2.at(2)){
                    return true;
                }
                return false;
            }
        }); // check coding of your lambda func too!
        for(int i = 0; i < n; ++i){
            vector<int>& currentInterval = trips.at(i);
            printf("Interval = [%d,%d,%d]\n", currentInterval.at(0), currentInterval.at(1), currentInterval.at(2));
        }

        // use of \lambda func
        auto Compare = [](vector<int>& intv1, vector<int>& intv2) -> bool{
            if(intv1.at(2) < intv2.at(2)){
                return false;
            } else {
                return true;
            } 
        };
        // https://stackoverflow.com/questions/16111337/declaring-a-priority-queue-in-c-with-a-custom-comparator
        std::priority_queue<vector<int>, vector<vector<int>>, decltype(Compare)> pq(Compare);
        for(int i = 0; i < n; ++i){
            pq.push(trips.at(i));
        }
        int runCap = 0;
        for(int i = 0; i < n; ++i){
            vector<int>& currentInterval = trips.at(i);
            // printf("Current ith Interval = [%d,%d,%d]\n", i, currentInterval.at(1), currentInterval.at(2));
            runCap += currentInterval.at(0);
            while(pq.size() > 0){
                // Access -> read -> then remove/evict
                const vector<int> earliestEndingInterval = pq.top();
                // const vector<int>& earliestEndingInterval = pq.top();
                if(earliestEndingInterval.at(2) <= currentInterval.at(1)){
                    // printf(" Earlier Interval = [%d,%d]\n", earliestEndingInterval.at(1), earliestEndingInterval.at(2));    
                    pq.pop(); // did order of ops here cause a mess up! OMG -> happens with references/ptrs!!
                    cout << earliestEndingInterval.at(0) << endl;
                    runCap -= earliestEndingInterval.at(0);
                } else {
                    break;
                }
            }
            cout << "RunCap = " << runCap << endl;
            if(runCap > capacity){
                status = false;
                return false;
            }
        }
        return status;
    }
};
