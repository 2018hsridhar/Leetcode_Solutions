

/*

1812. Determine Color of a Chessboard Square


Coordinate is always a valid chessboard square
Letter first - Number second ( e.g. A1 )
Is a basic switch-case statement 

std::stoi DENOTES string to an integer ( the type of casting in c++ )
std library includes a regex match method too!

Remember regex ranges - especially for characters. Will be faster than a hashset!

*/

class Solution 
{
public:
    bool squareIsWhite(string coordinates) 
    {
        std::regex regex_aceg = std::regex("[aceg]");
        std::regex regex_bdfg = std::regex("[bdfh]");
        string letter = coordinates.substr(0,1);
        int number = std::stoi(coordinates.substr(1,2));
        if(std::regex_match(letter, regex_aceg))
            return (number % 2 == 0);
        else if ( std::regex_match(letter, regex_bdfg))
            return (number % 2 == 1);
        return false;
    }
};
