package com.skimina.daniel.modelowanie.sasiedztwo;

import java.util.ArrayList;
import java.util.List;

import com.skimina.daniel.modelowanie.Matrix;
import com.skimina.daniel.modelowanie.MyCell;

public class SasiedztwoMoore extends Sasiedztwo{
	
	
	
	public List<MyCell> pobierzSasiedztwo(Matrix m, int x, int y) {
		lista.clear();
		dodajDoListy(m.getCell(x-1, y-1));
		dodajDoListy(m.getCell(x, y-1));
		dodajDoListy(m.getCell(x+1, y-1));
		dodajDoListy(m.getCell(x-1, y));
		dodajDoListy(m.getCell(x+1, y));
		dodajDoListy(m.getCell(x-1, y+1));
		dodajDoListy(m.getCell(x, y+1));
		dodajDoListy(m.getCell(x+1, y+1));
		return lista;
	}
	
	@Override
	public String toString() {
		return "Moore";
	}
	
	

}
