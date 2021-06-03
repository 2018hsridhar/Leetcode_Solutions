/*

https://leetcode.com/problems/design-parking-system/
1603. Design Parking System


Car type - 3 car kinds - { small, medium, big } = { 3, 2, 1 }
Can park only if spaces available too

Provide number of each type of slots into the Parking System too!
Wait - isn't this just scalars in the making???
We execute only "addCar" operations too! We never remove cars! 
Car types are bounded anyways in the closed interval [1,3]
And we never go beyond 2^32 - 1, for datatype = INT, for small-big


*/



class ParkingSystem {
    
    int small;
    int medium;
    int big;

    public ParkingSystem(int big, int medium, int small) 
    {
        this.small = small;
        this.medium = medium;
        this.big = big;
    }
    
    public boolean addCar(int carType) 
    {
        if(carType == 3 && small > 0)
        {
            --small;
            return true;
        }
        else if(carType == 2 && medium > 0)
        {
            --medium;
            return true;
        }
        else if(carType == 1 && big > 0)
        {
            --big;
            return true;
        }
        else
            return false; 
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */
