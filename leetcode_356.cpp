/*
URL = https://leetcode.com/problems/line-reflection/
356. Line Reflection

Let N := len(points)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    bool isReflected(vector<vector<int>>& points) {
        bool status = true;
        unordered_map<int,double> sumXPerY;
        unordered_map<int,double> countXPerY;
        unordered_map<int,set<int>> pointsOnY;
        // Always given points : must have one midpoint atleast
        for(const vector<int>& point : points){
            int x = point.at(0);
            int y = point.at(1);
            if(pointsOnY.find(y) == pointsOnY.end()){
                pointsOnY[y] = set<int>();
            }
            if(pointsOnY[y].find(x) == pointsOnY[y].end()){
                pointsOnY[y].insert(x);
                if(sumXPerY.find(y) == sumXPerY.end()){
                    sumXPerY[y] = 0;
                }
                if(countXPerY.find(y) == countXPerY.end()){
                    countXPerY[y] = 0;
                }
                sumXPerY[y] += static_cast<double>(x);
                countXPerY[y] += static_cast<double>(1);
            }
        }
        // can work off the first point, and ask from there
        // Test 1` : passes midpoints
        unordered_map<int,double> midPoints;
        int firstY = points.at(0).at(1);
        double expectedMidPoint = sumXPerY[firstY] / countXPerY[firstY];
        for(const auto& [curY,curSumPerY] : sumXPerY){
            double curCountPerY = countXPerY[curY];
            // cout << curSumPerY << '\t' << curCountPerY << endl;
            double curMidPoint = static_cast<double>(curSumPerY)/static_cast<double>(curCountPerY);
            // cout << curMidPoint << endl;
            if(curMidPoint != expectedMidPoint){
                status = false;
                return false;
            }
            midPoints[curY] = curMidPoint;
        }
        // Test 2 : countLeft == countRight ( if point on middle, ignore - > free space ) 
        unordered_map<int,int> numLeft;
        unordered_map<int,int> numRight;
        unordered_map<int,set<int>> pointsOnYTwo;
        for(const vector<int>& point : points){
            int x = point.at(0);
            int y = point.at(1);
            if(pointsOnYTwo.find(y) == pointsOnYTwo.end()){
                pointsOnYTwo[y] = set<int>();
            }
            if(pointsOnYTwo[y].find(x) == pointsOnYTwo[y].end()){
                double myMid = midPoints[y];
                // printf("My mid = %f\n", myMid);
                if(x < myMid){
                    numLeft[y]++;
                } else if ( x > myMid){
                    numRight[y]++;
                }
                pointsOnYTwo[y].insert(x);
            }
        }
        for(const auto& [myY,myNumLeft] : numLeft){
            int myNumRight = numRight[myY];
            // printf("numL = %d \t numR = %d\n", myNumLeft, myNumRight);
            if(myNumLeft != myNumRight){
                status = false;
            }
        }
        return status;
    }
};
