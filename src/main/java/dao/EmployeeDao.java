package dao;

import models.Employee;
import models.Role;

import java.util.List;

public interface EmployeeDao {
    //  CREATE
    public void add(Employee employee, int departmentId);
    public void addEmployeeToRole(Employee employee, Role role);

    // READ
    public List<Employee> getAll();
    public Employee getById(int id);
    public List<Role> getRolesForEmployee(int id);

    // DELETE
    public void deleteById(int id);


}
