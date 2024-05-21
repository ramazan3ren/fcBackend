package com.fc.ws.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fc.ws.configuration.FcProperties;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceFacility {

    @Autowired
    FcProperties fcProperties;
    JavaMailSenderImpl mailSender;

    @PostConstruct
    public JavaMailSenderImpl initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(fcProperties.getEmail().host());
        mailSender.setPort(fcProperties.getEmail().port());
        mailSender.setUsername(fcProperties.getEmail().username());
        mailSender.setPassword(fcProperties.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        return mailSender;
    }

    public void sendActivationEmail(String email, String activationToken, String facilityName) {
        this.mailSender = initialize();
        var activationUrl = fcProperties.getClient().host() + "/activation/" + activationToken;

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(fcProperties.getEmail().from());
            helper.setTo(email);
            helper.setSubject("FC Hesabınızı Şimdi Aktive Edin");

            String content = "<html dir=\"ltr\" lang=\"en\">" +
                    "" +
                    "  <head>" +
                    "    <meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\" />" +
                    "  </head>" +
                    "  <div style=\"display:none;overflow:hidden;line-height:1px;opacity:0;max-height:0;max-width:0\">FC Hesabınızı Şimdi Aktive Edin"
                    +
                    "  </div>" +
                    "" +
                    "  <body style=\"background-color:rgb(255,255,255);margin-top:auto;margin-bottom:auto;margin-left:auto;margin-right:auto;font-family:Montserrat\">"
                    +
                    "    <table align=\"center\" width=\"100%\" border=\"0\" cellPadding=\"0\" cellSpacing=\"0\" role=\"presentation\" style=\"max-width:465px;border-radius:0.25rem;margin-top:40px;margin-bottom:40px;margin-left:auto;margin-right:auto;padding:20px\">"
                    +
                    "      <tbody>" +
                    "        <tr style=\"width:100%\">" +
                    "          <td>" +
                    "            <table align=\"center\" width=\"100%\" border=\"0\" cellPadding=\"0\" cellSpacing=\"0\" role=\"presentation\" style=\"margin-top:32px\">"
                    +
                    "              <tbody>" +
                    "                <tr>" +
                    "                  <td><img alt=\"FC\" height=\"37\" src=\"https://i.pinimg.com/736x/9e/b7/65/9eb765ba9b4592232b345966fa1b7c5f.jpg\" style=\"display:block;outline:none;border:none;text-decoration:none;margin-top:0px;margin-bottom:0px;margin-left:auto;margin-right:auto\" width=\"40\" /></td>"
                    +
                    "                </tr>" +
                    "              </tbody>" +
                    "            </table>" +
                    "            <p style=\"font-size:14px;line-height:24px;margin:16px 0;color:rgb(0,0,0)\">Merhaba <!-- --> <span style=\"color:rgb(96,153,102);font-weight:700\">"
                    + facilityName + ",</span></p>"
                    +
                    "            <p style=\"font-size:14px;line-height:24px;margin:16px 0\">FC ailesine hoş geldiniz! Hesabınızı tam olarak aktive etmek ve platformumuzdaki avantajlardan yararlanmaya başlamak için lütfen aşağıdaki linke tıklayarak üyeliğinizi doğrulayın : </p>"
                    +
                    "            <table align=\"center\" width=\"100%\" border=\"0\" cellPadding=\"0\" cellSpacing=\"0\" role=\"presentation\">"
                    +
                    "              <tbody>" +
                    "                <tr>" +
                    "                  <td>" +
                    "                    <table align=\"center\" width=\"100%\" border=\"0\" cellPadding=\"0\" cellSpacing=\"0\" role=\"presentation\">"
                    +
                    "                      <tbody style=\"width:100%\">" +
                    "                        <tr style=\"width:100%\">" +
                    "                          <td align=\"right\" data-id=\"__react-email-column\"><img src=\"https://i.pinimg.com/736x/d8/65/fd/d865fd39b1e3252b9d0a7abf977718e1.jpg\" style=\"display:block;outline:none;border:none;text-decoration:none;border-radius:9999px\" width=\"50\" /></td>"
                    +
                    "                          <td align=\"center\" data-id=\"__react-email-column\"><img src=\"https://cdn0.iconfinder.com/data/icons/typicons-2/24/arrow-right-512.png\" style=\"display:block;outline:none;border:none;text-decoration:none\" width=\"24\" /></td>"
                    +
                    "                          <td align=\"left\" data-id=\"__react-email-column\"><img src=\"https://i.pinimg.com/736x/9e/b7/65/9eb765ba9b4592232b345966fa1b7c5f.jpg\" style=\"display:block;outline:none;border:none;text-decoration:none;border-radius:9999px\" width=\"50\" /></td>"
                    +
                    "                        </tr>" +
                    "                      </tbody>" +
                    "                    </table>" +
                    "                  </td>" +
                    "                </tr>" +
                    "              </tbody>" +
                    "            </table>" +
                    "            <table align=\"center\" width=\"100%\" border=\"0\" cellPadding=\"0\" cellSpacing=\"0\" role=\"presentation\" style=\"text-align:center;margin-top:32px;margin-bottom:32px\">"
                    +
                    "              <tbody>" +
                    "                <tr>" +
                    "                  <td><a style=\"line-height:100%;text-decoration:none;display:inline-block;max-width:100%;background-color:rgb(96,153,102);border-radius:0.25rem;color:rgb(255,255,255);font-size:12px;font-weight:600;text-decoration-line:none;text-align:center;padding-left:1.25rem;padding-right:1.25rem;padding-top:0.75rem;padding-bottom:0.75rem;padding:12px 20px 12px 20px\" target=\"_blank\" href=\""
                    + activationUrl
                    + "\"><span><!--[if mso]><i style=\"letter-spacing: 20px;mso-font-width:-100%;mso-text-raise:18\" hidden></i><![endif]--></span><span style=\"max-width:100%;display:inline-block;line-height:120%;mso-padding-alt:0px;mso-text-raise:9px\">FC Üyeliğimi Onaylıyorum</span><span><!--[if mso]><i style=\"letter-spacing: 20px;mso-font-width:-100%\" hidden></i><![endif]--></span></a></td>"
                    +
                    "                </tr>" +
                    "              </tbody>" +
                    "            </table>" +
                    "            <p style=\"font-size:14px;line-height:24px;margin:16px 0;color:rgb(0,0,0)\">Linke tıkladığınızda, hesabınızı doğrulayacak ve FC topluluğuna katılacaksınız. Bu adımı tamamladıktan sonra, takımlarınızı oluşturabilir, maçlarınızı düzenleyebilir ve futbol tutkusunu paylaşan birçok kişiyle etkileşimde bulunabilirsiniz.</p>"
                    +
                    "            <p style=\"font-size:14px;line-height:24px;margin:16px 0\">Eğer linke tıklamada herhangi bir sorun yaşarsanız veya herhangi bir yardıma ihtiyacınız olursa, lütfen bize ulaşmaktan çekinmeyin. Sizlere yardımcı olmaktan mutluluk duyarız.</p>"
                    +
                    "            <p style=\"font-size:14px;line-height:24px;margin:16px 0\">FC ekibi olarak, sizinle birlikte futbol tutkusunu paylaşmak için sabırsızlanıyoruz!</p>"
                    +
                    "            <p style=\"font-size:14px;line-height:24px;margin:16px 0\">Saygılarımızla, <br />FC Ekibi</p>"
                    +
                    "            <hr style=\"width:100%;border:none;border-top:1px solid #eaeaea;border-width:1px;border-style:solid;border-color:rgb(234,234,234);margin-top:24px;margin-bottom:24px;margin-left:0px;margin-right:0px\" />"
                    +
                    "            <p style=\"font-size:12px;line-height:24px;margin:16px 0;color:rgb(102,102,102)\">"
                    +
                    "" +
                    "              <head style=\"font-weight:700\">" +
                    "                <meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\" />Değerli Kullanıcı,"
                    +
                    "              </head> <br />FC olarak, müşteri güvenliği ve gizliliği bizim için en yüksek önceliktir. Hesabınızın güvenliği konusunda endişeleriniz varsa, size huzur ve güven sağlamak için buradayız. Platformumuzu kullanırken herhangi bir güvenlik sorunu veya şüphe yaşarsanız, lütfen bize ulaşın."
                    +
                    "            </p>" +
                    "          </td>" +
                    "        </tr>" +
                    "      </tbody>" +
                    "    </table>" +
                    "  </body>" +
                    "" +
                    "</html>";

            helper.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {

            e.printStackTrace();
        }

    }
}
