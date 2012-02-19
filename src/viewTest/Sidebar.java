import javax.swing.JFrame;
import java.awt.Rectangle; 
import java.awt.GraphicsEnvironment;

public class Sidebar extends ResetableFrame {

	public Sidebar(int x, int y) {
		super("Side Bar");

		Rectangle usableScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

		setSize(200, usableScreen.height - y);

		setLocation(x, y);
	
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		setVisible(true);
	}
}
