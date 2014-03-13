package com.skimina.daniel.modelowanie.sasiedztwo;

import java.util.ArrayList;
import java.util.List;

import com.skimina.daniel.modelowanie.Matrix;
import com.skimina.daniel.modelowanie.MyCell;

public class SasiedztwoVonNeumann implements Sasiedztwo{
	
	
	
	private List<MyCell> lista = new ArrayList<MyCell>();
	
	
	public List<MyCell> pobierzSasiedztwo(Matrix m, int x, int y) {
		lista.clear();
		if(x-1 >= 0) lista.add(m.getCell(x-1, y));
		if(x+1<m.getColumns()) lista.add(m.getCell(x+1, y));
		if(y-1 >= 0) lista.add(m.getCell(x, y-1));
		if(y+1<m.getRows()) lista.add(m.getCell(x, y+1));
		return lista;
	}
	
	@Override
	public String toString() {
		return "von Neumann";
	}
	
	

}
