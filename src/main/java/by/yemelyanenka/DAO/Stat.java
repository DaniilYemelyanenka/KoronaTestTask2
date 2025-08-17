package by.yemelyanenka.DAO;

public class Stat {
    private String departmentName;
    private double minSalary;
    private double maxSalary;
    private double midSalary;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public double getMidSalary() {
        return midSalary;
    }

    public void setMidSalary(double midSalary) {
        this.midSalary = midSalary;
    }

    @Override
    public String toString() {
        return departmentName  +
                ", " + minSalary +
                ", " + maxSalary +
                ", " + midSalary + "\n";
    }
}
