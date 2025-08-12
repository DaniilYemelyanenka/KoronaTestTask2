package by.yemelyanenka.DAO;


public class Person {

   private String role;
   private int id;
   private String name;
   private Double salary;

    public String getRole() {
        return role;
    }

    public Double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
