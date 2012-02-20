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
    
        createMenus();
        createMenuItems();    
       
    }

    private void createMenus(){
        menuMap = new HashMap<String,JMenu>();

		//Might be neater to implement this as file.toString()	
	
		JMenu file = new JMenu("File");
        add(file);
        menuMap.put("File", file);

		JMenu edit = new JMenu("Edit");
        add(edit);
		menuMap.put("Edit", edit);

        JMenu windows = new JMenu("Windows");
        add(windows);
		menuMap.put("Windows", windows);
    }

	public JMenu getMenu(String name){
		return menuMap.get(name);
	}
		

    private void createMenuItems(){
        menuItemMap = new HashMap<String, JMenuItem>();
		checkBoxMenuItemMap = new HashMap<String, JCheckBoxMenuItem>();

		JMenuItem exit = new JMenuItem("Exit");
        getMenu("File").add(exit);
		menuItemMap.put("Exit", exit);

	
		JCheckBoxMenuItem sideBar = new JCheckBoxMenuItem("Side Bar", true);
		getMenu("Windows").add(sideBar);
		checkBoxMenuItemMap.put("Side Bar", sideBar);

		JCheckBoxMenuItem sideView = new JCheckBoxMenuItem("Side View", true);
		getMenu("Windows").add(sideView);
		checkBoxMenuItemMap.put("Side View", sideView);

		JCheckBoxMenuItem topView = new JCheckBoxMenuItem("Top View", true);
		getMenu("Windows").add(topView);
		checkBoxMenuItemMap.put("Top View", topView);

		getMenu("Windows").addSeparator();

		JMenuItem reset = new JMenuItem("Reset");
		getMenu("Windows").add(reset);
		menuItemMap.put("Reset", reset);

    }

	public JMenuItem getMenuItem(String name){
		return menuItemMap.get(name);
	}

	public JCheckBoxMenuItem getCheckBoxMenuItem(String name){
		return checkBoxMenuItemMap.get(name);
	}
}
