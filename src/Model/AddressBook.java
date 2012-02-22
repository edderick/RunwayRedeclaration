package model;

import java.util.ArrayList;

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
	
	public void sendEmails(ArrayList<Contact> recipients, RunwayWithObstacle rwo){
		//Waiting for implementation
	}

}
