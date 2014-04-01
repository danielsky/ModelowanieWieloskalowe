package com.skimina.daniel.modelowanie;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.skimina.daniel.modelowanie.sasiedztwo.Sasiedztwo;
import com.skimina.daniel.modelowanie.sasiedztwo.SasiedztwoFurtherMoore;
import com.skimina.daniel.modelowanie.sasiedztwo.SasiedztwoMoore;
import com.skimina.daniel.modelowanie.sasiedztwo.SasiedztwoVonNeumann;

public class MainWindow extends JFrame {
	
	
	private JPanel contentPane;
	private JPanel panelMenu;
	private JPanel panelWork;
	private JLabel lblBrak;
	
	public MainWindow() {
		setTitle("Modelowanie Wieloskalowe by D.Skimina 2014 Zajêcia nr 1");
		
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
		spinInit.setModel(new SpinnerNumberModel(new Integer(5), null, null, new Integer(1)));
		panelInit.add(spinInit, BorderLayout.SOUTH);
		
		panelPrawdopodobienstwo = new JPanel();
		panelPrawdopodobienstwo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelPrawdopodobienstwo.setMaximumSize(new Dimension(32767, 50));
		panelPrawdopodobienstwo.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelMenu.add(panelPrawdopodobienstwo);
		panelPrawdopodobienstwo.setLayout(new BorderLayout(0, 0));
		
		lblPrawdopodobiestwo = new JLabel("Prawdopodobie\u0144stwo");
		lblPrawdopodobiestwo.setOpaque(true);
		lblPrawdopodobiestwo.setForeground(Color.WHITE);
		lblPrawdopodobiestwo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrawdopodobiestwo.setBorder(new EmptyBorder(3, 0, 3, 0));
		lblPrawdopodobiestwo.setBackground(Color.BLACK);
		panelPrawdopodobienstwo.add(lblPrawdopodobiestwo, BorderLayout.NORTH);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(80, 0, 100, 1));
		panelPrawdopodobienstwo.add(spinner, BorderLayout.CENTER);
		
		lblPercent = new JLabel("%");
		lblPercent.setBorder(new EmptyBorder(0, 5, 0, 5));
		panelPrawdopodobienstwo.add(lblPercent, BorderLayout.EAST);
		
		
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
				
				
				boolean repaint = chckbxOdwieanie.isSelected();
				
				int rows = -1;
				int columns = -1;
				int init = -1;
				int probability = -1;
				
				try{
					rows = Integer.parseInt(txtRows.getText());
					columns = Integer.parseInt(txtColumns.getText());
					init = (Integer) spinInit.getValue();
					probability = (Integer) spinner.getValue();
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
				Process p = new Process(rows, columns, init, probability, repaint, cycle);
				
				
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
	private JPanel panelInit;
	private JLabel lblPocztkowaIloZiaren;
	private JSpinner spinInit;
	private JPanel panelOpcjeCheckBox;
	private JPanel panelPrawdopodobienstwo;
	private JLabel lblPrawdopodobiestwo;
	private JSpinner spinner;
	private JLabel lblPercent;
	
	
	
	
	private class Process implements Runnable{
		
		
		private Runnable refresh = new Runnable() {
			
			public void run() {
				//panelWork.repaint();
				wyniki.repaint();
			}
		};
		
		private Random r = new Random();
		
		private boolean repaint;
		
		private PrzestrzenAutomatow przestrzen;
		
		
		private int rows;
		private int columns;
		private int init;
		private int probability;
		
		public Process( int rows, int columns, int init, int probability, boolean repaint, boolean cycle) {
			
			this.repaint = repaint; 
			
			this.rows = rows;
			this.columns = columns;
			this.init = init;
			this.probability = probability;
			
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
			}
			

			
			
			przestrzen.wizualizuj();
			SwingUtilities.invokeLater(refresh);
			
			lblBrak.setForeground(Color.BLUE);
			lblBrak.setText("Koniec symulacji");
			
		}
		
		
		
		
		
		
		
		
		private void initStep(){
			
			RandomColor colors = new RandomColor();
			if(init > colors.getNumbersOfColors()) init = colors.getNumbersOfColors();
			
			
			
			int counter =0;
			int tries = 0;
			while(counter < init && tries < 200){
				MyCell cell = przestrzen.getOldCell(r.nextInt(columns), r.nextInt(rows));
				if(cell.isInitialized()){
					tries++;
				}else{
					cell.init(colors.getRandomColor());
					tries++;
					counter++;
				}
			}
			
			
			
			przestrzen.wizualizuj();
			
			//JOptionPane.showMessageDialog(MainWindow.this, new ImageIcon(przestrzen.getImage()));
			
			SwingUtilities.invokeLater(refresh);
		}
		
		
		private int nextStep(){
	
			FunkcjaPrzejscia fp = new FunkcjaPrzejscia(probability);
			Sasiedztwo sMoore = new SasiedztwoMoore();
			Sasiedztwo sVonNeumann = new SasiedztwoVonNeumann();
			Sasiedztwo sFurtherMoore = new SasiedztwoFurtherMoore();
			
			
			int changes = 0;
			//przeleæ po wszytkich komorkach
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					MyCell oldCell = przestrzen.getOldCell(j, i);
					MyCell currCell = przestrzen.getNewCell(j, i);
					if(oldCell.isInitialized()){
						currCell.init(oldCell);
					}else{
						
						//First step
						fp.addCells(przestrzen.pobierzSasiedzwo(sMoore, j, i));
						MyCell bestChoice = fp.getFirstRuleBestChoice();
						if(bestChoice != null){
							currCell.init(bestChoice);
							changes++;
						}else{
							
							//second rule
							
							fp.addCells(przestrzen.pobierzSasiedzwo(sVonNeumann, j, i));
							bestChoice = fp.getSecondRuleBestChoice();
							if(bestChoice != null){
								currCell.init(bestChoice);
								changes++;
							}else{
								
								//third rule
								
								fp.addCells(przestrzen.pobierzSasiedzwo(sFurtherMoore, j, i));
								bestChoice = fp.getThirdRuleBestChoice();
								if(bestChoice != null){
									currCell.init(bestChoice);
									changes++;
								}else{
									
									//fourth rule
									
									fp.addCells(przestrzen.pobierzSasiedzwo(sMoore, j, i));
									bestChoice = fp.getFourthRuleBestChoice();
									if(bestChoice != null){
										currCell.init(bestChoice);
										changes++;
									}
								}
							}
						}
					}
				}
			}
			
			
			//zamien matrixy
			
			przestrzen.switchMatrix();
			
			if(repaint){
				przestrzen.wizualizuj();
				SwingUtilities.invokeLater(refresh);
			}
			
			return changes;
		}
	}
	
	

}