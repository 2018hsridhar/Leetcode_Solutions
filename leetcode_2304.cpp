/*
2304. Minimum Path Cost in a Grid
URL = https://leetcode.com/problems/minimum-path-cost-in-a-grid/

Complexity
Time = O(MN^2)
SPACE = O(MN) ( EXP ) O(1) ( EXP ) 
Done
No edge case -> debugged :-)
28 mins to solution
*/
class Solution {
public:
    int minPathCost(vector<vector<int>>& grid, vector<vector<int>>& moveCost) {
        int globalMinPathCost = INT_MAX;
        map<int,int> minCostToCell;
        int numCellsPerRow = grid.at(0).size();
        // [1] Precompute for all the rows
        for(int i = 0; i < grid.size(); ++i){
            vector<int>& cellsInRow = grid.at(i);
            if(i == 0){
                for(int c = 0; c < numCellsPerRow; ++c){
                    int cell = grid[0][c];
                    minCostToCell[cell] = cell; // the cell value itself
                    // cout << "For cell = " << cell << "Cost = " << minCostToCell[cell] << endl;
                }
            } else if ( i > 0 ){
                vector<int>& cellsAbove = grid.at(i-1);
                for(int c = 0; c < cellsInRow.size(); ++c){
                    int cell = grid[i][c];
                    int subProblemCost = INT_MAX; // cell itself
                    for(int pc = 0; pc < cellsAbove.size(); pc++){
                        int cellAbove = cellsAbove[pc];
                        int edgeCost = moveCost[cellAbove][c];
                        subProblemCost = std::min(subProblemCost, cell + minCostToCell[cellAbove] + edgeCost);
                    }
                    minCostToCell[cell] = subProblemCost;
                    // cout << "For cell = " << cell << "Cost = " << minCostToCell[cell] << endl;
                }
            }
        }
        // [2] Get min cost once in last row ( or could do it in above loop expr too )!
        for(int c = 0; c < numCellsPerRow; ++c){
            int cell = grid[grid.size() - 1][c];
            globalMinPathCost = std::min(globalMinPathCost, minCostToCell.at(cell));
        }
        return globalMinPathCost;
    }
};
