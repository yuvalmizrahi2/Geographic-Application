package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import database.DataBase;
import sql.Table;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AddSql extends JPanel {

	/**
	 * Create the panel.
	 */
	public AddSql(DataBase database , DataBase temp , ArrayList<Table> tables) {

		this.setSize(600, 300);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 580, 278);
		add(panel);
		panel.setLayout(null);

		JLabel Ip = new JLabel("Ip:");
		Ip.setHorizontalAlignment(SwingConstants.CENTER);
		Ip.setFont(new Font("Arial", Font.BOLD, 14));
		Ip.setBounds(10, 11, 30, 25);
		panel.add(Ip);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea.setBounds(50, 13, 520, 25);
		panel.add(textArea);

		JLabel Port = new JLabel("Port:");
		Port.setHorizontalAlignment(SwingConstants.LEFT);
		Port.setFont(new Font("Arial", Font.BOLD, 14));
		Port.setBounds(10, 47, 40, 25);
		panel.add(Port);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea_1.setBounds(50, 49, 520, 25);
		panel.add(textArea_1);

		JLabel NameData = new JLabel("Data base:");
		NameData.setHorizontalAlignment(SwingConstants.LEFT);
		NameData.setFont(new Font("Arial", Font.BOLD, 14));
		NameData.setBounds(10, 83, 75, 25);
		panel.add(NameData);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea_2.setBounds(95, 85, 475, 25);
		panel.add(textArea_2);

		JLabel User = new JLabel("User Name:");
		User.setHorizontalAlignment(SwingConstants.LEFT);
		User.setFont(new Font("Arial", Font.BOLD, 14));
		User.setBounds(10, 119, 80, 25);
		panel.add(User);

		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea_3.setBounds(95, 119, 475, 25);
		panel.add(textArea_3);

		JLabel Password = new JLabel("Password:");
		Password.setHorizontalAlignment(SwingConstants.LEFT);
		Password.setFont(new Font("Arial", Font.BOLD, 14));
		Password.setBounds(5, 155, 75, 25);
		panel.add(Password);

		JTextArea textArea_4 = new JTextArea();
		textArea_4.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea_4.setBounds(95, 155, 475, 25);
		panel.add(textArea_4);

		JLabel TavleName = new JLabel("Table:");
		TavleName.setHorizontalAlignment(SwingConstants.LEFT);
		TavleName.setFont(new Font("Arial", Font.BOLD, 14));
		TavleName.setBounds(10, 191, 45, 25);
		panel.add(TavleName);

		JTextArea textArea_5 = new JTextArea();
		textArea_5.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea_5.setBounds(65, 191, 505, 25);
		panel.add(textArea_5);

		JButton button = new JButton("Save");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource().equals(button))
				{
					if(textArea.getText().equals("") || textArea_1.getText().equals("") || textArea_2.getText().equals("")
							|| textArea_3.getText().equals("") || textArea_4.getText().equals("") || textArea_5.getText().equals(""))
					{
						JOptionPane.showMessageDialog(new JFrame(),"Must enter all the details");
					}
					else
					{
						Table table = new Table(textArea.getText(), textArea_1.getText(), textArea_2.getText(), textArea_3.getText(), textArea_4.getText(), textArea_5.getText());
						Wraper.Addsql(table, database, temp);
						tables.add(table);
					}
				}
			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 14));
		button.setBounds(245, 242, 89, 25);
		panel.add(button);



	}
}
