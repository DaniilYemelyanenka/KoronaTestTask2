package by.yemelyanenka.arguments;

import by.yemelyanenka.ArgumentHandle;
import com.beust.jcommander.Parameter;

public class PathArgument implements ArgumentHandle {

    @Parameter(names = {"-p","--path"},description = "Путь к файлу со статистико.",required = true)
    private String path;


    @Override
    public void handle() {
        System.out.println("Handle path");
    }

    @Override
    public boolean isParsed() {
        return path!=null;
    }
}
