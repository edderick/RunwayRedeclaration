import javax.swing.JFrame;

public class Toolbar extends JFrame {

    public Toolbar() {
        super("Program Name");
    
        //Set the menu bar to fill the screen 
        setSize(getToolkit().getScreenSize().width, 50);

        setJMenuBar(new Menubar());

	setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static void main(String[] args){

        Toolbar tb = new Toolbar();
        Sidebar sb = new Sidebar();
	TopView tv = new TopView();
	SideView sv = new SideView();


    }
    
}
