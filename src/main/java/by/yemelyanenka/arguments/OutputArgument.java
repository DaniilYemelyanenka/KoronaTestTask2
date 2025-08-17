package by.yemelyanenka.arguments;

import by.yemelyanenka.ArgumentHandle;
import by.yemelyanenka.output.Output;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputArgument  implements ArgumentHandle {

    @Parameter(names = {"-o","--output"},description = "Способ вывода статистики. По умолчанию вывод в консоль")
    private String output;

    @Parameter(names = {"-p","--path"},description = "Путь к файлу со статистикой.")
    private String path;


    @Override
    public void handle() {
        if(output == null){
            Output.printToConsole = true;
            output = "console";
        }else if(output.equalsIgnoreCase("file")){
            Output.printToConsole = false;
        }

        if(path!= null && isValidPath(path)){
            Output.path = Path.of(path);
        }
    }

    @Override
    public boolean isParsed() {
        return true;

    }

    public static boolean isValidPath(String pathStr) {
        try {
            Paths.get(pathStr);
            return true;
        } catch (InvalidPathException e) {
            throw new ParameterException("Путь к искомому файлу некорректен");
        }
    }
}
