package by.yemelyanenka.arguments;

import by.yemelyanenka.ArgumentHandle;
import com.beust.jcommander.Parameter;

public class OrderArguments implements ArgumentHandle {

    @Parameter(names = {"--order"},description = "Порядок сортировки. Может быть asc или desc")
    private String order = "asc";

    @Override
    public void handle() {
        System.out.println("Handle order");
    }

    @Override
    public boolean isParsed() {
        return order!=null;
    }
}
