package model;

public class Test {

	//Just a class to unit test functionality of the other classes
	
	public static void main(String[] args) {
		
		//create airport and 4 runways
		Airport a = new Airport("Heathrow");
		Runway r = new Runway("27R", 1, 2, 3, 4, 5);
		Runway r1 = new Runway("09L", 6, 7, 8, 9, 10);
		Runway r2 = new Runway("27L", 11, 12, 13, 14, 15);
		Runway r3 = new Runway("09R", 16, 17, 18, 19, 20);
		
		PhysicalRunway one = new PhysicalRunway("27R/09L", r, r1);
		PhysicalRunway two = new PhysicalRunway("27L/09R", r2, r3);
		
		//add physical runways to airport
		a.addPhysicalRunway(one);
		a.addPhysicalRunway(two);
		
		
		//iterate over the runways in the airport and print all values
		System.out.println("\n******************\nThis is the airport and its runways:\n" + a.getName());
		for (Object o : a.runways()) {
			System.out.println(((PhysicalRunway) o).getId() 
					+" "+ ((PhysicalRunway) o).getRunway(0).getName() 
					+" "+ ((PhysicalRunway) o).getRunway(0).getTORA(1)
					+" "+ ((PhysicalRunway) o).getRunway(0).getASDA(1)
					+" "+ ((PhysicalRunway) o).getRunway(0).getTODA(1)
					+" "+ ((PhysicalRunway) o).getRunway(0).getLDA(1)
	
					+" "+ ((PhysicalRunway) o).getRunway(1).getName()
					+" "+ ((PhysicalRunway) o).getRunway(1).getTORA(1)
					+" "+ ((PhysicalRunway) o).getRunway(1).getASDA(1)
					+" "+ ((PhysicalRunway) o).getRunway(1).getTODA(1)
					+" "+ ((PhysicalRunway) o).getRunway(1).getLDA(1)
					
					);
		}
		
		//remove runway with name "one"
		a.removePhysicalRunway("27R/09L");
		
		System.out.println("\nThis is the same airport with runway one removed:\n" + a.getName());
		//iterate over the runways in the airport and print all values
		for (Object o : a.runways()) {
			System.out.println(((PhysicalRunway) o).getId() 
					+" "+ ((PhysicalRunway) o).getRunway(0).getName() 
					+" "+ ((PhysicalRunway) o).getRunway(0).getTORA(1)
					+" "+ ((PhysicalRunway) o).getRunway(0).getASDA(1)
					+" "+ ((PhysicalRunway) o).getRunway(0).getTODA(1)
					+" "+ ((PhysicalRunway) o).getRunway(0).getLDA(1)
	
					+" "+ ((PhysicalRunway) o).getRunway(1).getName()
					+" "+ ((PhysicalRunway) o).getRunway(1).getTORA(1)
					+" "+ ((PhysicalRunway) o).getRunway(1).getASDA(1)
					+" "+ ((PhysicalRunway) o).getRunway(1).getTODA(1)
					+" "+ ((PhysicalRunway) o).getRunway(1).getLDA(1)
					
					);
		}
		
		//save airport a to xml file
		a.saveToXML();
		
		//create a second airport and a loadxmlfile object
		//load a file
		Airport a2 = null;
		LoadXMLFile lf = new LoadXMLFile();
		try {
			a2 = lf.loadFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\nThis is the loaded airport:\n" + a.getName());
		//iterate over the runways in the loaded airport and print all values
		for (Object o : a2.runways()) {
			System.out.println(((PhysicalRunway) o).getId() 
					+" "+ ((PhysicalRunway) o).getRunway(0).getName() 
					+" "+ ((PhysicalRunway) o).getRunway(0).getTORA(1)
					+" "+ ((PhysicalRunway) o).getRunway(0).getASDA(1)
					+" "+ ((PhysicalRunway) o).getRunway(0).getTODA(1)
					+" "+ ((PhysicalRunway) o).getRunway(0).getLDA(1)
	
					+" "+ ((PhysicalRunway) o).getRunway(1).getName()
					+" "+ ((PhysicalRunway) o).getRunway(1).getTORA(1)
					+" "+ ((PhysicalRunway) o).getRunway(1).getASDA(1)
					+" "+ ((PhysicalRunway) o).getRunway(1).getTODA(1)
					+" "+ ((PhysicalRunway) o).getRunway(1).getLDA(1)
					
					);
		}
		
	}

}
