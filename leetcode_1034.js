/**
 * @param {number[][]} grid
 * @param {number} row
 * @param {number} col
 * @param {number} color
 * @return {number[][]}
 */
 /*
 34 minutes spent
  -> careful with changing as we go here ( not like other DFS/BFS where we can do so )m
 URL := https://leetcode.com/problems/coloring-a-border/
 1034. Coloring A Border

Standard DFS/BFS Approach with modifications
Plz do iterative BFS -> let's make it harder :-) 

Not in component = not matching the start Color as well

Complexity
Let M, N := grid dims
Time := O(MN)
Space := O(MN) ( EXP ) O(1) ( IMP ) 

harder than expected ... huh

 */
var colorBorder = function(grid, row, col, color) {
    let m = grid.length // At least array prototype length is not a function call
    let n = grid[0].length
    // nested arrays via array.map
    let visited = Array(m).fill().map(() => Array(n).fill(false)) // fill map multiDim array
    let queue = Array()
    let startColor = grid[row][col]
    if(startColor == color) { // can not color ( already colored )
      return grid
    }
    queue.push(Array(row,col)) // Queue as just a dynamic array in JS
    dirs = Array(
        Array(-1,0),
        Array(1,0),
        Array(0,-1),
        Array(0,1)
    )
    // Stack is to DFS and Queue is to BFS
    // Assumption : Queue els included only the els meeting color standards too
    // Queue shift and Queue pop
    myBorder = [] // literal syntax array construction
    while(queue.length > 0) {
        let parent = queue.shift()
        let r = parent[0]
        let c = parent[1]
        if(visited[r][c] == false) { // If already colored -> NOP
          visited[r][c] = true
          if(isOnGridBoundary(r,c,grid)){
              myBorder.push(Array(r,c))
          }
          for (dir of dirs) { // for ... of retrieve val ( versus key ) 
              let delR = dir[0]
              let delC = dir[1]
              let cR = r + delR
              let cC = c + delC
              child = Array(cR, cC)
              if(isInBounds(cR,cC,grid)){
                  if(grid[cR][cC] == startColor) {
                      queue.push(child)
                  } else if ( grid[cR][cC] != startColor){
                    myBorder.push(Array(r,c)) // NOP if duplicate entry anyways
                  }
              }
          }
      }
    }
    // lambda capture .forEach
    // Not (r,c) syntax -> entire item syntax here
    // .forEach prototype power
    myBorder.forEach(coord => grid[coord[0]][coord[1]] = color)
    // return grid here? YES - UNDEFINED error thrown
    return grid
};

// No explicitl return in this language
function isInBounds(r,c,grid) {
    let m = grid.length
    let n = grid[0].length
    return (0 <= r && r < m) && (0 <= c && c < n)
}

// Woooh dodge the `isInBounds` cond check here :-)
// function-let syntax in JS
function isOnGridBoundary(r,c,grid){
    let m = grid.length
    let n = grid[0].length
    return (r == 0 || c == 0 || r == m-1 || c == n-1)
}
