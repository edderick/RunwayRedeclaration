import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.util.HashMap;
import java.util.Map;

public class Menubar extends JMenuBar {
    
    //Need to hold these in some kind of collection
    //Might use a hashMap... 
    
	//Idea: Instantiate using short scope variables
	//This allows ordering control
	//Then add to hashmap to allow external access

	private Map<String,JMenu> menuMap;
	private Map<String,JMenuItem> menuItemMap;
	private Map<String,JCheckBoxMenuItem> checkBoxMenuItemMap;

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


    private void createMenuItems(){
		menuItemMap = new HashMap<String, JMenuItem>();
		checkBoxMenuItemMap = new HashMap<String, JCheckBoxMenuItem>();
		menuMap = new HashMap<String,JMenu>();

		createMenu("File", null);	
		createMenu("New", "File");	
		createMenuItem("Airport", "File_New");
		createMenuItem("Obstacle", "File_New");
		createMenuItem("Open Airport", "File");
		createMenuItem("Open Recent Airport", "File");
		createMenuItem("Open Obstacle", "File");
	}

	public JMenuItem getMenuItem(String name){
		return menuItemMap.get(name);
	}

	public JCheckBoxMenuItem getCheckBoxMenuItem(String name){
		return checkBoxMenuItemMap.get(name);
	}
}

