import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainFrame extends JFrame{

	  private static void setNativeLookAndFeel() {
		    try {
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    } catch(Exception e) {
		      System.out.println("Error setting native LAF: " + e);
		    }
		  }
	
	public static void main(String[] args){
		MainFrame mf = new MainFrame();
		mf.setContentPane(new DesktopPane());
		mf.getContentPane().setBackground(Color.LIGHT_GRAY);
		mf.getContentPane().add(new TopView());
		mf.getContentPane().add(new SideView());
		mf.getContentPane().add(new Sidebar());		
	}
	
	public MainFrame(){
		super("Main Frame");
		//Set the minimized size
		setSize(400,400);
		setNativeLookAndFeel();
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
}
