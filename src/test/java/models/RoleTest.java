package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    @Test
    void instance_newRoleInstanceCreated_role() {
        Role role = getNewRole();
        assertTrue(role instanceof Role);
    }

    // Helpers
    public Role getNewRole() {
        return new Role("Supervise other employees");
    }

}