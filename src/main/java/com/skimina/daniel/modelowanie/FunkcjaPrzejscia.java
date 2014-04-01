package com.skimina.daniel.modelowanie;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class FunkcjaPrzejscia {

	
	private class MyCellCounter{
		private MyCell cell;
		private int counter;
		
		public MyCellCounter(MyCell cell) {
			this.cell = cell;
			this.counter = 1;
		}
		
		public void incrementCounter(){
			this.counter++;
		}
		
		public int getCounter() {
			return counter;
		}
		
		public MyCell getCell() {
			return cell;
		}
	}
	
	
	private class MyComparator implements Comparator<MyCellCounter>{
		
		public int compare(MyCellCounter o1, MyCellCounter o2) {
			return o1.getCounter()-o2.getCounter();
		}
		
	}
	
	
	public FunkcjaPrzejscia(int probability) {
		this.probability = probability;
		this.mapa = new HashMap<String, MyCellCounter>();
		this.r = new Random();
		this.comp = new MyComparator();
	}
	
	private HashMap<String, MyCellCounter> mapa;
	private Random r;
	private MyComparator comp;
	private int probability;
	
	
	
	
	public void addCells(List<MyCell> cells){
		mapa.clear();
		for(MyCell cell : cells){
			if(cell.isInitialized()){
				if(mapa.containsKey(cell.getId())){
					mapa.get(cell.getId()).incrementCounter();
				}else{
					mapa.put(cell.getId(), new MyCellCounter(cell));
				}
			}
		}
	}
	
	
	
	public MyCell getFirstRuleBestChoice() {
		
		if(mapa.isEmpty()) return null;
			
		MyCellCounter cellCount = Collections.max(mapa.values(), comp);
		
		return cellCount.getCounter()>=5 ? cellCount.getCell() : null;
		
	}
	
	public MyCell getSecondRuleBestChoice() {
		
		if(mapa.isEmpty()) return null;
		
		MyCellCounter cellCount = Collections.max(mapa.values(), comp);
		
		return cellCount.getCounter()>=3 ? cellCount.getCell() : null;
		
	}
	
	public MyCell getThirdRuleBestChoice() {
		
		if(mapa.isEmpty()) return null;
		
		MyCellCounter cellCount = Collections.max(mapa.values(), comp);
		
		return cellCount.getCounter()>=3 ? cellCount.getCell() : null;
		
	}

	public MyCell getFourthRuleBestChoice() {
		
		if(mapa.isEmpty()) return null;
		
		MyCellCounter cellCount = Collections.max(mapa.values(), comp);
		
		
		return r.nextInt(100)<probability ? cellCount.getCell() : null;
		
	}
}
