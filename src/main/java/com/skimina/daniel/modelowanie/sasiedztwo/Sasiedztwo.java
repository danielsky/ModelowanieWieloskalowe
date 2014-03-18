package com.skimina.daniel.modelowanie.sasiedztwo;

import java.util.ArrayList;
import java.util.List;

import com.skimina.daniel.modelowanie.Matrix;
import com.skimina.daniel.modelowanie.MyCell;

public abstract class Sasiedztwo {
	
	
	
	protected List<MyCell> lista = new ArrayList<MyCell>();
	

	public abstract List<MyCell> pobierzSasiedztwo(Matrix m, int x, int y);
	
	
	protected void dodajDoListy(MyCell cell){
		if(cell != null){
			lista.add(cell);
		}
	}
}
