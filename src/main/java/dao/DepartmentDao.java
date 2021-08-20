package dao;

import models.Department;
import models.Employee;
import models.News;
import models.NewsDepartmental;

import java.util.List;

public interface DepartmentDao {

    //// CREATE

    // Add department to database
    public void add(Department department);


    //// READ

    // Get all departments
    public List<Department> getAll();
    public Department getById(int id);
    public List<Employee> getEmployeesByDepartment(int departmentId);
    public List<NewsDepartmental> getNewsDepartmentalByDepartment(int departmentId);

    //// UPDATE

    public void update(Department newDepartment);

    ///// DELETE
    public void deleteById(int id);



}
