package model;

//testing svn jsjjsjsjsjsjsjsjsjsjsjsjjs

import java.util.ArrayList;

public class Airport {
	
	private ArrayList runways;
	
	public Airport(){
		
	}
	
	public void addRunway(Runway r){
		runways.add(r);
	}
	
	public void removeRunway(String n){
		for (Object r : runways) {
			if (((Runway) r).getName() == n){
				runways.remove(r);
			}
		}
	}

}
