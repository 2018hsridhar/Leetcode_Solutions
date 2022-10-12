/*
2043. Simple Bank System
URL = https://leetcode.com/problems/simple-bank-system/

["Bank","withdraw","transfer","deposit","transfer","withdraw"]
[[[10,100,20,50,30]],[3,10],[5,1,20],[5,20],[3,4,15],[10,50]]

*/
class Bank {
private:
    vector<long long> myBalances;
    int numAccounts;
    
    bool accountOutOfBounds(int accountId){
        accountId -= 1;
        return (!(0 <= accountId && accountId < numAccounts));
    }
    
public:
    Bank(vector<long long>& balance) {
        myBalances = balance; // copy assignment operator?
        numAccounts = balance.size();
    }
    
    bool transfer(int account1, int account2, long long money) {
        bool status = true;
        if(accountOutOfBounds(account1) || accountOutOfBounds(account2)){
            status = false;
        } else {
            account1 -= 1;
            account2 -= 1;
            if(myBalances[account1] < money){
                status = false;
            } else {
                myBalances[account1] -= money;
                myBalances[account2] += money;
            }
        }
        return status;
    }
    
    bool deposit(int account, long long money) {
        bool status = true;
        if(accountOutOfBounds(account)){
            status = false;
        } else {
            account--;
            myBalances[account] += money;
        }
        return status;        
    }
    
    bool withdraw(int account, long long money) {
        bool status = true;
        if(accountOutOfBounds(account) || myBalances[account - 1 ] < money){
            // printf("%d\t%d\n", myBalances[account-1], money);
            status = false;
        } else {
            account--;
            myBalances[account] -= money;
        }
        return status;        
    }
};

/**
 * Your Bank object will be instantiated and called as such:
 * Bank* obj = new Bank(balance);
 * bool param_1 = obj->transfer(account1,account2,money);
 * bool param_2 = obj->deposit(account,money);
 * bool param_3 = obj->withdraw(account,money);
 */
