package by.yemelyanenka.arguments;

import by.yemelyanenka.ArgumentHandle;
import by.yemelyanenka.SortEmployees;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

public class SortArguments implements ArgumentHandle {

    @Parameter(names = {"-s","--sort"},description = "Тип сортировки сотрудников: по имени или зарплате.")
    private String sort;

    @Parameter(names = {"--order"},description = "Порядок сортировки. Может быть asc или desc")
    private String order = "asc";


    @Override
    public void handle() {
        if(checkSortArguments(sort,order)){
            SortEmployees.sort(sort,order);
        }

    }

    @Override
    public boolean isParsed() {
        return checkSortArguments(sort,order);
    }


    private boolean checkSortArguments(String sortArgument,String sortOrder){
        if(sort != null){
            return (sort.equalsIgnoreCase("name") || sort.equalsIgnoreCase("salary"))
                    && (sortOrder.equalsIgnoreCase("desc") || sortOrder.equalsIgnoreCase("asc"));
        } else
            return false;
    }
}
