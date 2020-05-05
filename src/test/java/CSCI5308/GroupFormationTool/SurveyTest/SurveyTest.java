package CSCI5308.GroupFormationTool.SurveyTest;

import CSCI5308.GroupFormationTool.Questions.QuestionBank;
import CSCI5308.GroupFormationTool.Survey.Survey;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@SuppressWarnings("deprecation")
public class SurveyTest {

    @Test
    public void ConstructorTest() {
        Survey survey = new Survey();
        Assert.isTrue(survey.getCourseId() == -1);
        Assert.isTrue(survey.getQuestionId() == -1);
    }

    @Test
    public void setCourseIDTest() {
        Survey survey = new Survey();
        survey.setCourseId(10);
        Assert.isTrue(10 == survey.getCourseId());
    }

    @Test
    public void getCourseIDTest() {
        Survey survey = new Survey();
        survey.setCourseId(10);
        Assert.isTrue(10 == survey.getCourseId());
    }

    @Test
    public void setQuestionIDTest() {
        Survey survey = new Survey();
        survey.setQuestionId(10);
        Assert.isTrue(10 == survey.getQuestionId());
    }

    @Test
    public void getQuestionIDTest() {
        Survey survey = new Survey();
        survey.setQuestionId(10);
        Assert.isTrue(10 == survey.getQuestionId());
    }

    @Test
    public void insertQuestionintoSurveyTest() {
        SurveyDBMock dbmock = new SurveyDBMock();
        Survey survey = new Survey();
        boolean status = dbmock.insertQuestion(survey);
        Assert.isTrue(status);
    }

    @Test
    public void getAllQuestionsTest() {
        SurveyDBMock surveyDBMock = new SurveyDBMock();
        List<Survey> questionSurveyList = new ArrayList<>();
        questionSurveyList = surveyDBMock.getallQuestions();
        Assert.isTrue(questionSurveyList.size() > 0);
    }

    @Test
    public void deleteQuestionFromSurveyTest() {
        SurveyDBMock surveyDBMock = new SurveyDBMock();
        int question_id = 10;
        List<QuestionBank> questionBankList = new ArrayList<>();
        Survey survey1 = new Survey();
        survey1.setQuestionId(1);
        boolean status = surveyDBMock.deleteQuestionFromSurvey(survey1);
        Assert.isTrue(status);
    }

}
