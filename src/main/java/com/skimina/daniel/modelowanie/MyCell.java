package com.skimina.daniel.modelowanie;

import java.awt.Color;
import java.util.UUID;


public class MyCell {
	private String id;
	private Color c;
	private boolean wtracenie = false;
	
	
	public MyCell() {
		//reset();
		c = Color.WHITE;
		id = "";
	}
	
	public void reset(){
		c = Color.WHITE;
		id = "";
		wtracenie = false;
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
		this.wtracenie = cell.wtracenie;
	}
	
	public void init(Color c){
		this.id = UUID.randomUUID().toString();
		this.c = c;
	}
	
	public void makeAsWtracenie(){
		this.id = "WTRACENIE";
		this.c = Color.BLACK;
		this.wtracenie = true;
	}
	
	public void makeGrainAsWtracenie(){
		this.wtracenie = true;
	}
	
	public boolean isInitialized() {
		return !id.isEmpty();
	}
	
	public boolean isWtracenie(){
		return wtracenie;
	}
}
