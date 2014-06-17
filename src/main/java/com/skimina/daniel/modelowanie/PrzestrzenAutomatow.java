package com.skimina.daniel.modelowanie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.skimina.daniel.modelowanie.sasiedztwo.Sasiedztwo;
import com.skimina.daniel.modelowanie.sasiedztwo.SasiedztwoMoore;

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
	
	public void showEnergyLevel(){
		
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				double energy = old.getCell(j, i).getEnergy()*30;
				Color c = new Color(50, 30, 255- (int) energy);
				image.setRGB(j, i, c.getRGB());
			}
		}
	}
	
	
	public void przydzielEnergie(double energy){
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				old.getCell(j, i).setEnergy(energy);
			}
		}
	}
	
	public void przydzielEnergie(double energyInside, double energyBoundary){
		Sasiedztwo s = new SasiedztwoMoore();
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				MyCell cell = old.getCell(j, i);
				List<MyCell> sasiedzi = pobierzSasiedzwo(s, j, i);
				boolean naGranicy = false;
				for(MyCell n : sasiedzi){
					if(n.getId() != cell.getId()){
						naGranicy = true;
						break;
					}
				}
				old.getCell(j, i).setEnergy(naGranicy ? energyBoundary : energyInside);
			}
		}
	}
	
	
	
	public int applyMC(){
		//get all cell to list
		List<MyCell> cells = new ArrayList<MyCell>();
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				cells.add(old.getCell(j, i));
			}
		}
		
		
		Random r = new Random();
		while(!cells.isEmpty()){
			MyCell cell = cells.get(r.nextInt(cells.size()));
			
			//calculate change energy
			
			
			
			cells.remove(cell);
		}
		
		int unrecrystalized = 0;
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				if(!old.getCell(j, i).isRekrystalized()) unrecrystalized++;
			}
		}
		
		return unrecrystalized;
	}
	
	

}
