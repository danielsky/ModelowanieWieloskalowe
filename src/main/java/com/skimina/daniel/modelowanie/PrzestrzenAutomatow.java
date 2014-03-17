package com.skimina.daniel.modelowanie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import com.skimina.daniel.modelowanie.sasiedztwo.Sasiedztwo;

public class PrzestrzenAutomatow {
	
	
	private int rows;
	private int columns;
	
	
	private BufferedImage image;
	
	
	private Matrix current;
	private Matrix old;
	
	private Matrix temp = null;
	
	public PrzestrzenAutomatow(int rows, int columns, boolean cycle) {
		
		this.current = new Matrix(rows, columns, cycle);
		this.old = new Matrix(rows, columns, cycle);
		
		this.image = new BufferedImage(columns, rows, BufferedImage.TYPE_INT_RGB);
		this.rows = rows;
		this.columns = columns;
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, columns, rows);
	}
	
	
	public void switchMatrix(){
		temp = current;
		current = old;
		old = temp;
	}
	
	
	
	public Image getImage(){
		return image;
	}
	
	
	
	public MyCell getOldCell(int x, int y){
		return old.getCell(x, y);
	}
	
	public MyCell getNewCell(int x, int y){
		return current.getCell(x, y);
	}
	
	
	public void reset(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				old.getCell(j, i).reset();
				current.getCell(j, i).reset();
			}
		}
	}
	
	
	public List<MyCell> pobierzSasiedzwo(Sasiedztwo s, int x, int y){
		return s.pobierzSasiedztwo(old, x, y);
	}
	
	
	public void wizualizuj(){
		
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				Color c = old.getCell(j, i).getColor();
				//System.out.println(c.getBlue()+" "+c.getRed()+" "+c.getGreen());
				image.setRGB(j, i, c.getRGB());
			}
		}
	}
	
	

}
