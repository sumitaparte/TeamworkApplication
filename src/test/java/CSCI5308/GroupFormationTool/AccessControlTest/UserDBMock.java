package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;

public class UserDBMock implements IUserPersistence {
    public boolean loadUserByID(long id, User user) {
        user.setBannerID("B00000000");
        user.setEmail("rhawkey@dal.ca");
        user.setFirstName("Rob");
        user.setLastName("Hawkey");
        user.setID(id);
        return true;
    }

    public void loadUserByBannerID(String bannerID, User user) {
        user.setBannerID(bannerID);
        user.setEmail("rhawkey@dal.ca");
        user.setFirstName("Rob");
        user.setLastName("Hawkey");
        user.setID(1);
    }

    public boolean createUser(User user) {
        user.setBannerID("B00843607");
        user.setEmail("dp@gmail.com");
        user.setFirstName("Darpan");
        user.setLastName("Patel");
        user.setPassword("****");
        user.setID(2);
        return true;
    }

    public boolean updateUser(User user) {
        user.setBannerID("B00843607");
        user.setEmail("vr@gmail.com");
        user.setFirstName("Varun");
        user.setLastName("Chauhan");
        user.setPassword("****");
        user.setID(2);
        return true;
    }

    @Override
    public boolean checkUserExists(User user) {
        long id = user.getId();
        if (id == 0) {
            return false;
        } else {
            return true;
        }
    }
}
