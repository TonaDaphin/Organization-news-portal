package dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import models.Departments;
import models.Users;

import java.util.List;

public class SqlDepartmentsDao implements DepartmentsDao {
    private Sql2o sql2o;

    public SqlDepartmentsDao(Sql2o sql2o){
        this.sql2o=sql2o;
        int id;

    }


    @Override
    public void add(Departments departments) {
        String data="INSERT INTO departments(departDescription,departName,employees)VALUES(:departDescription,:departName,:employees);";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(data, true)
                    .bind(departments)
                    .executeUpdate()
                    .getKey();
            departments.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public List<Departments> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Departments.class);
        }
    }


    @Override
    public List<Users> getAllUsersOfDepartment(int departId) {
        return null;
    }

    @Override
    public Departments findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Departments.class);
        }
    }

    @Override
    public void update(String departName, String departDescription, int employees, int id) {
        String data="UPDATE departments SET (departName,departDescription,employees)=(:departName,:departDescription,:employees)";
        try (Connection con=sql2o.open()){
            con.createQuery(data)
                    .addParameter("departName",departName)
                    .addParameter("departDescription",departDescription)
                    .addParameter("employees",employees)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String data = "DELETE from departments WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(data)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Departments> getAllDepartmentsNews(int newsId) {
        String data = "SELECT * FROM news WHERE departmentId =:deptId";
        try(Connection con= sql2o.open()){
            return con.createQuery(data)
                    .addParameter("newsId", newsId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Departments.class);
        }

    }

    @Override
    public void clearAll() {
        String data = "DELETE from departments";
        try (Connection con = sql2o.open()) {
            con.createQuery(data).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Users> getAllUsersOfDepartments(int departId) {
        return null;
    }


}
