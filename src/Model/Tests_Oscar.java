package Model;

import java.util.ArrayList;

public class Tests_Oscar {

	/**
	 * Test cases for the Model
	 * @author Oscar Mariani
	 */
	public static void main(String[] args) {
		
		/*
		 * ********
		 * Test 0. 
		 * ******** 
		 * Step 1: Instantiate an Airport.
		 * Step 2: Instantiate 2 PhysicalRunways.
		 * Step 3: Set values to distanceAwayFromThreshold and distanceAwayFromCenterline on each PhysicalRunway object.
		 * Step 4: Add the PhysicalRunway objects to the Airport object.
		 * Step 5: Print all values.
		 */
		
		System.out.println("************\nTEST 0\n************");
		// Step 1.
		Airport airport0 = new Airport("Heathrow");
		Runway r = new Runway("27R", 1, 2, 3, 4, 5);
		Runway r1 = new Runway("09L", 6, 7, 8, 9, 10);
		Runway r2 = new Runway("27L", 11, 12, 13, 14, 15);
		Runway r3 = new Runway("09R", 16, 17, 18, 19, 20);
		
		// Steps 2 & 3.
		PhysicalRunway one = new PhysicalRunway("27R/09L", r, r1);
		one.setDistanceAwayFromThreshold(78);
		one.setDistanceAwayFromCenterLine(657);
		
		PhysicalRunway two = new PhysicalRunway("27L/09R", r2, r3);
		two.setDistanceAwayFromThreshold(78);
		two.setDistanceAwayFromCenterLine(657);
				
		// Step 4.
		airport0.addPhysicalRunway(one);
		airport0.addPhysicalRunway(two);
		
		// Step 5.
		System.out.println("\nThis is the loaded airport:\n" + airport0.getName());
		//iterate over the runways and physical runways in the loaded airport and print all values
		for (Object o : airport0.getPhysicalRunways()) {
			System.out.println(((PhysicalRunway) o).getId()
					+"\n resa: "+ ((PhysicalRunway) o).getRESA()
					+"\n stopway: "+ ((PhysicalRunway) o).getStopway()
					+"\n blast: "+ ((PhysicalRunway) o).getBlastAllowance()
					+"\n stripWidth: "+ ((PhysicalRunway) o).getRunwayStripWidth()
					+"\n clearedWidth: "+ ((PhysicalRunway) o).getClearedAndGradedWidth()
					+"\n distanceFromThreshold: "+ ((PhysicalRunway) o).getDistanceAwayFromThreshold()
					+"\n distanceFromCenterline: "+ ((PhysicalRunway) o).getDistanceAwayFromCenterLine()
					+"\n angleOfSlope: "+ ((PhysicalRunway) o).getAngleOfSlope()
					
					
					+"\n runway0 name: "+ ((PhysicalRunway) o).getRunway(0).getName() 
					+"\n runway0 tora: "+ ((PhysicalRunway) o).getRunway(0).getTORA(1)
					+"\n runway0 asda: "+ ((PhysicalRunway) o).getRunway(0).getASDA(1)
					+"\n runway0 toda: "+ ((PhysicalRunway) o).getRunway(0).getTODA(1)
					+"\n runway0 lda: "+ ((PhysicalRunway) o).getRunway(0).getLDA(1)
					+"\n runway0 dt: "+ ((PhysicalRunway) o).getRunway(0).getDisplacedThreshold(Runway.DEFAULT)
	
					+"\n runway1 name: "+ ((PhysicalRunway) o).getRunway(1).getName()
					+"\n runway1 tora: "+ ((PhysicalRunway) o).getRunway(1).getTORA(1)
					+"\n runway1 asda: "+ ((PhysicalRunway) o).getRunway(1).getASDA(1)
					+"\n runway1 toda: "+ ((PhysicalRunway) o).getRunway(1).getTODA(1)
					+"\n runway1 lda: "+ ((PhysicalRunway) o).getRunway(1).getLDA(1)
					+"\n runway1 dt: "+ ((PhysicalRunway) o).getRunway(0).getDisplacedThreshold(Runway.DEFAULT)
					
					);
		}
		
		
		
		/*
		 * ********
		 * Test 1. 
		 * ******** 
		 * Step 1: Save an Airport to an XML file.
		 * Step 2: Open the file saved and compare values with the expected ones.
		 */
		System.out.println("************\nTEST 1\n************");
		
		airport0.saveToXML();
		
		
		
		/*
		 * ********
		 * Test 2. 
		 * ******** 
		 * Step 1: Load an Airport from an XML file.
		 * Step 2: Print to stdout the values of the loaded airport 
		 * 			and compare them with the original ones.
		 * Expected output: same values as in Aiport airport0.
		 * 
		 */
		System.out.println("************\nTEST 2\n************");
		
		Airport airport1 = null;
		
		//Step1
		LoadXMLFile loadFile = new LoadXMLFile();
		try {
			airport1 = loadFile.loadAirport();
		} catch (Exception e) {
			System.out.println("Error: corrupted file");
			e.printStackTrace();
		}
		
		//Step2
		System.out.println("\nThis is the loaded airport:\n" + airport1.getName());
		//iterate over the runways and physical runways in the loaded airport and print all values
		
		for (Object o : airport1.getPhysicalRunways()) {
			System.out.println(((PhysicalRunway) o).getId()
					+"\n resa: "+ ((PhysicalRunway) o).getRESA()
					+"\n stopway: "+ ((PhysicalRunway) o).getStopway()
					+"\n blast: "+ ((PhysicalRunway) o).getBlastAllowance()
					+"\n stripWidth: "+ ((PhysicalRunway) o).getRunwayStripWidth()
					+"\n clearedWidth: "+ ((PhysicalRunway) o).getClearedAndGradedWidth()
					+"\n distanceFromThreshold: "+ ((PhysicalRunway) o).getDistanceAwayFromThreshold()
					+"\n distanceFromCenterline: "+ ((PhysicalRunway) o).getDistanceAwayFromCenterLine()
					+"\n angleOfSlope: "+ ((PhysicalRunway) o).getAngleOfSlope()
					
					
					+"\n runway0 name: "+ ((PhysicalRunway) o).getRunway(0).getName() 
					+"\n runway0 tora: "+ ((PhysicalRunway) o).getRunway(0).getTORA(1)
					+"\n runway0 asda: "+ ((PhysicalRunway) o).getRunway(0).getASDA(1)
					+"\n runway0 toda: "+ ((PhysicalRunway) o).getRunway(0).getTODA(1)
					+"\n runway0 lda: "+ ((PhysicalRunway) o).getRunway(0).getLDA(1)
					+"\n runway0 dt: "+ ((PhysicalRunway) o).getRunway(0).getDisplacedThreshold(Runway.DEFAULT)
	
					+"\n runway1 name: "+ ((PhysicalRunway) o).getRunway(1).getName()
					+"\n runway1 tora: "+ ((PhysicalRunway) o).getRunway(1).getTORA(1)
					+"\n runway1 asda: "+ ((PhysicalRunway) o).getRunway(1).getASDA(1)
					+"\n runway1 toda: "+ ((PhysicalRunway) o).getRunway(1).getTODA(1)
					+"\n runway1 lda: "+ ((PhysicalRunway) o).getRunway(1).getLDA(1)
					+"\n runway1 dt: "+ ((PhysicalRunway) o).getRunway(0).getDisplacedThreshold(Runway.DEFAULT)
					
					);
		}
		
		/*
		 * ********
		 * Test 3. 
		 * ******** 
		 * Step 1: Load an Airport from a corrupted XML file (mistakes in the syntax)
		 * Expected output: Exception thrown and "Error: corrupted file" in stdout
		 */
		System.out.println("************\nTEST 3\n************");
		
		@SuppressWarnings("unused")
		Airport airport2 = null;
		LoadXMLFile loadFile1 = new LoadXMLFile();
		try {
			airport2 = loadFile1.loadAirport();
		} catch (Exception e) {
			System.out.println("Error: corrupted file");
			e.printStackTrace();
		}
		
		/*
		 * ********
		 * Test 4. 
		 * ******** 
		 * Step 1: Load an Airport from an XML file without prompting a dialog box.
		 * Step 2: Print to stdout the values of the loaded Airport 
		 * 			and compare them with the original ones.
		 * Expected output: same values as in Airport airport1.
		 */
		
		System.out.println("************\nTEST 4\n************");
		
		//Step 1.
		LoadXMLFile lof3 = new LoadXMLFile();
		Airport airport3 = lof3.silentLoadAirport("/Users/oscarmariani/Desktop/Airport.xml");
		
		// Step 2.
		System.out.println("\nThis is the loaded airport:\n" + airport3.getName());
		//iterate over the runways and physical runways in the loaded airport and print all values
		
		for (Object o : airport3.getPhysicalRunways()) {
			System.out.println(((PhysicalRunway) o).getId()
					+"\n resa: "+ ((PhysicalRunway) o).getRESA()
					+"\n stopway: "+ ((PhysicalRunway) o).getStopway()
					+"\n blast: "+ ((PhysicalRunway) o).getBlastAllowance()
					+"\n stripWidth: "+ ((PhysicalRunway) o).getRunwayStripWidth()
					+"\n clearedWidth: "+ ((PhysicalRunway) o).getClearedAndGradedWidth()
					+"\n distanceFromThreshold: "+ ((PhysicalRunway) o).getDistanceAwayFromThreshold()
					+"\n distanceFromCenterline: "+ ((PhysicalRunway) o).getDistanceAwayFromCenterLine()
					+"\n angleOfSlope: "+ ((PhysicalRunway) o).getAngleOfSlope()
					
					
					+"\n runway0 name: "+ ((PhysicalRunway) o).getRunway(0).getName() 
					+"\n runway0 tora: "+ ((PhysicalRunway) o).getRunway(0).getTORA(1)
					+"\n runway0 asda: "+ ((PhysicalRunway) o).getRunway(0).getASDA(1)
					+"\n runway0 toda: "+ ((PhysicalRunway) o).getRunway(0).getTODA(1)
					+"\n runway0 lda: "+ ((PhysicalRunway) o).getRunway(0).getLDA(1)
					+"\n runway0 dt: "+ ((PhysicalRunway) o).getRunway(0).getDisplacedThreshold(Runway.DEFAULT)
	
					+"\n runway1 name: "+ ((PhysicalRunway) o).getRunway(1).getName()
					+"\n runway1 tora: "+ ((PhysicalRunway) o).getRunway(1).getTORA(1)
					+"\n runway1 asda: "+ ((PhysicalRunway) o).getRunway(1).getASDA(1)
					+"\n runway1 toda: "+ ((PhysicalRunway) o).getRunway(1).getTODA(1)
					+"\n runway1 lda: "+ ((PhysicalRunway) o).getRunway(1).getLDA(1)
					+"\n runway1 dt: "+ ((PhysicalRunway) o).getRunway(0).getDisplacedThreshold(Runway.DEFAULT)
					
					);
		}		
		
		
		/*
		 * ********
		 * Test 5. 
		 * ******** 
		 * Step 1: Instantiate an Obstacle.
		 * Step 2: Set its width and length values to random values.
		 * Step 3: Print its fields values.
		 * Expected output: Same values as entered.
		 */
		
		System.out.println("************\nTEST 5\n************");
		
		// Step 1.
		Obstacle obstacle = new Obstacle("boeing 747", 56.0);
		
		// Step 2.
		obstacle.setLength(40.0);
		obstacle.setWidth(50.0);
		
		// Step 3.
		System.out.println("Obstacle " + obstacle.getName() + "\nheigth: " + obstacle.getHeight()
				+ "\nlength: " + obstacle.getLength() + "\nwidth: " + obstacle.getWidth());
		
		
		/*
		 * ********
		 * Test 6. 
		 * ******** 
		 * Step 1: Save previous Obstacle to XML.
		 * Step 2: Open the file saved and compare values with the expected ones.
		 */
		
		System.out.println("************\nTEST 6\n************");
		
		// Step 1.
		obstacle.saveToXML();
		
		
		/*
		 * ********
		 * Test 7. 
		 * ******** 
		 * Step 1: Load an Obstacle from an XML file.
		 * Step 2: Print to stdout the values of the loaded Obstacle 
		 * 			and compare them with the original ones.
		 * Expected output: same values as in Obstacle obstacle.
		 */
		
		System.out.println("************\nTEST 7\n************");
		
		//Step 1.
		LoadXMLFile lof = new LoadXMLFile();
		Obstacle obstacle1 = null;
		
		try {
			obstacle1 = lof.loadObstacle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Step 2.
		System.out.println("Obstacle " + obstacle1.getName() + "\nheigth: " + obstacle1.getHeight()
				+ "\nlength: " + obstacle1.getLength() + "\nwidth: " + obstacle1.getWidth());
		
		
		/*
		 * ********
		 * Test 8. 
		 * ******** 
		 * Step 1: Load an Obstacle from an XML file without prompting a dialog box.
		 * Step 2: Print to stdout the values of the loaded Obstacle 
		 * 			and compare them with the original ones.
		 * Expected output: same values as in Obstacle obstacle.
		 */
		
		System.out.println("************\nTEST 8\n************");
		
		//Step 1.
		LoadXMLFile lof1 = new LoadXMLFile();
		Obstacle obstacle2 = lof1.silentLoadObstacle("/Users/oscarmariani/Desktop/obstacle.xml");
		
		// Step 2.
		System.out.println("Obstacle " + obstacle2.getName() + "\nheigth: " + obstacle2.getHeight()
				+ "\nlength: " + obstacle2.getLength() + "\nwidth: " + obstacle2.getWidth());
		
		
		
		
		/*
		 * ********
		 * Test 9. 
		 * ******** 
		 * Step 1: Instantiate two Contacts.
		 * Step 2: Create an ArrayList<Contact> and add both contacts to it
		 * Step 3: Print fields of all contacts in the ArrayList
		 * Expected output: Same values as entered.
		 */
		
		System.out.println("************\nTEST 9\n************");
		
		// Step 1.
		Contact contact1 = new Contact("Oscar", "Mariani", "mariani.oscar@gamil.com");
		Contact contact2 = new Contact("bob", "squarepants", "squarepants@gmail.com");
		
		// Step 2.
		ArrayList<Contact> contactsList = new ArrayList<Contact>();
		contactsList.add(contact1);
		contactsList.add(contact2);
		
		// Step 3.
		for (Contact c : contactsList){
			System.out.println("Contact: " + c.getFirstName() + " " + c.getLastName() + " - " + c.getEmail());
		}
		
		
		
		/*
		 * ********
		 * Test 10. 
		 * ******** 
		 * Step 1: Save previous list of Contacts to XML.
		 * Step 2: Open the file saved and compare values with the expected ones.
		 */
		
		System.out.println("************\nTEST 10\n************");
		
		// Step 1.
		try {
			@SuppressWarnings("unused")
			SaveToXMLFile saveTo = new SaveToXMLFile(contactsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/*
		 * ********
		 * Test 11. 
		 * ******** 
		 * Step 1: Load a list of Contacts from an XML file.
		 * Step 2: Print to stdout the values of the loaded Contacts 
		 * 			and compare them with the original ones.
		 * Expected output: same values as in ArrayList<Contact> contactsList.
		 */
		
		System.out.println("************\nTEST 11\n************");
		
		//Step 1.
		ArrayList<Contact> contactsList2 = null;
		LoadXMLFile lof2 = new LoadXMLFile();
		try {
			contactsList2 = lof2.loadContacts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Step 2.
		for (Contact c : contactsList2){
			System.out.println("Contact: " + c.getFirstName() + " " + c.getLastName() + " - " + c.getEmail());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
