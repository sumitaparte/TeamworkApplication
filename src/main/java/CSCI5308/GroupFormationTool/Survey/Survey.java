package CSCI5308.GroupFormationTool.Survey;

public class Survey {
    private int courseID;
    private int questionId;
    public String questionTitle;
    public String questionText;

    public Survey() {
        setDefault();
    }

    public void setDefault() {
        courseID = -1;
        questionId = -1;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public int getCourseId() {
        return courseID;
    }

    public void setCourseId(int courseID) {
        this.courseID = courseID;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "courseId=" + courseID +
                ", questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionText='" + questionText + '\'' +
                '}';
    }
}
