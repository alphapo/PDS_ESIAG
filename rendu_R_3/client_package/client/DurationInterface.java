package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DurationInterface extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DurationInterface frame = new DurationInterface();
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
	public DurationInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(34, 11, 367, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Button btDuration = new Button("Enregistr\u00E9");
		btDuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// envoi au serveur
			}
		});
		btDuration.setBounds(222, 207, 70, 22);
		panel.add(btDuration);
		
		Label durationYear = new Label("Ann\u00E9e(s)");
		durationYear.setBounds(48, 37, 62, 22);
		panel.add(durationYear);
		
		Label durationMonth = new Label("Mois");
		durationMonth.setBounds(48, 93, 62, 22);
		panel.add(durationMonth);
		
		TextField txtDurationYear = new TextField();
		txtDurationYear.setBounds(146, 37, 84, 22);
		panel.add(txtDurationYear);
		
		TextField txtMonth = new TextField();
		txtMonth.setBounds(146, 93, 89, 22);
		panel.add(txtMonth);
	}
}
