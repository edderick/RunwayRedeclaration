package View;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.AddressBook;
import Model.Contact;

public class AddressBookTest {

	@Test
	public void testSearchContactsByFirstName() {
		Contact expected = new Contact("First", "Last", "Email");
		AddressBook ab = new AddressBook();
		ab.addContact(expected);
		Contact actual = ab.searchContactsByFirstName("First").get(0);
		
		assertEquals(expected, actual);
	}

}
