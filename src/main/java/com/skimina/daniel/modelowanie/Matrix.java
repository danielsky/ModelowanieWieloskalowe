package com.skimina.daniel.modelowanie;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
		
		Random r = new Random();
		matrix = new MyCell[rows][columns];
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
				matrix[i][j] = new MyCell(c,j,i);
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
	
	public List<MyCell> getAllCells(){
		
		ArrayList<MyCell> cells = new ArrayList<MyCell>();
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				cells.add(matrix[i][j]);
			}
		}
		return cells;
	}
	
	
}
