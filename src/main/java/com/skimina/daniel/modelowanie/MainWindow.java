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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
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

import javax.swing.JToggleButton;

public class MainWindow extends JFrame {
	
	
	private JPanel contentPane;
	private JPanel panelMenu;
	private JPanel panelWork;
	private JLabel lblBrak;
	
	
	
	
	
	
	private PrzestrzenAutomatow przestrzen;
	
	
	
	
	private Runnable refresh = new Runnable() {
		
		public void run() {
			wyniki.repaint();
		}
	};
	
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
		
		panelSasiedztwo = new JPanel();
		panelSasiedztwo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
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
		
		panelInit = new JPanel();
		panelInit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
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
		spinInit.setModel(new SpinnerNumberModel(new Integer(35), null, null, new Integer(1)));
		panelInit.add(spinInit, BorderLayout.SOUTH);
		
		
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
		panelStartStop.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelStartStop.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelStartStop.setMaximumSize(new Dimension(32767, 20));
		panelMenu.add(panelStartStop);
		panelStartStop.setLayout(new BoxLayout(panelStartStop, BoxLayout.PAGE_AXIS));
		
		btnStart = new JButton("Start");
		btnStart.setMaximumSize(new Dimension(500, 23));
		btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
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
					JOptionPane.showMessageDialog(MainWindow.this, "B��dnie podana warto��", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				boolean cycle = chckbxWarunkiBrzegoweCykliczne.isSelected();
				Process p = new Process(sasiedztwo, rows, columns, init, repaint, cycle);
				Thread t = new Thread(p);
				t.start();
			}
		});
		panelStartStop.add(btnStart);
		
		tglbtnShowEnergy = new JToggleButton("Show Energy");
		tglbtnShowEnergy.setMaximumSize(new Dimension(500, 23));
		tglbtnShowEnergy.setAlignmentX(Component.CENTER_ALIGNMENT);
		tglbtnShowEnergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tglbtnShowEnergy.isSelected()){
					przestrzen.showEnergyLevel();
				}else{
					przestrzen.wizualizuj();
				}
				
				SwingUtilities.invokeLater(refresh);
			}
		});
		tglbtnShowEnergy.setEnabled(false);
		panelStartStop.add(tglbtnShowEnergy);
		
		btnStartRekrystalizacja = new JButton("Start Rekrystalizacja");
		btnStartRekrystalizacja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Process2());
				t.start();
			}
		});
		btnStartRekrystalizacja.setEnabled(false);
		btnStartRekrystalizacja.setMaximumSize(new Dimension(500, 23));
		btnStartRekrystalizacja.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelStartStop.add(btnStartRekrystalizacja);
		
		
		
		
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
	
	//private Sasiedztwo sasiedztwo = new SasiedztwoMoore();
	private JCheckBox chckbxWarunkiBrzegoweCykliczne;
	private JComboBox<Sasiedztwo> comboBox;
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
	private JPanel panelOpcjeCheckBox;
	private JToggleButton tglbtnShowEnergy;
	private JButton btnStartRekrystalizacja;
	
	
	
	
	private class Process implements Runnable{
		
		
		/*private Runnable refresh = new Runnable() {
			
			public void run() {
				//panelWork.repaint();
				wyniki.repaint();
			}
		};*/
		
		private Random r = new Random();
		private Sasiedztwo sasiedztwo;
		private boolean repaint;
		
		
		
		
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
			
			
			initStep();
			
			int changes = nextStep();
			while(changes > 0){
				changes = nextStep();
				//System.out.println("Zmian: "+changes);
			}
			
			
			
			
			przestrzen.wizualizuj();
			SwingUtilities.invokeLater(refresh);
			
			
			
			
			
			przestrzen.przydzielEnergie(2.0, 5.0);
			
			
			
			
			tglbtnShowEnergy.setEnabled(true);
			btnStartRekrystalizacja.setEnabled(true);
			
			lblBrak.setForeground(Color.BLUE);
			lblBrak.setText("Koniec symulacji");
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		private void initStep(){
			
			RandomColor colors = new RandomColor();
			if(init > colors.getNumbersOfColors()) init = colors.getNumbersOfColors();
			
			
			
			int counter =0;
			int tries = 0;
			int id = 1;
			while(counter < init && tries < 200){
				MyCell cell = przestrzen.getOldCell(r.nextInt(columns), r.nextInt(rows));
				if(cell.isInitialized()){
					tries++;
				}else{
					cell.init(colors.getRandomColor(), id++);
					tries++;
					counter++;
				}
			}
			
			przestrzen.wizualizuj();
			
			
			SwingUtilities.invokeLater(refresh);
		}
		
		
		private int nextStep(){
	
			FunkcjaPrzejscia res = new FunkcjaPrzejscia();
			int changes = 0;
			//przele� po wszytkich komorkach
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
						List<MyCell> cells = przestrzen.pobierzSasiedzwo(sasiedztwo, j, i);
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
	
	private class Process2 implements Runnable{
		
		
		public void run() {
			
			lblBrak.setForeground(Color.RED);
			
			
			przestrzen.setCallback(callback);
			przestrzen.resetStructure();
			przestrzen.wizualizuj();
			SwingUtilities.invokeLater(refresh);
			
			int i = 1;
			int left = 0;
			do{
				//lblBrak.setText("Monte Carlo nr: "+(i+1));
				przestrzen.applyNucleationModule(10);
				left = przestrzen.applyMC(i++);
				przestrzen.wizualizuj();
				SwingUtilities.invokeLater(refresh);
				System.out.println("Left: "+left);
			}while(left>0);
			
			lblBrak.setForeground(Color.BLUE);
			lblBrak.setText("<html><center>Rekrystalizacja metod�<br>Monte Carlo zako�czona</center></html>");
		}
	}
	
	private MainWindowCallback callback = new MainWindowCallback() {
		
		public void setInfo(String msg) {
			lblBrak.setText(msg);
		}
	};
	
	public interface MainWindowCallback{
		public void setInfo(String msg);
	}
	
	

}