package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CourseController {
    private static final String ID = "id";

    @GetMapping("/course/course")
    public String course(Model model, @RequestParam(name = ID) long courseId, RedirectAttributes redirectAttributes) {
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        Course course = new Course();
        boolean status = courseDB.loadCourseByID(courseId, course);
        if (status) {
            model.addAttribute("course", course);
            List<Role> userRoles = course.getAllRolesForCurrentUserInCourse();
            if (null == userRoles) {
                model.addAttribute("instructor", false);
                model.addAttribute("ta", false);
                model.addAttribute("student", false);
                model.addAttribute("guest", true);
            } else {
                model.addAttribute("instructor", userRoles.contains(Role.INSTRUCTOR));
                model.addAttribute("ta", userRoles.contains(Role.TA));
                model.addAttribute("student", userRoles.contains(Role.STUDENT));
                model.addAttribute("guest", userRoles.isEmpty());
            }
            return "course/course";
        } else {
            redirectAttributes.addFlashAttribute("status", "Course Can not be Loaded");
            return "redirect:/admin/course";
        }
    }
}
