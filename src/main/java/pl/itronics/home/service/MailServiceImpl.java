package pl.itronics.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import pl.itronics.home.domain.MailInfo;
import pl.itronics.home.domain.Measurement;
import pl.itronics.home.domain.SentReadings;
import pl.itronics.home.enums.MeasurementStatus;
import pl.itronics.home.enums.Month;
import pl.itronics.home.repo.MailRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    SimpleMailMessage preConfiguredMessage;

    public MailServiceImpl(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    @Bean
    public JavaMailSender getJavaMailSender()
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        MailInfo info = getSavedTemplate();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(25);

        mailSender.setUsername(info.getSource());
        mailSender.setPassword(info.getMailpass());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Override
    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Override
    public void sendPreConfiguredMail(String message)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    @Override
    public MailInfo getSavedTemplate() {
        return mailRepository.getSavedTemplate();
    }

    @Override
    public long updateTemplate(MailInfo mailInfo)  {
        MailInfo currentSavedInfo = mailRepository.getSavedTemplate();
        MailInfo updatedMailInfo = new MailInfo();

        updatedMailInfo.setId(fieldOrDefault(mailInfo.getId(), currentSavedInfo.getId()));
        updatedMailInfo.setSubject(fieldOrDefault(mailInfo.getSubject(), currentSavedInfo.getSubject()));
        updatedMailInfo.setSignature(fieldOrDefault(mailInfo.getSignature(), currentSavedInfo.getSignature()));
        updatedMailInfo.setTarget(fieldOrDefault(mailInfo.getTarget(), currentSavedInfo.getTarget()));
        updatedMailInfo.setSource(fieldOrDefault(mailInfo.getSource(), currentSavedInfo.getSource()));
        updatedMailInfo.setMailpass(fieldOrDefault(mailInfo.getMailpass(), currentSavedInfo.getMailpass()));
        updatedMailInfo.setTemplate(fieldOrDefault(mailInfo.getTemplate(), currentSavedInfo.getTemplate()));

        return mailRepository.updateMailTemplate(updatedMailInfo);
    }

    @Override
    public SentReadings sendLastReadings() {
        SentReadings sentInfo = new SentReadings();
        Measurement measurement = measurementService.findLast();
        MailInfo info = mailRepository.getSavedTemplate();
        sentInfo.setMailInfo(info);

        if (!measurement.getStatus().equals(MeasurementStatus.WAITING_FOR_SEND)) {
            sentInfo.setStatus("ALREADY_SENT");
        }
        else {
            try {
                sendMail(info.getTarget(),
                        getSubjectFilledWithCurrentMonthAndYear(info.getSubject()),
                        getTemplateFilledWithLatestReadings(info.getTemplate(), info.getSignature(), measurement)
                );
                measurementService.updateStatus(measurement.getId(), MeasurementStatus.WAITING_FOR_SETTLEMENT);
                sentInfo.setStatus("SUCCESS");
            }
            catch (Exception e) {
                sentInfo.setStatus("FAILED");
            }
        }

        return sentInfo;
    }

    private String getSubjectFilledWithCurrentMonthAndYear(String subjectTemplate) {
        LocalDate localDate = LocalDate.now();
        return subjectTemplate
                .replace("{year}",
                        String.valueOf(localDate.getYear())
                )
                .replace("{month}",
                        Month.valueOfNumber(localDate.getMonthValue()).toString()
                );
    }

    private String getTemplateFilledWithLatestReadings(String messageTemplate, String signature, Measurement measurement) {
        Map<String, String> keys = new HashMap<>();
        LocalDate localDate = LocalDate.now();

        keys.put("month", Month.valueOfNumber(localDate.getMonthValue()).toString());
        keys.put("year", String.valueOf(localDate.getYear()));
        keys.put("energy", measurement.getEnergyReading().getEnergyReading().getValue());
        keys.put("kitchen_cold", measurement.getKitchenReadings().getColdWater().getValue());
        keys.put("kitchen_hot", measurement.getKitchenReadings().getHotWater().getValue());
        keys.put("bathroom_cold", measurement.getBathroomReadings().getColdWater().getValue());
        keys.put("bathroom_hot", measurement.getBathroomReadings().getHotWater().getValue());
        keys.put("signature", signature);

        String filledTemplate = messageTemplate;

        for(Map.Entry<String, String> entry : keys.entrySet()) {
            filledTemplate = filledTemplate.replace("{" + entry.getKey() + "}", entry.getValue());
        }

        return filledTemplate.replaceAll("\\{CR}", "\n");
    }

    private String fieldOrDefault(String field, String defaultValue) {
        return field == null ? defaultValue : field;
    }

    private Integer fieldOrDefault(Integer field, Integer defaultValue) {
        return field == null ? defaultValue : field;
    }
}
