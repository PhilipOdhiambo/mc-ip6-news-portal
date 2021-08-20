package dao;

import models.Employee;
import models.Role;

import java.util.List;

public interface RoleDao {

    // ADD
    public void add();
    public void addToEmployee(Role role, Employee employee);

    // READ
    public List<Role> getAll();

    // DELETE
    public void deleById(int id);
}
