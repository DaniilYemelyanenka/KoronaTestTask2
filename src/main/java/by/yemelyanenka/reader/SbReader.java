package by.yemelyanenka.reader;

import by.yemelyanenka.DAO.Employee;
import by.yemelyanenka.DAO.Manager;
import by.yemelyanenka.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SbReader {

    public static  void readAllSbFiles(Path directory) throws IOException {
        List<Path> sbFiles = findSbFiles(directory);
        Map<Integer, Manager> managerList = new HashMap<>();
        List<Employee> employeeList =  new ArrayList<>();
        List<String> errorLog = new ArrayList<>();

        for(Path file : sbFiles){
            List<String> lines = Files.readAllLines(file);
            Parser.parseLines(lines);
        }

    }
    public static List<Path> findSbFiles(Path directory) throws IOException {

        return Files.walk(directory)
                    .filter(p -> p.toFile().isFile() && p.toString().endsWith(".sb")).collect(Collectors.toList());
    }
}
