package dao;

import models.NewsDepartmental;

import java.util.List;

public interface NewsDepartmentalDao {
    // CREATE
    public void add(NewsDepartmental newsDepartmental);

    // READ
    public List<NewsDepartmental> getAll();
    public NewsDepartmental getById(int id, int departmentid);

    // DELETE
    public void deleteById(int id);
}
