package View;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

/**
 * A dialog to show information about the authors of the application
 * @author Edward Seabrook
 */
public class AboutDialog extends JDialog{

	private static final long serialVersionUID = 1L;

	public AboutDialog() {
		setAlwaysOnTop(true);
		setTitle("About");

		setBounds(100, 100, 336, 232);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		getContentPane().setLayout(new MigLayout("", "[grow][][grow]", "[50][50][][][grow]"));

		JLabel lblThisProgramWas = new JLabel("This Program was made by:");
		getContentPane().add(lblThisProgramWas, "cell 1 0, alignx center, growy");

		JLabel lblSegGroup = new JLabel("SEG 2012 Group 09:");
		getContentPane().add(lblSegGroup, "cell 1 1, alignx center");

		JLabel lblEdwardSeabrookBrian = new JLabel("Edward Seabrook, Brian Yu, Kristian Eliott");
		getContentPane().add(lblEdwardSeabrookBrian, "cell 1 2,alignx center");

		JLabel lblKelvinChanOscar = new JLabel("Kelvin Chan, Oscar Mariani");
		getContentPane().add(lblKelvinChanOscar, "cell 1 3, alignx center");

		JButton btnOk = new JButton("Ok");

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});

		getContentPane().add(btnOk, "cell 1 4, alignx center");
		this.setVisible(true);
	}

}