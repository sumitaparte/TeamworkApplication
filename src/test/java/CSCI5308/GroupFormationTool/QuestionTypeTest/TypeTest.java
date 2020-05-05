package CSCI5308.GroupFormationTool.QuestionTypeTest;

import CSCI5308.GroupFormationTool.Questions.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeTest {
    @Test
    public void TypeInsideClassTest() {
        Type type = Type.NUMERIC;
        assertEquals(Type.valueOf("NUMERIC"), type);
    }
}
