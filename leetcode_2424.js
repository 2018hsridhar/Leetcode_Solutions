/**
 * @param {number} n
 */

 /*
    2424. Longest Uploaded Prefix
    URL := https://leetcode.com/problems/longest-uploaded-prefix/

    Length longest uploaded prefix
    Upload of content/media to a remote server ]
    Prefixes inclusitivity

    Streaming uploads problem

    Complexity
    Let V := #-videos uploaded to server
    Time := O(V)
    Space := O(V)( EXP ) O(1) ( IMP ) [ use a hashset here and a global counter ]

    5 mins to solve
 */

var LUPrefix = function(n) {
    this.hitVideos = new Set()
    this.lup = 0
};

/** 
 * @param {number} video
 * @return {void}
 */
LUPrefix.prototype.upload = function(video) {
    if(!this.hitVideos.has(video)){
        this.hitVideos.add(video)
    }
};

/**
 * @return {number}
 */
LUPrefix.prototype.longest = function() {
    while(true) {
        var nextLUP = this.lup + 1
        if(this.hitVideos.has(nextLUP)) {
            this.lup = nextLUP
        } else {
            break
        }
    }
    return this.lup
};

/** 
 * Your LUPrefix object will be instantiated and called as such:
 * var obj = new LUPrefix(n)
 * obj.upload(video)
 * var param_2 = obj.longest()
 */
