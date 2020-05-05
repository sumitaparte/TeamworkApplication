package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;

import java.util.List;

public class Course {
    private long id;
    private String title;
    private ICourseUserRelationship userRoleDecider;

    public Course() {
        setDefaults();
    }

    public Course(long id, ICoursePersistence courseDB) {
        setDefaults();
        courseDB.loadCourseByID(id, this);
    }

    public void setDefaults() {
        id = -1;
        title = "";
        userRoleDecider = new CourseUserRelationship();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public boolean delete(ICoursePersistence courseDB) {
        return courseDB.deleteCourse(id);
    }

    public boolean createCourse(ICoursePersistence courseDB) {
        return courseDB.createCourse(this);
    }

    public boolean checkCourse(ICoursePersistence courseDB) {
        return courseDB.loadCourseByName(this);
    }

    public boolean enrollUserInCourse(Role role, User user) {
        return userRoleDecider.enrollUserInCourse(user, this, role);
    }

    public boolean isCurrentUserEnrolledAsRoleInCourse(Role role) {
        return userRoleDecider.userHasRoleInCourse(CurrentUser.instance().getCurrentAuthenticatedUser(), role, this);
    }

    public boolean isCurrentUserEnrolledAsRoleInCourse(String role) {
        Role r = Role.valueOf(role.toUpperCase());
        return isCurrentUserEnrolledAsRoleInCourse(r);
    }

    public List<Role> getAllRolesForCurrentUserInCourse() {
        return userRoleDecider.loadAllRolesForUserInCourse(CurrentUser.instance().getCurrentAuthenticatedUser(), this);
    }
}
