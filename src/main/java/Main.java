import static spark.Spark.*;
import com.google.gson.Gson;
import dao.*;
import models.Department;
import models.Employee;
import models.News;
import models.NewsDepartmental;
import org.sql2o.Sql2o;

public class Main {
     static String user;
     static String pass;
     static String connectionString;

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            connectionString = "jdbc:postgresql://ec2-35-153-114-74.compute-1.amazonaws.com:5432/d7pbbvj5h1733r";
            user = "apuajtilynanbk";
            pass = "4e201ca47300a64c3ddeaa14acd967c0aec930cad7a7d59e2941b884b6755c8c";
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        // Return Local H2 connection

        connectionString = "jdbc:h2:~/news-portal.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        Sql2o sql2o = new Sql2o(connectionString, user, pass);
        Gson gson = new Gson();

        Sql2oDepartmentDao departments =  new Sql2oDepartmentDao(sql2o);
        Sql2oEmployeeDao employees = new Sql2oEmployeeDao(sql2o);
        Sqlo2RoleDao roles = new Sqlo2RoleDao(sql2o);
        Sql2oNewsDao newsGeneral = new Sql2oNewsDao(sql2o);
        Sql2oNewsDepartmentalDao newsDepartmental = new Sql2oNewsDepartmentalDao(sql2o);

        get("/", (req,res) -> {
            res.redirect("index.html");
            return null;
        });


        // Department Routes

        // Post a new department
        post("api/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);//make java from JSON with GSON
            departments.add(department);// Add department to database
            res.status(201);// Set status code for resource created
            return gson.toJson(department);//send it back to be displayed
        });

        // Read all departments
        get("api/departments", "application/json", (req, res) -> {
            return gson.toJson(departments.getAll());
        });

        // Read a single department
        get("api/departments/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int departmentId = Integer.parseInt(req.params("id"));
            return gson.toJson(departments.getById(departmentId));
        });

        // Delete a department


        // Employee/User Routes
        // Add a new employee
        post("api/departments/:deptId/employees/new", "application/json", (req, res) -> {
            Employee employee = gson.fromJson(req.body(), Employee.class);
            int departmentId = Integer.parseInt(req.params("deptId"));
            employee.setDepartmentid(departmentId);
            employees.add(employee);
            Employee createdEmployee = employees.getById(employee.getId());
//            res.status(201);
            return gson.toJson(createdEmployee);
        });
        // Get all employees
        get("api/employees", "application/json", (req, res) -> {
            return gson.toJson(employees.getAll());
        });
        // Get employees in a department
        get("api/departments/:id/employees", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            return gson.toJson(departments.getEmployeesByDepartment(departmentId));
        });
        // Get employee by id
        get("api/employees/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            return gson.toJson(employees.getById(id));
        });


        // General news

        //Post news
        post("api/general-news/new", "application/json",(req,res) -> {
            News generalNews = gson.fromJson(req.body(), News.class);
            newsGeneral.add(generalNews);
            return gson.toJson(generalNews);
        });

        // Get news by id
        get("api/general-news/:id", "application/json",(req,res) -> {
            int newsId = Integer.parseInt(req.params("id"));
            return gson.toJson(newsGeneral.getById(newsId));
        });

        // Get all general news
        get("api/general-news", "application/json",(req,res) -> {
            return gson.toJson(newsGeneral.getAll());
        });

        // DELETE

        // Delete now by id
        get("api/general-news/:id/delete", "application/json",(req,res) -> {
            int newsId = Integer.parseInt(req.params("id"));
            newsGeneral.deleteById(newsId);
            return null;
        });



        // NewsDepartmental

        //Post news
        post("api/departments/:departmentId/news-departmental/new", "application/json",(req,res) -> {
            int departmentId = Integer.parseInt(req.params("departmentId"));
            NewsDepartmental departmentalNews = gson.fromJson(req.body(), NewsDepartmental.class);
            newsDepartmental.add(departmentalNews);
            return gson.toJson(departmentalNews);
        });

        // Get news by id
        get("api/departments/:departmentId/news-departmental/id", "application/json",(req,res) -> {
            int newsId = Integer.parseInt(req.params("id"));
            int departmentid = Integer.parseInt(req.params("departmentId"));
            return gson.toJson(newsDepartmental.getById(newsId, departmentid));
        });

        // Get all general news
        get("api/departmental-news", "application/json",(req,res) -> {
            return gson.toJson(newsGeneral.getAll());
        });

        // DELETE

        // Delete news by id
        get("api/departmental-news/:id/delete", "application/json",(req,res) -> {
            int newsId = Integer.parseInt(req.params("id"));
            newsGeneral.deleteById(newsId);
            return null;
        });


        //FILTERS

        after((req, res) ->{
            res.type("application/json");
        });
    }
}
