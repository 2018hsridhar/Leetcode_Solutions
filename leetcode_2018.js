/**
 * @param {character[][]} board
 * @param {string} word
 * @return {boolean}
 */
 /*
 URL := https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword/
 2018. Check if Word Can Be Placed In Crossword

Complexity
Let M, N := dims(CrossWord)
Let W := len(word)
Time := O(MNW)
Space := O(1) ( EXP & IMP ) 
Reverse the word - be quick

Why are so many matrix questions naturally enumeration based?
Easy to introduce bugs in these questions
ALso easy to run into TLE issues

Algos is correct
How to get efficiency though?

35 minutes to a working solution

 */
var placeWordInCrossword = function(board, word) {
    // console.log(reverse(word))
    let revWord = reverse(word) // efficiency in reverse operation
    // String reverse multiple times provoked inefficiency -> please dodge that!
    let m = board.length
    let n = board[0].length
    // Can we change iteration styling?
    // For each array looping
    // console.log(canPlaceWordRight(2,1,reverse(word),board))
    for(let r = 0; r < m; r++) {
        for(let c = 0; c < n; c++) {
          // No complaints if I forget to parameter pass
          // Not emulating double ops in crossword puzzles?
            if(canPlaceWord(r,c,word,board) || canPlaceWord(r,c,revWord,board)){
              // JS allows for semicolons in coding!
                return true
            }
        }
    }
    return false;
};

// Lazy way out
function reverse(s){
    return s.split("").reverse().join("");
}

/*
Lack of static typing = can type func signatures faster?
Let us see func decomposition
*/
function canPlaceWord(r,c,word,grid) {
    return canPlaceWordBottom(r,c,word,grid) || canPlaceWordRight(r,c,word,grid)
}

// Columnar placement
function canPlaceWordRight(sR,sC,word,grid){
    let m = grid.length
    let n = grid[0].length
    let w = word.length
    if(sC + w > n) { // OOB here
        return false
    }
    let canPlaceWord = true
    // Lack of `let` kewyrod for vars also allowed!
    let leftCol = sC - 1
    let rightCol = sC + w
    if(!borderCellsMeetCriteria(sR,leftCol,sR,rightCol,grid)) {
      return false
    }
    let i = 0
    for(let c = sC; c < sC + w; c++) {
        // JS and JAVA use charAt(index)
        if(!meetCellCriteria(sR,c,word.charAt(i), grid)){
            return false
        }
        i++
    }
    // for each on Arrays -> split and create array here
    // let c = sC
    // word.split('').forEach(letter => {
    //   if(!meetCellCriteria(sR,c,letter, grid)){
    //     return false
    //   }
    //   c++
    // })
    return true
}

function borderCellsMeetCriteria(r1,c1,r2,c2,grid) {
    if(isInBounds(r1,c1,grid)) {
        if(grid[r1][c1] != '#') {
            return false
        }
    }
    if(isInBounds(r2,c2,grid)) {
        if(grid[r2][c2] != '#') {
            return false
        }
    }
    return true
}

// Row placement
function canPlaceWordBottom(sR,sC,word,grid) {
    let m = grid.length
    let n = grid[0].length
    let w = word.length
    let canPlaceWord = true
    let i = 0
    let topRow = sR - 1
    let bottomRow = sR + w
    if(sR + w > m) {
        return false
    }
    if(!borderCellsMeetCriteria(topRow,sC,bottomRow,sC,grid)) {
      return false
    } 
    for(let r = sR; r < sR + w; r++) {
        // JS and JAVA use charAt(index)
        if(!meetCellCriteria(r,sC,word.charAt(i), grid)){
            canPlaceWord = false
            break
        }
        i++
    }
    return canPlaceWord
}

function meetCellCriteria(r,c,letter,grid){
    let notHashCell = ( grid[r][c] != '#' )
    let emptyOrEqLetter = (grid[r][c] == ' ' || grid[r][c] == letter)
    let metCell = notHashCell && emptyOrEqLetter
    // console.log("Cell critiera = %d\n", metCell)
    return metCell
}

function isInBounds(r,c,grid) {
    let m = grid.length
    let n = grid[0].length
    return (0 <= r && r < m) && (0 <= c && c < n)
}
