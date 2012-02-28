import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame{

	public static void main(String[] args){
		MainFrame mf = new MainFrame();
		
		
		JSplitPane b = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JSplitPane a = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		
		mf.getContentPane().add(a);
		a.setRightComponent(b);
		a.setLeftComponent(new JPanel());	
		
		TopView tv = new TopView();
		tv.setRunwayDimensions(100, 20, "08L", "27R");
		tv.setValues(0, 0, 0, 0, true, 50, 10, 10, 10);
	
		b.setTopComponent(tv);
		b.setBottomComponent(new JPanel());
		
		//hackedy hack :D
		mf.setVisible(true);
		
		a.setDividerLocation(0.5);
		b.setDividerLocation(0.5);
	
	}
	
	public MainFrame(){
		super("Main Frame");
		
		setSize(400,400);

		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	

}
