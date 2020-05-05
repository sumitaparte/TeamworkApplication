package CSCI5308.GroupFormationTool.ErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public final ModelAndView handleResourceNotFoundException(ResourceNotFoundException e, final HttpServletRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CustomErrorResponse er = new CustomErrorResponse(
                e.getMessage(),
                badRequest,
                request.getRequestURI(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("error", er.getMessage());
        mv.addObject("status", er.getHttpStatus());
        mv.addObject("path", er.getRequest());
        return mv;
    }

    @ExceptionHandler(value = {CourseNotFoundException.class})
    public final ModelAndView handleCourseNotFoundException(CourseNotFoundException e, final HttpServletRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CustomErrorResponse er = new CustomErrorResponse(
                e.getMessage(),
                badRequest,
                request.getRequestURI(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("error", "This Course not Present. Please Go to the Home Page. Course Id You Requested was" + er.getMessage());
        mv.addObject("status", er.getHttpStatus());
        mv.addObject("path", er.getRequest());
        mv.addObject("timestamp", er.getTimestamp());
        return mv;
    }

    @ExceptionHandler(value = {MailNotSentException.class})
    public final ModelAndView mailnotsentException(MailNotSentException e, final HttpServletRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CustomErrorResponse er = new CustomErrorResponse(
                e.getMessage(),
                badRequest,
                request.getRequestURI(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("error_status", er.getMessage());
        return mv;
    }

    @ExceptionHandler(value = {DatabaseAccessException.class})
    public final ModelAndView databaseaccessException(DatabaseAccessException e, final HttpServletRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CustomErrorResponse er = new CustomErrorResponse(
                e.getMessage(),
                badRequest,
                request.getRequestURI(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("error", er.getMessage());
        mv.addObject("status", er.getHttpStatus());
        mv.addObject("path", er.getRequest());
        mv.addObject("timestamp", er.getTimestamp());
        return mv;
    }

    @ExceptionHandler(value = {DatabaseColumnNotFoundException.class})
    public final String databasecolumnnotfoundException(DatabaseColumnNotFoundException e, final HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CustomErrorResponse er = new CustomErrorResponse(
                e.getMessage(),
                badRequest,
                request.getRequestURI(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        ModelAndView mv = new ModelAndView("/admin/course");
        redirectAttributes.addFlashAttribute("status", e.getMessage());
        return "redirect:/admin/course";
    }

    @ExceptionHandler(value = {UserDuplicationException.class})
    public final String userduplicationException(UserDuplicationException e, final HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CustomErrorResponse er = new CustomErrorResponse(
                e.getMessage(),
                badRequest,
                request.getRequestURI(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        ModelAndView mv = new ModelAndView("/signup");
        redirectAttributes.addFlashAttribute("status", "User with same Banner ID Already Exists");
        return "redirect:/signup";
    }
}
