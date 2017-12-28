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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import filter.AndOperator;
import filter.NonOperator;
import filter.NotOperator;
import filter.Operator;

public class SaveFilter extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9189431394233100212L;
	private JFileChooser chooser;
	private JTextField NameText;
	/**
	 * Create the panel.
	 */
	public SaveFilter(Operator filter) {
		this.setSize(600, 300);
		setLayout(null);

		JPanel DirPanel = new JPanel();
		DirPanel.setBounds(10, 11, 580, 45);
		this.add(DirPanel);
		DirPanel.setLayout(null);

		JLabel Path = new JLabel("No Selection");
		Path.setHorizontalAlignment(SwingConstants.LEFT);
		Path.setFont(new Font("Arial", Font.PLAIN, 14));
		Path.setBounds(160, 11, 404, 25);
		DirPanel.add(Path);

		JButton ButtonDir = new JButton("Choose Dir");
		ButtonDir.setFont(new Font("Arial", Font.PLAIN, 14));
		ButtonDir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("choosertitle");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					Path.setText(chooser.getSelectedFile()+"");
				} else
				{
					Path.setText("No Selection");
				}
			}
		});
		ButtonDir.setBounds(10, 11, 105, 25);
		DirPanel.add(ButtonDir);

		JLabel Dir = new JLabel("Dir:");
		Dir.setHorizontalAlignment(SwingConstants.CENTER);
		Dir.setFont(new Font("Arial", Font.BOLD, 14));
		Dir.setBounds(125, 10, 25, 25);
		DirPanel.add(Dir);

		JPanel NamePanel = new JPanel();
		NamePanel.setBounds(10, 67, 580, 45);
		this.add(NamePanel);
		NamePanel.setLayout(null);

		JLabel Name = new JLabel("Name File:");
		Name.setHorizontalAlignment(SwingConstants.CENTER);
		Name.setFont(new Font("Arial", Font.BOLD, 14));
		Name.setBounds(10, 11, 80, 25);
		NamePanel.add(Name);

		NameText = new JTextField();
		NameText.setFont(new Font("Arial", Font.PLAIN, 14));
		NameText.setBounds(100, 11, 464, 25);
		NamePanel.add(NameText);
		NameText.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 239, 580, 50);
		add(panel);
		panel.setLayout(null);
		
				JButton Save = new JButton("Save");
				Save.setBounds(240, 11, 89, 25);
				panel.add(Save);
				Save.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(NameText.getText().equals("") || Path.getText().equals("No Selection"))
						{
							JOptionPane.showMessageDialog(new JFrame(),"Must enter name file and direction");
						}
						else
						{
							if(filter instanceof AndOperator)
								Wraper.SaveFilter(Path, NameText, filter,"A");
							else if(filter instanceof NonOperator)
								Wraper.SaveFilter(Path, NameText, filter,"Non");
							else if(filter instanceof NotOperator)
								Wraper.SaveFilter(Path, NameText, filter,"Not");
							else
								Wraper.SaveFilter(Path, NameText, filter,"N");
						}
					}
				});
				Save.setFont(new Font("Arial", Font.PLAIN, 14));



	}

}
