package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.GroupFormationToolLogs;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.List;
import java.util.logging.Level;

public class CourseUserRelationship implements ICourseUserRelationship {
    public boolean userHasRoleInCourse(User user, Role role, Course course) {
        if (null == user || !user.isValidUser()) {
            return false;
        }
        if (null == role) {
            return false;
        }
        if (null == course) {
            return false;
        }
        ICourseUserRelationshipPersistence userCourseRelationshipDB = SystemConfig.instance().getCourseUserRelationshipDB();
        List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
        if (null != roles && roles.contains(role)) {
            GroupFormationToolLogs.log(Level.INFO, "User has role in Course" + user.getId(), null);
            return true;
        }
        GroupFormationToolLogs.log(Level.INFO, "User Without the Role in Course" + user.getId(), null);
        return false;
    }

    public List<Role> loadAllRolesForUserInCourse(User user, Course course) {
        ICourseUserRelationshipPersistence userCourseRelationshipDB = SystemConfig.instance().getCourseUserRelationshipDB();
        List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
        return roles;
    }

    public boolean enrollUserInCourse(User user, Course course, Role role) {
        ICourseUserRelationshipPersistence userCourseRelationshipDB = SystemConfig.instance().getCourseUserRelationshipDB();
        return userCourseRelationshipDB.enrollUser(course, user, role);
    }
}
