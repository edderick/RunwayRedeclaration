package model;

public class PhysicalRunway {

	Runway a, b;
	
	public PhysicalRunway(Runway one, Runway two){	
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
	
	
	
}
