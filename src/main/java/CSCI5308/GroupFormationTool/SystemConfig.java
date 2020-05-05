package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AccessControl.Email;
import CSCI5308.GroupFormationTool.AccessControl.IUserNotifications;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.UserDB;
import CSCI5308.GroupFormationTool.Courses.CourseDB;
import CSCI5308.GroupFormationTool.Courses.CourseUserRelationshipDB;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Database.DefaultDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Database.IDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Questions.IQuestionBankPersistence;
import CSCI5308.GroupFormationTool.Questions.QuestionManagerDB;
import CSCI5308.GroupFormationTool.Response.IResponsePersistence;
import CSCI5308.GroupFormationTool.Response.ResponseDB;
import CSCI5308.GroupFormationTool.Security.BCryptPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyDB;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class SystemConfig {
    private static SystemConfig uniqueInstance = null;

    private IPasswordEncryption passwordEncryption;
    private IUserPersistence userDB;
    private IDatabaseConfiguration databaseConfiguration;
    private ICoursePersistence courseDB;
    private ICourseUserRelationshipPersistence courseUserRelationshipDB;
    private IUserNotifications notification;
    private JavaMailSenderImpl mailSender;
    private IQuestionBankPersistence questionBankPersistence;
    private IResponsePersistence responseDb;
    private ISurveyPersistence surveyPersistence;

    private SystemConfig() {
        passwordEncryption = new BCryptPasswordEncryption();
        userDB = new UserDB();
        databaseConfiguration = new DefaultDatabaseConfiguration();
        courseDB = new CourseDB();
        courseUserRelationshipDB = new CourseUserRelationshipDB();
        notification = new Email();
        mailSender = new JavaMailSenderImpl();
        questionBankPersistence = new QuestionManagerDB();
        responseDb = new ResponseDB();
        surveyPersistence = new SurveyDB();
    }

    public static SystemConfig instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new SystemConfig();
        }
        return uniqueInstance;
    }

    public IPasswordEncryption getPasswordEncryption() {
        return passwordEncryption;
    }

    public void setPasswordEncryption(IPasswordEncryption passwordEncryption) {
        this.passwordEncryption = passwordEncryption;
    }

    public IUserPersistence getUserDB() {
        return userDB;
    }

    public void setUserDB(IUserPersistence userDB) {
        this.userDB = userDB;
    }

    public IDatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public void setCourseDB(ICoursePersistence courseDB) {
        this.courseDB = courseDB;
    }

    public ICoursePersistence getCourseDB() {
        return courseDB;
    }

    public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB) {
        this.courseUserRelationshipDB = courseUserRelationshipDB;
    }

    public ICourseUserRelationshipPersistence getCourseUserRelationshipDB() {
        return courseUserRelationshipDB;
    }

    public IUserNotifications getNotification() {
        return notification;
    }

    public void setNotification(IUserNotifications notification) {
        this.notification = notification;
    }

    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public IQuestionBankPersistence getQuestionBankPersistence() {
        return questionBankPersistence;
    }

    public void setQuestionBankPersistence(IQuestionBankPersistence questionBankPersistence) {
        this.questionBankPersistence = questionBankPersistence;
    }

    public static SystemConfig getUniqueInstance() {
        return uniqueInstance;
    }

    public static void setUniqueInstance(SystemConfig uniqueInstance) {
        SystemConfig.uniqueInstance = uniqueInstance;
    }

    public IResponsePersistence getResponseDb() {
        return responseDb;
    }

    public void setResponseDb(IResponsePersistence responseDb) {
        this.responseDb = responseDb;
    }

    public ISurveyPersistence getSurveyPersistence() {
        return surveyPersistence;
    }

    public void setSurveyPersistence(ISurveyPersistence surveyPersistence) {
        this.surveyPersistence = surveyPersistence;
    }

}
