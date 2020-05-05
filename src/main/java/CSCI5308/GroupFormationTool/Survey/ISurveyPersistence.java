package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

public interface ISurveyPersistence {
    public boolean insertQuestion(Survey survey);

    public List<Survey> getallQuestions();

    public boolean deleteQuestionFromSurvey(Survey survey);

    public List<Survey> getallSurveyQuestions(int courseID);
}
