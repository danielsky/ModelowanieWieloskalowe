package com.skimina.daniel.modelowanie;

import java.awt.Color;


public class MyCell {
	
	private static int currentId = 1;
	
	private static int getNewId(){
		
		return currentId++;
	}
	
	
	
	private int id;
	private Color c;
	private int x;
	private int y;
	
	
	
	public Color getColor(){
		return c;
	}
	
	public int getId() {
		return id;
	}
	
	
	public void init(MyCell cell){
		this.id = cell.id;
		this.c = cell.c;
	}
	
	
	public MyCell(Color c, int x, int y){
		this.id = getNewId();
		this.c = c;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
