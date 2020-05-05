package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.GroupFormationToolLogs;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CustomAuthenticationManager implements AuthenticationManager {
    private static final String ADMIN_BANNER_ID = "B-000000";

    private Authentication checkAdmin(String password, User u, Authentication authentication) throws AuthenticationException {
        if (password.equals(u.getPassword())) {

            List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
            rights.add(new SimpleGrantedAuthority("ADMIN"));
            UsernamePasswordAuthenticationToken token;
            token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                    authentication.getCredentials(),
                    rights);
            GroupFormationToolLogs.log(Level.INFO, "Authentication Success", null);
            return token;
        } else {
            GroupFormationToolLogs.log(Level.WARNING, u.getBannerID() + " tried login with wrong password!", null);
            throw new BadCredentialsException("1000");
        }
    }

    private Authentication checkNormal(String password, User u, Authentication authentication) throws AuthenticationException {
        IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();
        if (passwordEncryption.matches(password, u.getPassword())) {
            List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
            rights.add(new SimpleGrantedAuthority("USER"));
            UsernamePasswordAuthenticationToken token;
            token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                    authentication.getCredentials(),
                    rights);
            GroupFormationToolLogs.log(Level.INFO, "Authentication Success", null);
            return token;
        } else {
            GroupFormationToolLogs.log(Level.WARNING, u.getBannerID() + " tried login with wrong password!", null);
            throw new BadCredentialsException("1000");
        }
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String bannerID = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        IUserPersistence userDB = SystemConfig.instance().getUserDB();
        User u;
        try {
            u = new User(bannerID, userDB);
        } catch (Exception e) {
            GroupFormationToolLogs.log(Level.WARNING, "Authentication Issue", null);
            throw new AuthenticationServiceException("1000");
        }
        if (u.isValidUser()) {
            if (bannerID.toUpperCase().equals(ADMIN_BANNER_ID)) {
                return checkAdmin(password, u, authentication);
            } else {
                return checkNormal(password, u, authentication);
            }
        } else {
            throw new BadCredentialsException("1001");
        }
    }
}
