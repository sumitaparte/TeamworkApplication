package CSCI5308.GroupFormationTool.QuestionTypeTest;

import CSCI5308.GroupFormationTool.QuestionType.QuestionType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionTypeTest {

    @Test
    public void ConstructorTest() {
        QuestionType qu = new QuestionType();
        Assert.isTrue(qu.getId() == -1);
        Assert.isTrue(qu.getQuestionType().isEmpty());
    }

    @Test
    public void setQuestionTypeTest() {
        QuestionType qu = new QuestionType();
        qu.setQuestionType("multiple choice");
        Assert.isTrue(qu.getQuestionType().equals("multiple choice"));
    }

    @Test
    public void getQuestionTypeTest() {
        QuestionType qu = new QuestionType();
        qu.setQuestionType("text");
        Assert.isTrue(qu.getQuestionType().equals("text"));
    }

    @Test
    public void setQuestionIDTest() {
        QuestionType qu = new QuestionType();
        qu.setId(1);
        Assert.isTrue(1 == qu.getId());
    }

    @Test
    public void getQuestionIDTest() {
        QuestionType qu = new QuestionType();
        qu.setId(1);
        Assert.isTrue(1 == qu.getId());
    }
}




