package CSCI5308.GroupFormationTool.ResponseTest;

import CSCI5308.GroupFormationTool.Questions.QuestionBank;
import CSCI5308.GroupFormationTool.Response.IResponsePersistence;
import CSCI5308.GroupFormationTool.Response.Response;
import CSCI5308.GroupFormationTool.Survey.Survey;

import java.util.ArrayList;
import java.util.List;

public class ResponseDBMock implements IResponsePersistence {

    public void loadresponsebyId(long id, Response r) {
        r.setStudentID("B003421431");
        r.setQuestionID(1);
        ArrayList options = new ArrayList();
        options.add("First Option");
        ArrayList values = new ArrayList();
        values.add(1);

    }

    public List<QuestionBank> getAllSurveyQuestions(int course_id) {
        List<QuestionBank> question_list = new ArrayList<>();
        List<Survey> survey_list = new ArrayList<>();
        Survey survey = new Survey();
        Survey survey2 = new Survey();
        QuestionBank questionBank = new QuestionBank();
        QuestionBank questionBank2 = new QuestionBank();
        questionBank.setQuestionID(1);
        questionBank.setQuestionText("Question Title");
        questionBank.setQuestionText("Question Text");

        questionBank2.setQuestionID(2);
        questionBank2.setQuestionText("Second Question");

        survey.setCourseId(1);
        survey.setQuestionId(1);
        survey_list.add(survey);
        survey2.setQuestionId(2);
        survey2.setQuestionId(2);
        survey_list.add(survey2);
        for (int i = 0; i <= survey_list.size() - 1; i++) {
            if (survey_list.get(i).getCourseId() == 1) {
                if (survey_list.get(i).getQuestionId() == questionBank.getQuestionID() ||
                        survey_list.get(i).getQuestionId() == questionBank2.getQuestionID()) {
                    question_list.add(questionBank);
                }
            }
        }

        return question_list;
    }

    @Override
    public boolean saveresponsebyId(String bannerID, List<Response> responses) {
        for (Response response : responses) {
            response.setResponse("user response");
            response.setQuestionID(99);
            response.setStudentID("B000123193");
        }
        if (responses.get(0).getResponse() != null) {
            return true;
        } else {
            return false;
        }

    }
}
