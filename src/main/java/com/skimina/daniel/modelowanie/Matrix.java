package com.skimina.daniel.modelowanie;

import java.util.ArrayList;
import java.util.List;

public class Matrix{
	
	private MyCell[][] matrix;
	
	private int rows;
	private int columns;
	
	public Matrix(int rows, int columns) {
		
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
		return matrix[y][x];
	}
	
	
	public int getColumns() {
		return columns;
	}
	
	public int getRows() {
		return rows;
	}
	
	private List<MyCell> lista = new ArrayList<MyCell>();
	
	public List<MyCell> getNeighborhood(int x, int y){
		lista.clear();
		if(x-1 >= 0) lista.add(matrix[y][x-1]);
		if(x+1<columns) lista.add(matrix[y][x+1]);
		if(y-1 >= 0) lista.add(matrix[y-1][x]);
		if(y+1<rows) lista.add(matrix[y+1][x]);
		return lista;
	}
	
	
}
