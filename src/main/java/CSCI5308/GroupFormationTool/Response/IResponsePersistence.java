package CSCI5308.GroupFormationTool.Response;

import CSCI5308.GroupFormationTool.Questions.QuestionBank;

import java.util.List;

public interface IResponsePersistence {
    public void loadresponsebyId(long id, Response r);

    public List<QuestionBank> getAllSurveyQuestions(int course_id);

    public boolean saveresponsebyId(String bannerID, List<Response> responses);
}
