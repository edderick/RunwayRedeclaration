package model;

public class PhysicalRunway {

	private String id;
	private Runway a, b;
	
	public PhysicalRunway(String identifier, Runway one, Runway two){	
		id = identifier;
		a = one;
		b = two;
	}

	public Runway getA() {
		return a;
	}

	public void setA(Runway a) {
		this.a = a;
	}

	public Runway getB() {
		return b;
	}

	public void setB(Runway b) {
		this.b = b;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
