package CSCI5308.GroupFormationTool.CourseTest;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

import java.util.ArrayList;
import java.util.List;

public class CourseDBMock implements ICoursePersistence {
    public List<Course> loadAllCourses() {
        List<Course> course_list = new ArrayList<>();
        Course course = new Course();
        course.setId(1);
        course.setTitle("New Course Title");
        course_list.add(course);
        Course course2 = new Course();
        course2.setId(2);
        course2.setTitle("Second Course Title");
        return course_list;
    }


    public boolean loadCourseByID(long id, Course course) {
        course.setTitle("New First Course");
        course.setId(1);
        return true;
    }


    public boolean loadCourseByName(Course course) {
        course.setTitle("New First Course");
        course.setId(1);
        return true;
    }


    public boolean createCourse(Course course) {
        course.setId(1);
        course.setTitle("New First Course");
        return true;
    }


    public boolean deleteCourse(long id) {
        Course course = new Course();
        course.setId(1);
        course.setTitle("New First Course");
        course = null;
        return true;
    }


}
