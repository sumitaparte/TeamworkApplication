package CSCI5308.GroupFormationTool.Questions;

import java.util.List;

public interface IQuestionBankPersistence {
    public boolean insertQuestion(QuestionBank questionBank);

    public QuestionBank getQuestionById(int question_id, QuestionBank questionBank);

    public List<QuestionBank> getallQuestions(String orderBy);

    public boolean deleteQuestionById(int question_id);
}
