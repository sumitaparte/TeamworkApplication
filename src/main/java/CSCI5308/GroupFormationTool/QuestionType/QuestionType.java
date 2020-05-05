package CSCI5308.GroupFormationTool.QuestionType;

public class QuestionType {

    private String questionType;
    private long id;

    public QuestionType() {
        setDefault();
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setDefault() {
        questionType = "";
        id = -1;
    }

}
