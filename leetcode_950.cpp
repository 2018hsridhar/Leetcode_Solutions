/*
950. Reveal Cards In Increasing Order
URL = https://leetcode.com/problems/reveal-cards-in-increasing-order/
*/
class Solution {
public:
    vector<int> deckRevealedIncreasing(vector<int>& deck) {
        int n = deck.size();
        vector<int> orderedDeck(n,0); // avoid push_back op
        int rIdx = 0;
        std::sort(deck.begin(), deck.end());
        queue<int> indexes;
        for(int i = 0; i < n; ++i)
            indexes.push(i);
        while(indexes.size() > 1){
            int firstIdx = indexes.front();
            indexes.pop();
            int secondIdx = indexes.front();
            indexes.pop();
            indexes.push(secondIdx);
            orderedDeck.at(firstIdx) = deck.at(rIdx);
            rIdx++;
        }
        orderedDeck[indexes.front()] = deck.at(rIdx);
        return orderedDeck;
    }
};
