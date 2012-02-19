import javax.swing.JFrame;

public class View extends JFrame {

	public View(int x, int y, int width, int height) {
		super();

		setSize(width, height);

		setLocation(x, y);

		setDefaultCloseOperation(HIDE_ON_CLOSE);

		setVisible(true);
	}


}
