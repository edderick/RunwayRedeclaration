package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.SaveToXMLFile;
import View.MainFrame;

public class SaveObstacleListener implements ActionListener{

	MainFrame mf;
	
	public SaveObstacleListener(MainFrame mf) {
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			new SaveToXMLFile(mf.getObstacle());
			System.out.println("Saved Obstacle ");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
