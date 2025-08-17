package by.yemelyanenka.output;

import by.yemelyanenka.DAO.Department;
import by.yemelyanenka.DAO.Stat;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class Output {

    public static Boolean printToConsole;

    public static Path path;

    public static void printTotalList(Map<String, Department> totalList, Set<String> errorLog,Set<Stat> statisticSet){

        totalList.forEach( (departmentName,department) -> outputToFile(departmentName,department.toString()));

        errorLog.forEach(error -> outputToFile("Error",error));


        if(printToConsole != null){
            StringBuilder statisticString = new StringBuilder();
            statisticString.append("Department,min,max,mid\n");
            statisticSet.forEach(stat -> statisticString.append(stat.toString()));
            if(printToConsole){
                printStatisticToConsole(statisticString.toString());
            }else {
                printStatisticToFile(statisticString.toString());
            }
        }


    }

    private static void printStatisticToConsole(String data){
        System.out.println(data);
    }

    private static void printStatisticToFile(String data){
        outputToFile(String.valueOf(path),data);
    }

    private static  void outputToFile(String name,String text){

        if(path!=null){
            try {
                Files.createDirectories(path.getParent());
            }catch(IOException e) {
                throw new RuntimeException("Ошибка создания папок для пути вывод в файл. \n" + e);
            }
        }

            try (FileWriter writer = new FileWriter(Path.of(name).toFile(), false)) {
                writer.write(text);
            } catch (IOException e) {
                throw new RuntimeException("При попытке записать итоговые данные в файл произошла ошибка");
            }
    }
}
