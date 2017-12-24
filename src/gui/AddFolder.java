package gui;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class AddFolder extends JPanel {

	/**
	 * Create the panel.
	 */
	public AddFolder() {
setFont(new Font("Arial", Font.PLAIN, 14));
		
		this.setSize(600, 300);
		setLayout(null);
		
		JPanel FolderChoosePanel = new JPanel();
		FolderChoosePanel.setLayout(null);
		FolderChoosePanel.setBounds(10, 11, 580, 45);
		add(FolderChoosePanel);
		
		JLabel DirLabel = new JLabel("No Selection");
		DirLabel.setHorizontalAlignment(SwingConstants.LEFT);
		DirLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		DirLabel.setBounds(190, 11, 374, 25);
		FolderChoosePanel.add(DirLabel);
		
		JLabel Dir = new JLabel("Dir:");
		Dir.setHorizontalAlignment(SwingConstants.CENTER);
		Dir.setFont(new Font("Arial", Font.BOLD, 14));
		Dir.setBounds(150, 11, 30, 25);
		FolderChoosePanel.add(Dir);

		JButton ChooseFolder = new JButton("Choose Folder");
		ChooseFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("choosertitle");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					DirLabel.setText(chooser.getSelectedFile()+"");
				} else
				{
					DirLabel.setText("No Selection");
				}
			}
		});
		ChooseFolder.setFont(new Font("Arial", Font.PLAIN, 14));
		ChooseFolder.setBounds(10, 11, 130, 25);
		FolderChoosePanel.add(ChooseFolder);
		
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
				if(DirLabel.getText().equals("No Selection"))
				{
					JOptionPane.showMessageDialog(new JFrame(),"Must enter Folder");
				}
				else
				{
					Wraper.AddFolder(DirLabel, group.getSelection());
				}
			}
		});
		SaveFile.setFont(new Font("Arial", Font.PLAIN, 14));
		SaveFile.setBounds(240, 264, 89, 25);
		add(SaveFile);

	}

}
