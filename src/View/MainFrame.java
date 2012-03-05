import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame{

	public static void main(String[] args){
		MainFrame mf = new MainFrame();
		
		mf.setJMenuBar(new Menubar());

		JSplitPane b = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JSplitPane a = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		
		mf.getContentPane().add(a);
		a.setRightComponent(b);
		a.setLeftComponent(new JPanel());	
		
		TopView tv = new TopView();
		tv.setRunwayDimensions(100, 20, "08L", "27R");
		tv.setValues(80, 5, 40, 0, 73, 2, 15, 50, true, 76, 3, 9, 4);
	
		b.setTopComponent(tv);
		b.setBottomComponent(new JPanel());
		
		//hackedy hack :D
		mf.setVisible(true);
		
		a.setDividerLocation(0.5);
		b.setDividerLocation(0.5);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tv.setValues(40, 10, 20, 0, 25, 6, 10, 25, true, 32, 5, 4, 2);
		tv.repaint();
	
	}
	
	public MainFrame(){
		super("Main Frame");
		
		setSize(400,400);

		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
}
