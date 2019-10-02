package dao;

import models.News;

import java.util.List;

public interface NewsDao {
    // LIST
    List<News> getAll();

    // CREATE
    void add(News news);

    // READ
//   News findById(int id);

    // UPDATE
//    void update(String heading,
//                String content,
//                int departId);

    // DELETE
    void deleteById(int id);

    void clearAll();

    //List<News> allNewsPostedByDepartment(int deptId);
}
