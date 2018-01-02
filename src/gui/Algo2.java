package gui;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import database.DataBase;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Algo2 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4344255583943172094L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the panel.
	 */
	public Algo2(DataBase database) {
		this.setSize(600, 300);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 580, 278);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 217, 560, 50);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 560, 195);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(6, 11, 544, 30);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setBounds(6, 5, 21, 21);
		rdbtnNewRadioButton.setSelected(true);
		panel_3.add(rdbtnNewRadioButton);

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setBounds(98, 6, 436, 20);
		panel_3.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Sample:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(33, 5, 55, 21);
		panel_3.add(lblNewLabel_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(6, 52, 533, 132);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel = new JLabel("Signal");
		lblNewLabel.setBounds(393, 7, 130, 17);
		panel_4.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblMac = new JLabel("Mac");
		lblMac.setBounds(33, 7, 350, 17);
		panel_4.add(lblMac);
		lblMac.setHorizontalAlignment(SwingConstants.CENTER);
		lblMac.setFont(new Font("Arial", Font.BOLD, 14));

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
		rdbtnNewRadioButton_1.setBounds(6, 7, 21, 21);
		panel_4.add(rdbtnNewRadioButton_1);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(33, 35, 490, 86);
		panel_4.add(panel_5);
		panel_5.setLayout(null);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_1.setBounds(10, 11, 360, 20);
		panel_5.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_2.setBounds(10, 35, 360, 20);
		panel_5.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_3.setBounds(10, 59, 360, 20);
		panel_5.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_4.setBounds(380, 11, 100, 20);
		panel_5.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_5.setBounds(380, 35, 100, 20);
		panel_5.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_6.setBounds(380, 59, 100, 20);
		panel_5.add(textField_6);
		textField_6.setColumns(10);

		ImageIcon[] image = new ImageIcon[1];

		JButton button = new JButton("Find Location");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(button))
				{
					if(rdbtnNewRadioButton.isSelected())
					{
						if(textField.getText().equals(""))
						{
							JOptionPane.showMessageDialog(new JFrame(),"Must enter sample as sting");
						}
						else
						{
							image[0] =  Wraper.Algo2(textField.getText(), database);
							JOptionPane.showMessageDialog(new JFrame(),image[0],null,JOptionPane.PLAIN_MESSAGE,null);
						}
					}
					else
					{
						if(textField_1.getText().equals("") || textField_2.getText().equals("") || textField_3.getText().equals("")
								|| textField_4.getText().equals("") || textField_5.getText().equals("") || textField_6.getText().equals(""))
						{
							JOptionPane.showMessageDialog(new JFrame(),"Must enter three macs and three signals");
						}
						else
						{
							ArrayList<String> mac = new ArrayList<>();
							mac.add(textField_1.getText());
							mac.add(textField_2.getText());
							mac.add(textField_3.getText());
							ArrayList<Double> signal = new ArrayList<>();
							signal.add(Double.parseDouble(textField_4.getText()));
							signal.add(Double.parseDouble(textField_5.getText()));
							signal.add(Double.parseDouble(textField_6.getText()));

							image[0] =  Wraper.Algo2(mac, signal, database);
							JOptionPane.showMessageDialog(new JFrame(),image[0],null,JOptionPane.PLAIN_MESSAGE,null);
						}
					}
					textField.setText("");
					textField_1.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					textField_6.setText("");
					rdbtnNewRadioButton.setSelected(true);
				}
			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 14));
		button.setBounds(215, 11, 120, 25);
		panel_1.add(button);


	}
}
