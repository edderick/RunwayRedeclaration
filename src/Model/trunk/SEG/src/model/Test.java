package model;

public class Test {

	//Just a class to unit test functionality of the other classes
	
	public static void main(String[] args) {
		
		//create airport and 4 runways
		Airport a = new Airport("Gatwick");
		Runway r = new Runway("one", 888, 999, 777, 666, 0);
		Runway r1 = new Runway("two", 88, 99, 77, 66, 306);
		Runway r2 = new Runway("three", 8884, 9994, 7774, 6664, 0);
		Runway r3 = new Runway("four", 88568, 95699, 77567, 65666, 307);
		
		//add runways to airport
		a.addRunway(r);
		a.addRunway(r1);
		a.addRunway(r2);
		a.addRunway(r3);
		
		//iterate over the runways in the airport and print all values
		System.out.println("\n******************\nThis is the airport and its runways:\n" + a.getName());
		for (Object o : a.runways()) {
			System.out.println(((Runway) o).getName() +" "+ ((Runway) o).getASDA() +" "+ ((Runway) o).getLDA() +" "+ ((Runway) o).getTODA() +" "+ ((Runway) o).getTORA());
		}
		
		//remove runway with name "one"
		a.removeRunway("one");
		
		System.out.println("\nThis is the same airport with runway one removed:\n" + a.getName());
		//iterate over the runways in the airport and print all values
		for (Object o : a.runways()) {
			System.out.println(((Runway) o).getName() +" "+ ((Runway) o).getASDA() +" "+ ((Runway) o).getLDA() +" "+ ((Runway) o).getTODA() +" "+ ((Runway) o).getTORA());
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
			System.out.println(((Runway) o).getName() +" "+ ((Runway) o).getASDA() +" "+ ((Runway) o).getLDA() +" "+ ((Runway) o).getTODA() +" "+ ((Runway) o).getTORA());
		}
		
	}

}
