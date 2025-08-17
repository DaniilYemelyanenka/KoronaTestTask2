package by.yemelyanenka.DAO;

public class Error {

    private String Info;

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public Error(String info) {
        Info = info;
    }

    @Override
    public String toString() {
        return  Info  + ";\n";
    }
}
