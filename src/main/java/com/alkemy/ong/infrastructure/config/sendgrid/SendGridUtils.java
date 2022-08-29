package com.alkemy.ong.infrastructure.config.sendgrid;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ServiceException;
import com.alkemy.ong.application.util.MailService;
import com.alkemy.ong.application.util.IMail;
import com.alkemy.ong.application.util.IMailSend;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendGridUtils implements IMailSend {

  @Value("${app.email}")
  private String fromEmail;
  @Value("${sendgrid.api-key}")
  private String sendGridApiKey;


  @Override
  public void send(IMail mail) {
    Mail mailRequest = buildMail(mail);

    SendGrid sendGrid = new SendGrid(sendGridApiKey);
    Request request = new Request();

    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mailRequest.build());
      Response response = sendGrid.api(request);
      log.info(String.valueOf(response.getStatusCode()));
      log.info(response.getBody());
      log.info(String.valueOf(response.getHeaders()));
    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new ServiceException(ErrorMessage.SERVICE_MAIL_FAILURE.getMessage());
    }

  }

  @Override
  public MailService getService() {
    return MailService.SENDGRID;
  }

  private Mail buildMail(IMail mail) {
    Email from = new Email(fromEmail);
    String subject = mail.getSubject();
    Email to = new Email(mail.getTo());
    Content content = new Content(mail.getContentType(), mail.getContent());
    return new Mail(from, subject, to, content);
  }
}
