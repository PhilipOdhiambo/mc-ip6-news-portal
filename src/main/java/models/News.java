package models;

import java.util.Objects;

public class News {
    private int id;
    private String content;

    public News (String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(content, news.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
