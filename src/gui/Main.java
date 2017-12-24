package gui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

public class Main extends JPanel {

	/**
	 * Create the panel.
	 */
	public Main() {
		
		this.setSize(600, 300);
		setLayout(null);
		
		JPanel ButtonFilterPanel = new JPanel();
		ButtonFilterPanel.setBounds(20, 234, 570, 45);
		add(ButtonFilterPanel);
		ButtonFilterPanel.setLayout(null);
		
		JPanel FilterDeatilPanel = new JPanel();
		FilterDeatilPanel.setBounds(20, 11, 570, 109);
		add(FilterDeatilPanel);
		FilterDeatilPanel.setLayout(null);
		
		JLabel TextFilter = new JLabel(" (Time(t0<data<t1) & (!(Position(radius<0.01, center =(32.103, 35.207))))) ");
		TextFilter.setBounds(20, 39, 554, 20);
		FilterDeatilPanel.add(TextFilter);
		TextFilter.setHorizontalAlignment(SwingConstants.CENTER);
		TextFilter.setFont(new Font("Arial", Font.PLAIN, 14));
		JButton FilterDataBase = new JButton("Filter Data Base");
		
		FilterDataBase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(TextFilter.getText().equals(""))
				{
					JOptionPane.showMessageDialog(new JFrame(),"Must enter filter");
				}
				else
				{
					Wraper.FilterDataBase(TextFilter);
				}
			}
		});
		FilterDataBase.setBounds(210, 11, 145, 25);
		ButtonFilterPanel.add(FilterDataBase);
		FilterDataBase.setAlignmentX(Component.CENTER_ALIGNMENT);
		FilterDataBase.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel Filter = new JLabel("Filter:");
		Filter.setBounds(10, 11, 554, 20);
		FilterDeatilPanel.add(Filter);
		Filter.setHorizontalAlignment(SwingConstants.CENTER);
		Filter.setAlignmentX(Component.CENTER_ALIGNMENT);
		Filter.setFont(new Font("Arial", Font.BOLD, 16));
		
		JPanel KindFilterPanel = new JPanel();
		KindFilterPanel.setBounds(10, 70, 550, 28);
		FilterDeatilPanel.add(KindFilterPanel);
		KindFilterPanel.setLayout(null);
		
		JRadioButton NonFilter = new JRadioButton("Non");
		NonFilter.setFont(new Font("Arial", Font.PLAIN, 14));
		NonFilter.setBounds(136, 11, 51, 14);
		KindFilterPanel.add(NonFilter);
		NonFilter.setSelected(true);
		
		JLabel KindFilter = new JLabel("Option To Filter:");
		KindFilter.setFont(new Font("Arial", Font.BOLD, 14));
		KindFilter.setBounds(10, 11, 120, 14);
		KindFilterPanel.add(KindFilter);
		
		JRadioButton AndFilter = new JRadioButton("And");
		AndFilter.setFont(new Font("Arial", Font.PLAIN, 14));
		AndFilter.setBounds(189, 11, 51, 14);
		KindFilterPanel.add(AndFilter);
		
		JRadioButton OrFilter = new JRadioButton("Or");
		OrFilter.setFont(new Font("Arial", Font.PLAIN, 14));
		OrFilter.setBounds(242, 11, 41, 14);
		KindFilterPanel.add(OrFilter);
		
		JRadioButton NotFilter = new JRadioButton("Not");
		NotFilter.setFont(new Font("Arial", Font.PLAIN, 14));
		NotFilter.setBounds(285, 11, 51, 14);
		KindFilterPanel.add(NotFilter);
		
		ButtonGroup group = new ButtonGroup();
		group.add(NonFilter);
		group.add(AndFilter);
		group.add(OrFilter);
		group.add(NotFilter);
		
		JPanel DetailPanel = new JPanel();
		DetailPanel.setBounds(20, 131, 570, 92);
		add(DetailPanel);
		DetailPanel.setLayout(null);
		
		JLabel Detail = new JLabel("Detail");
		Detail.setBounds(10, 11, 554, 14);
		DetailPanel.add(Detail);
		Detail.setHorizontalAlignment(SwingConstants.CENTER);
		Detail.setFont(new Font("Arial", Font.BOLD, 16));
		
		JPanel DetailsPanel = new JPanel();
		DetailsPanel.setBounds(190, 36, 190, 56);
		DetailPanel.add(DetailsPanel);
		DetailsPanel.setLayout(null);
		
		JLabel lblSample = new JLabel("Sample:");
		lblSample.setFont(new Font("Arial", Font.BOLD, 14));
		lblSample.setBounds(10, 12, 60, 14);
		DetailsPanel.add(lblSample);
		
		JLabel NumSample = new JLabel("5");
		NumSample.setHorizontalAlignment(SwingConstants.LEFT);
		NumSample.setFont(new Font("Arial", Font.PLAIN, 14));
		NumSample.setBounds(80, 12, 100, 14);
		DetailsPanel.add(NumSample);
		
		JLabel Mac = new JLabel("Mac:");
		Mac.setFont(new Font("Arial", Font.BOLD, 14));
		Mac.setBounds(10, 36, 60, 14);
		DetailsPanel.add(Mac);
		
		JLabel NumMac = new JLabel("5");
		NumMac.setHorizontalAlignment(SwingConstants.LEFT);
		NumMac.setFont(new Font("Arial", Font.PLAIN, 14));
		NumMac.setBounds(80, 37, 100, 14);
		DetailsPanel.add(NumMac);
		

	}
}
