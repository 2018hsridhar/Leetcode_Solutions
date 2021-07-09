
/*
588. Design In-Memory File System

THOUGHT PROCESS : 
In-memory file system : efficiency of operations , since in-memory ( vs. out-of-memory ) desireable

Bounds testing :
Path length and file length reasonable : not longer than 100 characters
Absolute pathnames only ( no relative ) -> all begin with '/' -> all stsart from this in the tree
File structrue -> to use a tree or not here?
All user operatinos will be valid : nothing weird for a production environment to test
Content length is reasonable too
300 calls max to all methods invoked! 
Lowcase values only here!

DUmb idea -> two hash tables
First hashtable : tell if my thing is a file or a dir
Second hashtable : obtain actual contents of the file or directory

So this is eerily akin to capacity planning problems in distributed systems
Additionally, based on the amount of calls invoked, aim to determine the performance time needed per operations. If allocating auxillary "in-memory" structures, aim for low performance time - e.g. O(1) constant time, in this case.

Computational complexity : 
List for directory should be in lexicographic ordering : how to sort here

Edge cases :

*/

class FileSystem 
{
    HashMap<String,Character> fileType;
    HashMap<String,List<String>> files;
    
    public FileSystem() 
    {
        fileType = new HashMap<String,Character>();
        files = new HashMap<String,List<String>>();
        
        // Add first directory ( default = "/") to fileType and files
        fileType.put("/", 'D');
        files.put("/", new LinkedList<String>());
    }
    
    // Guess one hashmap from the lsit anyways
    // Can assume paths always exists here
    // Can assume paths will always be absolute paths too ( one-level down only ) 
    public List<String> ls(String path) 
    {
        List<String> listing;
        char fileType = this.fileType.get(path);
        if(fileType == 'F')
        {
            // You want only the name of the file -> NOT the actual absolute path! Take note!
            // last index of method
            String fileName = path.substring(path.lastIndexOf('/') + 1);
            listing = new LinkedList<String>();
            listing.add(fileName);
            
        }
        else // is directory here
        {
            listing = files.get(path);
            Collections.sort(listing); 
        }
        return listing;
    }
    
    // Make a new directory
    // If middle dirs do not exists -> make those too as well
    // And add to directories!
    // Also assumming path si never equal to "/"
    // Do check if the same directory is being made again though -> do not do again!
    
    public void mkdir(String path) 
    {
        if(fileType.containsKey(path))
            return;
        // Track parent as we go anyways
        StringBuilder sb = new StringBuilder("/");
        StringBuilder parent = new StringBuilder("/"); // mutate each time
        
        String[] dirTree = path.split("/");
        for(int i = 1; i < dirTree.length; ++i) 
        {
            sb.append(dirTree[i]);
            String pwd = sb.toString();
            if(!files.containsKey(pwd))
            {
                fileType.put(pwd, 'D');
                files.put(pwd,new LinkedList<String>());
                files.get(parent.toString()).add(dirTree[i]);
            }
            parent.replace(0,parent.length(), pwd);
            sb.append("/");
        }
    }
    
    // The weird content case -> ugh : still use a List<String> but now it be a singleton list!
    public void addContentToFile(String filePath, String content) 
    {
        List<String> newContent = new LinkedList<String>();
        if(!fileType.containsKey(filePath))
        {
            fileType.put(filePath,'F');
            newContent.add(content);
            files.put(filePath, newContent);
            // Forgot to create the file : need to update its parent too! ( OMG ) 
            String parent = filePath.substring(0,Math.max(filePath.lastIndexOf("/"),1)); // a gotcha case : need a "1" here
            String file = filePath.substring(filePath.lastIndexOf("/") + 1);
            List<String> children = files.get(parent);
            children.add(file);
            files.put(parent,children);
            
        }
        else
        {
            String currentContent = files.get(filePath).get(0);
            newContent.add(currentContent + content);
            files.put(filePath, newContent);
        }
    }
    
    public String readContentFromFile(String filePath)
    {
        return files.get(filePath).get(0);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
