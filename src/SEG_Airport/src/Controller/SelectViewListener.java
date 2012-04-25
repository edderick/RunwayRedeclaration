package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;

public class SelectViewListener implements ActionListener{

	JSplitPane pane;
	JComponent view;
	boolean top;
	JRadioButtonMenuItem menuItem;
	
	//TODO: Change the type of view to sommat else
	public SelectViewListener(JRadioButtonMenuItem menuItem, JSplitPane pane, JComponent view, boolean top){

		this.menuItem = menuItem;
		this.pane = pane;
		this.view = view;
		this.top = top;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(top){
			pane.setTopComponent(view);			
		}
		else {
			pane.setBottomComponent(view);
		}
		menuItem.setSelected(true);
	}

	
	
}
