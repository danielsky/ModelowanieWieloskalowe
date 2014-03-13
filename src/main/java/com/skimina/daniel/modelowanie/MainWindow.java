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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame {
	
	
	private JPanel contentPane;
	private JPanel panelMenu;
	private MyPanel panelWork;
	
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
				//Thread t = new Thread(new InitStep());
				//t.start();
			}
		});
		panelMenu.add(btnStart);
		
		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Thread t = new Thread(new NextStep());
				//t.start();
			}
		});
		panelMenu.add(btnStop);
		
		JButton btnUID = new JButton("uid");
		btnUID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Thread t = new Thread(new Process());
				t.start();
			}
		});
		panelMenu.add(btnUID);
		
		panelWork = new MyPanel();
		contentPane.add(panelWork, BorderLayout.CENTER);
		
		
	}
	
	
	private int rows = 150;
	private int columns = 150;
	
	
	private Matrix m1 = new Matrix();
	private Matrix m2 = new Matrix();
	
	private Matrix current = m1;
	private Matrix old = m2;
	private Matrix temp = null;
	
	
	private JButton btnStart;
	private JButton btnStop;
	
	
	/*private class InitStep implements Runnable{
		private Random r = new Random();
	
		
		
		public void run() {
			
			//wylosuj 5 komorek i nadaj im kolor
			for(int i=1;i<=5;i++){
				MyCell cell = old.getCell(r.nextInt(columns), r.nextInt(rows));
				cell.init(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			}
			
			
			panelWork.repaint();
		}
	}
	
	private class NextStep implements Runnable{
		//private Random r = new Random();
		
		
		
		public void run() {
						
			NejberResolver2 res = new NejberResolver2();
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
						List<MyCell> cells = old.getNeighborhood(j, i);
						for(MyCell c : cells){
							res.addCell(c);
						}
						
						MyCell bestChoice = res.getBestChoice();
						if(bestChoice != null){
							currCell.init(bestChoice);
							changes++;
						}
					}
				}
			}
			
			System.out.println("Wykonano Zmian: "+changes);
			
			//zamien matrixy
			temp = current;
			current = old;
			old = temp;
			

			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					panelWork.repaint();
				}
			});
			
					
		
		}
	}*/
	
	
	
	private class Process implements Runnable{
		
		
		private Runnable refresh = new Runnable() {
			
			public void run() {
				panelWork.repaint();
			}
		};
		
		private Random r = new Random();
		
		public void run() {
			initStep();
			
			int changes = nextStep();
			while(changes > 0){
				changes = nextStep();
			}
			
			System.err.println("KONIEC");
			
		}
		
		
		private void initStep(){
			for(int i=1;i<=20;i++){
				MyCell cell = old.getCell(r.nextInt(columns), r.nextInt(rows));
				cell.init(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			}
			
			
			SwingUtilities.invokeLater(refresh);
		}
		
		
		private int nextStep(){
			NejberResolver2 res = new NejberResolver2();
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
						List<MyCell> cells = old.getNeighborhood(j, i);
						for(MyCell c : cells){
							res.addCell(c);
						}
						
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
			

			SwingUtilities.invokeLater(refresh);
			
			return changes;
		}
	}
	
	
	
	
	private class NejberResolver{
		
		private HashMap<String, Integer> counter = new HashMap<String, Integer>();
		private HashMap<String, MyCell> objects = new HashMap<String, MyCell>();
		
		public void addCell(MyCell cell){
			if(cell.isInitialized()){
				if(counter.containsKey(cell.getId())){
					counter.put(cell.getId(), counter.get(cell.getId()) + 1);
				}else{
					counter.put(cell.getId(), 1);
					objects.put(cell.getId(), cell);
				}
			}
		}
		
		
		public void clear(){
			counter.clear();
			objects.clear();
		}
		
		
		public MyCell getBestChoice(){
			if(counter.isEmpty()) return null;
			String keyString = null;
			for(String s : objects.keySet()){
				keyString = s;
				break;
			}
			return objects.get(keyString);
		}
		
	}
	
	private class NejberResolver2{
		
		private HashMap<String, MyCell> mapa = new HashMap<String, MyCell>();
		private List<String> ids = new ArrayList<String>();
		private List<MyCell> result = new ArrayList<MyCell>();
		
		private Random r = new Random();
		
		private void addCell(MyCell cell){
			if(cell.isInitialized()){
				mapa.put(cell.getId(), cell);
				ids.add(cell.getId());
			}
		}
		
		
		public void clear(){
			mapa.clear();
			ids.clear();
			result.clear();
		}
		
		
		public MyCell getBestChoice() {
			
			if(ids.isEmpty()) return null;
			
			int max = 0;
			
			for(String unique : mapa.keySet()){
				int fr = Collections.frequency(ids, unique);
				
				if(fr > max){
					max = fr;
					result.clear();
					result.add(mapa.get(unique));
					
				}else if(fr == max){
					result.add(mapa.get(unique));
				}
			}
			
			
			
			
			
			if(result.size() > 1){
				return result.get(r.nextInt(result.size()));
			}else{
				return result.get(0);
			}
			
		}
	}
	
	
	
	private class MyPanel extends JPanel{
		

		
		private int d = 3;
		
		
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
	
	
	
	private class Matrix{
		
		private MyCell[][] matrix = new MyCell[rows][columns];
		
		public Matrix() {
			for(int i=0;i<rows;i++){
				for(int y=0;y<columns;y++){
					matrix[i][y] = new MyCell();
				}
			}
		}
		
		private MyCell getCell(int x, int y){
			return matrix[y][x];
		}
		
		
		private List<MyCell> lista = new ArrayList<MyCell>();
		
		private List<MyCell> getNeighborhood(int x, int y){
			lista.clear();
			if(x-1 >= 0) lista.add(matrix[y][x-1]);
			if(x+1<columns) lista.add(matrix[y][x+1]);
			if(y-1 >= 0) lista.add(matrix[y-1][x]);
			if(y+1<rows) lista.add(matrix[y+1][x]);
			return lista;
		}
		
		
	}
	
	

}