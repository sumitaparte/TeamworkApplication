package CSCI5308.GroupFormationTool.QuestionType;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.GroupFormationToolLogs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class QuestionTypeDB implements IQuestionTypePersistence {

    public void loadQuestionTypeByID(long id, QuestionType question) {
        CallStoredProcedure proc = null;
        {
            try {
                proc = new CallStoredProcedure("spLoadQuestionTypeByID(?)");
                proc.setParameter(1, id);
                ResultSet resultSet = proc.executeWithResults();
                if (null == resultSet) {
                    GroupFormationToolLogs.log(Level.INFO, "Question Not Found", null);
                } else {
                    while (resultSet.next()) {
                        String questiontype = resultSet.getString(2);
                        question.setId(id);
                        question.setQuestionType(questiontype);
                    }
                    GroupFormationToolLogs.log(Level.INFO, "Load Question Success", null);
                }
            } catch (SQLException e) {
                GroupFormationToolLogs.log(Level.SEVERE, "Load Question Failed", e);
            } finally {
                if (null != proc) {
                    proc.cleanup();
                }
            }
        }

    }
}
