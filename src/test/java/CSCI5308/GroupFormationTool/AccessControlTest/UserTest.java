package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@SuppressWarnings("deprecation")
public class UserTest {
    @Test
    public void ConstructorTests() {
        User u = new User();
        Assert.isTrue(u.getBannerID().isEmpty());
        Assert.isTrue(u.getFirstName().isEmpty());
        Assert.isTrue(u.getLastName().isEmpty());
        Assert.isTrue(u.getEmail().isEmpty());

        IUserPersistence userDBMock = new UserDBMock();
        u = new User(1, userDBMock);
        Assert.isTrue(u.getBannerID().equals("B00000000"));

        u = new User("B00000000", userDBMock);
        Assert.isTrue(u.getBannerID().equals("B00000000"));
    }

    @Test
    public void setIDTest() {
        User u = new User();
        u.setID(10);
        Assert.isTrue(10 == u.getID());
    }

    @Test
    public void getIDTest() {
        User u = new User();
        u.setID(10);
        Assert.isTrue(10 == u.getID());
    }

    @Test
    public void setBannerIDTest() {
        User u = new User();
        u.setBannerID("B00000000");
        Assert.isTrue(u.getBannerID().equals("B00000000"));
    }

    @Test
    public void getBannerIDTest() {
        User u = new User();
        u.setBannerID("B00000000");
        Assert.isTrue(u.getBannerID().equals("B00000000"));
    }

    @Test
    public void setFirstNameTest() {
        User u = new User();
        u.setFirstName("Rob");
        Assert.isTrue(u.getFirstName().equals("Rob"));
    }

    @Test
    public void getFirstNameTest() {
        User u = new User();
        u.setFirstName("Rob");
        Assert.isTrue(u.getFirstName().equals("Rob"));
    }

    @Test
    public void setLastNameTest() {
        User u = new User();
        u.setLastName("Hawkey");
        Assert.isTrue(u.getLastName().equals("Hawkey"));
    }

    @Test
    public void getLastNameTest() {
        User u = new User();
        u.setLastName("Hawkey");
        Assert.isTrue(u.getLastName().equals("Hawkey"));
    }

    @Test
    public void setEmailTest() {
        User u = new User();
        u.setEmail("rhawkey@dal.ca");
        Assert.isTrue(u.getEmail().equals("rhawkey@dal.ca"));
    }

    @Test
    public void getEmailTest() {
        User u = new User();
        u.setEmail("rhawkey@dal.ca");
        Assert.isTrue(u.getEmail().equals("rhawkey@dal.ca"));
    }

    @Test
    public void isBannerIDValidTest() {
        Assert.isTrue(User.isBannerIDValid("B000000000"));
        Assert.isTrue(!User.isBannerIDValid(null));
        Assert.isTrue(!User.isBannerIDValid(""));
    }

    @Test
    public void isFirstNameValidTest() {
        Assert.isTrue(User.isFirstNameValid("rob"));
        Assert.isTrue(!User.isFirstNameValid(null));
        Assert.isTrue(!User.isFirstNameValid(""));
    }

    @Test
    public void isLastNameValidTest() {
        Assert.isTrue(User.isLastNameValid("hawkey"));
        Assert.isTrue(!User.isLastNameValid(null));
        Assert.isTrue(!User.isLastNameValid(""));
    }

    @Test
    public void isEmailValidTest() {
        Assert.isTrue(User.isEmailValid("rhawkey@dal.ca"));
        Assert.isTrue(!User.isEmailValid(null));
        Assert.isTrue(!User.isEmailValid(""));
        Assert.isTrue(!User.isEmailValid("@dal.ca"));
        Assert.isTrue(!User.isEmailValid("rhawkey@"));
    }

    @Test
    public void createUserTest() {
        UserDBMock userDBMock = new UserDBMock();
        User user = new User();
        userDBMock.createUser(user);
        Assert.isTrue(user.getId() == 2);
        Assert.isTrue(user.getBannerID().equals("B00843607"));
    }

    @Test
    public void checkUserExists() {
        UserDBMock userDBMock = new UserDBMock();
        User user = new User();
        user.setId(1);
        Assert.isTrue(userDBMock.checkUserExists(user));
    }
    @Test
    public void updateUserTest(){
        UserDBMock userDBMock = new UserDBMock();
        User user = new User();
        userDBMock.updateUser(user);
        Assert.isTrue(user.getLastName().equals("Chauhan"));
    }
}
