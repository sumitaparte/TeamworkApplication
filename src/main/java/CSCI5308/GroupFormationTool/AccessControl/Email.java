package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.GroupFormationToolLogs;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class Email implements IUserNotifications {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            GroupFormationToolLogs.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public void sendUserLoginCredentials(User user, String rawPassword) {

        SimpleMailMessage message = new SimpleMailMessage();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(properties.getProperty("spring.mail.port")));
        mailSender.setUsername(properties.getProperty("spring.mail.username"));
        mailSender.setPassword(properties.getProperty("spring.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        message.setTo(user.getEmail());
        message.setSubject("GroupFormationTool: Registration Successful");
        message.setText("Sign up done!! <br> Here is your raw password: " + rawPassword);
        try {
            mailSender.send(message);
        } catch (MailException ex) {
            GroupFormationToolLogs.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}