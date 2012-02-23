package model;

public class Test {

	//Just a class to unit test functionality of the other classes
	
	public static void main(String[] args) {
		
		Airport a = new Airport("JFK");
		Runway r = new Runway("one", 888, 999, 777, 666);
		Runway r1 = new Runway("two", 88, 99, 77, 66);
		Runway r2 = new Runway("three", 8884, 9994, 7774, 6664);
		Runway r3 = new Runway("four", 88568, 95699, 77567, 65666);
		
		a.addRunway(r);
		a.addRunway(r1);
		a.addRunway(r2);
		a.addRunway(r3);
		
		for (Object o : a.runways()) {
			System.out.println(((Runway) o).getName() +" "+ ((Runway) o).getASDA() +" "+ ((Runway) o).getLDA() +" "+ ((Runway) o).getTODA() +" "+ ((Runway) o).getTORA());
		}
		
		a.removeRunway("one");
		
		for (Object o : a.runways()) {
			System.out.println(((Runway) o).getName() +" "+ ((Runway) o).getASDA() +" "+ ((Runway) o).getLDA() +" "+ ((Runway) o).getTODA() +" "+ ((Runway) o).getTORA());
		}
		
		a.saveToXML();
		
		/*
		XMLFile x = new XMLFile();
		System.out.println(x.loadAirport());
		*/
	}

}
