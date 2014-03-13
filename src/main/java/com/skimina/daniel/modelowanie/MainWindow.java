package com.skimina.daniel.modelowanie;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.skimina.daniel.modelowanie.sasiedztwo.Sasiedztwo;
import com.skimina.daniel.modelowanie.sasiedztwo.SasiedztwoMoore;
import com.skimina.daniel.modelowanie.sasiedztwo.SasiedztwoVonNeumann;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.Component;

public class MainWindow extends JFrame {
	
	
	private JPanel contentPane;
	private JPanel panelMenu;
	private MyPanel panelWork;
	private JLabel label;
	
	public MainWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		getContentPane().setLayout(new BorderLayout());
		
		panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.WEST);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sasiedztwo sasiedztwo = (Sasiedztwo)comboBox.getSelectedItem();
				boolean repaint = chckbxOdwieanie.isSelected();
				
				Thread t = new Thread(new Process(sasiedztwo, repaint));
				t.start();
			}
		});
		panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
		panelMenu.add(btnStart);
		
		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Thread t = new Thread(new NextStep());
				//t.start();
			}
		});
		panelMenu.add(btnStop);
		
		chckbxWarunkiBrzegoweCykliczne = new JCheckBox("Warunki Brzegowe Cykliczne");
		panelMenu.add(chckbxWarunkiBrzegoweCykliczne);
		
		DefaultComboBoxModel<Sasiedztwo> model = new DefaultComboBoxModel<Sasiedztwo>();
		model.addElement(new SasiedztwoMoore());
		model.addElement(new SasiedztwoVonNeumann());
		
		chckbxOdwieanie = new JCheckBox("Od\u015Bwie\u017Canie");
		panelMenu.add(chckbxOdwieanie);
		
		comboBox = new JComboBox<Sasiedztwo>(model);
		comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		comboBox.setMaximumSize(new Dimension(32767, 20));
		panelMenu.add(comboBox);
		
		label = new JLabel();
		label.setForeground(Color.BLUE);
		panelMenu.add(label);
		
		panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelMenu.add(panel);
		
		
		panelWork = new MyPanel();
		contentPane.add(panelWork, BorderLayout.CENTER);
		
		
		
	}
	
	
	private int rows = 350;
	private int columns = 350;
	
	
	private Matrix m1 = new Matrix(rows, columns);
	private Matrix m2 = new Matrix(rows, columns);
	
	private Matrix current = m1;
	private Matrix old = m2;
	private Matrix temp = null;
	
	
	private JButton btnStart;
	private JButton btnStop;
	
	//private Sasiedztwo sasiedztwo = new SasiedztwoMoore();
	private JCheckBox chckbxWarunkiBrzegoweCykliczne;
	private JComboBox<Sasiedztwo> comboBox;
	private JPanel panel;
	private JCheckBox chckbxOdwieanie;
	
	
	
	
	private class Process implements Runnable{
		
		
		private Runnable refresh = new Runnable() {
			
			public void run() {
				panelWork.repaint();
			}
		};
		
		private Random r = new Random();
		private Sasiedztwo sasiedztwo;
		private boolean repaint;
		
		
		public Process(Sasiedztwo s, boolean repaint) {
			sasiedztwo = s;
			this.repaint = repaint; 
		}
		
		
		public void run() {
			
			label.setForeground(Color.RED);
			label.setText("Trwa symulacja...");
			
			reset();
			
			
			initStep();
			
			int changes = nextStep();
			while(changes > 0){
				changes = nextStep();
			}
			
			SwingUtilities.invokeLater(refresh);
			
			label.setForeground(Color.BLUE);
			label.setText("Koniec symulacji");
			
		}
		
		
		private void reset(){
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					old.getCell(j, i).reset();
					current.getCell(j, i).reset();
				}
			}
		}
		
		
		private void initStep(){
			for(int i=1;i<=20;i++){
				MyCell cell = old.getCell(r.nextInt(columns), r.nextInt(rows));
				cell.init(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			}
			
			
			SwingUtilities.invokeLater(refresh);
		}
		
		
		private int nextStep(){
	
			FunkcjaPrzejscia res = new FunkcjaPrzejscia();
			int changes = 0;
			//przeleæ po wszytkich komorkach
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					MyCell oldCell = old.getCell(j, i);
					MyCell currCell = current.getCell(j, i);
					if(oldCell.isInitialized()){
						currCell.init(oldCell);
					}else{
						res.clear();
						List<MyCell> cells = sasiedztwo.pobierzSasiedztwo(old, j, i)/*old.getNeighborhood(j, i)*/;
						res.addCells(cells);
						
						MyCell bestChoice = res.getBestChoice();
						if(bestChoice != null){
							currCell.init(bestChoice);
							changes++;
						}
					}
				}
			}
			
			
			//zamien matrixy
			temp = current;
			current = old;
			old = temp;
			
			if(repaint){
				SwingUtilities.invokeLater(refresh);
			}
			
			return changes;
		}
	}
	
	
	
	private class MyPanel extends JPanel{
		

		
		private int d = 1;
		
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			int startx = (int)((getWidth()-(columns*d))*0.5);
			int starty = (int)((getHeight()-(rows*d))*0.5);
			
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					g.setColor(old.getCell(j, i).getColor());
					g.fillRect(startx+j*d, starty+i*d, d, d);
				}
			}
			
			
			
		}
	}
	
	

}