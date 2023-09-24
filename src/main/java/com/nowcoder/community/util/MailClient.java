package com.nowcoder.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/*邮箱客户端 发邮件委托新浪做 相当于客户端
@Component表示通用的bean在哪里都可以用*/
@Component
public class MailClient {
//在关键的地方记录日志以当前类命名
  private static final Logger logger = LoggerFactory.getLogger(MailClient.class);
  @Autowired
    private JavaMailSender mailSender;
  @Value("${spring.mail.username}")
    private String from;
  public void sendMail(String to,String subject,String content){
/* to 发送目标 发送主题 发送内容*/
      try {
          MimeMessage message = mailSender.createMimeMessage();
          MimeMessageHelper helper = new MimeMessageHelper(message);
          helper.setFrom(from);
          helper.setTo(to);
          helper.setSubject(subject);
          helper.setText(content, true);//支持html文本就加true
          mailSender.send(helper.getMimeMessage());
      } catch (MessagingException e) {
          logger.error("发送邮件失败:" + e.getMessage());
      }

  }


}
