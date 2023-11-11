/*
URL := https://leetcode.com/problems/minesweeper/
529. Minesweeper

Huh GoLang with byte matrices? Interesting
Click as an initial position value

Complexity
Let M, N := #-rows,#-cols in the Minesweeper board
Time := O(MN) [ recurse all empty square no mines in the game ]
Space := O(MN) ( IMP ) O(1) ( EXP ) 

*/
func updateBoard(board [][]byte, click []int) [][]byte {
    dfs(board,click)
    return board
}

/*
Limiting of param passing in function calls
*/
func dfs(board [][]byte, pos []int) {
    adjList := [][]int{
        {-1,-1},
        {-1,0},
        {-1,1},
        {0,-1},
        {0,1},
        {1,-1},
        {1,0},
        {1,1},
    }
    m := len(board)
    n := len(board[0])
    x := pos[0]
    y := pos[1]
    boardEl := board[x][y]
    if(boardEl == 'M') {
        board[x][y] = 'X'
        return
    } else if (boardEl == 'E') {
        // adjMineCount := byte(0)
        var adjMineCount = int64(0)
        for _, step := range adjList {
            delX := x + step[0]
            delY := y + step[1]
            if(isInBounds(m,n,delX,delY)) {
                if(board[delX][delY] == 'M') { // unrevealed mine state
                    adjMineCount++
                }   
            }
        }
        if(adjMineCount == 0) {
            board[x][y] = 'B'
            for _, step := range adjList {
                delX := x + step[0]
                delY := y + step[1]
                // adjacent unrevealed squares only revealed recursively
                if(isInBounds(m,n,delX,delY) && board[delX][delY] == 'E') {
                    dfs(board,[]int{delX,delY})
                }
            }
        } else {
            // board[x][y] = (byte)(strconv.Itoa(adjMineCount)) // gaah no byte direct converion
            // board[x][y] = (byte)(adjMineCount) // unicopde introduced
            // board[x][y] = ((rune)(adjMineCount))
            temp := strconv.FormatInt(adjMineCount,10)
            // Ok now this is crummy. int64 -> strconv formatting? like wow
            board[x][y] = temp[0]
            return
        }
    }
}

func isInBounds(m, n, x, y int) bool {
    return (0 <= x && x < m) && (0 <= y && y < n)
}

