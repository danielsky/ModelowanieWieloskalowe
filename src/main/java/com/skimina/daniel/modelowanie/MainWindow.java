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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;

public class MainWindow extends JFrame {
	
	
	private JPanel contentPane;
	private JPanel panelMenu;
	private JPanel panelWork;
	private JLabel lblBrak;
	
	public MainWindow() {
		setTitle("Modelowanie Wieloskalowe");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(MainWindow.class.getResource("/b_agh.gif")).getImage());
		
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		
		panelMenu = new JPanel();
		panelMenu.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(panelMenu, BorderLayout.WEST);
		panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
		
		panelOpcje = new JPanel();
		panelOpcje.setMaximumSize(new Dimension(32767, 20));
		panelOpcje.setOpaque(false);
		panelOpcje.setForeground(Color.WHITE);
		panelOpcje.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelMenu.add(panelOpcje);
		panelOpcje.setLayout(new BorderLayout(0, 0));
		
		lblOpcje = new JLabel("Opcje");
		lblOpcje.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpcje.setOpaque(true);
		lblOpcje.setBackground(Color.BLACK);
		lblOpcje.setBorder(new EmptyBorder(3, 0, 3, 0));
		lblOpcje.setForeground(Color.WHITE);
		panelOpcje.add(lblOpcje);
		
		chckbxWarunkiBrzegoweCykliczne = new JCheckBox("Warunki Brzegowe Cykliczne");
		panelMenu.add(chckbxWarunkiBrzegoweCykliczne);
		
		DefaultComboBoxModel<Sasiedztwo> model = new DefaultComboBoxModel<Sasiedztwo>();
		model.addElement(new SasiedztwoMoore());
		model.addElement(new SasiedztwoVonNeumann());
		
		chckbxOdwieanie = new JCheckBox("Od\u015Bwie\u017Canie");
		chckbxOdwieanie.setSelected(true);
		panelMenu.add(chckbxOdwieanie);
		
