package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import database.DataBase;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Algo1 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2978828103662475845L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Algo1(DataBase database) {
		this.setSize(600, 300);
		setLayout(null);

		JPanel AlgoPanel = new JPanel();
		AlgoPanel.setBounds(10, 11, 580, 278);
		add(AlgoPanel);
		AlgoPanel.setLayout(null);

		JLabel MacLabel = new JLabel("Mac:");
		MacLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MacLabel.setFont(new Font("Arial", Font.BOLD, 14));
		MacLabel.setBounds(10, 11, 50, 20);
		AlgoPanel.add(MacLabel);

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setBounds(70, 12, 500, 20);
		AlgoPanel.add(textField);
		textField.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(10, 217, 560, 50);
		AlgoPanel.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 42, 560, 164);
		AlgoPanel.add(panel_1);
		panel_1.setLayout(null);
		
		

		ImageIcon[] image = new ImageIcon[1];

		JButton button = new JButton("Find Location");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(new JFrame(),"Must enter mac");
				}
				else
				{
					image[0] =  Wraper.GetMapLocation(database, textField.getText());
					JOptionPane.showMessageDialog(new JFrame(),image[0],null,JOptionPane.PLAIN_MESSAGE,null);
					textField.setText("");
				}
			}
		});
		button.setBounds(215, 11, 120, 25);
		panel.add(button);
		button.setFont(new Font("Arial", Font.PLAIN, 14));
	}
}
