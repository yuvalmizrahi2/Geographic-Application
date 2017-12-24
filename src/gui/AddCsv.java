package gui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

public class AddCsv extends JPanel {

	/**
	 * Create the panel.
	 */
	public AddCsv() {
		setFont(new Font("Arial", Font.PLAIN, 14));
		
		this.setSize(600, 300);
		setLayout(null);
		
		JPanel FileChoosePanel = new JPanel();
		FileChoosePanel.setLayout(null);
		FileChoosePanel.setBounds(10, 11, 580, 45);
		add(FileChoosePanel);
		
		JLabel label = new JLabel("No Selection");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Arial", Font.PLAIN, 14));
		label.setBounds(175, 11, 389, 25);
		FileChoosePanel.add(label);
		
		JLabel File = new JLabel("File:");
		File.setHorizontalAlignment(SwingConstants.CENTER);
		File.setFont(new Font("Arial", Font.BOLD, 14));
		File.setBounds(135, 11, 30, 25);
		FileChoosePanel.add(File);

		JButton ChooseFile = new JButton("Choose File");
		ChooseFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
		        FileNameExtensionFilter filter = new FileNameExtensionFilter(
		                "csv", "csv");
		        chooser.setFileFilter(filter);
		        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		        	label.setText(chooser.getSelectedFile().getName()+"");
		        }
		        else
		        	label.setText("No Selection");
			}
		});
		ChooseFile.setFont(new Font("Arial", Font.PLAIN, 14));
		ChooseFile.setBounds(10, 11, 115, 25);
		FileChoosePanel.add(ChooseFile);
		
		JPanel PanelFormat = new JPanel();
		PanelFormat.setFont(new Font("Arial", Font.PLAIN, 14));
		PanelFormat.setBounds(10, 67, 580, 45);
		add(PanelFormat);
		PanelFormat.setLayout(null);
		
		JLabel FormatLabel = new JLabel("Format File:");
		FormatLabel.setFont(new Font("Arial", Font.BOLD, 14));
		FormatLabel.setBounds(10, 11, 85, 23);
		PanelFormat.add(FormatLabel);
		
		JPanel PanelKindFormat = new JPanel();
		PanelKindFormat.setBounds(105, 11, 465, 23);
		PanelFormat.add(PanelKindFormat);
		PanelKindFormat.setLayout(null);
		
		JRadioButton WigleForamt = new JRadioButton("Wigle Foramt");
		WigleForamt.setFont(new Font("Arial", Font.PLAIN, 14));
		WigleForamt.setBounds(6, 0, 109, 23);
		WigleForamt.setSelected(true);
		PanelKindFormat.add(WigleForamt);
		
		JRadioButton CsvForamt = new JRadioButton("Csv 46 Column Foramt");
		CsvForamt.setFont(new Font("Arial", Font.PLAIN, 14));
		CsvForamt.setBounds(117, 0, 170, 23);
		PanelKindFormat.add(CsvForamt);
		
		ButtonGroup group = new ButtonGroup();
		group.add(CsvForamt);
		group.add(WigleForamt);
		JButton SaveFile = new JButton("Save");
		SaveFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(label.getText().equals("No Selection"))
				{
					JOptionPane.showMessageDialog(new JFrame(),"Must enter file");
				}
				else
				{
					Wraper.AddCsv(label,group.getSelection());
				}
			}
		});
		SaveFile.setFont(new Font("Arial", Font.PLAIN, 14));
		SaveFile.setBounds(240, 264, 89, 25);
		add(SaveFile);
	}
}
