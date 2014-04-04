package com.skimina.daniel.modelowanie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Set;

public class PreparedPrzestrzenAutomatow {
	
	private Matrix matrix;
	private BufferedImage image;
	
	public PreparedPrzestrzenAutomatow(Matrix m) {
		this.matrix = m;
		this.image = new BufferedImage(m.getColumns(), m.getRows(), BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, m.getColumns(), m.getColumns());
	}
	
	
	public BufferedImage getImage() {
		return image;
	}
	
	
	public void wizualizuj(Set<String> enabledIds){
		
		for(int i=0;i<matrix.getRows();i++){
			for(int j=0;j<matrix.getColumns();j++){
				MyCell cell = matrix.getCell(j, i); 
				Color c = enabledIds.contains(cell.getId()) ? cell.getColor() : Color.WHITE;
				image.setRGB(j, i, c.getRGB());
			}
		}
	}

}
