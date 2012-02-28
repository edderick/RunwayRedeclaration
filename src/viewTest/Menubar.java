import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;

public class Menubar extends JMenuBar {
    
    //Need to hold these in some kind of collection
    //Might use a hashMap... 
    
	//Idea: Instantiate using short scope variables
	//This allows ordering control
	//Then add to hashmap to allow external access

	private Map<String,JMenu> menuMap;
	private Map<String,JMenuItem> menuItemMap;

    public Menubar(){
        super();
        createMenuItems();    
    }


	public JMenu getMenu(String name){
		return menuMap.get(name);
	}
	

	private void createMenu(String text, String parent){
		JMenu m = new JMenu(text);
		JMenu p = getMenu(parent);
		if (p == null){
			add(m);
			menuMap.put(text, m);
		}
		else{
			p.add(m);
			menuMap.put(parent + "_" + text, m);
		}
	}

	private void createMenuItem(String text, String parent){
		JMenuItem mi = new JMenuItem(text);
		getMenu(parent).add(mi);
		menuItemMap.put(parent + "_" + text,mi);
	}

	private void createRadioButtonMenuItem(String text, String parent, ButtonGroup bg){
		JRadioButtonMenuItem mi = new JRadioButtonMenuItem(text);
		getMenu(parent).add(mi);
		menuItemMap.put(parent + "_" + text,mi);
		bg.add(mi);
	}


    private void createMenuItems(){
		menuItemMap = new HashMap<String, JMenuItem>();
		menuMap = new HashMap<String,JMenu>();

		createMenu("File", null);	
		createMenu("New", "File");	
		createMenuItem("Airport", "File_New");
		createMenuItem("Obstacle", "File_New");
		createMenuItem("Open Airport", "File");
		createMenuItem("Open Recent Airport", "File");
		createMenuItem("Open Obstacle", "File");
		createMenuItem("Open Recent Obstacle", "File");
		createMenuItem("Save Airport", "File");
		createMenuItem("Save Obstacle", "File");
		createMenuItem("Exit", "File");

		createMenu("Edit", null);
		createMenuItem("Select Runway", "Edit");
		createMenuItem("Runway", "Edit");
		createMenuItem("Airport", "Edit");
		createMenuItem("Obstacle", "Edit");
		createMenuItem("Obstacle Position", "Edit");
		
		createMenu("View", null);
		createMenu("Top Panel", "View");
		createMenu("Bottom Panel", "View");
		
		ButtonGroup bg1 = new ButtonGroup();
		createRadioButtonMenuItem("Top View", "View_Top Panel", bg1);
		createRadioButtonMenuItem("Side View", "View_Top Panel", bg1);
		createRadioButtonMenuItem("Calculations", "View_Top Panel", bg1);
		createRadioButtonMenuItem("None", "View_Top Panel", bg1);
		
		ButtonGroup bg2 = new ButtonGroup();
		createRadioButtonMenuItem("Top View", "View_Bottom Panel", bg2);
		createRadioButtonMenuItem("Side View", "View_Bottom Panel", bg2);
		createRadioButtonMenuItem("Calculations", "View_Bottom Panel", bg2);
		createRadioButtonMenuItem("None", "View_Bottom Panel", bg2);

		createMenu("Order Food", null);
		createMenuItem("Sorbet", "Order Food");

		createMenu("Tools", null);
		createMenuItem("Email", "Tools");
		createMenuItem("Address Book", "Tools");
	}

	public JMenuItem getMenuItem(String name){
		return menuItemMap.get(name);
	}
}

