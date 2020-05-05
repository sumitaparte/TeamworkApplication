package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.GroupFormationToolLogs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class SurveyDB implements ISurveyPersistence {

    @Override
    public boolean insertQuestion(Survey survey) {
        CallStoredProcedure proc = null;
        {
            try {
                proc = new CallStoredProcedure("spInsertSurveyQuestion(?,?)");
                proc.setParameter(1, survey.getCourseId());
                proc.setParameter(2, survey.getQuestionId());
                proc.execute();
                GroupFormationToolLogs.log(Level.INFO, "Question Inserted Successfully, Question id is " + survey.getQuestionId(), null);
            } catch (SQLException e) {
                GroupFormationToolLogs.log(Level.SEVERE, "Insert Question into Survey Failed" + Integer.toString(survey.getCourseId()) + e.getMessage(), e);
                return false;
            } finally {
                proc.cleanup();
            }
        }

        return true;
    }

    @Override
    public List<Survey> getallQuestions() {

        List<Survey> sur = new ArrayList<Survey>();
        CallStoredProcedure proc = null;

        try {
            proc = new CallStoredProcedure("spLoadAllQuestionSurvey()");
            ResultSet result = proc.executeWithResults();

            if (null == result) {
                GroupFormationToolLogs.log(Level.INFO, "No Questions", null);
            } else {
                while (result.next()) {
                    Survey survey = new Survey();
                    survey.setQuestionText(result.getString(1));
                    survey.setQuestionTitle(result.getString(2));
                    survey.setQuestionId(result.getInt(3));
                    sur.add(survey);
                }
                GroupFormationToolLogs.log(Level.INFO, "All Question Retrieved", null);
            }

        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, "Questions Retrieve Failed" + e.getMessage(), e);
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return sur;
    }

    @Override
    public List<Survey> getallSurveyQuestions(int course_id) {
        List<Survey> survey_list = new ArrayList<Survey>();
        CallStoredProcedure proc = null;

        try {
            proc = new CallStoredProcedure("spLoadSurveyQuestions(?)");
            proc.setParameter(1, course_id);
            ResultSet result = proc.executeWithResults();
            if (null == result) {
                GroupFormationToolLogs.log(Level.INFO, "No Survey Questions Retrieved For Course Id" + course_id, null);
            } else {
                while (result.next()) {
                    Survey survey = new Survey();
                    survey.setQuestionText(result.getString(3));
                    survey.setQuestionTitle(result.getString(2));
                    survey.setQuestionId(result.getInt(1));
                    survey_list.add(survey);
                }
                GroupFormationToolLogs.log(Level.INFO, "All Survey Question Retrieved", null);
            }
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, "Course ID is: " + Integer.toString(course_id) + e.getMessage(), e);
            List<Survey> survey_list_exception = new ArrayList<>();
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return survey_list;
    }

    @Override
    public boolean deleteQuestionFromSurvey(Survey survey) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spDeleteQuestionFromSurvey(?,?)");
            proc.setParameter(2, survey.getCourseId());
            proc.setParameter(1, survey.getQuestionId());
            proc.execute();
            GroupFormationToolLogs.log(Level.INFO, "Question Deleted: Question id" + survey.getQuestionId(), null);
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, "Question ID is: " + Integer.toString(survey.getCourseId()) + e.getMessage(), e);
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return true;
    }
}


