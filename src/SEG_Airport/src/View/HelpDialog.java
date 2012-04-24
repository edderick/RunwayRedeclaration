package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

import Controller.HelpHyperlinkListener;

public class HelpDialog extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JScrollPane leftScrollPane;
	private JScrollPane rightScrollPane;
	private JEditorPane navigation;
	private JEditorPane body;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpDialog frame = new HelpDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HelpDialog() {
		setTitle("Help");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.25);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		leftScrollPane = new JScrollPane();
		leftScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		splitPane.setLeftComponent(leftScrollPane);
		
		navigation = new JEditorPane();
		navigation.setEditable(false);
		navigation.setContentType("text/html");
		leftScrollPane.setViewportView(navigation);
		
		rightScrollPane = new JScrollPane();
		rightScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		splitPane.setRightComponent(rightScrollPane);
		
		body = new JEditorPane();
		body.setEditable(false);
		body.setContentType("text/html\r\n");
		rightScrollPane.setViewportView(body);
		
		try {
			
			body.setPage(new File("man/index.html").toURI().toURL());
			navigation.setPage(new File("man/nav.html").toURI().toURL());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		navigation.addHyperlinkListener(new HelpHyperlinkListener(body));
		
		
	}

}
