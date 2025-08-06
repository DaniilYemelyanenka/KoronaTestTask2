package by.yemelyanenka.arguments;

import by.yemelyanenka.ArgumentHandle;
import com.beust.jcommander.Parameter;

public class StatArgument implements ArgumentHandle {

    @Parameter(names = {"--stat"},description = "Включает генерацию статистики по депортаментам.")
    private Boolean  stat = false;


    @Override
    public void handle() {
        System.out.println("Handle stat");
    }

    @Override
    public boolean isParsed() {
        return stat;
    }
}
