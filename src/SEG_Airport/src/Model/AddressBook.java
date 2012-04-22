package Model;

import java.util.ArrayList;

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
}
