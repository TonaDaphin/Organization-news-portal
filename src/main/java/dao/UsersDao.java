package dao;

import models.Departments;
import models.Users;

import java.util.List;

public interface UsersDao {
    // LIST
    List<Users> getAll();

    // CREATE
    void add(Users users);

    // READ
    Users findById(int id);

    // UPDATE
//    void update(String userName,
//                String userPosition,
//                int userRole,
//                int id);

    // DELETE
    Users deleteById(int id);

    void clearAll();

//void addUsersToDepartment(Users users, Departments department);
List<Departments> getAllDepartmentsForUsers(int id);
}
