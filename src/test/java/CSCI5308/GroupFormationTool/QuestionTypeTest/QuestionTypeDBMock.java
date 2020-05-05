package CSCI5308.GroupFormationTool.QuestionTypeTest;

import CSCI5308.GroupFormationTool.QuestionType.IQuestionTypePersistence;
import CSCI5308.GroupFormationTool.QuestionType.QuestionType;

public class QuestionTypeDBMock implements IQuestionTypePersistence {


    public void loadQuestionTypeByID(long id, QuestionType question) {
        question.setId(id);
        question.setQuestionType("mcq");
    }


    public boolean delete(long id) {
        QuestionType question = new QuestionType();
        question.setId(id);
        return true;
    }
}
