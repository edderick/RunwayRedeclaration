package Model;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class AddressBook {

	private ArrayList<Contact> contacts;

	public AddressBook() {
		contacts = new ArrayList<Contact>();
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void addContact(Contact newContact) {
		contacts.add(newContact);
	}

	public ArrayList<Contact> searchContactsByFirstName(String firstName) {
		return getContactsByName(firstName, true);
	}

	public ArrayList<Contact> searchContactsByLastName(String LastName) {
		return getContactsByName(LastName, false);
	}

	private ArrayList<Contact> getContactsByName(String name, boolean first) {
		ArrayList<Contact> result = new ArrayList<Contact>();
		Contact tempC;
		if (first) {
			for (int i = 0; i < contacts.size(); i++) {
				tempC = contacts.get(i);
				if (tempC.getFirstName().compareTo(name) == 0) {
					result.add(tempC);
				}
			}
		} else {
			for (int i = 0; i < contacts.size(); i++) {
				tempC = contacts.get(i);
				if (tempC.getLastName().compareTo(name) == 0) {
					result.add(tempC);
				}
			}
		}
		return result;
	}

	public void removeContactByName(String name) {
		Contact tempC;
		for (int i = 0; i < contacts.size(); i++) {
			tempC = contacts.get(i);
			if (tempC.getFullName().compareTo(name) == 0) {
				contacts.remove(i);
				break;
			}
		}
	}

	public void removeContactByEmail(String email) {
		Contact tempC;
		for (int i = 0; i < contacts.size(); i++) {
			tempC = contacts.get(i);
			if (tempC.getEmail().compareTo(email) == 0) {
				contacts.remove(i);
				break;
			}
		}
	}
	
	public static void sendEmails(ArrayList<Contact> recipients, String to, String subject, String text){
		String host = "smtp.gmail.com";
	    String from = "abc";     //username
	    String pass = "abcxyz"; //password
	    Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", pass);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");

	    Session session = Session.getDefaultInstance(props, null);
	    MimeMessage message = new MimeMessage(session);
	    try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[recipients.size()];

		    // To get the array of addresses
		    for( int i=0; i < recipients.size(); i++ ) {
		        toAddress[i] = new InternetAddress(recipients.get(i).getEmail());
		    }
		    System.out.println(Message.RecipientType.TO);

		    for( int i=0; i < toAddress.length; i++) {
		        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		    }
		    message.setSubject(subject);
		    message.setText(text);
		    Transport transport = session.getTransport("smtp");
		    transport.connect(host, from, pass);
		    transport.sendMessage(message, message.getAllRecipients());
		    transport.close();
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
