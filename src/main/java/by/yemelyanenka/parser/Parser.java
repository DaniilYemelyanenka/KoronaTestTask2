package by.yemelyanenka.parser;

import by.yemelyanenka.DAO.Department;
import by.yemelyanenka.DAO.Employee;
import by.yemelyanenka.DAO.Manager;
import by.yemelyanenka.output.Output;

import java.util.*;

public class Parser {

    public static void parseLines(List<String> lines,
                                  Map<Integer,Manager> managerList,
                                  Set<Employee> employeeSet,
                                  Set<String> errorLog,
                                  Map<String, Department> totalList){

        for(String line : lines){
            String[] item =  line.trim().split(",");


            Optional<Object> parsed = parseObject(item);

            if(parsed.isEmpty()){
                errorLog.add(line + ";");
            }else{
                Object object = parsed.get();

                if(object instanceof Manager){
                    managerList.put(((Manager) object).getId(), (Manager) object);
                }
                else if (object instanceof Employee){
                    employeeSet.add( (Employee) object);
                }
            }
        }

        for(Manager m : managerList.values()){
            Department department = new Department();
            department.setManager(m);
            totalList.put(m.getDepartment(),department);
        }
        for (Employee employee : employeeSet){
            Manager emManager = managerList.get(employee.getManagerId());

            if(emManager != null){
                    totalList.get(emManager.getDepartment()).getEmployees().add(employee);

            }

        }
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
                        else return Optional.empty();
                } else
                    return Optional.empty();

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
