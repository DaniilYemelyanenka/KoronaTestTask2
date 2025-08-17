package by.yemelyanenka.output;

import by.yemelyanenka.DAO.Department;
import by.yemelyanenka.SortEmployees;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Output {

    public static Boolean printToConsole;

    public static Path path;

    public static void printTotalList(Map<String, Department> totalList, Set<String> errorLog){

        StringBuilder dataForOutput = new StringBuilder();
        totalList.forEach((departmentName,department) -> {
            dataForOutput.append("*********").append(departmentName).append("***********").append("\n")
                    .append(department);
        });
        dataForOutput.append("********* incorrect data ***********").append("\n");
        errorLog.forEach( error -> {
            dataForOutput.append(error);
            dataForOutput.append("\n");
        });

        if(printToConsole != null){
            if(printToConsole){
                printToConsole(dataForOutput.toString());
            }else {
                printToFile(dataForOutput.toString());
            }
        }


    }

    private static void printToConsole(String data){
        System.out.println(data);
    }

    private static void printToFile(String data){
        try {
            Files.createDirectories(path.getParent());

            try(FileWriter writer = new FileWriter(path.toFile(),false)) {
                writer.write(data);
            }
        } catch (IOException e) {
            throw new RuntimeException("Оштбка создания папок для пути вывод в файл. \n" + e);
        }
    }
}
