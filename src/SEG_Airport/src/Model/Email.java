package Model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class sends emails using a simplified interface to the JavaMail API.
 * An email is sent by first instantiating this class, then setting the body,
 * subject and adding recipients. Finally the send message is called.
 * @author Edward
 */
public class Email {
	
	private Properties props = System.getProperties(); 
	private Session session;
	
	//TODO: Maybe stored these settings in a file to be read in.
	private String host = "smtp.gmail.com";
	private String from = "seg2012gp9@gmail.com";
	private String password = "SasanMaleki";

	private String subject = "";
	private String body = "";
	private ArrayList<Contact> recipients = new ArrayList<Contact>();
	
	/**
	 * Default constructor for email class
	 * Sets up the properties for a message to be sent
	 */
	public Email(){
		
		try{
			loadSettings();
		}
		catch(IOException e){
			System.out.println("Reverting to default email settings");
			host = "smtp.gmail.com";
			from = "seg2012gp9@gmail.com";
			password = "SasanMaleki";
		}
		
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		session = Session.getDefaultInstance(props, null);
	}

	/**
	 * Loads settings from file
	 */
	private void loadSettings() throws IOException{
		FileInputStream fis = new FileInputStream("data/emailSettings");
		InputStreamReader in = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(in);

		String line = br.readLine();
		if (line != null) host = line;
		line = br.readLine();
		if (line != null) from = line;
		line = br.readLine();
		if (line != null) password = line;
	}
	
	/**
	 * Sets the subject of the email to be sent
	 * @param subject The subject of the email
	 */
	public void setSubject(String subject){
		this.subject = subject;
	}
	
	/**
	 * Sets the body text of the email to be sent
	 * @param subject The body text of the email
	 */
	public void setBody(String body){
		this.body = body;
	}
	
	/**
	 * @param contact Contact to add to recipients
	 */
	public void addRecipient(Contact contact){
		recipients.add(contact);
	}
	
	/**
	 * @param contacts List of contact to add to recipients
	 */
	public void addRecipients(List<Contact> contacts){
		for(Contact c : contacts){
			recipients.add(c);
		}
	}
	
	/**
	 * Sends the email
	 */
	public void send(){
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
		
			for( Contact c : recipients ) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(c.getEmail())); 
			}
			
			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (AuthenticationFailedException e){
			System.out.println("Unable to authenticate");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			//Might just throw these up to a higher power :p
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
