package com.agito.choom.service.event;

import com.agito.choom.endpoint.event.model.ImageUploaded;
import com.agito.choom.mail.Email;
import com.agito.choom.mail.Mailer;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import java.util.List;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageUploadedService implements Consumer<ImageUploaded> {
  private final Mailer mailer;

  @Override
  @SneakyThrows
  public void accept(ImageUploaded imageUploaded) {
    sendConfirmationEmailToUser(imageUploaded);
  }

  public void sendConfirmationEmailToUser(ImageUploaded imageUploaded) throws AddressException {
    var image = imageUploaded.getImage().filename();
    var to = imageUploaded.getImage().email();
    var subject = "Confirmation Email";
    var htmlBody =
        """
                <html>
  <body>
    <p>Dear Ms or Mr,</p>
    <p>Your image has been succesfully uploaded.</p>
    <p>Please find the image download url below:</p>
       <p><a href="%s" style="background-color: #3498db; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;">Download your certificate (PDF)</a></p>
       <p><small>This link is valid for 24 hours.</small></p>
    <p>Thank you!</p>
    <p>Best regards,</p>
    <p>The Team</p>
  </body>
</html>
"""
            .formatted(image);
    var InternetAddress = new InternetAddress(to);
    var email = new Email(InternetAddress, List.of(), List.of(), subject, htmlBody, List.of());
    mailer.accept(email);
  }
}