		panelSasiedztwo = new JPanel();
		panelSasiedztwo.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelSasiedztwo.setMaximumSize(new Dimension(32767, 50));
		panelMenu.add(panelSasiedztwo);
		panelSasiedztwo.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("Typ S\u0105siedztwa");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setBorder(new EmptyBorder(3, 0, 3, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelSasiedztwo.add(lblNewLabel, BorderLayout.NORTH);
		
		comboBox = new JComboBox<Sasiedztwo>(model);
		panelSasiedztwo.add(comboBox);
		comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		comboBox.setMaximumSize(new Dimension(32767, 20));
		
		panelPrzestrzen = new JPanel();
		panelPrzestrzen.setMaximumSize(new Dimension(32767, 45));
		panelPrzestrzen.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelMenu.add(panelPrzestrzen);
		panelPrzestrzen.setLayout(new BorderLayout(0, 0));
		
		lblRozmiarPrzestrzeni = new JLabel("Rozmiar Przestrzeni");
		lblRozmiarPrzestrzeni.setBorder(new EmptyBorder(3, 0, 3, 0));
		lblRozmiarPrzestrzeni.setHorizontalAlignment(SwingConstants.CENTER);
		lblRozmiarPrzestrzeni.setOpaque(true);
		lblRozmiarPrzestrzeni.setBackground(Color.BLACK);
		lblRozmiarPrzestrzeni.setForeground(Color.WHITE);
		panelPrzestrzen.add(lblRozmiarPrzestrzeni, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panelPrzestrzen.add(panel_1);
		panel_1.setMaximumSize(new Dimension(32767, 20));
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		txtRows = new JTextField();
		txtRows.setText("350");
		txtRows.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtRows);
		txtRows.setColumns(10);
		
		lblX = new JLabel(" x ");
		panel_1.add(lblX);
		
		txtColumns = new JTextField();
		txtColumns.setText("350");
		txtColumns.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtColumns);
		txtColumns.setColumns(10);
		
		panelInit = new JPanel();
		panelInit.setMaximumSize(new Dimension(32767, 50));
		panelInit.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelMenu.add(panelInit);
		panelInit.setLayout(new BorderLayout(0, 0));
		
		lblPocztkowaIloZiaren = new JLabel("Pocz\u0105tkowa ilo\u015B\u0107 ziaren");
		lblPocztkowaIloZiaren.setBorder(new EmptyBorder(3, 0, 3, 0));
		lblPocztkowaIloZiaren.setHorizontalAlignment(SwingConstants.CENTER);
		lblPocztkowaIloZiaren.setOpaque(true);
		lblPocztkowaIloZiaren.setForeground(Color.WHITE);
		lblPocztkowaIloZiaren.setBackground(Color.BLACK);
		panelInit.add(lblPocztkowaIloZiaren, BorderLayout.NORTH);
		
		spinInit = new JSpinner();
		spinInit.setModel(new SpinnerNumberModel(new Integer(5), null, null, new Integer(1)));
		panelInit.add(spinInit, BorderLayout.SOUTH);
		
		panelInfo = new JPanel();
		panelInfo.setMaximumSize(new Dimension(32767, 40));
		panelInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelMenu.add(panelInfo);
		panelInfo.setLayout(new BorderLayout(0, 0));
		
		lblBrak = new JLabel();
		lblBrak.setHorizontalAlignment(SwingConstants.CENTER);
		panelInfo.add(lblBrak);
		lblBrak.setBorder(new EmptyBorder(10, 0, 10, 0));
		lblBrak.setText("Brak Symulacji");
		lblBrak.setForeground(Color.BLACK);
		
		panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelMenu.add(panel);
		
		panelStartStop = new JPanel();
		panelStartStop.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelStartStop.setMaximumSize(new Dimension(32767, 20));
		panelMenu.add(panelStartStop);
		panelStartStop.setLayout(new BorderLayout(0, 0));
		
		btnStart = new JButton("Start");
		btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelStartStop.add(btnStart, BorderLayout.NORTH);
		
		btnStop = new JButton("Stop");
		btnStop.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelStartStop.add(btnStop, BorderLayout.SOUTH);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Thread t = new Thread(new NextStep());
				//t.start();
			}
		});
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sasiedztwo sasiedztwo = (Sasiedztwo)comboBox.getSelectedItem();
				boolean repaint = chckbxOdwieanie.isSelected();
				
				int rows = -1;
				int columns = -1;
				int init = -1;
				
				try{
					rows = Integer.parseInt(txtRows.getText());
					columns = Integer.parseInt(txtColumns.getText());
					init = (Integer) spinInit.getValue();
				}catch(NumberFormatException nfe){
					nfe.printStackTrace();
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				if(rows <= 0 || columns <= 0 || init <=0){
					JOptionPane.showMessageDialog(MainWindow.this, "B³êdnie podana wartoœæ", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				boolean cycle = chckbxWarunkiBrzegoweCykliczne.isSelected();
				
				Thread t = new Thread(new Process(sasiedztwo, rows, columns, init, repaint, cycle));
				t.start();
			}
		});
		
		
		panelWork = new JPanel();
		contentPane.add(panelWork, BorderLayout.CENTER);
		panelWork.setLayout(new BorderLayout());
		
		wyniki = new JLabel();
		wyniki.setHorizontalAlignment(SwingConstants.CENTER);
		panelWork.add(wyniki, BorderLayout.CENTER);
		
		
		
	}
	
	
	//private int rows = 350;
	//private int columns = 350;
	
	
	//private Matrix m1 = new Matrix(rows, columns, true);
	//private Matrix m2 = new Matrix(rows, columns, true);
	
	//private Matrix current = m1;
	//private Matrix old = m2;
	//private Matrix temp = null;
	
	private JLabel wyniki;
	
	private JButton btnStart;
	private JButton btnStop;
	
	//private Sasiedztwo sasiedztwo = new SasiedztwoMoore();
	private JCheckBox chckbxWarunkiBrzegoweCykliczne;
	private JComboBox<Sasiedztwo> comboBox;
	private JPanel panel;
	private JCheckBox chckbxOdwieanie;
	private JLabel lblRozmiarPrzestrzeni;
	private JPanel panel_1;
	private JTextField txtRows;
	private JLabel lblX;
	private JTextField txtColumns;
	private JPanel panelStartStop;
	private JPanel panelPrzestrzen;
	private JPanel panelSasiedztwo;
	private JLabel lblNewLabel;
	private JPanel panelOpcje;
	private JLabel lblOpcje;
	private JPanel panelInfo;
	private JPanel panelInit;
	private JLabel lblPocztkowaIloZiaren;
	private JSpinner spinInit;
	
	
	
	
	private class Process implements Runnable{
		
		
		private Runnable refresh = new Runnable() {
			
			public void run() {
				//panelWork.repaint();
				wyniki.repaint();
			}
		};
		
		private Random r = new Random();
		private Sasiedztwo sasiedztwo;
		private boolean repaint;
		
		private PrzestrzenAutomatow przestrzen;
		
		private int rows;
		private int columns;
		private int init;
		
		
		public Process(Sasiedztwo s, int rows, int columns, int init, boolean repaint, boolean cycle) {
			this.sasiedztwo = s;
			this.repaint = repaint; 
			
			this.rows = rows;
			this.columns = columns;
			this.init = init;
			
			przestrzen = new PrzestrzenAutomatow(rows, columns, cycle);
			
			wyniki.setIcon(new ImageIcon(przestrzen.getImage()));
		}
		
		
		public void run() {
			
			lblBrak.setForeground(Color.RED);
			lblBrak.setText("Trwa symulacja...");
			
			reset();
			
			
			initStep();
			
			int changes = nextStep();
			while(changes > 0){
				changes = nextStep();
				//System.out.println("Zmian: "+changes);
			}
			przestrzen.wizualizuj();
			SwingUtilities.invokeLater(refresh);
			
			lblBrak.setForeground(Color.BLUE);
			lblBrak.setText("Koniec symulacji");
			
		}
		
		
		private void reset(){
			/*for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					old.getCell(j, i).reset();
					current.getCell(j, i).reset();
				}
			}*/
			przestrzen.reset();
		}
		
		
		private void initStep(){
			for(int i=1;i<=init;i++){
				//MyCell cell = old.getCell(r.nextInt(columns), r.nextInt(rows));
				MyCell cell = przestrzen.getOldCell(r.nextInt(columns), r.nextInt(rows));
				cell.init(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			}
			
			przestrzen.wizualizuj();
			
			//JOptionPane.showMessageDialog(MainWindow.this, new ImageIcon(przestrzen.getImage()));
			
			SwingUtilities.invokeLater(refresh);
		}
		
		
		private int nextStep(){
	
			FunkcjaPrzejscia res = new FunkcjaPrzejscia();
			int changes = 0;
			//przeleæ po wszytkich komorkach
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					//MyCell oldCell = old.getCell(j, i);
					//MyCell currCell = current.getCell(j, i);
					MyCell oldCell = przestrzen.getOldCell(j, i);
					MyCell currCell = przestrzen.getNewCell(j, i);
					if(oldCell.isInitialized()){
						currCell.init(oldCell);
					}else{
						res.clear();
						List<MyCell> cells = przestrzen.pobierzSasiedzwo(sasiedztwo, j, i)/*sasiedztwo.pobierzSasiedztwo(old, j, i)/*old.getNeighborhood(j, i)*/;
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
			//temp = current;
			//current = old;
			//old = temp;
			przestrzen.switchMatrix();
			
			if(repaint){
				przestrzen.wizualizuj();
				//JOptionPane.showMessageDialog(MainWindow.this, new ImageIcon(przestrzen.getImage()));
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
			/*
			int startx = (int)((getWidth()-(columns*d))*0.5);
			int starty = (int)((getHeight()-(rows*d))*0.5);
			
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					g.setColor(old.getCell(j, i).getColor());
					g.fillRect(startx+j*d, starty+i*d, d, d);
				}
			}
			*/
			
			
			
		}
	}
	
	

}