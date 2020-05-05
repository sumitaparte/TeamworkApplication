package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

public interface ICoursePersistence {
    public List<Course> loadAllCourses();

    public boolean loadCourseByID(long id, Course course);

    public boolean createCourse(Course course);

    public boolean deleteCourse(long id);

    public boolean loadCourseByName(Course course);
}
