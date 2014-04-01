package com.skimina.daniel.modelowanie.sasiedztwo;

import java.util.List;
import java.util.Random;

import com.skimina.daniel.modelowanie.Matrix;
import com.skimina.daniel.modelowanie.MyCell;

public class SasiedztwoHeksagonalne extends Sasiedztwo{
	
private Random r;
	
	public SasiedztwoHeksagonalne() {
		super();
		r = new Random();
	}
	
	public List<MyCell> pobierzSasiedztwo(Matrix m, int x, int y) {
		lista.clear();
		switch(r.nextInt(2)){
		case 0:
			dodajDoListy(m.getCell(x-1, y-1));
			dodajDoListy(m.getCell(x, y-1));
			//dodajDoListy(m.getCell(x+1, y-1));
			dodajDoListy(m.getCell(x-1, y));
			dodajDoListy(m.getCell(x+1, y));
			//dodajDoListy(m.getCell(x-1, y+1));
			dodajDoListy(m.getCell(x, y+1));
			dodajDoListy(m.getCell(x+1, y+1));
			break;
		case 1:
			//dodajDoListy(m.getCell(x-1, y-1));
			dodajDoListy(m.getCell(x, y-1));
			dodajDoListy(m.getCell(x+1, y-1));
			dodajDoListy(m.getCell(x-1, y));
			dodajDoListy(m.getCell(x+1, y));
			dodajDoListy(m.getCell(x-1, y+1));
			dodajDoListy(m.getCell(x, y+1));
			//dodajDoListy(m.getCell(x+1, y+1));
			break;
		}
		return lista;
	}
	
	@Override
	public String toString() {
		return "Heksagonalne";
	}
	
	

}
