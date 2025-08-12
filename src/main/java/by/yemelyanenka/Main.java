package by.yemelyanenka;


import by.yemelyanenka.arguments.*;
import by.yemelyanenka.reader.SbReader;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);

        while(true){
            OutputArgument output = new OutputArgument();
            StatArgument stat = new StatArgument();
            SortArguments sort = new SortArguments();

            List<ArgumentHandle> handles = List.of(output,stat,sort);


            //TODO добавть работу коммандной строки о значениями типа k=v
            JCommander commander = JCommander.newBuilder()
                    .addObject(output)
                    .addObject(stat)
                    .addObject(sort)
                    .build();


            String[] inputArgs;
            if(args.length == 0){
                String line = scanner.nextLine();
                inputArgs = line.trim().split("\\s+");
            } else {
                inputArgs = args;
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
                args = new String[0];
            }catch(IOException exception){
                System.err.println("Ошибка обработки файлов: "  + exception.getMessage());
            }
        }
    }
}