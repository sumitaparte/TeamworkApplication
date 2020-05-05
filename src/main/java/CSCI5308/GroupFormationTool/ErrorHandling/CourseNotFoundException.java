package CSCI5308.GroupFormationTool.ErrorHandling;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
