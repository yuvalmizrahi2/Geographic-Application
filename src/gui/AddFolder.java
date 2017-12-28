package gui;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import database.DataBase;

public class AddFolder extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7805920556520565132L;

	/**
	 * Create the panel.
	 */
	public AddFolder(DataBase database , DataBase temp) {
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
				chooser.setDialogTitle("Choose Folder");
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
				if(DirLabel.getText().equals("No Selection"))
				{
					JOptionPane.showMessageDialog(new JFrame(),"Must enter Folder");
				}
				else
				{
					Wraper.AddFolder(DirLabel,database , temp);
				}
			}
		});
		SaveFile.setFont(new Font("Arial", Font.PLAIN, 14));

	}

}
