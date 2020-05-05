package CSCI5308.GroupFormationTool.CourseTest;

import CSCI5308.GroupFormationTool.Courses.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {
    @Test
    public void RoleInsideClassTest() {
        Role role = Role.INSTRUCTOR;
        assertEquals(Role.valueOf("INSTRUCTOR"), role);
    }
}
