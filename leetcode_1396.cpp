/*
Leverage the constraint : a customer can check in to only one place at a time.
And must correspondingly check out at another station, before attempting a later check in. 
URL = https://leetcode.com/problems/design-underground-system/
1396. Design Underground System

*/
class CustInfo{
    public:
        int id;
        string station;
        int t;
        CustInfo(){}
        CustInfo(int id, string station, int t) { this->id = id; this->station = station; this->t = t; }
};

class UndergroundSystem {
private:
    map<int,CustInfo> startStations;
    map<string,map<string,pair<int,double>>> deltas; // start,fin,freq,totalTime
public:
    UndergroundSystem() {
        
    }
    
    void checkIn(int id, string stationName, int t) {
        if(startStations.find(id) == startStations.end()){
            startStations[id] = CustInfo(id,stationName,t);;   
        }
        if(deltas.find(stationName) == deltas.end()){
            deltas[stationName] = std::map<string,pair<int,double>>();
        }
    }
    
    void checkOut(int id, string stationName, int t) {
        long long timeDelta = t - startStations[id].t;
        std::string startStation = startStations[id].station;
        std::string endStation = stationName;
        // Hey they both work too!
        deltas[startStation][endStation].first += 1;
        (deltas[startStation])[endStation].second += timeDelta;
        startStations.erase(id); // delete the key after processing
    }
    
    double getAverageTime(string startStation, string endStation) {
        return (double)((deltas[startStation])[endStation].second) / (double)((deltas[startStation])[endStation].first);
    }
};

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem* obj = new UndergroundSystem();
 * obj->checkIn(id,stationName,t);
 * obj->checkOut(id,stationName,t);
 * double param_3 = obj->getAverageTime(startStation,endStation);
 */
