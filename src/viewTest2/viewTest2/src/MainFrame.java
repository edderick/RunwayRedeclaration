import javax.swing.JFrame;

public class MainFrame extends JFrame{

	public static void main(String[] args){
		
		MainFrame mf = new MainFrame();
		mf.setContentPane(new DesktopPane());
		mf.getContentPane().add(new DemoChildWindow());
		mf.getContentPane().add(new DemoChildWindow());
		
		System.out.println("wtf?");
	}
	
	public MainFrame(){
		super("Main Frame");
		setSize(400, 400);
		setVisible(true);
	}
	
}
