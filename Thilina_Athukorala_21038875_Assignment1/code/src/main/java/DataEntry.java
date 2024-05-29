package edu.curtin.app;
import java.util.*;
import java.io.*;

//Common function which triggers find operation
public interface DataEntry {
    public abstract FileEntry find(File fileContent,  List<User> userData);

    public abstract int countLines();

    public abstract File getFile();
    
    public abstract String getName();
    
    public abstract void setName(String inName);


   
}