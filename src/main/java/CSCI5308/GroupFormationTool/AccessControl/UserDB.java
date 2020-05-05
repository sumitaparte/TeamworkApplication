package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.ErrorHandling.UserDuplicationException;
import CSCI5308.GroupFormationTool.GroupFormationToolLogs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class UserDB implements IUserPersistence {
    public boolean loadUserByID(long id, User user) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spLoadUser(?)");
            proc.setParameter(1, id);
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    long userID = results.getLong(1);
                    String bannerID = results.getString(2);
                    String password = results.getString(3);
                    String firstName = results.getString(4);
                    String lastName = results.getString(5);
                    String email = results.getString(6);
                    user.setID(userID);
                    user.setBannerID(bannerID);
                    user.setPassword(password);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                }
                GroupFormationToolLogs.log(Level.INFO, "User loaded Successfully", null);
            }
        } catch (Exception e) {
            GroupFormationToolLogs.log(Level.SEVERE, e.getMessage(), e);
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return true;
    }

    public void loadUserByBannerID(String bannerID, User user) {
        CallStoredProcedure proc = null;
        long userID = -1;
        try {
            proc = new CallStoredProcedure("spFindUserByBannerID(?)");
            proc.setParameter(1, bannerID);
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    userID = results.getLong(1);
                }
                GroupFormationToolLogs.log(Level.SEVERE, "User found with Banner Id", null);
            }
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, "User can not be loaded by Banner Id", e);

        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        if (userID > -1) {
            loadUserByID(userID, user);
        }
    }

    public boolean checkUserExists(User user) {
        CallStoredProcedure proc = null;
        try {

            proc = new CallStoredProcedure("spFindUserByBannerID(?)");
            proc.setParameter(1, user.getBannerID());
            ResultSet results = proc.executeWithResults();
            if (results.next()) {
                GroupFormationToolLogs.log(Level.INFO, "User Found" + user.getBannerID(), null);
                throw new UserDuplicationException(user.getBannerID());
            } else {
                GroupFormationToolLogs.log(Level.INFO, "User Not Found" + user.getBannerID(), null);
                return true;
            }

        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, "User can not be created successfully", e);
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    public boolean createUser(User user) {
        CallStoredProcedure proc = null;
        try {

            proc = new CallStoredProcedure("spCreateUser(?, ?, ?, ?, ?, ?)");
            proc.setParameter(1, user.getBannerID());
            proc.setParameter(2, user.getPassword());
            proc.setParameter(3, user.getFirstName());
            proc.setParameter(4, user.getLastName());
            proc.setParameter(5, user.getEmail());
            proc.registerOutputParameterLong(6);
            proc.execute();
            GroupFormationToolLogs.log(Level.INFO, "User Created Successfully", null);
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, "User can not be created successfully", e);
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return true;
    }

    public boolean updateUser(User user) {
        return false;
    }
}
