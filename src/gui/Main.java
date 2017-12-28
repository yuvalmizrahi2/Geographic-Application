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

import database.DataBase;
import filter.AndOperator;
import filter.FilterNotFilterOperator;
import filter.NonOperator;
import filter.NotOperator;
import filter.Operator;
import filter.OrOperator;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7757448236100361656L;

	/**
	 * Create the panel.
	 */
	
	public Main(DataBase database ,Operator[] curentoperator ) {

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

		JLabel TextFilter = new JLabel(curentoperator[0].toString());
		TextFilter.setBounds(10, 39, 554, 20);
		FilterDeatilPanel.add(TextFilter);
		TextFilter.setHorizontalAlignment(SwingConstants.CENTER);
		TextFilter.setFont(new Font("Arial", Font.PLAIN, 14));
		JButton FilterDataBase = new JButton("Filter Data Base");
		FilterDataBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(FilterDataBase))
				{
					if(TextFilter.getText().contains("null"))
					{
						JOptionPane.showMessageDialog(new JFrame(),"Must enter filter");
					}
					else
					{
						Wraper.FilterDataBase(curentoperator , database);
					}
				}
			}
		});
		FilterDataBase.setBounds(270, 11, 145, 25);
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
		NonFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(NonFilter))
				{
					if(NonFilter.isSelected())
					{
						filter.Filter temp = null;
						if(!(curentoperator[0] instanceof NonOperator))
						{
							if(curentoperator[0] instanceof AndOperator)
							{
								temp = ((AndOperator) curentoperator[0]).getFilter1();
							}
							else if(curentoperator[0] instanceof OrOperator)
							{
								temp = ((OrOperator)curentoperator[0]).getFilter1();
								
							}
							else if(curentoperator[0] instanceof FilterNotFilterOperator)
							{
								temp = ((FilterNotFilterOperator)curentoperator[0]).getFilter1();
							}
							else
							{
								temp = ((NotOperator)curentoperator[0]).getFilter();

							}
							curentoperator[0] = new NonOperator(temp);
						}
					}
				}
			}
		});
		NonFilter.setFont(new Font("Arial", Font.PLAIN, 14));
		NonFilter.setBounds(136, 11, 51, 14);
		KindFilterPanel.add(NonFilter);
		NonFilter.setSelected(true);

		JLabel KindFilter = new JLabel("Option To Filter:");
		KindFilter.setFont(new Font("Arial", Font.BOLD, 14));
		KindFilter.setBounds(10, 11, 120, 14);
		KindFilterPanel.add(KindFilter);

		JRadioButton AndFilter = new JRadioButton("And");
		AndFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(AndFilter))
				{
					if(AndFilter.isSelected())
					{
						filter.Filter temp = null;
						filter.Filter temp2 = null;
						if(!(curentoperator[0] instanceof AndOperator))
						{
							if(curentoperator[0] instanceof NonOperator)
							{
								temp = ((NonOperator) curentoperator[0]).getFilter();
							}
							else if(curentoperator[0] instanceof OrOperator)
							{
								temp = ((OrOperator)curentoperator[0]).getFilter1();
								temp2 = ((OrOperator)curentoperator[0]).getFilter2();
								
							}
							else if(curentoperator[0] instanceof FilterNotFilterOperator)
							{
								temp = ((FilterNotFilterOperator)curentoperator[0]).getFilter1();
								temp2 = ((FilterNotFilterOperator)curentoperator[0]).getFilter2();

							}
							else
							{
								temp = ((NotOperator)curentoperator[0]).getFilter();

							}
							curentoperator[0] = new AndOperator(temp, temp2);
						}
					}
				}
				
			}
		});
		AndFilter.setFont(new Font("Arial", Font.PLAIN, 14));
		AndFilter.setBounds(189, 11, 51, 14);
		KindFilterPanel.add(AndFilter);

		JRadioButton OrFilter = new JRadioButton("Or");
		OrFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(OrFilter))
				{
					if(OrFilter.isSelected())
					{
						filter.Filter temp = null;
						filter.Filter temp2 = null;
						if(!(curentoperator[0] instanceof OrOperator))
						{
							if(curentoperator[0] instanceof NonOperator)
							{
								temp = ((NonOperator) curentoperator[0]).getFilter();
							}
							else if(curentoperator[0] instanceof AndOperator)
							{
								temp = ((AndOperator)curentoperator[0]).getFilter1();
								temp2 = ((AndOperator)curentoperator[0]).getFilter2();
								
							}
							else if(curentoperator[0] instanceof FilterNotFilterOperator)
							{
								temp = ((FilterNotFilterOperator)curentoperator[0]).getFilter1();
								temp2 = ((FilterNotFilterOperator)curentoperator[0]).getFilter2();

							}
							else
							{
								temp = ((NotOperator)curentoperator[0]).getFilter();

							}
							curentoperator[0] = new OrOperator(temp, temp2);
						}
					}
				}
			}
		});
		OrFilter.setFont(new Font("Arial", Font.PLAIN, 14));
		OrFilter.setBounds(242, 11, 41, 14);
		KindFilterPanel.add(OrFilter);

		JRadioButton NotFilter = new JRadioButton("Not");
		NotFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(NotFilter))
				{
					if(NotFilter.isSelected())
					{
						filter.Filter temp = null;
						filter.Filter temp2 = null;
						if(!(curentoperator[0] instanceof FilterNotFilterOperator))
						{
							if(curentoperator[0] instanceof NonOperator)
							{
								temp = ((NonOperator) curentoperator[0]).getFilter();
							}
							else if(curentoperator[0] instanceof OrOperator)
							{
								temp = ((OrOperator)curentoperator[0]).getFilter1();
								temp2 = ((OrOperator)curentoperator[0]).getFilter2();
								
							}
							else if(curentoperator[0] instanceof AndOperator)
							{
								temp = ((AndOperator)curentoperator[0]).getFilter1();
								temp2 = ((AndOperator)curentoperator[0]).getFilter2();

							}
							else
							{
								temp = ((NotOperator)curentoperator[0]).getFilter();

							}
							curentoperator[0] = new FilterNotFilterOperator(temp, temp2);
						}
					}
				}
				
			}
		});
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

		JLabel NumSample = new JLabel(database.getSetsample().size()+"");
		NumSample.setHorizontalAlignment(SwingConstants.LEFT);
		NumSample.setFont(new Font("Arial", Font.PLAIN, 14));
		NumSample.setBounds(80, 12, 100, 14);
		DetailsPanel.add(NumSample);

		JLabel Mac = new JLabel("Mac:");
		Mac.setFont(new Font("Arial", Font.BOLD, 14));
		Mac.setBounds(10, 36, 60, 14);
		DetailsPanel.add(Mac);

		JLabel NumMac = new JLabel(database.getMap().size()+"");
		NumMac.setHorizontalAlignment(SwingConstants.LEFT);
		NumMac.setFont(new Font("Arial", Font.PLAIN, 14));
		NumMac.setBounds(80, 37, 100, 14);
		DetailsPanel.add(NumMac);
		
		JButton RemoveFilter = new JButton("Remove Filter");
		RemoveFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(RemoveFilter))
				{
					NonFilter.setSelected(true);
					curentoperator[0] = new NonOperator();
				}
			}
		});
		RemoveFilter.setFont(new Font("Arial", Font.PLAIN, 14));
		RemoveFilter.setBounds(135, 11, 125, 25);
		ButtonFilterPanel.add(RemoveFilter);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true)
				{
					NumSample.setText(database.getSetsample().size()+"");
					NumMac.setText(database.getMap().size()+"");
					TextFilter.setText(curentoperator[0].toString());
				}

			}
		}).start();
	}
}
