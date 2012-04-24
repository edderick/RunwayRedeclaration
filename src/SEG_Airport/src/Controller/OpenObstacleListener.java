package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.LoadXMLFile;
import Model.Obstacle;
import View.MainFrame;

public class OpenObstacleListener implements ActionListener{

	MainFrame mf;
	
	public OpenObstacleListener(MainFrame mf){
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		LoadXMLFile lf = new LoadXMLFile();
		try {
			Obstacle o = lf.loadObstacle();
			mf.setObstacle(o);
			System.out.println("Obstacle Opened");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
 
}
