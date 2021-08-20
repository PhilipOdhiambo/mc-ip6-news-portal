package dao;

import models.News;

import java.util.List;

public interface NewsDao {
    // CREATE
    public void add(News news);

    // READ
    public List<News> getAll();
    public News getById(int id);
}
