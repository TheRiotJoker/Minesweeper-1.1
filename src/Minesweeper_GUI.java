import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Minesweeper_GUI extends JFrame{
	private Minesweeper_Strng steuerung;
	private JButton leicht;
	private JButton mittel;
	private JButton schwer;
	private JLabel startText;
	private JPanel startPanel;
	private JTextField zeitField;
	private JTextField flaggeField;
	private JButton statusButton;
	public Minesweeper_GUI() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Minesweeper 1.0");
		setResizable(false);
		setLocation(1920/3, 1680/4);
		initStartScreen();
	}
	public static void main(String[] args) {
		Minesweeper_GUI gui = new Minesweeper_GUI();
	}
	private void initStartScreen() {
		startPanel = new JPanel();
		setSize(290,150);
		startPanel.setSize(getWidth(), getHeight());
		startPanel.setLayout(null);
		
		startText = new JLabel("Wählen Sie eine Schwierigkeitsstufe aus.");
		startText.setBounds(0,10,getWidth(),40);
		startText.setHorizontalAlignment(SwingConstants.CENTER);
		startPanel.add(startText);
		
		leicht = new JButton("Leicht");
		leicht.setBounds(10,50,80,40);
		leicht.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				starteSpiel(10);
				
			}
			
		});
		startPanel.add(leicht);
		
		
		mittel = new JButton("Mittel");
		mittel.setBounds(100,50,80,40);
		mittel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				starteSpiel(40);
				
			}
			
		});
		startPanel.add(mittel);
		
		schwer = new JButton("Schwer");
		schwer.setBounds(190,50,80,40);
		schwer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				starteSpiel(99);
				
			}
			
		});
		startPanel.add(schwer);
		
		setVisible(true);
		add(startPanel);
		System.out.println(getContentPane());
	}
	private void starteSpiel(int minen) {
		remove(startPanel);
		steuerung = new Minesweeper_Strng(this, minen);
		
		zeitField = new JTextField();
		zeitField.setBounds(getWidth()-65, 7, 50,20);
		zeitField.setEditable(false);
		zeitField.setHorizontalAlignment(SwingConstants.CENTER);
		add(zeitField);
		
		flaggeField = new JTextField();
		flaggeField.setBounds(10,7,40,20);
		flaggeField.setEditable(false);
		flaggeField.setHorizontalAlignment(SwingConstants.CENTER);
		add(flaggeField);
		
		statusButton = new JButton("Warte");
		statusButton.setBackground(new Color(230,230,230));
		statusButton.setBounds(80, 7, zeitField.getX()-110, 20);
		statusButton.setHorizontalAlignment(SwingConstants.CENTER);
		statusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				steuerung.restart();
				
			}
			
		});
		add(statusButton);
		
		steuerung.starteZeit();
		setLayout(null);
	}
	public void aktualisiereZeitField(String t) {
		zeitField.setText(t);
	}
	public void aktualisiereFlaggeField(String t) {
		flaggeField.setText(t);
	}
	public void aktualisiereStatusField(String t, Color c) {
		statusButton.setForeground(c);
		statusButton.setText(t);
	}
}
