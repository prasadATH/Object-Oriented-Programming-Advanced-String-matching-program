package edu.curtin.app;
import java.util.*;


public class OperationContext {
    private Operation operationStrategy;
    private Map<String, Operation> allOperations;


    public OperationContext()
    {
      allOperations = new HashMap<>();
    }
 
    public OperationContext(Operation inStrategy)
    {
       this.operationStrategy = inStrategy;
    }
 
    public List<Line> executeOperation(List<Line> allData, List<User> inList){
       return operationStrategy.operation(allData, inList);
    }

    public void initOperations()
    {

      OperationContext op1 =  new OperationContext(new IncludeOperation());
      OperationContext op2 =  new OperationContext(new ExcludeOperation());

      allOperations.put("i r", op1.operationStrategy);
      allOperations.put("e r", op2.operationStrategy);
      allOperations.put("i t", op1.operationStrategy);
      allOperations.put("e t", op2.operationStrategy);

    

    }


    public Map<String, Operation> getAllOperation()
    {
      return allOperations;
    //  return allOperations;
    }
 }