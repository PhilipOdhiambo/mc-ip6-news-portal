import static spark.Spark.*;
import com.google.gson.Gson;
import dao.*;
import models.Department;
import models.Employee;
import models.News;
import models.NewsDepartmental;

public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();

        // Database connection
        int portNumber = DatabaseConnection.getPortNumber();
        port(portNumber);

        staticFileLocation("/public");

        Sql2oDepartmentDao departments =  new Sql2oDepartmentDao(DatabaseConnection.sql2o);
        Sql2oEmployeeDao employees = new Sql2oEmployeeDao(DatabaseConnection.sql2o);
        Sqlo2RoleDao roles = new Sqlo2RoleDao(DatabaseConnection.sql2o);
        Sql2oNewsDao newsGeneral = new Sql2oNewsDao(DatabaseConnection.sql2o);
        Sql2oNewsDepartmentalDao newsDepartmental = new Sql2oNewsDepartmentalDao(DatabaseConnection.sql2o);

        get("/", (req,res) -> {
            res.redirect("index.html");
            return null;
        });


        // Department Routes

        // Post a new department
        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);//make java from JSON with GSON
            departments.add(department);// Add department to database
            res.status(201);// Set status code for resource created
            return gson.toJson(department);//send it back to be displayed
        });

        // Read all departments
        get("/departments", "application/json", (req, res) -> {
            return gson.toJson(departments.getAll());
        });

        // Read a single department
        get("/departments/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int departmentId = Integer.parseInt(req.params("id"));
            return gson.toJson(departments.getById(departmentId));
        });

        // Delete a department



        // Employee/User Routes
        // Add a new employee
        post("/departments/:deptId/employees/new", "application/json", (req, res) -> {
            Employee employee = gson.fromJson(req.body(), Employee.class);
            int departmentId = Integer.parseInt(req.params("deptId"));
            employee.setDepartmentid(departmentId);
            employees.add(employee);
            Employee createdEmployee = employees.getById(employee.getId());
//            res.status(201);
            return gson.toJson(createdEmployee);
        });
        // Get all employees
        get("/employees", "application/json", (req, res) -> {
            return gson.toJson(employees.getAll());
        });
        // Get employees in a department
        get("/departments/:id/employees", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            return gson.toJson(departments.getEmployeesByDepartment(departmentId));
        });
        // Get employee by id
        get("/employees/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            return gson.toJson(employees.getById(id));
        });


        // General news

        //Post news
        post("/general-news/new", "application/json",(req,res) -> {
            News generalNews = gson.fromJson(req.body(), News.class);
            newsGeneral.add(generalNews);
            return gson.toJson(generalNews);
        });

        // Get news by id
        get("/general-news/:id", "application/json",(req,res) -> {
            int newsId = Integer.parseInt(req.params("id"));
            return gson.toJson(newsGeneral.getById(newsId));
        });

        // Get all general news
        get("/general-news", "application/json",(req,res) -> {
            return gson.toJson(newsGeneral.getAll());
        });

        // DELETE

        // Delete now by id
        get("/general-news/:id/delete", "application/json",(req,res) -> {
            int newsId = Integer.parseInt(req.params("id"));
            newsGeneral.deleteById(newsId);
            return null;
        });



        // NewsDepartmental

        //Post news
        post("/departments/:departmentId/news-departmental/new", "application/json",(req,res) -> {
            int departmentId = Integer.parseInt(req.params("departmentId"));
            NewsDepartmental departmentalNews = gson.fromJson(req.body(), NewsDepartmental.class);
            newsDepartmental.add(departmentalNews);
            return gson.toJson(departmentalNews);
        });

        // Get news by id
        get("/departments/:departmentId/news-departmental/id", "application/json",(req,res) -> {
            int newsId = Integer.parseInt(req.params("id"));
            int departmentid = Integer.parseInt(req.params("departmentId"));
            return gson.toJson(newsDepartmental.getById(newsId, departmentid));
        });

        // Get all general news
        get("/departmental-news", "application/json",(req,res) -> {
            return gson.toJson(newsGeneral.getAll());
        });

        // DELETE

        // Delete news by id
        get("/departmental-news/:id/delete", "application/json",(req,res) -> {
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
