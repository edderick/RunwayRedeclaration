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
		b.setTopComponent(new JPanel());
		b.setBottomComponent(new JPanel());
	}
	
	public MainFrame(){
		super("Main Frame");

		setSize(400,400);

		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

}
