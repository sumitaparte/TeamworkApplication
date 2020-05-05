package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.ErrorHandling.DatabaseColumnNotFoundException;
import CSCI5308.GroupFormationTool.GroupFormationToolLogs;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class QuestionManagerDB implements IQuestionBankPersistence {

    @Override
    public boolean insertQuestion(QuestionBank questionBank) {
        {
            String qOption = "";
            String qValue = "";
            for (OptionValue optionValue : questionBank.getOptionValues()) {
                if (StringUtils.isNotEmpty(optionValue.getOption()) && StringUtils.isNotEmpty(optionValue.getValue())) {
                    qOption = qOption + optionValue.getOption() + ",";
                    qValue = qValue + optionValue.getValue() + ",";
                }
            }
            CallStoredProcedure proc = null;
            try {
                proc = new CallStoredProcedure("spInsertQuestion(?, ?, ?, ?, ?)");
                proc.setParameter(1, questionBank.getQuestionTitle());
                proc.setParameter(2, questionBank.getQuestionText());
                proc.setParameter(3, questionBank.getQuestionType());
                proc.setParameter(4, qOption);
                proc.setParameter(5, qValue);
                proc.execute();
                GroupFormationToolLogs.log(Level.INFO, "Question Inserted Successfully", null);
            } catch (SQLException e) {
                GroupFormationToolLogs.log(Level.SEVERE, e.getMessage(), e);
                return false;
            } finally {
                if (null != proc) {
                    proc.cleanup();
                }
            }
            return true;
        }
    }

    @Override
    public QuestionBank getQuestionById(int question_id, QuestionBank questionBank) {
        return null;
    }

    @Override
    public List<QuestionBank> getallQuestions(String orderBy) {
        List<QuestionBank> questions = new ArrayList<QuestionBank>();
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spLoadAllQuestions(?)");
            proc.setParameter(1, orderBy);
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    QuestionBank qBank = new QuestionBank();
                    qBank.setQuestionID(results.getInt(1));
                    qBank.setQuestionTitle(results.getString(2));
                    questions.add(qBank);
                }
                GroupFormationToolLogs.log(Level.INFO, "Questions Retrieved Successfully", null);
            }
        } catch (SQLSyntaxErrorException e) {
            throw new DatabaseColumnNotFoundException("QuestionManagerDatabaseError");
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, e.getMessage(), e);

        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return questions;
    }


    @Override
    public boolean deleteQuestionById(int questionId) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spDeleteQuestionByID(?)");
            proc.setParameter(1, questionId);
            ResultSet resultSet = proc.executeWithResults();
            if (!resultSet.next()) {
                GroupFormationToolLogs.log(Level.INFO, "Question Deleted: Question id" + questionId, null);
            } else {
                GroupFormationToolLogs.log(Level.INFO, "Question Can Not be Deleted: Question id" + questionId, null);
                return false;
            }

        } catch (SQLSyntaxErrorException e) {
            throw new DatabaseColumnNotFoundException("QuestionManagerDB");
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, e.getMessage(), e);
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return true;
    }
}
