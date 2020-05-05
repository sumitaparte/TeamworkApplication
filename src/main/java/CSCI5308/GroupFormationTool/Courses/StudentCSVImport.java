package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SystemConfig;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StudentCSVImport {
    private List<String> successResults;
    private List<String> failureResults;
    private Course course;
    private MultipartFile uploadedFile;
    private IUserPersistence userDB;
    private IPasswordEncryption passwordEncryption;

    public StudentCSVImport(Course course, MultipartFile file) {
        this.course = course;
        this.uploadedFile = file;
        successResults = new ArrayList<String>();
        failureResults = new ArrayList<String>();
        userDB = SystemConfig.instance().getUserDB();
        passwordEncryption = SystemConfig.instance().getPasswordEncryption();
        parseCSVFile();
    }

    private void parseCSVFile() {
        try {
            Reader reader = new InputStreamReader(uploadedFile.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).build();
            List<String[]> records = csvReader.readAll();
            Iterator<String[]> iter = records.iterator();
            while (iter.hasNext()) {
                String[] record = iter.next();
                enrollStudentFromRecord(record);
            }
        } catch (IOException e) {
            failureResults.add("Failure reading uploaded file: " + e.getMessage());
        } catch (Exception e) {
            failureResults.add("Failure parsing CSV file: " + e.getMessage());
        }
    }

    private void enrollStudentFromRecord(String[] record) {
        if (record.length != 4) {
            failureResults.add("Faulty row: " + Arrays.toString(record));
            return;
        }
        String bannerID = record[0];
        String firstName = record[1];
        String lastName = record[2];
        String email = record[3];
        User u = new User();
        userDB.loadUserByBannerID(bannerID, u);
        if (!u.isValidUser()) {
            u.setBannerID(bannerID);
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setEmail(email);
            if (u.createUser(userDB, passwordEncryption, null)) {
                successResults.add("Created: " + Arrays.toString(record));
            } else {
                failureResults.add("Unable to save this user to DB: " + Arrays.toString(record));
                return;
            }
        }
        if (course.enrollUserInCourse(Role.STUDENT, u)) {
            failureResults.add("Unable to enroll user in course: " + Arrays.toString(record));
        }
    }

    public List<String> getSuccessResults() {
        return successResults;
    }

    public List<String> getFailureResults() {
        return failureResults;
    }
}
