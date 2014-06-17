package com.skimina.daniel.modelowanie;

import java.awt.Color;


public class MyCell {
	//public static final int EMPTY = 0;
	//public static final int WTRACENIE = -1;
	
	private int id;
	private Color c;
	//private boolean wtracenie = false;
	private double energia = 2.0;
	private boolean rekrystalizacja = false;
	
	private int row;
	private int column;
	
	
	public void setRowAndColumn(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	
	public MyCell() {
		c = Color.WHITE;
		id = -1;
	}
	
	public Color getColor(){
		return c;
	}
	
	public int getId() {
		return id;
	}
	
	
	public void init(MyCell cell){
		this.id = cell.id;
		this.c = cell.c;
		//this.wtracenie = cell.wtracenie;
	}
	
	public void init(Color c, int id){
		this.id = id;
		this.c = c;
	}
	
	/*public void makeAsWtracenie(){
		this.id = WTRACENIE;
		this.c = Color.BLACK;
		this.wtracenie = true;
	}*/
	
	public boolean isInitialized() {
		return id > 0;
	}
	
	/*public boolean isWtracenie(){
		return wtracenie;
	}*/
	
	public void setEnergy(double energia) {
		this.energia = energia;
	}
	
	public double getEnergy(){
		return energia;
	}
	
	public boolean isRekrystalized() {
		return rekrystalizacja;
	}
	
	
	public void makeRekrystalized(int id, Color c) {
		this.energia = 0;
		this.rekrystalizacja = true;
		this.id = id;
		this.c = c;
	}
}
