package models;

import java.util.Objects;

public class Departments {
    private String departName;
    private String departDescription;
    private int employees;
    private int id;


    public Departments(String departName,String departDescription,int employees ){
        this.departName=departName;
        this.departDescription=departDescription;
        this.employees=employees;
    }

    public String getDepartName(){
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
    public String getDepartDescription(){
        return departDescription;
    }

    public void setDepartDescription(String departDescription) {
        this.departDescription = departDescription;
    }
    public int getEmployees(){
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departments that = (Departments) o;
        return employees == that.employees &&
                id == that.id &&
                Objects.equals(departName, that.departName) &&
                Objects.equals(departDescription, that.departDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departName, departDescription, employees, id);
    }
}