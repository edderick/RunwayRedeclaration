package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SelectViewListener implements ActionListener{

	JPanel panel, view;
	
	//TODO: Change the type of view to sommat else
	public SelectViewListener(JPanel panel, JPanel view){
		this.panel = panel;
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
