package br.com.hotel.util;


import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import br.com.hotel.entity.MailJava;


public class EnviarEmail {

	public void senderMail(final MailJava mail) throws  UnsupportedEncodingException, MessagingException {
	        Properties properties = new Properties();
	        properties.setProperty("mail.transport.protocol", "smtp");
	        properties.setProperty("mail.host", mail.getSmtpHostMail());
	        properties.setProperty("mail.smtp.auth", mail.getSmtpAuth());
	        properties.setProperty("mail.smtp.starttls.enable", mail.getSmtpStarttls());
	        properties.setProperty("mail.smtp.port", mail.getSmtpPortMail());
	        properties.setProperty("mail.mime.charset", mail.getCharsetMail());
	        
	        //classe anonima que realiza a autenticação do usuario no servidor de email.
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(
	                                   mail.getUserMail(), mail.getPassMail()
	                 );
	            }
	        };
	 
	        // Cria a sessao passando as propriedades e a autenticação
	        Session session = Session.getDefaultInstance(properties, auth);
	        // Gera um log no console referente ao processo de envio
	        session.setDebug(true);
	 
	        //cria a mensagem setando o remetente e seus destinatários
	        Message msg = new MimeMessage(session);
	        //aqui seta o remetente
	        msg.setFrom(new InternetAddress(mail.getUserMail(), mail.getFromNameMail()));
	        boolean first = true;
	        
	        for (Map.Entry<String, String> map : mail.getToMailsUsers().entrySet()) {
	            if (first) {
	                //setamos o 1° destinatario
	                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(map.getKey(), map.getValue()));
	                first = false;
	            } else {
	                //setamos os demais destinatarios
	                msg.addRecipient(Message.RecipientType.CC, new InternetAddress(map.getKey(), map.getValue()));
	            }
	        }
	 
	        // Adiciona um Assunto a Mensagem
	        msg.setSubject(mail.getSubjectMail());
	 
	        // Cria o objeto que recebe o texto do corpo do email
	        MimeBodyPart textPart = new MimeBodyPart();
	        textPart.setContent(mail.getBodyMail(), mail.getTypeTextMail());
	 
	        // Monta a mensagem SMTP  inserindo o conteudo, texto e anexos
	        Multipart mps = new MimeMultipart();
	        for (int index = 0; index < mail.getFileMails().size(); index++) {
	 
	            // Cria um novo objeto para cada arquivo, e anexa o arquivo
	            MimeBodyPart attachFilePart = new MimeBodyPart();
	            FileDataSource fds =   new FileDataSource(mail.getFileMails().get(index));
	            attachFilePart.setDataHandler(new DataHandler(fds));
	            attachFilePart.setFileName(fds.getName());
	 
	            //adiciona os anexos da mensagem
	            mps.addBodyPart(attachFilePart, index);
	        }
	 
	        //adiciona o corpo texto da mensagem
	        mps.addBodyPart(textPart);
	 
	        //adiciona a mensagem o conteúdo texto e anexo
	        msg.setContent(mps);
	 
	        // Envia a Mensagem
	        Transport.send(msg);
	    }
	public void enviarEmailStringGmail(String para,String de,String hosst,String assunto,String mensagem,final String login,final String senha) throws MessagingException{
		// Recipient's email ID needs to be mentioned, example abcd@gmail.com.
	      String to = para;

	      // Sender's email ID needs to be mentioned web@gmail.com
	      String from = de;

	      // Assuming you are sending email from localhost example localhost
//	      String host = hosst;

	      // Get system properties
	      Properties properties = new Properties();
	      properties.put("mail.smtp.host","smtp.gmail.com");
	      properties.put("mail.smtp.socketFactory.port", "465");
	      properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	      properties.put("mail.smtp.auth","true");
	      properties.put("mail.smtp.port","465");  
	      

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties,
	    		  new javax.mail.Authenticator() {

	    	  protected PasswordAuthentication getPasswordAuthentication() {

	    	  return new PasswordAuthentication(login,senha);}});

	      
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	        		 InternetAddress.parse(to));

	         // Set Subject: header field This is the Subject Line!
	         message.setSubject(assunto);

	         // Now set the actual message
	         message.setText(mensagem);

	         // Send message
	         Transport.send(message);
	         System.out.println("mensagem enviada com sucesso");
	      
	   }
}
	
	

