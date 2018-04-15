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


    public void sendMail(String mailDestinataire, String messageToSend, String title){
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
            message.setSubject(title);
            message.setText(messageToSend+"\nMail : "+mailDestinataire);

            Transport.send(message);

            System.out.println("The mail sent !");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Fonction permettant de réalisé la fonction mot de passe oublié
     * @param mailDestinataire email de la personne qui a perdu son mot de passe
     * @param newPassword nouveau mot de passe, que la personne va recevoir par mail
     */
    public void mailingPasswordForgot(String mailDestinataire, String newPassword){
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
            message.setSubject("Site Rose Corail : Modification de mot de passe");
            message.setText("Voici votre nouveau mot de passe :\n"+newPassword+"\n \n Vous pouvez maintenant vous connecter avec ce nouveau mot de passe sur : www.rosecorail.com");

            Transport.send(message);

            System.out.println("The mail sent !");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette fonction envoit un mail à l'adresse destinatrice, avec le message et le nom de
     * la personne indiqués en paramètre. Cette fonction est utilisée pour la page
     * "Contact" qui permet de prendre contact avec l'entreprise Rose Corail
     * @param mail de la personne qui envoie le message
     * @param name le nom de la personne qui envoie le message
     * @param messageWrite le message que l'on veut transmettre
     */
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
