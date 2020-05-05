package CSCI5308.GroupFormationTool.CourseTest;

import CSCI5308.GroupFormationTool.Courses.Course;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@SuppressWarnings("deprecation")
public class CourseTest {
    @Test
    public void ConstructorTests() {
        Course course = new Course();
        Assert.isTrue(course.getId() == -1);
        Assert.isTrue(course.getTitle().isEmpty());
    }

    @Test
    public void setIdTest() {
        Course course = new Course();
        course.setId(10);
        Assert.isTrue(10 == course.getId());
    }

    @Test
    public void getIdTest() {
        Course course = new Course();
        course.setId(10);
        Assert.isTrue(10 == course.getId());
    }

    @Test
    public void setTitleTest() {
        Course course = new Course();
        course.setTitle("New Topic");
        Assert.isTrue(course.getTitle().equals("New Topic"));
    }

    @Test
    public void getTitleTest() {
        Course course = new Course();
        course.setTitle("New Topic");
        Assert.isTrue(course.getTitle().equals("New Topic"));
    }

    @Test
    public void loadAllCoursesTest() {
        CourseDBMock courseDBMock = new CourseDBMock();
        List<Course> course_list = new ArrayList<>();
        course_list = courseDBMock.loadAllCourses();
        Assert.isTrue(course_list.size() > 0);
    }

    @Test
    public void loadCourseByIdTest() {
        CourseDBMock courseDBMock = new CourseDBMock();
        Course course = new Course();
        courseDBMock.loadCourseByID(1, course);
        Assert.isTrue(course.getId() == 1);
        Assert.isTrue(course.getTitle().equals("New First Course"));
    }

    @Test
    public void createCourseTest() {
        CourseDBMock courseDBMock = new CourseDBMock();
        Course course = new Course();
        courseDBMock.createCourse(course);
        Assert.isTrue(course.getId() == 1);
        Assert.isTrue(course.getTitle().equals("New First Course"));
    }

    @Test
    public void deleteCourseTest() {
        CourseDBMock courseDBMock = new CourseDBMock();
        boolean returned_value = courseDBMock.deleteCourse(1);
        Assert.isTrue(returned_value == true);
    }


}
