package dao;

import models.Employee;
import models.Role;

import java.util.List;

public interface EmployeeDao {
    //  CREATE
    public void add();
    public void addToRole(Employee employee, Role role);

    // READ
    public List<Employee> getAll();
    public List<Role> getRolesForEmployee(int id);

    // DELETE
    public void deleteById(int id);


}
