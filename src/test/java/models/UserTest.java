package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void instance_newUserInstanceCreated_user() {
        User user = getNewUser();
        assertTrue(user instanceof User);
    }


    // helpers
    public User getNewUser() {
        return new User("Philip",1);
    }
}