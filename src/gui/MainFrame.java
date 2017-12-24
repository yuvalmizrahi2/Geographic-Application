package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame {

	private JFrame Main;
	private JPanel savecsvfile;
	private JPanel main;
	private JPanel addcsv;
	private JPanel addfolder;
	private JPanel savekmlfile;
	private JPanel idfilter;
	private JPanel currentpanel;

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
		Main = new JFrame();
		Main.setTitle("Main");
		Main.setSize(600, 350);
		Main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Main.setResizable(false);
		addcsv = new AddCsv();
		Main.getContentPane().add(addcsv).setVisible(false);
		addfolder = new AddFolder();
		Main.getContentPane().add(addfolder).setVisible(false);
		savecsvfile = new SaveCsvFile();
		Main.getContentPane().add(savecsvfile).setVisible(false);
		savekmlfile = new SaveKmlFile();
		Main.getContentPane().add(savekmlfile).setVisible(false);
		idfilter = new IdFilter();
		Main.getContentPane().add(idfilter).setVisible(false);
		main = new Main();
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

		JMenu Filter = new JMenu("Filter");
		Menu.add(Filter);

		JMenuItem Time = new JMenuItem("By time");
		Filter.add(Time);

		JMenuItem Place = new JMenuItem("By place");
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
		Algo.add(Algo1);

		JMenuItem Algo2 = new JMenuItem("Algorithm 2");
		Algo.add(Algo2);

		JMenu DB = new JMenu("Data base");
		Menu.add(DB);

		JMenuItem Reset = new JMenuItem("Reset");
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == Reset)
				{
					Wraper.Reset();
				}
			}
		});
		DB.add(Reset);

		JMenuItem CancelFilter = new JMenuItem("Cancel filter");
		CancelFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == CancelFilter)
				{
					Wraper.CancelFilter();
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
	}
}
