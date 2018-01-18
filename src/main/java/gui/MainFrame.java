package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import database.DataBase;
import filter.NonOperator;
import filter.Operator;
import sql.Table;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainFrame {

	private JFrame Main;
	private JPanel savecsvfile;
	public JPanel main;
	private JPanel addcsv;
	private JPanel addfolder;
	private JPanel savekmlfile;
	private JPanel idfilter;
	private JPanel timeflter;
	private JPanel locationfilter;
	private JPanel algo1;
	private JPanel algo2;
	private JPanel currentpanel;
	private DataBase database;
	private JPanel savefilter;
	private JPanel addfilter;
	private Operator[] currentoperator; 
	private AddSql addsql;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.Main.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		database = new DataBase();
		currentoperator = new Operator[1];
		currentoperator[0] = new NonOperator();
		Main = new JFrame();
		Main.setTitle("Main");
		Main.setSize(600, 350);
		Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.setResizable(false);
		addcsv = new AddCsv(database);
		Main.getContentPane().add(addcsv).setVisible(false);
		addfolder = new AddFolder(database);
		Main.getContentPane().add(addfolder).setVisible(false);
		savecsvfile = new SaveCsvFile(database);
		Main.getContentPane().add(savecsvfile).setVisible(false);
		savekmlfile = new SaveKmlFile(database);
		Main.getContentPane().add(savekmlfile).setVisible(false);
		idfilter = new IdFilter(currentoperator);
		Main.getContentPane().add(idfilter).setVisible(false);
		timeflter = new TimeFilter(currentoperator);
		Main.getContentPane().add(timeflter).setVisible(false);
		locationfilter = new LocationFilter(currentoperator);
		Main.getContentPane().add(locationfilter).setVisible(false);
		algo1 = new Algo1(database);
		Main.getContentPane().add(algo1).setVisible(false);
		algo2 = new Algo2(database);
		Main.getContentPane().add(algo2).setVisible(false);
		savefilter = new SaveFilter(currentoperator[0]);
		Main.getContentPane().add(savefilter).setVisible(false);
		addsql = new AddSql(database);
		Main.getContentPane().add(addsql).setVisible(false);
		addfilter = new AddFilter(currentoperator);
		Main.getContentPane().add(addfilter).setVisible(false);
		main = new Main(database , currentoperator);
		Main.getContentPane().add(main);
		currentpanel = main;
		JMenuBar Menu = new JMenuBar();
		Main.setJMenuBar(Menu);
		JMenu Input = new JMenu("Input");
		Menu.add(Input);

		JMenuItem CsvFile = new JMenuItem("Add csv file");
		CsvFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == CsvFile)
				{
					currentpanel.setVisible(false);
					addcsv.setVisible(true);
					currentpanel = addcsv;
					Main.setTitle("Add Csv File");
				}

			}
		});
		Input.add(CsvFile);

		JMenuItem Folder = new JMenuItem("Add folder");
		Folder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == Folder)
				{
					currentpanel.setVisible(false);
					addfolder.setVisible(true);
					currentpanel = addfolder;
					Main.setTitle("Add Folder");
				}
			}
		});
		Input.add(Folder);
		
		JMenuItem AddFilter = new JMenuItem("Add Filter");
		AddFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(AddFilter))
				{
					currentpanel.setVisible(false);
					addfilter.setVisible(true);
					currentpanel = addfilter;
					Main.setTitle("Add Filter");
				}
			}
		});
		Input.add(AddFilter);
		
		JMenuItem mntmAddSql = new JMenuItem("Add sql");
		mntmAddSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(mntmAddSql))
				{
					currentpanel.setVisible(false);
					addsql.setVisible(true);
					currentpanel = addsql;
					Main.setTitle("Add Sql");
				}
			}
		});
		Input.add(mntmAddSql);

		JMenu OutPut = new JMenu("Output");
		Menu.add(OutPut);

		JMenuItem SaveCsv = new JMenuItem("Save as csv");
		SaveCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == SaveCsv)
				{
					currentpanel.setVisible(false);
					savecsvfile.setVisible(true);
					currentpanel = savecsvfile;
					Main.setTitle("Save To Csv");
				}
			}
		});
		OutPut.add(SaveCsv);

		JMenuItem SaveKml = new JMenuItem("Save as kml");
		SaveKml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == SaveKml)
				{
					currentpanel.setVisible(false);
					savekmlfile.setVisible(true);
					currentpanel = savekmlfile;
					Main.setTitle("Save To Kml");
				}
			}
		});
		OutPut.add(SaveKml);
		
		JMenuItem SaveFilter = new JMenuItem("Save Filter");
		SaveFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(SaveFilter))
				{
					currentpanel.setVisible(false);
					savefilter.setVisible(true);
					currentpanel = savefilter;
					Main.setTitle("Save Filter");
				}
			}
		});
		OutPut.add(SaveFilter);

		JMenu Filter = new JMenu("Filter");
		Menu.add(Filter);

		JMenuItem Time = new JMenuItem("By time");
		Time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == Time)
				{
					currentpanel.setVisible(false);
					timeflter.setVisible(true);
					currentpanel = timeflter;
					Main.setTitle("Time Filter");
				}
			}
		});
		Filter.add(Time);

		JMenuItem Place = new JMenuItem("By place");
		Place.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == Place)
				{
					currentpanel.setVisible(false);
					locationfilter.setVisible(true);
					currentpanel = locationfilter;
					Main.setTitle("Location Filter");
				}
			}
		});
		Filter.add(Place);

		JMenuItem Id = new JMenuItem("By id");
		Id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == Id)
				{
					currentpanel.setVisible(false);
					idfilter.setVisible(true);
					currentpanel = idfilter;
					Main.setTitle("Id Filter");
				}
			}
		});
		Filter.add(Id);

		JMenu Algo = new JMenu("Algorithm");
		Menu.add(Algo);

		JMenuItem Algo1 = new JMenuItem("Algorithm 1");
		Algo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == Algo1)
				{
					currentpanel.setVisible(false);
					algo1.setVisible(true);
					currentpanel = algo1;
					Main.setTitle("Algorithm 1");
				}
			}
		});
		Algo.add(Algo1);

		JMenuItem Algo2 = new JMenuItem("Algorithm 2");
		Algo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == Algo2)
				{
					currentpanel.setVisible(false);
					algo2.setVisible(true);
					currentpanel = algo2;
					Main.setTitle("Algorithm 2");
				}
			}
		});
		Algo.add(Algo2);

		JMenu DB = new JMenu("Data base");
		Menu.add(DB);

		JMenuItem Reset = new JMenuItem("Reset");
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == Reset)
				{
					Wraper.Reset(database);
				}
			}
		});
		DB.add(Reset);

		JMenuItem CancelFilter = new JMenuItem("Cancel filter");
		CancelFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == CancelFilter)
				{
					Wraper.CancelFilter(database);
				}
			}
		});
		DB.add(CancelFilter);

		JMenuItem ReturnMain = new JMenuItem("Return Main");
		ReturnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == ReturnMain)
				{
					currentpanel.setVisible(false);
					main.setVisible(true);
					currentpanel = main;
					Main.setTitle("Main");
				}
			}
		});
		DB.add(ReturnMain);
		try {
			Wraper.fileslisten(database);
			Wraper.folderlisten(database);
			Wraper.sqltablelisten(database);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
