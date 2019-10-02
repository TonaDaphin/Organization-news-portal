import dao.SqlDepartmentsDao;
import dao.SqlNewsDao;
import dao.SqlUsersDao;
import models.Departments;
import models.News;
import models.Users;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        SqlNewsDao newsDao;
        SqlDepartmentsDao departmentsDao;
        SqlUsersDao usersDao;
        Connection conn;
        String connectionString = "jdbc:postgresql://localhost:5432/organize";
        Sql2o sql2o = new Sql2o(connectionString, "tona", "1990");

        usersDao=new SqlUsersDao(sql2o);
        departmentsDao= new SqlDepartmentsDao(sql2o);
        newsDao= new SqlNewsDao(sql2o);
//        conn = (Connection)sql2o.open();

        //FRONT END

        //  All News

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("allNews",newsDao.getAll());
            return new ModelAndView(model, "welcome.hbs");
        },new HandlebarsTemplateEngine());

        // All Users

        get("/staff", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("users", usersDao.getAll());
            return new ModelAndView(model, "Draft.hbs");
        },new HandlebarsTemplateEngine());

        // All Departments
        get("/dept", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("departments", departmentsDao.getAll());
            return new ModelAndView(model, "Draft.hbs");
        },new HandlebarsTemplateEngine());

//        Departments

        get("/department/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("allDepartments",departmentsDao.getAll());
            return new ModelAndView(model, "department-form.hbs");
        },new HandlebarsTemplateEngine());

        post("/department", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String departName = request.queryParams("departName");
            String departDescription = request.queryParams("departDescription");
            int employees = Integer.parseInt(request.queryParams("employees"));
            Departments newDepartment = new Departments(departName, departDescription, employees);
            departmentsDao.add(newDepartment);
            model.put("departments", departmentsDao.getAll());
            model.put("departName",departName);
            model.put("departDescription",departDescription);
            model.put("employees",employees);
            return new ModelAndView(model, "dep-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/departments",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("departments", departmentsDao.getAll());
            return new ModelAndView(model, "departments.hbs");
        },new HandlebarsTemplateEngine());

        //Users

        get("/user/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "user-form.hbs");
        },new HandlebarsTemplateEngine());

        post("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String userName = request.queryParams("userName");
            String userPosition=request.queryParams("userPosition");
            String userRole = request.queryParams("userRole");
            int departId = Integer.parseInt(request.queryParams("departId"));
            Users newUser = new Users(userName, userPosition, userRole, departId);
            usersDao.add(newUser);
            model.put("users", usersDao.getAll());
            model.put("userName",userName);
            model.put("userPosition",userPosition);
            model.put("userRole",userRole);
            model.put("departId",departId);
            return new ModelAndView(model, "user-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("users", usersDao.getAll());
            return new ModelAndView(model, "user.hbs");
        },new HandlebarsTemplateEngine());


        // News

        get("/news/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("allNews",newsDao.getAll());
            model.put("departments", departmentsDao.getAll());
            return new ModelAndView(model, "news-form.hbs");
        },new HandlebarsTemplateEngine());

        post("/news", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String heading=request.queryParams("heading");
            String content = request.queryParams("content");
            int departId = Integer.parseInt(request.queryParams("departId"));
            News newNews = new News(heading,content, departId);
            newsDao.add(newNews);
            model.put("news", newsDao.getAll());
            model.put("heading",heading);
            model.put("content",content);
            model.put("departId",departId);
            return new ModelAndView(model, "new-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/news", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("allNews",newsDao.getAll());
            return new ModelAndView(model, "news.hbs");
        },new HandlebarsTemplateEngine());

        //Particular Department News

        post("/departments/:id/news", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            int departId = Integer.parseInt(request.params("id"));
            Departments idToFind = departmentsDao.findById(departId);
            model.put("id", departId);
            model.put("dataId", idToFind.getId());
            model.put("allDepartments",departmentsDao.getAllDepartmentsNews(departId));

            return new ModelAndView(model, "department-news.hbs");
        },new HandlebarsTemplateEngine());

        // Add News to a Department

        get("/departments/:id/news/new", (request, response) -> {
            Map<String, Object> user = new HashMap<>();
            int departId = Integer.parseInt(request.params("id"));
            String heading = request.queryParams("heading");
            String content = request.queryParams("content");
            Departments newNews = new Departments(heading, content, departId);
            departmentsDao.add(newNews);
            System.out.println(departmentsDao.getAllDepartmentsNews(departId));
            return new ModelAndView(user, "new-success.hbs");
        }, new HandlebarsTemplateEngine());

        // Department Details

        get("/departments/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            System.out.println(id);
            Departments findDepDetails=departmentsDao.findById(id);
            Departments idToFind = departmentsDao.findById(Integer.parseInt(request.params("id")));
            model.put("id", findDepDetails);
            model.put("departmentDetails",findDepDetails);
            model.put("users", usersDao.getAll());
            return new ModelAndView(model, "dep-details.hbs");
        },new HandlebarsTemplateEngine());

        // Users Details

        get("/users/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            System.out.println(id);
            Users foundUserDetails = usersDao.findById(id);
            Users user=usersDao.findById(Integer.parseInt(request.params("id")));
            model.put("id", foundUserDetails);
            model.put("user",user);
            model.put("userDetails", foundUserDetails);
            model.put("userDepartment", usersDao.getAllDepartmentsForUsers(id));
            model.put("allDepartments", departmentsDao.getAll());
            return new ModelAndView(model, "user-details.hbs");
        },new HandlebarsTemplateEngine());

        get("/users/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfUserToEdit = Integer.parseInt(request.params("id"));
            Users editUser=usersDao.findById(idOfUserToEdit);
            model.put("editUser",editUser);
            model.put("editUser",true);
            return new ModelAndView(model, "user-form.hbs");
        },new HandlebarsTemplateEngine());

        get("/users/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfUserToDelete = Integer.parseInt(request.params("id"));
            Users deleteUser = usersDao.deleteById(idOfUserToDelete);
            usersDao.deleteById(idOfUserToDelete);
            model.put("deleteUser",deleteUser);
            model.put("deleteUser",true);
            return new ModelAndView(model, "del-user.hbs");
        },new HandlebarsTemplateEngine());

        get("/departments/:id/users", (request, response) -> {
            Map<String, Object> user = new HashMap<>();
            int departId = Integer.parseInt(request.params("id"));
            Departments idToFind = departmentsDao.findById(departId);
            user.put("depUsers", departmentsDao.getAllUsersOfDepartment(departId));
            user.put("departId", departId);
            user.put("departId", idToFind.getId());
            user.put("allUsers", usersDao.getAll());
            return new ModelAndView(user, "department-users.hbs");
        }, new HandlebarsTemplateEngine());

            //  Add a new user to a Department

        post("/departments/:deptId/users/new", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int departId = Integer.parseInt(req.params("departId"));
            int userId = Integer.parseInt(req.params("userId"));
            Departments department = departmentsDao.findById(departId);
            Users users = usersDao.findById(userId);
            return new ModelAndView(model, "user-success.hbs");
        }, new HandlebarsTemplateEngine());

        // News Details

        get("/news/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int departId = Integer.parseInt(request.queryParams("departId"));
            Departments idToFind = departmentsDao.findById(departId);
            model.put("departId", idToFind);
            return new ModelAndView(model, "news-details.hbs");
        },new HandlebarsTemplateEngine());



    }
}