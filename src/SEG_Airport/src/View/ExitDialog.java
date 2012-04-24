package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class ExitDialog extends JFrame{

	public static void main(String[] args) {
		new ExitDialog();
	}
	
	public ExitDialog() {
		
		setBounds(100, 100, 335, 158);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new MigLayout("", "[grow]", "[50,grow][][grow]"));
		
		JTextArea txtrYouHaveOne = new JTextArea();
		txtrYouHaveOne.setBackground(SystemColor.control);
		txtrYouHaveOne.setLineWrap(true);
		txtrYouHaveOne.setText("You have one or more unsaved changes, are you sure you wish to exit?");
		getContentPane().add(txtrYouHaveOne, "cell 0 0,alignx center,grow");
		
		JButton btnSave = new JButton("Save & Exit");
		getContentPane().add(btnSave, "flowx,cell 0 2");
		
		JButton btnOk = new JButton("Cancel");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JButton btnExitWithoutSaving = new JButton("Exit without saving");
		getContentPane().add(btnExitWithoutSaving, "cell 0 2");
		getContentPane().add(btnOk, "cell 0 2,alignx center");
		this.setVisible(true);
	}
	
}
