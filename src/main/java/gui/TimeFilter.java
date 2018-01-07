package gui;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingConstants;


import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

import filter.AndOperator;
import filter.DateFilter;
import filter.Filter;
import filter.FilterNotFilterOperator;
import filter.NonOperator;
import filter.NotOperator;
import filter.Operator;
import filter.OrOperator;

import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SpinnerDateModel;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class TimeFilter extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6618144518494386430L;

	/**
	 * Create the panel.
	 */
	public TimeFilter(Operator[] currentoperator) {
		this.setSize(600, 300);
		setLayout(null);

		JPanel RangePanel = new JPanel();
		RangePanel.setBounds(10, 0, 580, 40);
		RangePanel.setLayout(null);
		add(RangePanel);

		JLabel label = new JLabel("Range:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		label.setBounds(10, 11, 50, 25);
		RangePanel.add(label);

		JRadioButton InRadio = new JRadioButton("In Range");
		InRadio.setSelected(true);
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

		JPanel TimePanel = new JPanel();
		TimePanel.setBounds(10, 51, 580, 177);
		add(TimePanel);
		TimePanel.setLayout(null);

		JPanel MinTimePanel = new JPanel();
		MinTimePanel.setBounds(10, 11, 280, 155);
		TimePanel.add(MinTimePanel);
		MinTimePanel.setLayout(null);

		JPanel MInPanel = new JPanel();
		MInPanel.setBounds(10, 11, 260, 14);
		MinTimePanel.add(MInPanel);
		MInPanel.setLayout(null);

		JLabel MinLabel = new JLabel("Minimum Time");
		MinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MinLabel.setFont(new Font("Arial", Font.BOLD, 14));
		MinLabel.setBounds(10, 0, 240, 14);
		MInPanel.add(MinLabel);

		JDateChooser MinDate = new JDateChooser();
		MinDate.setDateFormatString("yyyy-MM-dd");
		MinDate.setFont(new Font("Arial", Font.PLAIN, 14));
		MinDate.setBounds(70, 36, 130, 20);
		MinDate.setCalendar(Calendar.getInstance());
		MinTimePanel.add(MinDate);

		Date date = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		JSpinner MinSpinner = new JSpinner(sm);
		MinSpinner.setFont(new Font("Arial", Font.PLAIN, 14));
		MinSpinner.setBounds(70, 67, 130, 20);
		JSpinner.DateEditor de = new DateEditor(MinSpinner, "HH:mm:ss");
		MinSpinner.setEditor(de);
		MinTimePanel.add(MinSpinner);

		JPanel MaxTimePanel = new JPanel();
		MaxTimePanel.setBounds(300, 11, 270, 155);
		TimePanel.add(MaxTimePanel);
		MaxTimePanel.setLayout(null);

		JPanel MaxPanel = new JPanel();
		MaxPanel.setBounds(10, 11, 250, 14);
		MaxTimePanel.add(MaxPanel);
		MaxPanel.setLayout(null);

		JLabel MaxLabel = new JLabel("Maximum Time");
		MaxLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MaxLabel.setFont(new Font("Arial", Font.BOLD, 14));
		MaxLabel.setBounds(10, 0, 230, 14);
		MaxPanel.add(MaxLabel);

		JDateChooser MaxDate = new JDateChooser();
		MaxDate.setDateFormatString("yyyy-MM-dd");
		MaxDate.setFont(new Font("Arial", Font.PLAIN, 14));
		MaxDate.setBounds(70, 36, 130, 20);
		MaxDate.setCalendar(Calendar.getInstance());
		MaxTimePanel.add(MaxDate);

		Date date2 = new Date();
		SpinnerDateModel sm2 = new SpinnerDateModel(date2, null, null, Calendar.HOUR_OF_DAY);
		JSpinner MaxSpinner = new JSpinner(sm2);
		MaxSpinner.setFont(new Font("Arial", Font.PLAIN, 14));
		MaxSpinner.setBounds(70, 67, 130, 20);
		JSpinner.DateEditor de2 = new DateEditor(MaxSpinner, "HH:mm:ss");
		MaxSpinner.setEditor(de2);

		MaxTimePanel.add(MaxSpinner);

		JPanel panel = new JPanel();
		panel.setBounds(10, 239, 580, 50);
		add(panel);
		panel.setLayout(null);

		JButton button = new JButton("Save Filter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(button))
				{
					Filter filter = null;
					SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
					SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date min = null , max = null;
					try {
						min = dateFormat.parse(dateFormat3.format(MinDate.getDate()) + " " + dateFormat2.format(MinSpinner.getValue()));
						max = dateFormat.parse(dateFormat3.format(MaxDate.getDate()) + " " + dateFormat2.format(MaxSpinner.getValue()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					if(!min.before(max))
					{
						JOptionPane.showMessageDialog(new JFrame(),"Must enter correct time");
					}
					else
					{
						if(OutRadio.isSelected())
						{
							filter = new NotOperator(new DateFilter(min, max));
						}
						else
						{
							filter = new DateFilter(min, max);
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

					}
				}
			}


		});
		button.setBounds(240, 11, 110, 25);
		panel.add(button);
		button.setFont(new Font("Arial", Font.PLAIN, 14));


	}
}
