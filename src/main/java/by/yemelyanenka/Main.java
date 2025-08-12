package by.yemelyanenka;


import by.yemelyanenka.arguments.*;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

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

                break;
            }catch(ParameterException exception){
                System.err.println("Ошибка обработки аргументов командной строки." );
                commander.usage();
                args = new String[0];
            }
        }






    }
}