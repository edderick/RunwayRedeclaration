package Model;

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
					+"\n resa: "+ ((PhysicalRunway) o).getRESA(0)
					+"\n stopway: "+ ((PhysicalRunway) o).getStopway(0)
					+"\n blast: "+ ((PhysicalRunway) o).getBlastAllowance(0)
					+"\n stripWidth: "+ ((PhysicalRunway) o).getRunwayStripWidth()
					+"\n clearedWidth: "+ ((PhysicalRunway) o).getClearedAndGradedWidth()
					+"\n distanceFromThreshold: "+ ((PhysicalRunway) o).getDistanceAwayFromThreshold()
					+"\n distanceFromCenterline: "+ ((PhysicalRunway) o).getDistanceAwayFromCenterLine()
					+"\n angleOfSlope: "+ ((PhysicalRunway) o).getAngleOfSlope(0)
					
					
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
					+"\n resa: "+ ((PhysicalRunway) o).getRESA(0)
					+"\n stopway: "+ ((PhysicalRunway) o).getStopway(0)
					+"\n blast: "+ ((PhysicalRunway) o).getBlastAllowance(0)
					+"\n stripWidth: "+ ((PhysicalRunway) o).getRunwayStripWidth()
					+"\n clearedWidth: "+ ((PhysicalRunway) o).getClearedAndGradedWidth()
					+"\n distanceFromThreshold: "+ ((PhysicalRunway) o).getDistanceAwayFromThreshold()
					+"\n distanceFromCenterline: "+ ((PhysicalRunway) o).getDistanceAwayFromCenterLine()
					+"\n angleOfSlope: "+ ((PhysicalRunway) o).getAngleOfSlope(0)
					
					
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
		Airport airport2 = null;
		LoadXMLFile loadFile1 = new LoadXMLFile();
		try {
			airport2 = loadFile1.loadAirport();
		} catch (Exception e) {
			System.out.println("Error: corrupted file");
			e.printStackTrace();
		}
		
		
		
		
		
		
		

	}

}
