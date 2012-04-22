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

/**
 * This class contains a list of contact details 
 * @author Kelvin
 */
public class AddressBook {

	private ArrayList<Contact> contacts;

	/**
	 * Default constructor for AddressBook Class
	 */
	public AddressBook() {
		contacts = new ArrayList<Contact>();
	}

	/**
	 * @return An ArrayList of all contacts
	 */
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	/**
	 * Adds a new contact to the Address Book
	 * @param newContact The contact to be added
	 */
	public void addContact(Contact newContact) {
		contacts.add(newContact);
	}

	/**
	 * @param firstName The first name of the contact to search for
	 * @return All contacts with the given first name
	 */
	public ArrayList<Contact> searchContactsByFirstName(String firstName) {
		return getContactsByName(firstName, true);
	}

	/**
	 * @param lastName The last name of the contact to search for
	 * @return All contacts with the given last name
	 */
	public ArrayList<Contact> searchContactsByLastName(String LastName) {
		return getContactsByName(LastName, false);
	}

	/**
	 * @param name Name to search for
	 * @param first If true search by first name, else search by last name
	 * @return
	 */
	private ArrayList<Contact> getContactsByName(String name, boolean first) {
		ArrayList<Contact> result = new ArrayList<Contact>();

		for (Contact c : contacts) {
			if(c.getFirstName().equals(name) && first) {
				result.add(c);
			}
			else if(c.getLastName().equals(name) && !first){
				result.add(c);
			}
		}
		return result;
	}

	/**
	 * Removes a contact from the Address book
	 * @param name Name of contact to remove
	 */
	public void removeContactByName(String name) {
		for(Contact c : contacts){
			if(c.getFullName().equals(name)){
				contacts.remove(c);
				break;
			}
		}
	}

	 /** Removes a contact from the Address book
	 * @param email Email Address of contact to remove
	 */
	public void removeContactByEmail(String email) {
		for(Contact c : contacts){
			if(c.getEmail().equals(email)){
				contacts.remove(c);
				break;
			}
		}
	}
	
	/**
	 * Sends an email
	 * ****************************** CONSIDER REFACTORING INTO OWN CLASS ***************************************************
	 * @param recipients A list of contacts to send the email to 
	 * @param to erm?????????????????????????????????????????????????????????????????????????????????????????????????????????
	 * @param subject The subject of the email to be sent
	 * @param text The body text of the email to be sent
	 */
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
