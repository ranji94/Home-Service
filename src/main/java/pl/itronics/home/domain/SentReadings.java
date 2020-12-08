package pl.itronics.home.domain;

public class SentReadings {
    private MailInfo mailInfo;
    private String status;

    public SentReadings(MailInfo mailInfo, String status) {
        this.mailInfo = mailInfo;
        this.status = status;
    }

    public SentReadings() {
    }

    public MailInfo getMailInfo() {
        return mailInfo;
    }

    public void setMailInfo(MailInfo mailInfo) {
        this.mailInfo = mailInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
