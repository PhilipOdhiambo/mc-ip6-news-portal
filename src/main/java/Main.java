import static spark.Spark.*;
import com.google.gson.Gson;
import dao.*;
import models.Department;
import models.Employee;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Main {
    public static void main(String[] args) {

        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/news-portal.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        Sql2oDepartmentDao departments =  new Sql2oDepartmentDao(sql2o);
        Sql2oEmployeeDao employees = new Sql2oEmployeeDao(sql2o);
        Sqlo2RoleDao roles = new Sqlo2RoleDao(sql2o);
        Sql2oNewsDao newsGeneral = new Sql2oNewsDao(sql2o);
        Sql2oNewsDepartmentalDao newsDepartmental = new Sql2oNewsDepartmentalDao(sql2o);


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

        // Read a single department b id
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


        //


        //FILTERS

        after((req, res) ->{
            res.type("application/json");
        });
    }
}
