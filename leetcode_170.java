/*

URL = https://leetcode.com/problems/two-sum-iii-data-structure-design/
170. Two Sum III - Data structure design

Nte : numbers range into the negatives too [ -100,000, 100,000 ]
- Ask if 2sum works in the negative values range too

Luckily, values bounded by integers data type

Number of calls be reasonable too


*/


class TwoSum 
{

    /** Initialize your data structure here. */
    HashMap<Integer,Integer> numFreq;
    public TwoSum() 
    {
        numFreq = new HashMap<Integer,Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) 
    {
        if(numFreq.containsKey(number))
            numFreq.put(number, numFreq.get(number) + 1);
        else
            numFreq.put(number, 1);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) 
    {
        int half = (value / 2); // goes down if odd num case anyways ( eg. 9 / 2 = 4, not 9 / 2 = 5)
        // Handle 0 case and negative case too!
        Set<Map.Entry<Integer,Integer>> kv_pairs = numFreq.entrySet();
        for(Map.Entry<Integer,Integer> kv : kv_pairs)
        {
            int operand1 = kv.getKey();
            int freq = kv.getValue();
            if(numFreq.containsKey(operand1))
            {
                int operand2 = (value - operand1);
                if(operand2 == operand1)
                {
                    if(freq >= 2)
                        return true;
                }
                else
                {
                    if(numFreq.containsKey(operand2))
                        return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
