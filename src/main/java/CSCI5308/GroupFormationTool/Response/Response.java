package CSCI5308.GroupFormationTool.Response;

import CSCI5308.GroupFormationTool.Questions.OptionValue;

import java.util.List;

public class Response {
    private String studentID;
    private long questionID;
    private OptionValue responseOption;
    private String response;
    private List<String> mcqResponse;

    public Response() {
        setDefault();
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(long questionID) {
        this.questionID = questionID;
    }

    public OptionValue getResponseOption() {
        return responseOption;
    }

    public void setResponseOption(OptionValue responseOption) {
        this.responseOption = responseOption;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<String> getMcqResponse() {
        return mcqResponse;
    }

    public void setMcqResponse(List<String> mcqResponse) {
        this.mcqResponse = mcqResponse;
    }

    public void setDefault() {
        studentID = null;
        questionID = -1;
        responseOption = null;
    }

    @Override
    public String toString() {
        return "Response{" +
                "studentID=" + studentID +
                ", questionID=" + questionID +
                ", responseOption=" + responseOption +
                ", response='" + response + '\'' +
                ", mcqResponse=" + mcqResponse +
                '}';
    }
}
