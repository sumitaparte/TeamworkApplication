package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
import java.util.List;

@Controller
public class CourseAdminController {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String INSTRUCTOR = "instructor";

    @GetMapping("/admin/course")
    public String course(Model model) {
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        List<Course> allCourses = courseDB.loadAllCourses();
        model.addAttribute("courses", allCourses);
        return "admin/course";
    }

    @GetMapping("/admin/assigninstructor")
    public String assignInstructor(Model model, @RequestParam(name = ID) long courseId) {
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        Course c = new Course();
        courseDB.loadCourseByID(courseId, c);
        model.addAttribute("course", c);
        ICourseUserRelationshipPersistence courseUserRelationshipDB = SystemConfig.instance().getCourseUserRelationshipDB();
        List<User> allUsersNotCurrentlyInstructors = courseUserRelationshipDB.findAllUsersWithoutCourseRole(Role.INSTRUCTOR, courseId);
        model.addAttribute("users", allUsersNotCurrentlyInstructors);
        return "admin/assigninstructor";
    }

    @GetMapping("/admin/deletecourse")
    public ModelAndView deleteCourse(@RequestParam(name = ID) long courseId) {
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        Course c = new Course();
        c.setId(courseId);
        c.delete(courseDB);
        ModelAndView mav = new ModelAndView("redirect:/admin/course");
        return mav;
    }

    @RequestMapping(value = "/admin/createcourse", method = RequestMethod.POST)
    public ModelAndView createCourse(@RequestParam(name = TITLE) String title, RedirectAttributes redirectAttributes) {
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        Course c = new Course();
        c.setTitle(title);
        boolean status = c.checkCourse(courseDB);
        ModelAndView mav = new ModelAndView("redirect:/admin/course");
        if (status) {
            redirectAttributes.addFlashAttribute("status", "Course Can not be Loaded");
        } else {
            c.createCourse(courseDB);
            redirectAttributes.addFlashAttribute("status", "Course Added Successfully");
        }
        return mav;
    }

    @RequestMapping(value = "/admin/assigninstructor", method = RequestMethod.POST)
    public ModelAndView assignInstructorToCourse(@RequestParam(name = INSTRUCTOR) List<Integer> instructor,
                                                 @RequestParam(name = ID) long courseId) {
        Course c = new Course();
        c.setId(courseId);
        Iterator<Integer> iter = instructor.iterator();
        ICourseUserRelationshipPersistence courseUserRelationshipDB = SystemConfig.instance().getCourseUserRelationshipDB();
        while (iter.hasNext()) {
            User u = new User();
            u.setId(iter.next().longValue());
            courseUserRelationshipDB.enrollUser(c, u, Role.INSTRUCTOR);
        }
        ModelAndView mav = new ModelAndView("redirect:/admin/course");
        return mav;
    }
}