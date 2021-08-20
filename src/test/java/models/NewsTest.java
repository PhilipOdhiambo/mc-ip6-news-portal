package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewsTest {
    @Test
    void instance_newNewsInstanceCreated_news() {
        News news = getNewNews();
        assertTrue(news instanceof News);
    }

    // Helpers
    public News getNewNews() {
        return new News("This is the best news for our general employee");
    }

}