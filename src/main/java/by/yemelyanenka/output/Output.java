package by.yemelyanenka.output;

import by.yemelyanenka.DAO.Department;

import java.util.Map;

public class Output {

    public static void printTotalList(Map<String, Department> totalList){
        totalList.forEach((departmentName,department) -> {
            System.out.println("*********" + departmentName+ "***********");
            System.out.println(department);
        });
    }
}
