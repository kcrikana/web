package kr.mjc.minbeom.web.dao;

import lombok.Data;
import org.owasp.encoder.Encode;

import java.sql.Date;

@Data
public class Article {
    int articleId;
    String title;
    String content;
    int userId;
    String name;
    Date cdate;
    Date udate;

    public String getTitleHtml() {
        return Encode.forHtml(title);
    }

    public String getContentHtml() {
        return Encode.forHtml(content).replaceAll("\n", "<br>");
    }
}