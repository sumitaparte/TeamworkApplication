package CSCI5308.GroupFormationTool.Response;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.GroupFormationToolLogs;
import CSCI5308.GroupFormationTool.Questions.OptionValue;
import CSCI5308.GroupFormationTool.Questions.QuestionBank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ResponseDB implements IResponsePersistence {


    public void loadresponsebyId(long id, Response r) {
    }

    @Override
    public List<QuestionBank> getAllSurveyQuestions(int courseId) {
        List<QuestionBank> questions = new ArrayList<QuestionBank>();
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spLoadAllSurveyQuestionsByCourseId(?)");
            proc.setParameter(1, courseId);
            ResultSet results = proc.executeWithResults();
            if (null == results) {
                GroupFormationToolLogs.log(Level.INFO, "Survey Questions not loaded successfully", null);
            } else {
                while (results.next()) {
                    QuestionBank qBank = new QuestionBank();
                    qBank.setQuestionID(results.getInt(1));
                    qBank.setQuestionText(results.getString(2));
                    qBank.setQuestionType(results.getString(3));
                    String option = results.getString(4);
                    String[] options = option.split(",");

                    String value = results.getString(5);
                    String[] values = value.split(",");

                    List<OptionValue> optionValues = new ArrayList<OptionValue>();

                    for (int i = 0; i < values.length - 1; i++) {
                        OptionValue optionValue = new OptionValue();
                        optionValue.setOption(options[i]);
                        optionValue.setValue(values[i]);

                        optionValues.add(optionValue);
                    }
                    qBank.setOptionValues(optionValues);
                    questions.add(qBank);
                }
                GroupFormationToolLogs.log(Level.INFO, "Survey Questions loaded successfully", null);
            }
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, "Survey Questions Loading Failed", e);
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return questions;
    }

    @Override
    public boolean saveresponsebyId(String bannerID, List<Response> responses) {
        for (Response response : responses) {
            String resp = "";
            if ("null".equalsIgnoreCase(response.getResponse())) {
                String delim = ",";
                resp = String.join(delim, response.getMcqResponse());
            } else {
                resp = response.getResponse();
            }
            CallStoredProcedure proc = null;
            try {
                proc = new CallStoredProcedure("spInsertSurveyResponse(?, ?, ?)");
                proc.setParameter(1, bannerID);
                proc.setParameter(2, response.getQuestionID());
                proc.setParameter(3, resp);
                proc.execute();
                GroupFormationToolLogs.log(Level.INFO, "Survey Questions Saved Successfully", null);
            } catch (SQLException e) {
                GroupFormationToolLogs.log(Level.SEVERE, "Survey Responses Saving Failed", e);
                return false;
            } finally {
                if (null != proc) {
                    proc.cleanup();
                }
            }
        }

        return true;
    }
}
