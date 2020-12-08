package pl.itronics.home.service;

import pl.itronics.home.domain.MailInfo;
import pl.itronics.home.domain.SentReadings;

import java.lang.reflect.InvocationTargetException;

public interface MailService {
    public void sendMail(String to, String subject, String body);
    public void sendPreConfiguredMail(String message);
    public MailInfo getSavedTemplate();
    public long updateTemplate(MailInfo mailInfo);
    public SentReadings sendLastReadings();
}
