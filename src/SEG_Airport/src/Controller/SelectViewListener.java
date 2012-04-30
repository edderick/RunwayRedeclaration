package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;

import View.TopView;
import View.ViewPanel;

public class SelectViewListener implements ActionListener{

	JSplitPane splitPane;
	JComponent view;
	boolean top;
	JRadioButtonMenuItem menuItem;
	
	//TODO: Change the type of view to sommat else
	public SelectViewListener(JSplitPane splitPane, JComponent view, boolean top){
		this.splitPane = splitPane;
		this.view = view;
		this.top = top;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(top){
			if(splitPane.getLeftComponent() != null) ((ViewPanel)splitPane.getLeftComponent()).setVisible(false);
			splitPane.setLeftComponent(view);			
			if(splitPane.getLeftComponent() != null) ((ViewPanel)splitPane.getLeftComponent()).setVisible(true);
		}
		else {
			if(splitPane.getRightComponent() != null) ((ViewPanel)splitPane.getRightComponent()).setVisible(false);
			splitPane.setRightComponent(view);			
			if(splitPane.getRightComponent() != null) ((ViewPanel)splitPane.getRightComponent()).setVisible(true);
		}
	}
	
}
