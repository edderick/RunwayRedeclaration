import javax.swing.JInternalFrame;
import javax.swing.UIManager;

public class DemoChildWindow extends JInternalFrame{
	
	public DemoChildWindow(){
		super("Child");
		setSize(200,200);
		setClosable(true);
		setMaximizable(true);
		setResizable(true);
		setVisible(true);
	}
	
}
