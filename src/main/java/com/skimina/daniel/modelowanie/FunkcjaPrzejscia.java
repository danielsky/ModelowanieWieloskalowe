package com.skimina.daniel.modelowanie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class FunkcjaPrzejscia {

	
	private HashMap<Integer, MyCell> mapa = new HashMap<Integer, MyCell>();
	private List<Integer> ids = new ArrayList<Integer>();
	private List<MyCell> result = new ArrayList<MyCell>();
	
	private Random r = new Random();
	
	public void addCells(List<MyCell> cells){
		for(MyCell cell : cells){
			if(cell.isInitialized() && !cell.isWtracenie()){
				mapa.put(cell.getId(), cell);
				ids.add(cell.getId());
			}
		}
	}
	
	
	public void clear(){
		mapa.clear();
		ids.clear();
		result.clear();
	}
	
	
	public MyCell getBestChoice() {
		
		if(ids.isEmpty()) return null;
		
		int max = 0;
		
		for(Integer unique : mapa.keySet()){
			int fr = Collections.frequency(ids, unique);
			
			if(fr > max){
				max = fr;
				result.clear();
				result.add(mapa.get(unique));
				
			}else if(fr == max){
				result.add(mapa.get(unique));
			}
		}
		
		
		
		
		//je¿eli istnie wiecej niz jeden najlepszy wybór wylosuj jeden z nich
		if(result.size() > 1){
			return result.get(r.nextInt(result.size()));
		}else{
			return result.get(0);
		}
		
	}
}
