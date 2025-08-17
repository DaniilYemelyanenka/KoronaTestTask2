package by.yemelyanenka.output;

import by.yemelyanenka.DAO.Department;
import by.yemelyanenka.SortEmployees;

import java.util.List;
import java.util.Map;

public class Output {

    public static void printTotalList(Map<String, Department> totalList, List<String> errorLog){

        SortEmployees.sort(totalList);

        totalList.forEach((departmentName,department) -> {
            System.out.println("*********" + departmentName+ "***********");
            System.out.println(department);
        });

        System.out.println("********* incorrect data ***********");
        errorLog.forEach(System.out::println);
    }
}
