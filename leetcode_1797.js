/*
URL := https://leetcode.com/problems/design-authentication-manager/
1797. Design Authentication Manager

Huh ignore request behavior
All tokens share same TTL too
Do we need a map with current approach? ... or just a set?

ID dimensionality reasonble : 26^5 combinations
2K calls max over all funcs ( design questions scope for calls from extenral world ) 
... Close. 88/91 passing

Complexity
Let T := unique #-token Ids
Space := O(T) ( EXP ) O(1) ( IMP ) 
Time := O(T) 

21 minutes to solution
*/
/**
 * @param {number} timeToLive
 */
var AuthenticationManager = function(timeToLive) {
    this.timeToLive = timeToLive
    this.tokenCount = new Map();
    this.tokenWindow = [] // token window ( a sliding window ) ( manage sessions via sliding windows ) 
};

/** 
 * @param {string} tokenId 
 * @param {number} currentTime
 * @return {void}
 */
AuthenticationManager.prototype.generate = function(tokenId, currentTime) {
    // should check tokenId exists
    if(!this.tokenCount.has(tokenId)){
        let sTime = currentTime;
        let eTime = sTime + this.timeToLive;
        let tokenInterval = [tokenId,sTime,eTime]
        this.tokenCount.set(tokenId,1);
        this.tokenWindow.push(tokenInterval);
    }
};

/** 
 * @param {string} tokenId 
 * @param {number} currentTime
 * @return {void}
 */
AuthenticationManager.prototype.renew = function(tokenId, currentTime) {
    this.countUnexpiredTokens(currentTime); // no return it ( expiration precedes renewal ) 
    if(this.tokenCount.has(tokenId)){
        let sTime = currentTime;
        let eTime = sTime + this.timeToLive;
        let tokenInterval = [tokenId,sTime,eTime]
        this.tokenCount.set(tokenId,this.tokenCount.get(tokenId) + 1);
        this.tokenWindow.push(tokenInterval);
    }
};

/** 
 * @param {number} currentTime
 * @return {number}
 */
 // Meat of logic is in this method
 // Expiration before other actions
 // [1] do eviction
 // [2] go over token window and tell number Unique elements there
 // Do we really ahve to count tokens in the other window; if we know a renewal count ( and eviction count ) -> can we store the assoc counts and mess from there ( remove the key only when a count was hit too ) ?
AuthenticationManager.prototype.countUnexpiredTokens = function(currentTime) {
    // use size of the tokenCount window instead
    let unexpTokenCount = 0;
    while(this.tokenWindow.length > 0) {
        let firstToken = this.tokenWindow[0];
        let firstTokenId = firstToken[0];
        let firstTokenEnd = firstToken[2];
        if(firstTokenEnd <= currentTime) {
            this.tokenCount.set(firstTokenId, this.tokenCount.get(firstTokenId) - 1);
            if(this.tokenCount.get(firstTokenId) == 0) {
                this.tokenCount.delete(firstTokenId);
            }
            this.tokenWindow.shift();    
        } else { // rest of tokens on time
            break;
        }
    }
    unexpTokenCount = this.tokenCount.size; // an accessor property ( not a func ) 
    return unexpTokenCount;
};

/** 
 * Your AuthenticationManager object will be instantiated and called as such:
 * var obj = new AuthenticationManager(timeToLive)
 * obj.generate(tokenId,currentTime)
 * obj.renew(tokenId,currentTime)
 * var param_3 = obj.countUnexpiredTokens(currentTime)
 */
