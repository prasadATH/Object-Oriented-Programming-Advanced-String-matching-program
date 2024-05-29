package edu.curtin.app;
import java.util.*;
import java.io.*;

public class FileEntry implements DataEntry {


        //Intentionally private to restrict access
    private String fileName;
        //Intentionally private to restrict access
    private int lineMatches;
        //Intentionally private to restrict access
    private File fileEntryFile;
        //Intentionally private to restrict access
    private List<Line> resultList;
    //Intentionally private to restrict access
    private List<Line> storeData;
    //Intentionally private to restrict access
    private List<Line> exList;
 //Intentionally private to restrict access
    private OperationContext initOperate = new OperationContext();
//Intentionally private to restrict access
    private int exListUsed;

    private int inListUsed;

    
    

    public FileEntry()
    {
        fileName="";
       // allOperations = new HashMap<>();
        lineMatches = 0;
        fileEntryFile =null;
        resultList = new ArrayList<>();
        storeData = new ArrayList<>();
        exList = new ArrayList<>();
        initOperate.initOperations();
        exListUsed =0;
        inListUsed=0;



    }


    public List<Line> getData()
    {

        return resultList;
    }

    @Override
    public String getName()
    {

        return fileName;
    }


    @Override
    public void setName(String inFileName)
    {
        fileName = inFileName;
 
    }

    @Override
    public File getFile()
    {

        return fileEntryFile;
    }
    @Override
    public int countLines()
    {
          return lineMatches;
    }

    public void initOperations()
    {
     // allOperations.put("i t ", new StringIncludeOperation());
     // allOperations.put("e t ", new StringExcludeOperation());


    

    }


    @Override
    public FileEntry find(File fileContent,List<User> userData) {

        setName(fileContent.getName());


         
       
        try (Scanner fileReader = new Scanner(fileContent)){

                   


                    storeData = new ArrayList<>();
                    exList = new ArrayList<>();
            
                      int lineNum=0;
                     
                        while (fileReader.hasNextLine()) 
                        {
                               lineNum++;
                               String data = fileReader.nextLine();

                               Line lineObj = new Line();

                               lineObj.setLineData(data);
                               lineObj.setLineNum(lineNum);


                             // adds all lines of document as Line objects to an arraylist
                              storeData.add(lineObj);

             
                        }
                      fileEntryFile = fileContent;
                      



                      for(User userInfo : userData)
                      {
                           if((userInfo.getUserFunction()).equals("e t") || (userInfo.getUserFunction()).equals("e r"))
                           {
                               OperationContext exoperate = new OperationContext( (initOperate.getAllOperation()).get((userInfo.getUserFunction())));

                              exList = exoperate.executeOperation(storeData, userData);

                               exListUsed =1;


                           }
                      }

                      for(User userInfo : userData)
                      {
                           if((userInfo.getUserFunction()).equals("i t") || (userInfo.getUserFunction()).equals("i r"))
                           {
                            OperationContext inoperate = new OperationContext( (initOperate.getAllOperation()).get((userInfo.getUserFunction())));

                            inListUsed=1;
                            if(exListUsed ==0)
                               {
                                resultList = inoperate.executeOperation(storeData, userData);
                               }

                               if(exListUsed ==1)
                               {
                                resultList = inoperate.executeOperation(exList, userData);
                               }

                              

                             //  allOperations.get((userInfo.getUserfunction()));

                           }
                      }

                      if(exListUsed==1 && inListUsed==0)
                      {
                        resultList= exList;

                      }
            


                lineMatches = resultList.size();
              //  fileReader.close();

            
        } catch (FileNotFoundException e) {

            System.out.println("File not found! "+ e.getMessage());
            
           
           
        }

        return this;
        
    } 
    


}