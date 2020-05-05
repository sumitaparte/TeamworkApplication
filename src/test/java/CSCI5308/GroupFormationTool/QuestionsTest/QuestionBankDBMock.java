package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.IQuestionBankPersistence;
import CSCI5308.GroupFormationTool.Questions.OptionValue;
import CSCI5308.GroupFormationTool.Questions.QuestionBank;

import java.util.ArrayList;
import java.util.List;

public class QuestionBankDBMock implements IQuestionBankPersistence {
    public boolean insertQuestion(QuestionBank questionBank) {
        questionBank.setQuestionText("How are the course contents");
        questionBank.setQuestionTitle("Ratings");
        OptionValue optionValue = new OptionValue();
        optionValue.setOption("Better");
        optionValue.setValue("2");
        return true;
    }


    public QuestionBank getQuestionById(int question_id, QuestionBank qu) {
        qu.setQuestionID(1);
        qu.setQuestionTitle("New question");
        qu.setQuestionText("New Question Text");
        OptionValue optionValue = new OptionValue();
        optionValue.setOption("Best");
        optionValue.setValue("3");
        return qu;
    }

    public boolean deleteQuestionById(int question_id) {
        QuestionBank qBank = new QuestionBank();
        qBank.setQuestionID(1);
        qBank.setQuestionTitle("New Question");
        qBank.setQuestionText("New Question Text");
        qBank = null;
        return true;
    }

    public List<QuestionBank> getallQuestions(String orderBy) {
        List<QuestionBank> questionBank = new ArrayList<>();
        QuestionBank qu = new QuestionBank();
        QuestionBank qu1 = new QuestionBank();
        qu.setQuestionID(1);
        qu.setQuestionTitle("New question");
        qu.setQuestionText("New Question Text");
        OptionValue optionValue = new OptionValue();
        optionValue.setOption("Good");
        optionValue.setValue("1");

        questionBank.add(qu);

        qu1.setQuestionID(2);
        qu1.setQuestionTitle("New second question");
        qu1.setQuestionText("New second Question Text");
        OptionValue optionValue1 = new OptionValue();
        optionValue.setOption("Better");
        optionValue.setValue("2");
        questionBank.add(qu1);
        return questionBank;
    }
}


