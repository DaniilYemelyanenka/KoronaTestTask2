package by.yemelyanenka.arguments;

import by.yemelyanenka.ArgumentHandle;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputArgument  implements ArgumentHandle {

    @Parameter(names = {"-o","--output"},description = "Способ вывода статистики. По умолчанию вывод в консоль")
    private String output = "console";

    @Parameter(names = {"-p","--path"},description = "Путь к файлу со статистикой.")
    private String path;


    @Override
    public void handle() {
        System.out.println("Handle output");
    }

    @Override
    public boolean isParsed() {
        return output!=null;
    }

    private boolean isPathCorrect(String path){

        Path fspath = Paths.get(path);

        if(isValidPath(path)){
            if (Files.exists(fspath)){
                System.out.println("Файл найден работаю ...");
                return true;
            }else {
                throw new ParameterException("Искомый файл не существует");
            }
        }else{
            throw new ParameterException("Путь к искомому файлу некорректен");
        }
    }

    public static boolean isValidPath(String pathStr) {
        try {
            Paths.get(pathStr);
            return true;
        } catch (InvalidPathException e) {
            return false;
        }
    }
}
