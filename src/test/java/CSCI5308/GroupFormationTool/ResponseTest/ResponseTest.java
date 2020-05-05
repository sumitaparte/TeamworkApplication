package CSCI5308.GroupFormationTool.ResponseTest;

import CSCI5308.GroupFormationTool.Questions.QuestionBank;
import CSCI5308.GroupFormationTool.Response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@SuppressWarnings("deprecation")

public class ResponseTest {

    @Test
    public void ConstructorTest() {
        Response r = new Response();
        Assert.isTrue(r.getQuestionID() == -1);
        Assert.isTrue(r.getStudentID() == null);
        Assert.isTrue(r.getResponseOption() == null);
    }

    @Test
    public void getStudentIDTest() {
        Response r = new Response();
        r.setStudentID("10");
        Assert.isTrue("10" == r.getStudentID());
    }

    @Test
    public void setStudentIDTest() {
        Response r = new Response();
        r.setStudentID("10");
        Assert.isTrue("10" == r.getStudentID());
    }

    @Test
    public void getQuestionIDTest() {
        Response r = new Response();
        r.setQuestionID(1);
        Assert.isTrue(1 == r.getQuestionID());
    }

    @Test
    public void setQuestionIDTest() {
        Response r = new Response();
        r.setQuestionID(1);
        Assert.isTrue(1 == r.getQuestionID());
    }

    @Test
    public void getAllSurveyQuestionTest() {
        ResponseDBMock responseDBMock = new ResponseDBMock();
        List<QuestionBank> question_list = new ArrayList<>();
        question_list = responseDBMock.getAllSurveyQuestions(1);
        Assert.isTrue(question_list.size() > 0);
    }

    @Test
    public void loadResponseByIdTest() {
        ResponseDBMock responseDBMock = new ResponseDBMock();
        List<QuestionBank> question_list = new ArrayList<>();
        question_list = responseDBMock.getAllSurveyQuestions(1);
        Assert.isTrue(question_list.size() > 0);
    }

    @Test
    public void saveresponsebyIdTest() {
        Response response = new Response();
        List<Response> responses = new ArrayList<>();
        responses.add(response);
        ResponseDBMock responseDBMock = new ResponseDBMock();
        responseDBMock.saveresponsebyId("B00838", responses);
    }

}
