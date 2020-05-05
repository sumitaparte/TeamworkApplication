package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.IQuestionBankPersistence;
import CSCI5308.GroupFormationTool.Questions.OptionValue;
import CSCI5308.GroupFormationTool.Questions.QuestionBank;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionBankTest {

    @Test
    public void ConstructorTests() {
        QuestionBank questionBank = new QuestionBank();
        Assert.isTrue(questionBank.getQuestionID() == -1);
        Assert.isTrue(questionBank.getQuestionText().isEmpty());
        Assert.isTrue(questionBank.getQuestionTitle().isEmpty());
        Assert.isTrue(questionBank.getOptionValues().isEmpty());
    }

    @Test
    public void setquestionIDTest() {
        QuestionBank qu = new QuestionBank();
        qu.setQuestionID(10);
        Assert.isTrue(10 == qu.getQuestionID());
    }

    @Test
    public void getIDTest() {
        QuestionBank qu = new QuestionBank();
        qu.setQuestionID(10);
        Assert.isTrue(10 == qu.getQuestionID());
    }

    @Test
    public void getQuestionTextTest() {
        QuestionBank qu = new QuestionBank();
        qu.setQuestionText("My first question");
        Assert.isTrue(qu.getQuestionText().equals("My first question"));
    }

    @Test
    public void setQuestionTextTest() {
        QuestionBank qu = new QuestionBank();
        qu.setQuestionText("My first question");
        Assert.isTrue(qu.getQuestionText().equals("My first question"));
    }

    @Test
    public void getQuestionTitleTest() {
        QuestionBank qu = new QuestionBank();
        qu.setQuestionTitle("Course name survey");
        Assert.isTrue(qu.getQuestionTitle().equals("Course name survey"));
    }

    @Test
    public void setQuestionTitleTest() {
        QuestionBank qu = new QuestionBank();
        qu.setQuestionTitle("Course name survey");
        Assert.isTrue(qu.getQuestionTitle().equals("Course name survey"));
    }

    @Test
    public void getOptionValues() {
        QuestionBank qu = new QuestionBank();
        OptionValue op = new OptionValue();
        OptionValue op1 = new OptionValue();
        List<OptionValue> optionValues = new ArrayList<>();
        optionValues.add(op);
        optionValues.add(op1);
        qu.setOptionValues(optionValues);
        Assert.isTrue(qu.getOptionValues().contains(op));
    }

    @Test
    public void setOptionValues() {
        QuestionBank qu = new QuestionBank();
        OptionValue op = new OptionValue();
        OptionValue op1 = new OptionValue();
        List<OptionValue> optionValues = new ArrayList<>();
        optionValues.add(op);
        optionValues.add(op1);
        qu.setOptionValues(optionValues);
        Assert.isTrue(qu.getOptionValues().contains(op));
    }

    @Test
    public void insertQuestionTest() {
        IQuestionBankPersistence questionDBMock = new QuestionBankDBMock();
        QuestionBank qu = new QuestionBank();
        boolean returned_value = questionDBMock.insertQuestion(qu);
        Assert.isTrue(returned_value);

    }

    @Test
    public void getQuestionbyidTest() {
        IQuestionBankPersistence questionDBMock = new QuestionBankDBMock();
        QuestionBank qu = new QuestionBank();
        qu.setQuestionID(-1);
        QuestionBank qu1 = questionDBMock.getQuestionById(1, qu);
        Assert.isTrue(qu1.getQuestionID() == 1);
        Assert.isTrue(qu1.getQuestionTitle().equals("New question"));
        Assert.isTrue(qu1.getQuestionText().equals("New Question Text"));
    }

    @Test
    public void getallQuestionsTest() {
        IQuestionBankPersistence questionDBMock = new QuestionBankDBMock();
        QuestionBank qu = new QuestionBank();
        List<QuestionBank> list_of_questions = questionDBMock.getallQuestions("questionTitle");
        Assert.isTrue(list_of_questions.size() > 1);
        Assert.isTrue(list_of_questions.get(0).getQuestionID() == 1);
        Assert.isTrue(list_of_questions.get(1).getQuestionID() == 2);
    }

    @Test
    public void deleteQuestionTest() {
        IQuestionBankPersistence questionDBMock = new QuestionBankDBMock();
        Assert.isTrue(questionDBMock.deleteQuestionById(1));
    }

}
