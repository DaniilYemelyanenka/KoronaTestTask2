package by.yemelyanenka.arguments;

import by.yemelyanenka.ArgumentHandle;
import com.beust.jcommander.Parameter;

public class SortArguments implements ArgumentHandle {

    @Parameter(names = {"-s","--sort"},description = "Тип сортировки сотрудников: по имени или зарплате.")
    private String sort;


    @Override
    public void handle() {
        System.out.println("Handle sort");
    }

    @Override
    public boolean isParsed() {
        return sort!=null;
    }
}
