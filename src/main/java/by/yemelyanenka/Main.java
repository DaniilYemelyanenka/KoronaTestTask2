package by.yemelyanenka;


import by.yemelyanenka.arguments.*;
import by.yemelyanenka.interfaces.ArgumentHandle;
import by.yemelyanenka.reader.SbReader;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<String> processed = new ArrayList<>();
        for (String arg : args) {
            if ((arg.startsWith("--") || arg.startsWith("-")) && arg.contains("=")) {
                String[] parts = arg.split("=", 2);
                processed.add(parts[0]); // ключ
                processed.add(parts[1]); // значение
            } else {
                processed.add(arg);
            }
        }

        Scanner scanner = new Scanner(System.in);

        while(true){
            OutputArgument output = new OutputArgument();
            SortArguments sort = new SortArguments();

            List<ArgumentHandle> handles = List.of(output,sort);

            JCommander commander = JCommander.newBuilder()
                    .addObject(output)
                    .addObject(sort)
                    .build();


            String[] inputArgs;
            if(processed.isEmpty()){
                String line = scanner.nextLine();
                inputArgs = line.trim().split("\\s+");
            } else {
                inputArgs = processed.toArray(new String[0]);
            }

            try{
                commander.parse(inputArgs);

                handles.stream()
                        .filter(ArgumentHandle::isParsed)
                        .forEach(ArgumentHandle::handle);


                Path currentPath = Paths.get("").toAbsolutePath().normalize();

                SbReader.readAllSbFiles(currentPath);

                break;
            }catch(ParameterException exception){
                System.err.println("Ошибка обработки аргументов командной строки: "  + exception.getMessage());
                commander.usage();
                processed.clear();
            }catch(IOException exception){
                System.err.println("Ошибка обработки файлов: "  + exception.getMessage());
            }
        }
    }
}