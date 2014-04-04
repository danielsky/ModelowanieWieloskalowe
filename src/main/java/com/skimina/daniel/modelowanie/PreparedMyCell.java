package com.skimina.daniel.modelowanie;

import java.awt.Color;

public class PreparedMyCell {
	
	private String id;
	private Color color;
	
	private boolean selected;
	
	public PreparedMyCell(MyCell cell) {
		id = cell.getId();
		color = cell.getColor();
		selected = true;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void toogleSelection(){
		selected = !selected;
	}

}
