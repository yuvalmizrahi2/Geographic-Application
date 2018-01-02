package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import filter.AndOperator;
import filter.Filter;
import filter.FilterNotFilterOperator;
import filter.NonOperator;
import filter.NotOperator;
import filter.Operator;
import filter.OrOperator;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class IdFilter extends JPanel {
	private JTextField IdText;

	/**
	 * Create the panel.
	 */
	public IdFilter(Operator[] currentoperator) {
		this.setSize(600, 300);
		setLayout(null);

		JPanel RangePanel = new JPanel();
		RangePanel.setBounds(10, 11, 580, 40);
		add(RangePanel);
		RangePanel.setLayout(null);

		JLabel RangeLabel = new JLabel("Range:");
		RangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RangeLabel.setFont(new Font("Arial", Font.BOLD, 14));
		RangeLabel.setBounds(10, 11, 50, 25);
		RangePanel.add(RangeLabel);

		JRadioButton InRadio = new JRadioButton("In Range");
		InRadio.setHorizontalAlignment(SwingConstants.CENTER);
		InRadio.setFont(new Font("Arial", Font.PLAIN, 14));
		InRadio.setBounds(66, 11, 90, 23);
		RangePanel.add(InRadio);
		InRadio.setSelected(true);

		JRadioButton OutRadio = new JRadioButton("Out Range");
		OutRadio.setHorizontalAlignment(SwingConstants.CENTER);
		OutRadio.setFont(new Font("Arial", Font.PLAIN, 14));
		OutRadio.setBounds(158, 11, 109, 23);
		RangePanel.add(OutRadio);

		ButtonGroup group = new ButtonGroup();
		group.add(OutRadio);
		group.add(InRadio);

		JPanel IdPanel = new JPanel();
		IdPanel.setLayout(null);
		IdPanel.setBounds(10, 68, 580, 45);
		add(IdPanel);

		JLabel IdLabel = new JLabel("Id:");
		IdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		IdLabel.setFont(new Font("Arial", Font.BOLD, 14));
		IdLabel.setBounds(10, 11, 80, 25);
		IdPanel.add(IdLabel);

		IdText = new JTextField();
		IdText.setHorizontalAlignment(SwingConstants.LEFT);
		IdText.setFont(new Font("Arial", Font.PLAIN, 14));
		IdText.setColumns(10);
		IdText.setBounds(100, 11, 464, 25);
		IdPanel.add(IdText);

		JPanel panel = new JPanel();
		panel.setBounds(10, 239, 580, 50);
		add(panel);
		panel.setLayout(null);

		JButton SaveFilter = new JButton("Save Filter");
		SaveFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(SaveFilter))
				{
					if(IdText.getText().equals(""))
					{
						JOptionPane.showMessageDialog(new JFrame(),"Must enter id");
					}
					else
					{
						Filter filter;
						if(OutRadio.isSelected())
						{
							filter = new NotOperator(new filter.IdFilter(IdText.getText()));
						}
						else
						{
							filter = new filter.IdFilter(IdText.getText());
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
						IdText.setText("");
						InRadio.setSelected(true);
					}
				}
			}
		});
		SaveFilter.setBounds(240, 11, 110, 25);
		panel.add(SaveFilter);
		SaveFilter.setFont(new Font("Arial", Font.PLAIN, 14));


	}
}
