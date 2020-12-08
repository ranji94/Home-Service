package pl.itronics.home.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.itronics.home.domain.MailInfo;
import pl.itronics.home.domain.SentReadings;
import pl.itronics.home.service.MailService;

@RestController
@Api(value = "mail", tags = {"mail"})
public class MailController {
    @Autowired
    private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/mail/update")
    public ResponseEntity<Long> update(MailInfo mailInfo) {
        return new ResponseEntity<>(mailService.updateTemplate(mailInfo), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/mail/getTemplate")
    public ResponseEntity<MailInfo> getTemplate() {
        return new ResponseEntity<>(mailService.getSavedTemplate(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/mail/sendLastReadings")
    public ResponseEntity<SentReadings> sendLastReadings() {
        SentReadings readings = mailService.sendLastReadings();

        return new ResponseEntity<>(readings, readings.getStatus().equals("SUCCESS") ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
    }
}
