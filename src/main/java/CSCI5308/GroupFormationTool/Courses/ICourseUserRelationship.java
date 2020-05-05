package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.AccessControl.User;

import java.util.List;

public interface ICourseUserRelationship {
    public boolean userHasRoleInCourse(User user, Role role, Course course);

    public List<Role> loadAllRolesForUserInCourse(User user, Course course);

    public boolean enrollUserInCourse(User user, Course course, Role role);
}
