package CSCI5308.GroupFormationTool.CourseTest;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@SuppressWarnings("deprecation")
public class CourseUserRelationshipTest {

    @Test
    public void findAllUsersWithCourseRoleTest() {
        CourseUserRelationshipDBMock courseUserRelationshipDBMock = new CourseUserRelationshipDBMock();
        int courseID = 1;
        List<User> users = courseUserRelationshipDBMock.findAllUsersWithCourseRole(Role.STUDENT, courseID);
        Assert.isTrue(users.size() > 1);
        Assert.isTrue(users.get(0).getId() == 1);
        Assert.isTrue(users.get(1).getId() == 2);
    }

    @Test
    public void findAllUsersWithoutCourseRoleTest() {
        CourseUserRelationshipDBMock courseUserRelationshipDBMock = new CourseUserRelationshipDBMock();
        int courseID = 1;
        List<User> users = courseUserRelationshipDBMock.findAllUsersWithoutCourseRole(Role.STUDENT, courseID);
        Assert.isTrue(users.size() > 1);
        Assert.isTrue(users.get(0).getId() == 1);
        Assert.isTrue(users.get(1).getId() == 2);
    }

    @Test
    public void enrollUser() {
        CourseUserRelationshipDBMock courseUserRelationshipDBMock = new CourseUserRelationshipDBMock();
        Course course = new Course();
        User user = new User();
        boolean result = courseUserRelationshipDBMock.enrollUser(course, user, Role.STUDENT);
        Assert.isTrue(result);
    }

    @Test
    public void loadUserRolesForCourse() {
        CourseUserRelationshipDBMock courseUserRelationshipDBMock = new CourseUserRelationshipDBMock();
        Course course = new Course();
        course.setId(1);
        User user = new User();
        user.setId(2);
        List<Role> roles = courseUserRelationshipDBMock.loadUserRolesForCourse(course, user);
        Assert.isTrue(roles.size() > 1);
    }
}
