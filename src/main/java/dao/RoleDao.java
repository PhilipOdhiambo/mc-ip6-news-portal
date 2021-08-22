package dao;

import models.Employee;
import models.Role;

import java.util.List;

public interface RoleDao {
    //  CREATE
    public void add(Role role);
    public void addRoleToEmloyee(Role role, Employee employee);

    // READ
    public List<Role> getAll();
    public Role getById(int id);
    public List<Employee> getEmployeesForRole(int id);

    // DELETE
    public void deleteById(int id);


}
