/*

URL = https://leetcode.com/problems/design-file-system/
1166. Design File System

Always ask what the path format is ( and language range : in this case, [a-z] )
"/" and "" are not valid paths ( "/" = root path )
Check if we need to care about the root path too
Find out if path ends with "/" or does not end with "/" 
Are paths absolute paths or relative paths?

Edge cases for path testing : 

(1) Degenerate cases - "" and "/")
(2) No parent path, for provided child - "/leetcode/problems"
(3) Parent paths provided for child path - "/a/b/c/d", "/a/b/c", "/a/b", "/a"
(4) A singleton path - "/a"




*/

class FileSystem 
{
    HashMap<String, Integer> pathValues;

    public FileSystem() 
    {
        pathValues = new HashMap<String,Integer>();
    }
    
    public boolean createPath(String path, int value) 
    {
        if(path.equals("") || path.equals("/") || pathValues.containsKey(path))
            return false;
        
        // Tokenize the string now
        // Exert caution as strings already start with this delimieter too ( if in beginning : get an empty string :-O )
        String[] tokens = path.split("/"); // Note escape sequence boyo : // != /
        StringBuilder parentPath = new StringBuilder("/");
        int i = 1;
        while(i < (tokens.length - 1)) // not < tokens.length : need to test the child
        {
            String token = tokens[i];
            parentPath.append(token);
            String parent = parentPath.toString();
            if(!pathValues.containsKey(parent))
                return false; // Must check all parents BTW
            parentPath.append("/"); // Do only at the end
            ++i;
        }
        pathValues.put(path,value);
        return true;
        
    }
    
    public int get(String path) 
    {
        if(!pathValues.containsKey(path))
            return -1;
        return pathValues.get(path);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */
