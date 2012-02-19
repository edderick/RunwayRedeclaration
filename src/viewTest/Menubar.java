import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menubar extends JMenuBar {
    
    //Need to hold these in some kind of collection
    //Might use a hashMap... 
    JMenu file;
    JMenu edit;
    JMenu windows;
    
    JMenuItem exit;


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
    }


}
