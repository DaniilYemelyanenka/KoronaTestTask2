package by.yemelyanenka.DAO;

import java.util.List;

public class Department {
    private Manager manager;
    private List<Employee> employees;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "manager=" + manager +
                ", employees=" + employees +
                '}';
    }
}
