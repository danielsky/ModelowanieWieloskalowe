package com.skimina.daniel.modelowanie;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomColor {
	
	private Random r;
	
	private List<Color> lista;
	
	public RandomColor() {
		r = new Random();
		lista = new ArrayList<Color>();
		
		
		lista.add(new Color(126,112,120));
		lista.add(new Color(160,85,143));
		lista.add(new Color(30,29,237));
		lista.add(new Color(142,198,193));
		lista.add(new Color(117,111,10));
		lista.add(new Color(81,213,66));
		lista.add(new Color(197,104,129));
		lista.add(new Color(217,126,37));
		
		lista.add(new Color(78,17,111));
		lista.add(new Color(12,202,207));
		lista.add(new Color(17,162,232));
		lista.add(new Color(160,255,90));
		lista.add(new Color(2,80,118));
		lista.add(new Color(252,140,5));
		lista.add(new Color(69,131,9));
		lista.add(new Color(99,181,228));
		lista.add(new Color(254,205,92));
		
		lista.add(new Color(204,92,19));
		lista.add(new Color(156,91,132));
		lista.add(new Color(221,175,191));
		lista.add(new Color(246,207,99));
		lista.add(new Color(119,128,60));
		lista.add(new Color(40,249,57));
		lista.add(new Color(166,192,177));
		lista.add(new Color(90,39,185));
		lista.add(new Color(104,32,220));
		lista.add(new Color(219,254,145));
		
		lista.add(new Color(240,34,245));
		lista.add(new Color(225,107,229));
		lista.add(new Color(55,201,214));
		lista.add(new Color(78,180,68));
		lista.add(new Color(33,86,61));
		lista.add(new Color(47,193,140));
		lista.add(new Color(93,199,132));
		lista.add(new Color(132,202,144));
		lista.add(new Color(24,211,116));
		lista.add(new Color(205,124,162));
		
		lista.add(new Color(87,164,113));
		lista.add(new Color(213,189,202));
		lista.add(new Color(113,72,96));
		lista.add(new Color(195,207,220));
		lista.add(new Color(140,45,193));
		lista.add(new Color(107,111,65));
		lista.add(new Color(41,139,196));
		lista.add(new Color(95,207,255));
		lista.add(new Color(23,33,115));
		lista.add(new Color(246,82,201));
		
		
	}
	
	public Color getRandomColor(){
		Color c = lista.get(r.nextInt(lista.size()));
		lista.remove(c);
		return c;
	}
	
	public int getNumbersOfColors(){
		return lista.size();
	}

}
