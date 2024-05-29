package edu.curtin.app;
import java.util.*;
import java.io.*;
import java.util.regex.*;

public class IncludeOperation implements Operation {

    @Override
    public List<Line> operation(List<Line> allData, List<User> inList) {

        // variable to store if included criteria is included in the line
        int found;

        // list to store all proper lines(which satisfy given criteria)
        List<Line> includedData = new ArrayList<>();
try {

    

 
            
        for (Line lineElement : allData) {
            // in each line
            found = 0;
            
            // initially found is not true
           

            // checking each criteria in the list, in selected line
            for (User includeCheck : inList) {

               // System.out.println("."+includeCheck.getUserfunction() + ". "+ includeCheck.getUsercriteria());



                if((includeCheck.getUserFunction()).equals("i r"))
                {
                  //  System.out.println("i r  found");
                // initiating criteria to check
                Pattern p = Pattern.compile(includeCheck.getUserCriteria());

                // assigning the string to be checked
                Matcher m = p.matcher(lineElement.getLineData());

                // if atleast one of the criteria is satisfying the criteria
                if (m.find()) {

                    found = 1;
                }
            }else if((includeCheck.getUserFunction()).equals("i t"))
            { 
                //System.out.println("i t  found" + inList.toString());


                if ((lineElement.getLineData()).contains(includeCheck.getUserCriteria())) {

                    found = 1;
                    // System.out.println("Match found");
                    // includedData.add(lineElement);
                    // operation(includedData, inList);
                }

            }

            }

            if (found == 1) {
                // System.out.println("Match found");
                includedData.add(lineElement);
                // operation(includedData, inList);
            }

        }

    }catch (PatternSyntaxException e) 
    {
 
     System.out.println("Invalid pattern found! "+ e.getMessage());
 
    
    }
    
        return includedData;

    }

}