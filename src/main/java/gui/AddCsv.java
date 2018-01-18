package gui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.DataBase;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AddCsv extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7615760676167335303L;

	/**
	 * Create the panel.
	 */
	public AddCsv(DataBase database) {
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
				chooser.setDialogTitle("Choose Csv File");
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					label.setText(chooser.getSelectedFile().getAbsolutePath());
				}
				else
					label.setText("No Selection");
			}
		});
		ChooseFile.setFont(new Font("Arial", Font.PLAIN, 14));
		ChooseFile.setBounds(10, 11, 115, 25);
		FileChoosePanel.add(ChooseFile);

		JPanel panel = new JPanel();
		panel.setBounds(10, 239, 580, 50);
		add(panel);
		panel.setLayout(null);
		JButton SaveFile = new JButton("Save");
		SaveFile.setBounds(240, 11, 89, 25);
		panel.add(SaveFile);
		SaveFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(label.getText().equals("No Selection"))
				{
					JOptionPane.showMessageDialog(new JFrame(),"Must enter file");
				}
				else
				{
					database.getFile().add(label.getText());
					Wraper.AddCsv(label.getText(),database);
					label.setText("No Selection");
				}
			}
		});
		SaveFile.setFont(new Font("Arial", Font.PLAIN, 14));
	}
}
