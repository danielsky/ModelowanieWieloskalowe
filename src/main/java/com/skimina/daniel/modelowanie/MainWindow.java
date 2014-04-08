package com.skimina.daniel.modelowanie;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.skimina.daniel.modelowanie.sasiedztwo.Sasiedztwo;
import com.skimina.daniel.modelowanie.sasiedztwo.SasiedztwoHeksagonalne;
import com.skimina.daniel.modelowanie.sasiedztwo.SasiedztwoMoore;
import com.skimina.daniel.modelowanie.sasiedztwo.SasiedztwoPentagonalne;
import com.skimina.daniel.modelowanie.sasiedztwo.SasiedztwoVonNeumann;

public class MainWindow extends JFrame {
	
	
	private JPanel contentPane;
	private JPanel panelMenu;
	private JPanel panelWork;
	private JLabel lblBrak;
	
	public MainWindow() {
		setTitle("Modelowanie Wieloskalowe by D.Skimina 2014");
		
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
		panelOpcje.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelMenu.add(panelOpcje);
		panelOpcje.setMaximumSize(new Dimension(32767, 20));
		panelOpcje.setOpaque(false);
		panelOpcje.setForeground(Color.WHITE);
		panelOpcje.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelOpcje.setLayout(new BorderLayout(0, 0));
		
		lblOpcje = new JLabel("Opcje");
		lblOpcje.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpcje.setOpaque(true);
		lblOpcje.setBackground(Color.BLACK);
		lblOpcje.setBorder(new EmptyBorder(3, 0, 3, 0));
		lblOpcje.setForeground(Color.WHITE);
		panelOpcje.add(lblOpcje, BorderLayout.NORTH);
		
		panelOpcjeCheckBox = new JPanel();
		panelOpcje.add(panelOpcjeCheckBox, BorderLayout.CENTER);
		panelOpcjeCheckBox.setLayout(new BoxLayout(panelOpcjeCheckBox, BoxLayout.Y_AXIS));
		
		chckbxWarunkiBrzegoweCykliczne = new JCheckBox("Warunki Brzegowe Cykliczne");
		panelOpcjeCheckBox.add(chckbxWarunkiBrzegoweCykliczne);
		
		chckbxOdwieanie = new JCheckBox("Od\u015Bwie\u017Canie");
		panelOpcjeCheckBox.add(chckbxOdwieanie);
		chckbxOdwieanie.setSelected(true);
		
		DefaultComboBoxModel<Sasiedztwo> model = new DefaultComboBoxModel<Sasiedztwo>();
		model.addElement(new SasiedztwoMoore());
		model.addElement(new SasiedztwoVonNeumann());
		model.addElement(new SasiedztwoPentagonalne());
		model.addElement(new SasiedztwoHeksagonalne());
		
		panelPrzestrzen = new JPanel();
		panelPrzestrzen.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
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
		
		
		panelInfo = new JPanel();
		panelInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelMenu.add(panelInfo);
		panelInfo.setLayout(new BorderLayout(0, 0));
		
		lblBrak = new JLabel();
		lblBrak.setHorizontalAlignment(SwingConstants.CENTER);
		panelInfo.add(lblBrak);
		lblBrak.setBorder(new EmptyBorder(10, 0, 10, 0));
		lblBrak.setText("Brak Symulacji");
		lblBrak.setForeground(Color.BLACK);
		
		panelStartStop = new JPanel();
		panelStartStop.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelStartStop.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelStartStop.setMaximumSize(new Dimension(32767, 20));
		panelMenu.add(panelStartStop);
		panelStartStop.setLayout(new BorderLayout(0, 0));
		
		btnStart = new JButton("Start");
		btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelStartStop.add(btnStart, BorderLayout.NORTH);
		
		btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		btnStop.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelStartStop.add(btnStop, BorderLayout.SOUTH);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mutex = false;
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
			}
		});
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean repaint = chckbxOdwieanie.isSelected();
				
				int rows = -1;
				int columns = -1;
				
				
				try{
					rows = Integer.parseInt(txtRows.getText());
					columns = Integer.parseInt(txtColumns.getText());
				}catch(NumberFormatException nfe){
					nfe.printStackTrace();
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				if(rows <= 0 || columns <= 0){
					JOptionPane.showMessageDialog(MainWindow.this, "B³êdnie podana wartoœæ", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				boolean cycle = chckbxWarunkiBrzegoweCykliczne.isSelected();
				
				
				
				mutex = true;
				btnStart.setEnabled(false);
				btnStop.setEnabled(true);
				
				
				Process p = new Process(rows, columns, repaint, cycle);
				Thread t = new Thread(p);
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
	
	

	
	private JLabel wyniki;
	
	private JButton btnStart;
	private JButton btnStop;
	
	private JCheckBox chckbxWarunkiBrzegoweCykliczne;
	private JCheckBox chckbxOdwieanie;
	private JLabel lblRozmiarPrzestrzeni;
	private JPanel panel_1;
	private JTextField txtRows;
	private JLabel lblX;
	private JTextField txtColumns;
	private JPanel panelStartStop;
	private JPanel panelPrzestrzen;
	private JPanel panelOpcje;
	private JLabel lblOpcje;
	private JPanel panelInfo;
	private JPanel panelOpcjeCheckBox;
	
	
	
	
	private boolean mutex = false;
	
	
	
	
	private class Process implements Runnable{
		
		
		private Runnable refresh = new Runnable() {
			
			public void run() {
				wyniki.repaint();
			}
		};
		
		private Random r = new Random();
	
		private boolean repaint;
		
		private PrzestrzenAutomatow przestrzen;
		
		
		
	
		
		
		public Process(int rows, int columns, boolean repaint, boolean cycle) {
			this.repaint = repaint; 
			
			
			przestrzen = new PrzestrzenAutomatow(rows, columns, cycle);
			
			wyniki.setIcon(new ImageIcon(przestrzen.getImage()));
		}
		
		
		
		
		
		public void run() {
			
			lblBrak.setForeground(Color.RED);
			lblBrak.setText("Trwa symulacja...");
			
			
			
			while(mutex){
				nextStep();
			}
			
			
			
			
			
			
			przestrzen.wizualizuj();
			SwingUtilities.invokeLater(refresh);
			
			lblBrak.setForeground(Color.BLUE);
			lblBrak.setText("Koniec symulacji");
			
		}
		
		
		
		
		
		private void nextStep(){
			
			List<MyCell> lista = przestrzen.getAllCells();
			int counter = 0;
			double size = lista.size();
			
			while(!lista.isEmpty() && mutex){
				MyCell cell = lista.get(r.nextInt(lista.size()));
				przestrzen.testujEnergie(cell);
				lista.remove(cell);
				if(counter>15){
					double percentage = lista.size()*100.0 / size; 
					lblBrak.setText(String.format("Trwa symulacja... (%.2f %%)", 100.0-percentage));
					counter = 0;
				}
				counter++;
			}
	
			
			
			if(repaint){
				przestrzen.wizualizuj();
				SwingUtilities.invokeLater(refresh);
			}
		}
	}	
	

}