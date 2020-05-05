package CSCI5308.GroupFormationTool.SurveyTest;

import CSCI5308.GroupFormationTool.Questions.QuestionBank;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Survey;

import java.util.ArrayList;
import java.util.List;

public class SurveyDBMock implements ISurveyPersistence {
    @Override
    public boolean insertQuestion(Survey survey) {
        survey.setQuestionId(10);
        survey.setCourseId(1);
        if (survey.getQuestionId() == 10) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Survey> getallQuestions() {
        List<Survey> survey_list = new ArrayList<>();
        List<Survey> new_survey_list = new ArrayList<>();
        List<QuestionBank> question_list = new ArrayList<>();
        Survey survey = new Survey();
        Survey survey2 = new Survey();
        survey.setQuestionId(1);
        survey.setCourseId(1);
        survey2.setQuestionId(2);
        survey2.setCourseId(2);
        survey_list.add(survey);
        survey_list.add(survey2);

        QuestionBank questionBank = new QuestionBank();
        questionBank.setQuestionID(1);
        if (survey.getQuestionId() == questionBank.getQuestionID()) {
            new_survey_list.add(survey);
        }
        return new_survey_list;
    }

    @Override
    public boolean deleteQuestionFromSurvey(Survey survey1) {
        Survey survey = new Survey();
        survey.setQuestionId(1);
        survey.setCourseId(1);
        if (survey.getQuestionId() == survey1.getQuestionId()) {
            survey = null;
            return true;
        } else {
            return false;
        }
    }

    public List<Survey> getallSurveyQuestions(int course_id) {
        List<Survey> survey_list = new ArrayList<>();
        Survey survey = new Survey();
        survey.setCourseId(1);
        survey.setQuestionId(1);
        survey_list.add(survey);

        Survey survey_two = new Survey();
        survey.setQuestionId(2);
        survey.setCourseId(2);
        survey_list.add(survey_two);

        return survey_list;
    }

    ;

}
