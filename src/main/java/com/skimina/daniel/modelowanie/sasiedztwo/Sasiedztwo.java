package com.skimina.daniel.modelowanie.sasiedztwo;

import java.util.List;

import com.skimina.daniel.modelowanie.Matrix;
import com.skimina.daniel.modelowanie.MyCell;

public interface Sasiedztwo {

	public List<MyCell> pobierzSasiedztwo(Matrix m, int x, int y);
}
