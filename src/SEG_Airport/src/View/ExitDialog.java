package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import Model.Saveable;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class ExitDialog extends JFrame{

	private static final long serialVersionUID = 1L;
	private List<Saveable> saves;
	
	public ExitDialog(List<Saveable> toSave) {
		
		saves = toSave;
		
		setBounds(100, 100, 335, 158);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new MigLayout("", "[grow]", "[][][grow][]"));
		
		JLabel lblMessage1 = new JLabel("You have one or more unsaved changes,");
		getContentPane().add(lblMessage1, "cell 0 0");
		
		JLabel lblNewLabel = new JLabel("are you sure you wish to exit?");
		getContentPane().add(lblNewLabel, "cell 0 1,alignx left");
		
		StringBuffer sb = new StringBuffer("Files to save: ");
		for(Saveable s : toSave){
			sb.append(s.getType());
			sb.append(" ");
		}
		
		JLabel lblToSave = new JLabel(sb.toString());
		getContentPane().add(lblToSave, "cell 0 2");
		
		
		JButton btnSave = new JButton("Save & Exit");
		getContentPane().add(btnSave, "flowx,cell 0 3");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Saveable s: saves){
					s.saveToXML();
				}
				dispose();
			}
		});
		
		JButton btnOk = new JButton("Cancel");
		getContentPane().add(btnOk, "cell 0 3,alignx center");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JButton btnExitWithoutSaving = new JButton("Exit without saving");		
		getContentPane().add(btnExitWithoutSaving, "cell 0 3");
		btnExitWithoutSaving.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});		
	
		setAlwaysOnTop(true);
		this.setVisible(true);
	}
	
}
