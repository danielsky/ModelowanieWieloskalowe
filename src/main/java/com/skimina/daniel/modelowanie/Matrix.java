package com.skimina.daniel.modelowanie;

import java.util.Set;


public class Matrix{
	
	private MyCell[][] matrix;
	
	private int rows;
	private int columns;
	private boolean cycle;
	
	
	public Matrix(int rows, int columns) {
		this(rows, columns, false);
	}
	
	public Matrix(int rows, int columns, boolean cycle) {
		
		this.rows = rows;
		this.columns = columns;
		this.cycle = cycle;
		
		matrix = new MyCell[rows][columns];
		for(int i=0;i<rows;i++){
			for(int y=0;y<columns;y++){
				matrix[i][y] = new MyCell();
			}
		}
	}
	
	public Matrix(Matrix parent) {
		
		this.rows = parent.rows;
		this.columns = parent.columns;
		this.cycle = parent.cycle;
		
		matrix = new MyCell[rows][columns];
		for(int i=0;i<rows;i++){
			for(int y=0;y<columns;y++){
				matrix[i][y] = new MyCell();
				matrix[i][y].init(parent.matrix[i][y]);
			}
		}
	}
	
	public MyCell getCell(int x, int y){
		if(cycle){
			if(x<0 || x >= columns) x = (x % columns + columns) % columns;
			if(y<0 || y >= rows) y = (y % rows + rows) % rows;
		}else if(x<0 || x>= columns || y < 0 || y >= rows) return null;
		return matrix[y][x];
	}
	
	
	public int getColumns() {
		return columns;
	}
	
	public int getRows() {
		return rows;
	}
	
	
	public void usunNiechcianeZiarna(Set<String> ids){
		
	}
	
	
}
