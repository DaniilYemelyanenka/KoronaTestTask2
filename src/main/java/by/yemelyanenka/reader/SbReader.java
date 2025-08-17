package by.yemelyanenka.reader;

import by.yemelyanenka.DAO.*;
import by.yemelyanenka.DAO.Error;
import by.yemelyanenka.sort.SortEmployees;
import by.yemelyanenka.output.Output;
import by.yemelyanenka.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class SbReader {

    public static  void readAllSbFiles(Path directory) throws IOException {
        List<Path> sbFiles = findSbFiles(directory);
        Map<String, Department> totalList = new HashMap<>();
        Map<Integer, Manager> managerList = new HashMap<>();
        Set<Employee> employeeSet =  new HashSet<>();
        Set<Error> errorLog = new HashSet<>();
        Set<Stat> statisticSet = new HashSet<>();

        for(Path file : sbFiles){
            List<String> lines = Files.readAllLines(file);
            Parser.parseLines(lines,managerList,employeeSet,errorLog,totalList);
        }


        totalList.forEach(((departmentName, department) -> {

            List<Employee> employees = department.getEmployees();
            Stat stat = new Stat();

            Double min = employees.stream()
                    .min(Comparator.comparing(Employee::getSalary))
                    .map(Employee::getSalary).orElse(0.0);

            Double max = employees.stream()
                    .max(Comparator.comparing(Employee::getSalary))
                    .map(Employee::getSalary).orElse(0.0);

            Double mid = employees.stream()
                        .mapToDouble(Employee::getSalary)
                        .average()
                        .orElse(0.0);

            stat.setDepartmentName(departmentName);
            stat.setMinSalary(min);
            stat.setMaxSalary(max);
            stat.setMidSalary(mid);
            statisticSet.add(stat);
        }));

        SortEmployees.sort(totalList);

        Output.printTotalList(totalList,errorLog,statisticSet);

    }
    public static List<Path> findSbFiles(Path directory) throws IOException {

        return Files.walk(directory)
                    .filter(p -> p.toFile().isFile() && p.toString().endsWith(".sb")).collect(Collectors.toList());
    }
}
