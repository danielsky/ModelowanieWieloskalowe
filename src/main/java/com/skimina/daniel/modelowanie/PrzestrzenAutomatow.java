package com.skimina.daniel.modelowanie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;

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
	
	
	/*public void reset(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				old.getCell(j, i).reset();
				current.getCell(j, i).reset();
			}
		}
	}*/
	
	
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
	
	public void makeWtracenie(int x, int y, int d, Wtracenie wtr){
		switch (wtr) {
		case KWADRAT:
			makeWtracenieKwadrat(x, y, d);
			break;
		case KOLO:
			makeWtracenieKolo(x, y, d);
			break;
		case BRAK:
			break;
		}
	}
	
	private void makeWtracenieKwadrat(int x, int y, int d){
		int startX = x - d/2;
		int startY = y - d/2;
		for(int i = startY;i<=startY+d;i++){
			for(int j = startX;j<=startX+d;j++){
				MyCell c = old.getCell(j, i);
				if(c != null){
					c.makeAsWtracenie();
				}
			}
		}
	}
	
	private void makeWtracenieKolo(int x, int y, int d){
		int startX = x - d/2;
		int startY = y - d/2;
		for(int i = startY;i<=startY+d;i++){
			for(int j = startX;j<=startX+d;j++){
				
				int dx = x-j;
				int dy = y-i;
				int r = d/2;
				if(dx*dx+dy*dy<r*r){
					MyCell c = old.getCell(j, i);
					if(c != null){
						c.makeAsWtracenie();
					}
				}
			}
		}
	}
	
	
	
	public PreparedPrzestrzenAutomatow getPreparedPrzestrzenAutomatow(){
		PreparedPrzestrzenAutomatow przestrzen = new PreparedPrzestrzenAutomatow(new Matrix(old));
		return przestrzen;
	}
	
	
	public void usunNiechcianeZiarna(Set<String> ids){
		old.usunNiechcianeZiarna(ids);
		current.reset();
	}
	
	
	public List<MyCell> getEmptyCells(){
		return old.getEmptyCells();
	}
	

}
