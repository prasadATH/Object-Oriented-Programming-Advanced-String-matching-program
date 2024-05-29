package edu.curtin.app;
import java.util.*;
import java.io.*;

public class Directory implements DataEntry {


  //Intentionally private to restrict access
   private String dirName;
   //Intentionally private to restrict access
   private List<DataEntry> subDirectories;
   //Intentionally private to restrict access
   private File dirFile;
   //Intentionally private to restrict access
   private FileEntry fileDetected;


   public Directory()
   {
    dirName = "";
    dirFile = null;
    subDirectories = new ArrayList<>();

    
   }
   @Override
   public String getName()
   {
     return dirName;

   }

   @Override
   public void setName(String inDirName)
   {
      dirName = inDirName;

   }

   @Override
   public File getFile()
   {
     return dirFile;

   }
   
   public List<DataEntry> getSubDirectories()
   {
     return subDirectories;

   }

   public void add(DataEntry component) 
   {

    subDirectories.add(component);

   }

   @Override
   public int countLines() {
     int count = 0;
     for (DataEntry subDir : subDirectories) {
   
       if(subDir instanceof FileEntry)
       {
         count += subDir.countLines();
       }
         
     }
     return count;
   }
   



//Find operation which finds a file and return it to FileEntry for processing
    @Override
    public FileEntry find(File fileContent, List<User> userData) {


    try {

  

        File[] files = fileContent.listFiles();

       
        for (File file : files) {

            
            if (file.isDirectory()) {


            Directory subDirectory = new Directory();
            subDirectory.setName(file.getName());
           add(subDirectory);
          
          
          subDirectory.find(file, userData); // recurse into subdirectories
          subDirectory.dirFile = file;
         
         
            find(file,userData); // recurse into Directories
            dirFile = fileContent;
            

            }

            else
             {
              
               
              fileDetected = new FileEntry();

             add(fileDetected.find(file, userData));


           
           }
        }

      }catch (IllegalArgumentException e) 
      {
        System.out.println("Path of the file is invalid! "+ e.getMessage());

        
         
      }

      return fileDetected;
    }







}