package pl.itronics.home.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.itronics.home.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MailRepository {
    @Autowired
    JdbcTemplate template;

    private final String UPDATE_MAIL_DATA = "UPDATE maildata " +
            "SET signature=?, target=?, source=?, template=?, mailpass=?, subject=?" +
            " WHERE id=1;";

    private final String SELECT_MAIL_DATA = "SELECT" +
            " id," +
            " signature," +
            " target," +
            " source," +
            " template," +
            " mailpass," +
            " subject" +
            " FROM maildata WHERE id=1;";

    public long updateMailTemplate(MailInfo mailTemplate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_MAIL_DATA);
            ps.setString(1, mailTemplate.getSignature());
            ps.setString(2, mailTemplate.getTarget());
            ps.setString(3, mailTemplate.getSource());
            ps.setString(4, mailTemplate.getTemplate());
            ps.setString(5, mailTemplate.getMailpass());
            ps.setString(6, mailTemplate.getSubject());
            return ps;
        }, keyHolder);

        try {
            return (long) keyHolder.getKey();
        }
        catch (NullPointerException e) {
            return 0L;
        }
    }

    public MailInfo getSavedTemplate() {
        try {
            Connection connection = template.getDataSource().getConnection();
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(SELECT_MAIL_DATA);
            MailInfo mailInfo = mapRowToMailInfo(rs);
            st.close();

            return mailInfo;
        }
        catch (SQLException e) {
            return new MailInfo();
        }
    }

    private MailInfo mapRowToMailInfo(ResultSet rs) throws SQLException {
        MailInfo mailInfo = new MailInfo();

        while (rs.next())
        {
            mailInfo.setId(rs.getInt("id"));
            mailInfo.setSignature(rs.getString("signature"));
            mailInfo.setTarget(rs.getString("target"));
            mailInfo.setSource(rs.getString("source"));
            mailInfo.setTemplate(rs.getString("template"));
            mailInfo.setMailpass(rs.getString("mailpass"));
            mailInfo.setSubject(rs.getString("subject"));
        }

        return mailInfo;
    }
}
