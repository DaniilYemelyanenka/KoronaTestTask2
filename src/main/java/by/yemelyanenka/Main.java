package by.yemelyanenka;


import by.yemelyanenka.arguments.*;
import com.beust.jcommander.JCommander;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        OutputArgument output = new OutputArgument();
        PathArgument path = new PathArgument();
        StatArgument stat = new StatArgument();
        OrderArguments order = new OrderArguments();
        SortArguments sort = new SortArguments();

        List<ArgumentHandle> handles = List.of(output,path,stat);

        JCommander commander = JCommander.newBuilder()
                .addObject(output)
                .addObject(path)
                .addObject(stat)
                .addObject(order)
                .addObject(sort)
                .build();

        commander.parse(args);

        handles.stream()
                .filter(ArgumentHandle::isParsed)
                .forEach(ArgumentHandle::handle);


    }
}