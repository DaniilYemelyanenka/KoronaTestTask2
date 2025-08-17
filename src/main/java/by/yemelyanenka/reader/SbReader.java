package by.yemelyanenka.reader;

import by.yemelyanenka.DAO.Department;
import by.yemelyanenka.DAO.Employee;
import by.yemelyanenka.DAO.Manager;
import by.yemelyanenka.SortEmployees;
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
        Set<String> errorLog = new HashSet<>();

        for(Path file : sbFiles){
            List<String> lines = Files.readAllLines(file);
            Parser.parseLines(lines,managerList,employeeSet,errorLog,totalList);
        }

        SortEmployees.sort(totalList);
        Output.printTotalList(totalList,errorLog);

    }
    public static List<Path> findSbFiles(Path directory) throws IOException {

        return Files.walk(directory)
                    .filter(p -> p.toFile().isFile() && p.toString().endsWith(".sb")).collect(Collectors.toList());
    }
}
