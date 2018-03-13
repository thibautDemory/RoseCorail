package hei.ProjetRoseCorail.entities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    private String mailDestinataire;

    public Mail(String mailDestinataire){
        mailDestinataire = this.mailDestinataire;
    }

    public void mailing(){
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
                    InternetAddress.parse("thibaut.demory@hei.yncrea.fr"));
            message.setSubject("Testing Subject");
            message.setText("Tu me peux dire si ça marche, merci ! cordialement ! tout ça tout ça !");

            Transport.send(message);

            System.out.println("The mail sent !");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
