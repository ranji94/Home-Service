package pl.itronics.home.domain;

public class MailInfo {
    private Integer id;
    private String signature;
    private String target;
    private String source;
    private String template;
    private String mailpass;
    private String subject;

    public Integer getId() {
        return id;
    }

    public String getSignature() {
        return signature;
    }

    public String getTarget() {
        return target;
    }

    public String getSource() {
        return source;
    }

    public String getTemplate() {
        return template;
    }

    public String getMailpass() {
        return mailpass;
    }

    public String getSubject() {
        return subject;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setMailpass(String mailpass) {
        this.mailpass = mailpass;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MailInfo() {}
}
