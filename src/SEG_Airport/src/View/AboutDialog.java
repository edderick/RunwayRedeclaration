package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AboutDialog extends JFrame{


	public AboutDialog() {
		
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new MigLayout("", "[grow][][grow]", "[50][50][][][grow]"));
		
		JLabel lblThisProgramWas = new JLabel("This Program was made by:");
		getContentPane().add(lblThisProgramWas, "cell 1 0,alignx center,growy");
		
		JLabel lblSegGroup = new JLabel("SEG 2012 Group 09:");
		getContentPane().add(lblSegGroup, "cell 1 1,alignx center");
		
		JLabel lblEdwardSeabrookBrian = new JLabel("Edward Seabrook, Brian Yu, Kristian Eliott");
		getContentPane().add(lblEdwardSeabrookBrian, "cell 1 2,alignx center");
		
		JLabel lblKelvinChanOscar = new JLabel("Kelvin Chan, Oscar Mariani");
		getContentPane().add(lblKelvinChanOscar, "cell 1 3,alignx center");
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		getContentPane().add(btnOk, "cell 1 4,alignx center");
		this.setVisible(true);
	}

}
