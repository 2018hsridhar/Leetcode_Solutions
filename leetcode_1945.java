class Solution {
    public int getLucky(String s, int k) 
    {
        if(s.equals("") || s == null)
            return 0;
        int lucky = 0;
        StringBuilder sb = new StringBuilder("");
        for(char ch : s.toCharArray() ) 
        {
            int ascii = (int)(ch - 'a' + 1);
            sb.append(ascii);
        }
        for(int a = 0; a < k; ++a)
        {
            int temp = 0;
            String res = sb.toString();
            for(char ch : res.toCharArray())
            {
                // System.out.printf("ch = %s\n", ch);
                temp += (ch-'0');
            }
            sb.delete(0, sb.length());
            sb.append(temp);
        }
        lucky = Integer.parseInt(sb.toString());
        return lucky;
    }
}
