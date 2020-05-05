package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.ErrorHandling.CourseNotFoundException;
import CSCI5308.GroupFormationTool.ErrorHandling.DatabaseColumnNotFoundException;
import CSCI5308.GroupFormationTool.GroupFormationToolLogs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CourseDB implements ICoursePersistence {

    public List<Course> loadAllCourses() {
        List<Course> courses = new ArrayList<Course>();
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spLoadAllCourses()");
            ResultSet results = proc.executeWithResults();
            if (null == results) {
                GroupFormationToolLogs.log(Level.INFO, "Courses can not be loaded", null);
            } else {
                while (results.next()) {
                    long id = results.getLong(1);
                    String title = results.getString(2);
                    Course c = new Course();
                    c.setId(id);
                    c.setTitle(title);
                    courses.add(c);
                }
                GroupFormationToolLogs.log(Level.INFO, "All Courses loaded successfully", null);
            }
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return courses;
    }

    public boolean loadCourseByID(long id, Course course) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spFindCourseByID(?)");
            proc.setParameter(1, id);
            ResultSet results = proc.executeWithResults();
            if (!results.next()) {
                GroupFormationToolLogs.log(Level.INFO, "Course Not Found with that Course ID: " + id, null);
                throw new CourseNotFoundException(Long.toString(id));
            } else {
                do {
                    String title = results.getString(2);
                    course.setId(id);
                    course.setTitle(title);
                } while (results.next());
                GroupFormationToolLogs.log(Level.INFO, "Course loaded successfully, Course Id:" + id, null);
            }
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, "Course Loading Failed", e);
            return false;

        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return true;
    }

    public boolean loadCourseByName(Course course) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spFindCourseByName(?)");
            proc.setParameter(1, course.getTitle());
            ResultSet results = proc.executeWithResults();
            GroupFormationToolLogs.log(Level.INFO, "Course Found", null);
            if (results.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, e.getMessage(), e);
            return false;

        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean createCourse(Course course) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spCreateCourse(?, ?)");
            proc.setParameter(1, course.getTitle());
            proc.registerOutputParameterLong(2);
            proc.execute();
            GroupFormationToolLogs.log(Level.INFO, "Course created successfully", null);
        } catch (SQLSyntaxErrorException e) {
            GroupFormationToolLogs.log(Level.SEVERE, e.getMessage(), e);
            throw new DatabaseColumnNotFoundException("Can not Add Course, because of database error. Please try again after some time.");
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, e.getMessage(), e);
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return true;
    }

    public boolean deleteCourse(long id) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spDeleteCourse(?)");
            proc.setParameter(1, id);
            proc.execute();
            GroupFormationToolLogs.log(Level.INFO, "Delete Course Success", null);
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, e.getMessage(), e);
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return true;
    }
}
