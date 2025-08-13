package by.yemelyanenka.parser;

import by.yemelyanenka.DAO.Department;
import by.yemelyanenka.DAO.Employee;

import java.util.List;
import java.util.Map;

public class Parser {

    public static void parseLine(String line){
        Map<String, Department> totalList;
        List<Employee> employeeList;
        List<String> errorLog;

       String[] item =  line.split(",");

       for(String i : item){
           if(i.equalsIgnoreCase("Employee")){
               System.out.println(line);
               //TODO call function wich trying to create Employee object and add it to employee list
           }else if (i.equalsIgnoreCase("Manager")){
               //TODO call function wich trying to create Manager object and add it to
           }
       }
    }
}
