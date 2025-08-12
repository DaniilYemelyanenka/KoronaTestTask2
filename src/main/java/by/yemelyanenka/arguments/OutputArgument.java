package by.yemelyanenka.arguments;

import by.yemelyanenka.ArgumentHandle;
import com.beust.jcommander.Parameter;

public class OutputArgument  implements ArgumentHandle {

    @Parameter(names = {"-o","--output"},description = "Способ вывода статистики. По умолчанию вывод в консоль")
    private String output = "console";

    @Parameter(names = {"-p","--path"},description = "Путь к файлу со статистикой.",required = true)
    private String path;


    @Override
    public void handle() {
        System.out.println("Handle output");
    }

    @Override
    public boolean isParsed() {
        return output!=null;
    }
}
