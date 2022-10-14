class Robot {
private:
    int width;
    int height;
    int curX;
    int curY;
    int dir;
    // Can not assign vals to fixed array : can leverage initializer syntax for std::vector<T>
    // Prefer std::vector<T> to array due to flexibility as well.
    // int card[4]; // brackets after identifier of array
    std::vector<int> card;
    std::vector<std::string> dirs;
public:
    // At least objects - not primitive types/structs - get default initialized.
    // Helps ensure safety.
    Robot(int width, int height) {
        this->dir = 1; // EAST
        this->card = {0,1,2,3}; // N,E,S,W dirs
        this->dirs = {"North","East","South","West"};
        this->curX = 0;
        this->curY = 0;
        this->width = width;
        this->height = height;
    }
    
    bool OutOfBounds(int x, int y){
        return (!(0 <= x && x < this->width ) || !(0 <= y && y < this->height));
    }
    
    void step(int num) {
        while(num > 0){
            switch(dir){
                case 0:
                    if(OutOfBounds(curX,curY+1)){
                        dir = 3;
                        curX--;
                    } else {
                        curY++;
                    }
                    break;
                case 1:
                    if(OutOfBounds(curX+1,curY)){
                        dir = 0;
                        curY++;
                    } else {
                        curX++;
                    }
                    break;
                case 2:
                    if(OutOfBounds(curX,curY-1)){
                        dir = 1;
                        curX++;
                    } else {
                        curY--;
                    }
                    break;
                case 3:
                    if(OutOfBounds(curX-1,curY)){
                        dir = 2;
                        curY--;
                    } else {
                        curX--;
                    }
                    break;
            }
            num--;
        }
    }
    
    vector<int> getPos() {
        return vector<int>{curX,curY};
    }
    
    string getDir() {
        return dirs.at(dir);
    }
};

/**
 * Your Robot object will be instantiated and called as such:
 * Robot* obj = new Robot(width, height);
 * obj->step(num);
 * vector<int> param_2 = obj->getPos();
 * string param_3 = obj->getDir();
 */
