package by.yemelyanenka.parser;

import by.yemelyanenka.DAO.Department;
import by.yemelyanenka.DAO.Employee;
import by.yemelyanenka.DAO.Manager;
import by.yemelyanenka.output.Output;

import java.util.*;

public class Parser {

    public static void parseLine(String line){
        Map<Integer,Manager> managerList = new HashMap<>();
        List<Employee> employeeList =  new ArrayList<>();
        List<String> errorLog = new ArrayList<>();

       String[] item =  line.trim().split(",");


       Optional<Object> parsed = parseObject(item);

       if(parsed.isEmpty()){
           errorLog.add(line);
       }else{
           Object object = parsed.get();

           if(object instanceof Manager){
               managerList.put(((Manager) object).getId(), (Manager) object);
           }
           else if (object instanceof Employee){
               employeeList.add( (Employee) object);
           }
       }

       //TODO  перенести этот кусок после считывания всех строк
       
        Map<String, Department> totalList = new HashMap<>();

        for(Manager m : managerList.values()){
            Department department = new Department();
            department.setManager(m);
            totalList.put(m.getDepartment(),department);
        }
        for (Employee employee : employeeList){
            Manager emManager = managerList.get(employee.getManagerId());

            if(emManager != null){
                totalList.get(emManager.getDepartment())
                        .getEmployees().add(employee);
            }

        }

        Output.printTotalList(totalList);


    }

    private static Optional<Object> parseObject(String[] lineToParse){
        if(lineToParse.length<5){
            return Optional.empty();
        }else {
            try{
                String role = lineToParse[0];
                Integer id = Integer.parseInt(lineToParse[1]);
                String name = lineToParse[2];
                Double salary = null;

                if(!lineToParse[3].isEmpty()){
                    Double buffedSalary = Double.parseDouble(lineToParse[3]);
                    if(buffedSalary>0) salary = buffedSalary;
                }

                if(role.equalsIgnoreCase("Manager")){

                    Manager manager = new Manager();
                    manager.setRole(role);
                    manager.setId(id);
                    manager.setName(name);
                    manager.setSalary(salary);
                    manager.setDepartment(lineToParse[4]);

                    return Optional.of(manager);

                }else if(role.equalsIgnoreCase("Employee")){

                    Employee employee = new Employee();
                    employee.setRole(role);
                    employee.setId(id);
                    employee.setName(name);
                    employee.setSalary(salary);
                    employee.setManagerId(Integer.parseInt(lineToParse[4]));

                    return Optional.of(employee);

                }

            }catch(Exception ex){
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
