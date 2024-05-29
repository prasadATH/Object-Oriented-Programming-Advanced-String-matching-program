package edu.curtin.app;
import java.util.*;
import java.io.*;
import java.util.regex.*;


public class ExcludeOperation implements Operation {



    @Override
    public List<Line> operation(List<Line> allData, List<User> exList) {

        // variable to store if excluded text is included in the line
        int notFound;

        // list to store all proper lines(which satisfy given criteria)
        List<Line> excludedData = new ArrayList<>();
try {

        
        for (Line lineElement : allData) 
        {
            // in each line

            // initially notfound is true
            notFound = 1;

            // checking each criteria in the list, in selected line
            for (User excludeCheck : exList) {

                // initiating criteria to check
            if((excludeCheck.getUserFunction()).equals("e r"))
            {

                Pattern patternToCheck = Pattern.compile(excludeCheck.getUserCriteria());
                   
                

                // assigning the string to be checked
                Matcher matcherObject = patternToCheck.matcher(lineElement.getLineData());

           // if atleast one of the criteria to be excluded, contained in the string line
               if (matcherObject.find()) {

               // if found , not found is set to false
                    notFound = 0;
              }

            }else if((excludeCheck.getUserFunction()).equals("e t"))
            {

                if ((lineElement.getLineData()).contains(excludeCheck.getUserCriteria())) {

                    // if found , not found is set to false
                         notFound = 0;
                   }

            }


            }
            // check wether not found is true and add it to the list
            if (notFound == 1) {
                // System.out.println("Match found");

                // notFound = 0;
                excludedData.add(lineElement);
                // operation(excludedData, exList);
            }
           
        }

        } catch (PatternSyntaxException e) 
        {
         System.out.println("Invalid pattern found! "+ e.getMessage());
         
          
        }
       
    
        // return result list of lines which exclude the criteria
        return excludedData;

    }

}