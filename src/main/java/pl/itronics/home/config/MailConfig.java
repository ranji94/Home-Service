package pl.itronics.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import pl.itronics.home.utils.LocalCreds;

@Configuration
public class MailConfig {
    @Bean
    public SimpleMailMessage emailTemplate()
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(LocalCreds.getCredentials("mail:to"));
        message.setFrom(LocalCreds.getCredentials("mail:from"));
        message.setText("FATAL - Application crash. Save your job !!");
        return message;
    }
}
