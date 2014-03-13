package com.skimina.daniel.modelowanie;

import java.awt.Color;
import java.util.UUID;


public class MyCell {
	private String id;
	private Color c;
	
	
	public MyCell() {
		reset();
	}
	
	public void reset(){
		c = Color.WHITE;
		id = "";
	}
	
	public Color getColor(){
		return c;
	}
	
	public String getId() {
		return id;
	}
	
	
	public void init(MyCell cell){
		this.id = cell.id;
		this.c = cell.c;
	}
	
	public void init(Color c){
		this.id = UUID.randomUUID().toString();
		this.c = c;
	}
	
	public boolean isInitialized() {
		return !id.isEmpty();
	}
}
