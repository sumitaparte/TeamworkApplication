package CSCI5308.GroupFormationTool.Questions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionBank {
    private int questionID;
    private int questiontypeID;
    private String questionTitle;
    private String questionText;
    private ArrayList questionOption;
    private ArrayList questionValue;
    private String questionType;
    private List<OptionValue> optionValues;
    private Date entryDate;

    public QuestionBank() {
        setDefaults();
    }

    public void setDefaults() {
        questionID = -1;
        questiontypeID = -1;
        questionTitle = "";
        questionText = "";
        ArrayList empty_options = new ArrayList();
        ArrayList empty_values = new ArrayList();
        questionOption = empty_options;
        questionValue = empty_values;
        this.optionValues = new ArrayList<>();
        entryDate = null;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<OptionValue> getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(List<OptionValue> optionValues) {
        this.optionValues = optionValues;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getQuestiontypeID() {
        return questiontypeID;
    }

    public void setQuestiontypeID(int questiontypeID) {
        this.questiontypeID = questiontypeID;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public ArrayList getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(ArrayList questionOption) {
        this.questionOption = questionOption;
    }

    public ArrayList getQuestionValue() {
        return questionValue;
    }

    public void setQuestionValue(ArrayList questionValue) {
        this.questionValue = questionValue;
    }

    private static boolean isStringNullOrEmpty(String s) {
        if (null == s) {
            return true;
        }
        return s.isEmpty();
    }

    public static boolean isQuestionTextValid(String questionText) {
        return !isStringNullOrEmpty(questionText);
    }

    public static boolean isQuestionTitleValid(String questionTitle) {
        return !isStringNullOrEmpty(questionTitle);
    }

    public void addOptionValue(OptionValue optionValue) {
        this.optionValues.add(optionValue);
    }

    @Override
    public String toString() {
        return "QuestionBank{" +
                "questionID=" + questionID +
                ", questiontypeID=" + questiontypeID +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionText='" + questionText + '\'' +
                ", questionOption=" + questionOption +
                ", questionValue=" + questionValue +
                ", type='" + questionType + '\'' +
                ", optionValues=" + optionValues +
                '}';
    }
}
