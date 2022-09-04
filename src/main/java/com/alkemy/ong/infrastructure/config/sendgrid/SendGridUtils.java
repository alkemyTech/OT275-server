package com.alkemy.ong.infrastructure.config.sendgrid;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ServiceException;
import com.alkemy.ong.application.util.IMail;
import com.alkemy.ong.application.util.IMailSender;
import com.alkemy.ong.application.util.MailService;
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
public class SendGridUtils implements IMailSender {

  @Value("${app.email}")
  private String fromEmail;
  @Value("${sendgrid.api-key}")
  private String sendGridApiKey;


  @Override
  public void send(IMail mail) {
    try {
      Mail mailRequest = buildMail(mail);

      SendGrid sendGrid = new SendGrid(sendGridApiKey);
      Request request = new Request();

      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mailRequest.build());
      Response response = sendGrid.api(request);

      ensureStatusCodeIsDifferentTo202(response);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new ServiceException(ErrorMessage.SERVICE_MAIL_FAILURE.getMessage());
    }
  }

  private static void ensureStatusCodeIsDifferentTo202(Response response) {
    if (response.getStatusCode() != 202) {
      log.warn(String.valueOf(response.getStatusCode()));
      log.warn(response.getBody());
      throw new ServiceException(ErrorMessage.SERVICE_MAIL_FAILURE.getMessage());
    }
  }

  @Override
  public MailService getService() {
    return MailService.SENDGRID;
  }

  private Mail buildMail(IMail mail) throws IOException {
    Email from = new Email(fromEmail);
    String subject = mail.getSubject();
    Email to = new Email(mail.getTo());
    Content content = new Content(mail.getContentType(), mail.getContent());
    return new Mail(from, subject, to, content);
  }
}
