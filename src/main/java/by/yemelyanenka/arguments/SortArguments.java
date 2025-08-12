package by.yemelyanenka.arguments;

import by.yemelyanenka.ArgumentHandle;
import by.yemelyanenka.SortEmployees;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

public class SortArguments implements ArgumentHandle {

    @Parameter(names = {"-s","--sort"},description = "Тип сортировки сотрудников: по имени или зарплате.")
    private String sort;

    @Parameter(names = {"--order"},description = "Порядок сортировки. Может быть asc или desc")
    private String order;


    @Override
    public void handle() {
        if(checkSortArguments(sort,order) ){

            if (order == null) {
                order = "asc";
            }

            SortEmployees.sort(sort,order);
        }

    }

    @Override
    public boolean isParsed() {
        return checkSortArguments(sort,order) ;
    }


    private boolean checkSortArguments(String sortArgument,String sortOrder){

        if (sortArgument == null && sortOrder != null) {
            throw new ParameterException("Флаг --order можно использовать только совместно с флагом --sort(-s)");
        }

        if(sortArgument != null) {

            if (sortOrder == null) {
                sortOrder = "asc";
            }

            if ((sortArgument.equalsIgnoreCase("name") || sortArgument.equalsIgnoreCase("salary"))
                    && (sortOrder.equalsIgnoreCase("desc") || sortOrder.equalsIgnoreCase("asc"))) {
                return true;
            } else {
                throw new ParameterException("Флаг --sort(-s) или --order имеет некорректное значение");
            }
        }
        return false;
    }
}
