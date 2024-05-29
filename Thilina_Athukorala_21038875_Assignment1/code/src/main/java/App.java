package edu.curtin.app;
import java.util.*;
import java.io.*;
import java.util.logging.*;
import java.util.regex.PatternSyntaxException;

public class App {
   
    private static final Logger PROJECTLOGGER = Logger.getLogger(App.class.getName());
    public static void main(String[] args) {

        if(args.length < 1)
        {
            System.out.println("No command-line arguments : Please enter name of the 'Directory' to continue");

            return;
        }
  

        int indentation;
        int netTotal;
        Directory directoryOperation;
        File dir;
        int selection;
        int outputSelection;
        Map<File, Integer> indentationList = new HashMap<>();
        User userObj;
        List<User> allData = new ArrayList<>();
        dir=new File(args[0]);
        Scanner scannerObj = new Scanner(System.in);//NOPMD
        //eventhough the resource is closed at the end, PMD rules triggers as if it is not closed.therefore it is supressed

        selection = 0;
        

        outputSelection = 1;
        
        try{
        //by default include all lines in all files
        userObj = new User();
        FileHandler filehandler = new FileHandler("projectLog.log");
        PROJECTLOGGER.addHandler(filehandler);

        PROJECTLOGGER.log(Level.INFO, "Initializing Defult result");
        userObj.setUserFunction("i r");
        userObj.setUserCriteria(".*");
        allData.add(userObj);

       

        
        selection = 0;
        

          outputSelection = 1;

                      //tries to locate file and add to a file feild
          dir = new File(args[0]);
  

        
//checks wether file exists and valid
        if((!(dir.exists())))
        {
            
            System.out.println("Error in file please enter a valid directory ");
            PROJECTLOGGER.log(Level.INFO, "Error in file(not found or not valid)");
            return;
        }

     } catch(FileNotFoundException e)
    {
        if(PROJECTLOGGER.isLoggable(Level.SEVERE))
        {
        System.out.println("Error in file not found"+e.getMessage());
        PROJECTLOGGER.log(Level.SEVERE, "Error in file(not found)"+e.getMessage());
        }

    }catch(IOException e)
    {
        if(PROJECTLOGGER.isLoggable(Level.SEVERE))
        {
        System.out.println("Error in log file!");
        PROJECTLOGGER.log(Level.SEVERE, "File not found"+e.getMessage());
        }
    }

        //criteriaFunction = "";
        while (selection != 4) {
           
        try
        {
            selection=0;
        
            
//while user does not select exit option
     
            System.out.println("\n>1. Set Criteria.");
            System.out.println(">2. Set Output Format.");
            System.out.println(">3. Report.");
            System.out.println(">4. Quit.");
            System.out.println("\nEnter digit corresponding to your option (No.) : ");

            selection = scannerObj.nextInt();

            
            netTotal=0;


            //by default user will be displayed the line counts(matching lines are hidden)
        

           //
            switch (selection) {
                //user selects option to enter new criteria to search
                case 1:
                     //Each time user selects to enter a criteria lists are cleared(emptied)
                     allData.clear();
                    
                     PROJECTLOGGER.log(Level.INFO, "Getting user Input");
                     scannerObj.nextLine();
                     String criteriaFunction;
                    // criteriaFunction ="";

                     System.out.println("Enter any text criteria to be included as 'i<space> t <space> criterian'");
                     System.out.println("Enter any text criteria to be Excluded as 'e<space> t <space> criterian'");
                     System.out.println("Enter any Regular Expression criteria to be Included as 'i <space> r <space> criterian'");
                     System.out.println("Enter any Regular Expression criteria to be Excluded as 'e <space> r <space> criterian'");

                     System.out.println("(Note: If only excluding criteria is entered all the lines which are excluding all of the criteria will be displayed )");

                     System.out.println("\nEnter a empty line at the end of criteria list and press 'Enter' to save) : ");

                     do{
                        userObj = new User();

                        criteriaFunction = scannerObj.nextLine();

                        if(criteriaFunction.equals("")==false)
                        {

                        //Inserting User object to allData list as user inputs data
                        userObj.setUserCriteria(criteriaFunction.substring(4));
                        userObj.setUserFunction(criteriaFunction.substring(0,3));

  
                        //if user has selected an include criteria , include it to  includeCriteria list
                        PROJECTLOGGER.log(Level.INFO, "Adding User object to the data list");
                       allData.add(userObj);
                        }


                    }while(criteriaFunction.equals("")==false);
                   
                       // directoryOperation.getAllFiles(dir);

                    
                        

                    break;

         //Specify display format required by the user
                case 2:

                    System.out.println("\n>1. Count of matching lines per file.");
                    System.out.println(">2. Show actual matching lines.");

                    System.out.println("\nEnter digit corresponding to your option (No.) : ");

                    
                    outputSelection = scannerObj.nextInt();
                    PROJECTLOGGER.log(Level.INFO, "Output format set");
                    //if user has selected to display only the count

                    break;

                case 3:
                              //Create a Directory object to trigger line matching algorithm
                           directoryOperation= new Directory();

                             //Trigger the function to find the matching lines in Directory Object
                           directoryOperation.find(dir, allData);

                           //Recurse through the root directory to get the indentation levels required for each directory and store them
                           updateDirectoryLevels(dir, 1, indentationList);
                           PROJECTLOGGER.log(Level.INFO, "Indentation levels set");

                
                                    //gets the Total matches found in root directory
                                      for (DataEntry allFilesElements : directoryOperation.getSubDirectories())
                                       {
                                        //for each file found in directories
                                            if(allFilesElements instanceof FileEntry)

                                                     {//Collect all line matches found in all files in root directory
                                                       netTotal= netTotal+allFilesElements.countLines();
                            
                                                     }
                                       }
                           //if the total lines matched is zero(no lines found)
                              if(netTotal==0)
                                      {
                                        PROJECTLOGGER.log(Level.INFO, "No output for the criteria provided");
                                           System.out.println("\nNo match found for the entered criteria!\n");
                                      }

                    
                      //Starts with the print of total matches found in the root directory
                            System.out.println(getIndentation(0)+dir.getName()+" : "+netTotal +" Lines");

                      //checks for all the subdirectories and files to get matched lines
                      PROJECTLOGGER.log(Level.INFO, "Intiating search");
                             for (DataEntry dataEntryElement : directoryOperation.getSubDirectories()) 
                             {
                                    //if the dataEntry element is an object of Directory class
                                    if (dataEntryElement instanceof Directory)
                                     {
                                                //For each element of Directory type found ,
                                                //get the relevant Directory of the Directory object,
                                                //get the indentation level of the relevant Directory and print it before the Directory name 
                                                //get the line match count stored in relevant Directory object and display                               
                                          System.out.println(getIndentation((indentationList.get(((Directory)dataEntryElement).getFile())))
                                          +((Directory)dataEntryElement).getName() +" : "+ dataEntryElement.countLines()+ " Lines" );

                                          //if the dataEntry element is an object of FileEntry class
                                     }else if(dataEntryElement instanceof FileEntry)
                                              {
                                                //For each element of FileEntry type found ,
                                                //get the relevant file of the FileEntry object,
                                                //get the indentation level of the relevant file and print it before the file name
                                                 //get the line match count stored in relevant FileEntry object and display

                                                   System.out.println(getIndentation(indentationList.get(((FileEntry)dataEntryElement).getFile()))
                                                   +((FileEntry)dataEntryElement).getName()+" : " + dataEntryElement.countLines() + " Lines");

                                                   //Save the indentation level to print the matching lines in position
                                                   indentation = indentationList.get(((FileEntry)dataEntryElement).getFile()); 

                                                  //only if user has selected showlines option from menu selection number 2, lines will be displayed
                                                     if(outputSelection==2)
                                                            {
                                                                 System.out.println();
                                                                         //get data saved in each file object and display by incrementing indentation by one unit
                                                                for(Line data : ((FileEntry)dataEntryElement).getData())
                                                                      {
                                                                            System.out.println(getIndentation(indentation+1)+data.getLineNum()+ "  : " + data.getLineData());
                                                                              //System.out.println(getIndentation(indentation)+ data.getLineNum()+ "  : " + data.getLineData());

                                                                      }

                                                                 System.out.println();
                                                            }
                          

                                              }

                             }

                             PROJECTLOGGER.log(Level.INFO, "End of search");
        

                    break;

                default:   //Terminates the program
                   
                PROJECTLOGGER.log(Level.INFO, "User selected to exit programme");
                    System.out.println("Thank you for using this software!");
                    break;
            }

        
    }catch(InputMismatchException e)
    {
        if(PROJECTLOGGER.isLoggable(Level.SEVERE))
        {
            System.out.println("Invalid Input. Please enter a valid input value!");
            PROJECTLOGGER.log(Level.SEVERE, "Invalid input entered. Triggered"+e.getMessage());
    
        }
    }catch(PatternSyntaxException e)
    {
        if(PROJECTLOGGER.isLoggable(Level.SEVERE))
        {
        System.out.println("Invalid Input criteria. Please enter a valid input value!");
        PROJECTLOGGER.log(Level.SEVERE, "Invalid pattern entered"+e.getMessage());
        }
    }
}

scannerObj.close();
       
    }
//Returns the multiplied string for indenting the directories and files
    public static String getIndentation(int dirNum) {

        //Initially indentation is set to zero in each request
        String indent = "";

        //Loops to add the string for iterations according to the level provided to the function
        for (int i = 0; i < (dirNum); i++) 
        {
            indent = indent+ ("    ");
        }
        
        return indent;

    }

//Stores the indentation level of the directories and files
    public static void updateDirectoryLevels(File rootDirectory, int depth, Map<File,Integer> indentationList)
     {
        File[] allFiles = rootDirectory.listFiles();
        
        //Loops through the diractories listed in File array(allFiles)
        for (File fileElement : allFiles) 
        {
            //Stores the indentation level relevant to each directory in the list
             indentationList.put(fileElement, depth);

             //if the element is a directory , recurse the function again
            if (fileElement.isDirectory()) {
                updateDirectoryLevels(fileElement, (depth + 1), indentationList);
            }
        }
    }



}