class ATM {
private:
    std::vector<long long> noteFreq;
    std::vector<long long> bankNotes{20,50,100,200,500};
public:
    ATM() {
        // Seems to be a correct initialization?
        // 0-initialize the stack memory too.
        noteFreq = std::vector<long long>(5);
        for(int i = 0; i < 5; ++i){
            noteFreq[i] = 0;
        }
    }
    
    void deposit(vector<int> banknotesCount) {
        for(int i = 0; i < banknotesCount.size(); ++i){
            noteFreq[i] += banknotesCount[i];
        }
    }
    
    vector<int> withdraw(int amount) {
        // std::vector<int>* toWithdraw = new std::vector<int>();
        // TODO : why does the top line work, but not the other two lines.
        // Investigate this further!
        std::vector<int> toWithdraw(5);
        // std::vector<int> toWithdraw;
        // toWithdraw.reserve(5);
        std::vector<int> freqDelta(5);
        // toWithdraw->reserve(5); // Use this method to set up space too
        for(int i = 0; i < 5; ++i){
            freqDelta[i] = 0;
            // (*toWithdraw)[i] = 0;
            (toWithdraw)[i] = 0;
        }
        for(int i = bankNotes.size() - 1; i >= 0; --i){
            int denom = bankNotes[i];
            if(amount / denom > 0){
                int broken = amount / denom;
                // printf("broken = %d\n", broken);
                int toSubtract = std::min((long long)broken,noteFreq[i]);
                amount -= (toSubtract * denom);
                // (*toWithdraw)[i] = toSubtract;
                (toWithdraw)[i] = toSubtract;
                freqDelta[i] = toSubtract;
            }
        }
        if(amount > 0){
            return std::vector<int>{-1};
        }
        for(int i = noteFreq.size() - 1; i >= 0; --i){
            noteFreq[i] -= freqDelta[i];
            // printf("%d,",(*toWithdraw)[i]);
        }
        return toWithdraw;
    }
};

/**
 * Your ATM object will be instantiated and called as such:
 * ATM* obj = new ATM();
 * obj->deposit(banknotesCount);
 * vector<int> param_2 = obj->withdraw(amount);
 */
