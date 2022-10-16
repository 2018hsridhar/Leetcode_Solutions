/*
609. Find Duplicate File in System
URL = https://leetcode.com/problems/find-duplicate-file-in-system/
*/
class Solution {
public:
    vector<vector<string>> findDuplicate(vector<string>& paths) {
       vector<vector<string>> groups = vector<vector<string>>();
       map<string, vector<string>> contentGroups = map<string,vector<string>>(); // stack obj-instantiation.
        for(auto& entry : paths){ //  range-loops preferred to iterator-based syntax
            // apply split here
            // leverage indexOf("") operation on each file too
            std::istringstream iss(entry);
            string s;
            string dir;
            string file;
            bool hitDir = false;
            // this annoying : no arrays.split() method -> must operate like stream via getline
            while(std::getline(iss,s,' ' )) {
                if(!hitDir){
                    dir = s; // implicit copy op
                    hitDir = true;
                } else {
                    file = s;
                    // find method for characters helpful
                    int leftParanIdx = s.find('(');
                    int rightParanIdx = s.find(')');
                    int len = file.size();
                    // string content = file.substr(leftParanIdx + 1, len - 3);
                    // Jeez : substr(index,offset) only : NOT substr(leftIdx,rightIx)
                    string content = file.substr(leftParanIdx + 1, rightParanIdx-1-leftParanIdx);
                    // printf("Content = %s\n", content);
                    // std::cout << "Content = " << content << std::endl; // control string stream
                    if(contentGroups.find(content) == contentGroups.end()){
                        contentGroups[content] = std::vector<string>();
                    }
                    // c++ at least overloads strings like this :-)
                    string absPath = dir + "/" + file.substr(0,leftParanIdx);
                    // printf("absPath = %s\n", absPath.c_str());
                    contentGroups[content].push_back(absPath);
                }
            }
        }
        // Structured binding :-)
        for(const auto& [content,content_list] : contentGroups){
            if(content_list.size() >= 2){
                auto new_group = vector<string>(content_list); // deep copy vector
                groups.push_back(new_group);
            }
        }
        
       return groups;   
    }
};
