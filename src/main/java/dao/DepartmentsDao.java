package dao;

import models.Departments;
import models.Users;

import java.util.List;

public interface DepartmentsDao {
    // LIST
    List<Departments> getAll();

    // CREATE
    void add(Departments departments);

    List<Users> getAllUsersOfDepartment(int departId);

    // READ
    Departments findById(int id);

    // UPDATE
     void update(String departName,
                 String departDescription,
                 int employees,
                 int id);

    // DELETE
     void deleteById(int id);

     void clearAll();

//    void addDepartmentsToUsers(Departments departments, Users users);
    List<Users> getAllUsersOfDepartments(int departId);
    List<Departments> getAllDepartmentsNews(int newsId);


}
