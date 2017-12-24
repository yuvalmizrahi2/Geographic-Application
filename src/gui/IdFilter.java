package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class IdFilter extends JPanel {
	private JTextField IdText;

	/**
	 * Create the panel.
	 */
	public IdFilter() {
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
		
		JButton SaveFilter = new JButton("Save Filter");
		SaveFilter.setFont(new Font("Arial", Font.PLAIN, 14));
		SaveFilter.setBounds(240, 264, 110, 25);
		add(SaveFilter);

	}
}
