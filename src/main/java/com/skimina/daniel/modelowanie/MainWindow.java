package com.skimina.daniel.modelowanie;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	
	
	private JPanel contentPane;
	private JPanel panelMenu;
	private MyPanel panelWork;
	
	public MainWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 500);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		getContentPane().setLayout(new BorderLayout());
		
		panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.WEST);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStop.setEnabled(true);
				btnStart.setEnabled(false);
				mutex = true;
				Thread t = new Thread(new Podmieniacz(200,250));
				t.start();
			}
		});
		panelMenu.add(btnStart);
		
		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStop.setEnabled(false);
				btnStart.setEnabled(true);
				mutex = false;
			}
		});
		btnStop.setEnabled(false);
		panelMenu.add(btnStop);
		
		panelWork = new MyPanel();
		contentPane.add(panelWork, BorderLayout.CENTER);
		
		
	}
	
	
	private int rows = 30;
	private int columns = 50;
	private Matrix m = new Matrix();
	private boolean mutex = false;
	private JButton btnStart;
	private JButton btnStop;
	
	
	private class Podmieniacz implements Runnable{
		private Random r = new Random();
		private int sleepTime;
		private int changesToRepaint = 100;
		
		public Podmieniacz(int changes, int refreshInterval) {
			changesToRepaint = changes;
			sleepTime = refreshInterval / changesToRepaint;	//1 second
		}
		
		public void run() {
			int counter = 0;
			while(mutex){
				MyCell cell = m.getCell(r.nextInt(columns), r.nextInt(rows));
				cell.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
				try{
					Thread.sleep(sleepTime);
				}catch(InterruptedException ex){
					ex.printStackTrace();
				}
				counter++;
				
				if(counter>changesToRepaint){
					panelWork.repaint();
					counter = 0;
				}
			}
			
			panelWork.repaint();
		}
	}
	
	
	
	private class MyPanel extends JPanel{
		

		
		private int d = 10;
		
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			int startx = (int)((getWidth()-(columns*d))*0.5);
			int starty = (int)((getHeight()-(rows*d))*0.5);
			
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					g.setColor(m.getCell(j, i).getValue());
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
		
		
	}
	
	
	
	
	
	
	
	
	
	
	private static class MyCell{
		
		private static Random r = new Random();
		
		private Color c;
		
		
		public MyCell() {
			c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		}
		
		public Color getValue(){
			return c;
		}
		
		public void setColor(Color c){
			this.c = c;
		}
		
	}

}