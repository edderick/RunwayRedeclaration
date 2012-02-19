import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;

public class Menubar extends JMenuBar {
    
    //Need to hold these in some kind of collection
    //Might use a hashMap... 
    
    //Menus
    JMenu file;
    JMenu edit;
    JMenu windows;
    
    //Menu items
    JMenuItem exit;
	JMenuItem reset;
    
    //CheckBox menu items
    JCheckBoxMenuItem sideBar;
	JCheckBoxMenuItem sideView;
	JCheckBoxMenuItem topView;

    public Menubar(){
        super();
    
        createMenus();
        createMenuItems();    
       
    }

    private void createMenus(){
        file = new JMenu("File");
        add(file);

        edit = new JMenu("Edit");
        add(edit);

        windows = new JMenu("Windows");
        add(windows);
    }


    private void createMenuItems(){
        exit = new JMenuItem("Exit");
        file.add(exit);
	
		sideBar = new JCheckBoxMenuItem("Side Bar", true);
		windows.add(sideBar);

		sideView = new JCheckBoxMenuItem("Side View", true);
		windows.add(sideView);
		
		topView = new JCheckBoxMenuItem("Top View", true);
		windows.add(topView);

		windows.addSeparator();

		reset = new JMenuItem("Reset");
		windows.add(reset);

    }

}
