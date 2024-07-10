package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email {
	//pass: worq umoj zget cpro
	//email: thienthantfb@gmail.com
	
	public static void sendEmail(String toEmail, String message) {

		final String fromEmail = "thienthantfb@gmail.com";
		final String pass ="spin tsvb vtiw ejfq";
		//create authenticator
		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(fromEmail, pass);
			}
		};
		
		Properties pr = createProp(new Properties());
		//phiên làm việc 
		Session session = Session.getInstance(pr,authenticator);
		//gửi mail
		//Tạo một tin nhắn mới
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.addHeader("Content-type", "text/HTML; chartset= UTF-8");
			//người nhận
			msg.setFrom();
			//người gửi
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail,false));
			//Tiêu đề email

			msg.setSubject("XEM MÃ CODE  ĐÂY","UTF-8");
			//nội dung
			msg.setText("Mã code của bạn là: "+message, "UTF-8");

			//gửi mail
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public static Properties createProp(Properties prop) {
		Properties pr = prop;
		pr.put("mail.smtp.host", "smtp.gmail.com");//SMTP HOST
		pr.put("mail.smtp.port", "587");//STLS 587 SSL 465
		pr.put("mail.smtp.auth", "true");//thực hiện việc đăng nhập
		pr.put("mail.smtp.starttls.enable", "true");
		return pr;
	}
//	public static void main(String[] args) {
//
//		sendEmail("trungg16122003@gmail.com", "haha");
//	}
		
	

}
