package by.yemelyanenka;

import by.yemelyanenka.DAO.Department;
import by.yemelyanenka.DAO.Person;

import java.util.Comparator;
import java.util.Map;

public class SortEmployees {

    public static Boolean sortByName;
    public static Boolean sortByAsc;

    public static void sort(Map<String, Department> totalList){
        if(sortByName != null && sortByAsc != null){
            if(sortByName) sortByName(totalList); else sortBySalary( totalList);
        }
    }

    public static void sortByName(Map<String, Department> totalList){


        if(sortByAsc){
            totalList.forEach( (s, department) -> department.getEmployees().sort(Comparator.comparing(Person::getName)));
        }else{
            totalList.forEach( (s, department) -> department.getEmployees().sort(Comparator.comparing(Person::getName).reversed()));
        }

    }

    public static void sortBySalary(Map<String, Department> totalList){
        if(sortByAsc){
            totalList.forEach( (s, department) -> department.getEmployees().sort(Comparator.comparing(Person::getSalary)));
        }else{
            totalList.forEach( (s, department) -> department.getEmployees().sort(Comparator.comparing(Person::getSalary).reversed()));
        }
    }
}
