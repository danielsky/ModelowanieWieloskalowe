package com.skimina.daniel.modelowanie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import com.skimina.daniel.modelowanie.sasiedztwo.Sasiedztwo;
import com.skimina.daniel.modelowanie.sasiedztwo.SasiedztwoMoore;

public class PrzestrzenAutomatow {
	
	
	private int rows;
	private int columns;
	
	
	private BufferedImage image;
	
	private Matrix matrix;
	
	private Sasiedztwo s;
	private Random r;
	
	
	
	public PrzestrzenAutomatow(int rows, int columns, boolean cycle) {
		
		this.matrix = new Matrix(rows, columns, cycle);
		
		this.image = new BufferedImage(columns, rows, BufferedImage.TYPE_INT_RGB);
		this.rows = rows;
		this.columns = columns;
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, columns, rows);
		
		this.s = new SasiedztwoMoore();
		this.r = new Random();
	}
	
	
	
	
	
	
	public Image getImage(){
		return image;
	}
	
	
	
	public MyCell getCell(int x, int y){
		return matrix.getCell(x, y);
	}
	
	
	public List<MyCell> pobierzSasiedzwo(Sasiedztwo s, int x, int y){
		return s.pobierzSasiedztwo(matrix, x, y);
	}
	
	
	public void wizualizuj(){
		
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				Color c = matrix.getCell(j, i).getColor();
				image.setRGB(j, i, c.getRGB());
			}
		}
	}	
	
	public List<MyCell> getAllCells(){
		return matrix.getAllCells();
	}
	
	public void testujEnergie(MyCell cell){
		List<MyCell> sasiedzi = s.pobierzSasiedztwo(matrix, cell.getX(), cell.getY());
		
		int currEnergy = calculateEnergy(cell.getId(), sasiedzi);
		MyCell randCell = sasiedzi.get(r.nextInt(sasiedzi.size()));
		int newEnergy = calculateEnergy(randCell.getId(), sasiedzi);
		if(newEnergy <= currEnergy){
			cell.init(randCell);
		}
		
	}
	
	private int calculateEnergy(int id, List<MyCell> cells){
		int energy = 0;
		for(MyCell cell : cells){
			if(cell.getId() != id) energy++;
		}
		
		return energy;
	}

}
