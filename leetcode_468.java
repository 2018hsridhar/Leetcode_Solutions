/*

468. Validate IP Address
Make sure to validate for digits ONLY in the case of IPv4 - > check that Integer.parseInt() works as expected
Be careful with the "parseInt" function -> tends to ignore leading 0's too

URL = https://leetcode.com/problems/validate-ip-address/


Test cases : 
For IPv4
192.1.22.333
192.1.22.222
1.1.1.1
192.168.1.0
255.255.0.0
256.256.256.256
254.001.1.1
"192.1..4.22.222"

For IPv6
2001:0db8:85a3:0000:0000:8a2e:0370:7334
2001:0db8:85a3::8A2E:037j:7334
02001:0db8:85a3:0000:0000:8a2e:0370:7334
2001:0db8:85a3:0:0:8A2E:0370:7334:
2001:0db8:85a3:0:0:8A2E:0370:7334:
*/


class Solution 
{
    public String validIPAddress(String IP) 
    {
        // IPV4 Case
        if(IP == null || IP.length() == 0)
            return "Neither";
        if(IP.charAt(IP.length() - 1) == '.' || IP.charAt(IP.length() - 1) == ':')
            return "Neither";
        if(IP.indexOf('.') != -1)
        {
            String[] tokens = IP.split("\\.");
            if(tokens.length != 4)
                return "Neither";
            for(String s : tokens)
            {
                if(s.length() < 1 || s.length() > 3)
                    return "Neither";
                if(s.length() > 1 && s.charAt(0) == '0')
                    return "Neither";
                if(!s.matches("\\d+"))
                    return "Neither";                    
                int val = Integer.parseInt(s);
                // System.out.printf("val = X%dX\n", val);
                if(val > 255 || val < 0)
                    return "Neither";
            }
            return "IPv4";
        }
        // IPV6 case
        // Is not 6 tokens -> is actually 8 tokens here!
        // Is allowed to have leading zeroes BTW! ( WOAH ) 
        // No real range testing ( due to HEXADECIMAL string based pattern ) 
        // Check for [0-9a-fA-F]
        else if ( IP.indexOf(':') != -1 )
        {
            String[] tokens = IP.split(":");
            if(tokens.length != 8)
                return "Neither";
            for(String s : tokens)
            {
                // System.out.printf("Token = %s\n", s);
                if(s.length() < 1 || s.length() > 4)
                    return "Neither";
                for(int i = 0; i < s.length(); ++i)
                {
                    char c = s.charAt(i);
                    if(Character.isDigit(c) || ('a' <= c && c <= 'f') || ( 'A' <= c && c <= 'F'))
                        continue;
                    else
                        return "Neither";
                }
                // if(!s.matches("0-9a-fA-F"))
                    // return "Neither";                    
            }
            return "IPv6";      
        }
        else
        {
        }
        return "Neither";
    }
}
