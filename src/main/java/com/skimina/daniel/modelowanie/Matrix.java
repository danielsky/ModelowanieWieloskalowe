package com.skimina.daniel.modelowanie;


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
		
		matrix = new MyCell[rows][columns];
		for(int i=0;i<rows;i++){
			for(int y=0;y<columns;y++){
				matrix[i][y] = new MyCell();
			}
		}
	}
	
	public MyCell getCell(int x, int y){
		if(x<0 || x>= columns || y < 0 || y >= rows) return null;
		return matrix[y][x];
	}
	
	
	public int getColumns() {
		return columns;
	}
	
	public int getRows() {
		return rows;
	}
	
	
}
