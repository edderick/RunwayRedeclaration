package Model;

/**
 * Plain Ol' Data class to represent a contact
 * @author Kelvin
 */
public class Contact {
	
	//Member variables
	private String firstName;
	private String lastName;
	private String email;
	
	/**
	 * Default constructor for Contact Class
	 * @param firstName First name of contact
	 * @param lastName Last name of contact
	 * @param email Email address of contact
	 */
	public Contact(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	/**
	 * @param email The email address of the contact
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @param firstName The first name of the contact
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @param lastName The last name of the contact
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return The email address of the contact
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return The first name of the contact
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @return The last name of the contact
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @return The full name of the contact
	 */
	public String getFullName(){
		return getFirstName() + " " + getLastName();
	}
	
	/**
	 * @return The full name and the email of the contact
	 */
	public String toString(){
		return "Name: "+getFullName()+"\nEmail: "+getEmail()+"\n";
	}

}
