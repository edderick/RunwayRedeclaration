import javax.swing.JFrame;
import java.awt.Rectangle;

public class ResetableFrame extends JFrame {

	private Rectangle defaultAttributes;

	public ResetableFrame(String title){
		super(title);
		defaultAttributes = new Rectangle(0,0,0,0);
	}

	public void setCurrentAsDefault(){
		defaultAttributes = new Rectangle(getLocation(), getSize());
	}

	public void reset(){
		setLocation(defaultAttributes.getLocation());
		setSize(defaultAttributes.getSize());
	}

}
