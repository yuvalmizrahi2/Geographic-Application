package gui;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import filter.AndOperator;
import filter.Filter;
import filter.FilterNotFilterOperator;
import filter.NonOperator;
import filter.NotOperator;
import filter.Operator;
import filter.OrOperator;
import sample.WayPoint;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class LocationFilter extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3125565142527573872L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public LocationFilter(Operator[] currentoperator) {
		this.setSize(600, 300);
		setLayout(null);
		
		JPanel LocPanel = new JPanel();
		LocPanel.setBounds(10, 11, 580, 217);
		add(LocPanel);
		LocPanel.setLayout(null);
		
		JPanel DetailPanel = new JPanel();
		DetailPanel.setBounds(10, 62, 560, 135);
		LocPanel.add(DetailPanel);
		DetailPanel.setLayout(null);
		
		JLabel LatLabel = new JLabel("Lat:");
		LatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LatLabel.setFont(new Font("Arial", Font.BOLD, 14));
		LatLabel.setBounds(10, 13, 60, 20);
		DetailPanel.add(LatLabel);
		
		JLabel LonLabel = new JLabel("Lon:");
		LonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LonLabel.setFont(new Font("Arial", Font.BOLD, 14));
		LonLabel.setBounds(10, 44, 60, 20);
		DetailPanel.add(LonLabel);
		
		JLabel lblAlt = new JLabel("Alt:");
		lblAlt.setFont(new Font("Arial", Font.BOLD, 14));
		lblAlt.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlt.setBounds(10, 75, 60, 20);
		DetailPanel.add(lblAlt);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setBounds(80, 11, 470, 20);
		DetailPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_1.setBounds(80, 45, 470, 20);
		DetailPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_2.setBounds(80, 76, 470, 20);
		DetailPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblRaduis = new JLabel("Radius:");
		lblRaduis.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaduis.setFont(new Font("Arial", Font.BOLD, 14));
		lblRaduis.setBounds(10, 106, 60, 20);
		DetailPanel.add(lblRaduis);
		
		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_3.setBounds(80, 107, 470, 20);
		DetailPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 560, 40);
		LocPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Range:");
		label.setBounds(10, 10, 50, 25);
		panel_1.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		
		JRadioButton radioButton = new JRadioButton("In Range");
		radioButton.setBounds(62, 10, 90, 23);
		panel_1.add(radioButton);
		radioButton.setSelected(true);
		radioButton.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JRadioButton radioButton_1 = new JRadioButton("Out Range");
		radioButton_1.setBounds(147, 10, 109, 23);
		panel_1.add(radioButton_1);
		radioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton);
		group.add(radioButton_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 239, 580, 50);
		add(panel);
		panel.setLayout(null);
		
		JButton SaveButton = new JButton("Save Filter");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(SaveButton))
				{
					if(textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("") || textField_3.getText().equals(""))
					{
						JOptionPane.showMessageDialog(new JFrame(),"Must enter value");
					}
					else
					{
						Filter filter;
						if(radioButton_1.isSelected())
						{
							filter = new NotOperator(new filter.LocationFilter(new WayPoint(Double.parseDouble(textField.getText()), Double.parseDouble(textField_1.getText()), Double.parseDouble(textField_2.getText())), Double.parseDouble(textField_3.getText())));
						}
						else
						{
							filter = new filter.LocationFilter(new WayPoint(Double.parseDouble(textField.getText()), Double.parseDouble(textField_1.getText()), Double.parseDouble(textField_2.getText())), Double.parseDouble(textField_3.getText()));
						}
						if(currentoperator[0] instanceof NonOperator)
						{
							if(((NonOperator)currentoperator[0]).getFilter() == null)
							{
								((NonOperator)currentoperator[0]).setFilter(filter);
							}
						}
						else if(currentoperator[0] instanceof AndOperator)
						{
							if(((AndOperator)currentoperator[0]).getFilter1() == null)
								((AndOperator)currentoperator[0]).setFilter1(filter);
							else if(((AndOperator)currentoperator[0]).getFilter2() == null)
								((AndOperator)currentoperator[0]).setFilter2(filter);
						}
						else if(currentoperator[0] instanceof OrOperator)
						{
							if(((OrOperator)currentoperator[0]).getFilter1() == null)
								((OrOperator)currentoperator[0]).setFilter1(filter);
							else if(((OrOperator)currentoperator[0]).getFilter2() == null)
								((OrOperator)currentoperator[0]).setFilter2(filter);
						} 
						else 
						{
							if(((FilterNotFilterOperator)currentoperator[0]).getFilter1() == null)
								((FilterNotFilterOperator)currentoperator[0]).setFilter1(filter);
							else if(((FilterNotFilterOperator)currentoperator[0]).getFilter2() == null)
								((FilterNotFilterOperator)currentoperator[0]).setFilter2(filter);
						}
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						radioButton.setSelected(true);
					}
				}
			}
		});
		SaveButton.setBounds(240, 11, 110, 25);
		panel.add(SaveButton);
		SaveButton.setFont(new Font("Arial", Font.PLAIN, 14));

	}
}
