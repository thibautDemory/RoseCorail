package hei.ProjetRoseCorail.entities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    public String mailDestinataire;

    public Mail(String mailDestinataire){
        mailDestinataire = this.mailDestinataire;
    }

    public void mailing(String mailDestinataire, String newPassword){
        final String username = "williamthibaut.evrarddemory@gmail.com";
        final String password = "motdepassedufutur";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("williamthibaut.evrarddemory@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailDestinataire));
            message.setSubject("Testing Subject");
            message.setText("Voici votre nouveau mot de passe :\n"+newPassword+"\n \n Vous pouvez maintenant vous connecter avec ce nouveau mot de passe sur : www.rosecorail.com");

            Transport.send(message);

            System.out.println("The mail sent !");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMailWithMessageAndName(String mail, String name, String messageWrite){
        final String username = "williamthibaut.evrarddemory@gmail.com";
        final String password = "motdepassedufutur";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(username));
            message.setSubject("Site rosecorail.com : Message de contact de "+name);
            message.setText(messageWrite+"\nMail : "+mail);

            Transport.send(message);

            System.out.println("The mail sent !");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
