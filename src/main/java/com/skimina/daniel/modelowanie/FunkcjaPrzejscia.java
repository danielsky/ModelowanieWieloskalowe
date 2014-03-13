package com.skimina.daniel.modelowanie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class FunkcjaPrzejscia {

	
	private HashMap<String, MyCell> mapa = new HashMap<String, MyCell>();
	private List<String> ids = new ArrayList<String>();
	private List<MyCell> result = new ArrayList<MyCell>();
	
	private Random r = new Random();
	
	public void addCells(List<MyCell> cells){
		for(MyCell cell : cells){
			if(cell.isInitialized()){
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
		
		for(String unique : mapa.keySet()){
			int fr = Collections.frequency(ids, unique);
			
			if(fr > max){
				max = fr;
				result.clear();
				result.add(mapa.get(unique));
				
			}else if(fr == max){
				result.add(mapa.get(unique));
			}
		}
		
		
		
		
		
		if(result.size() > 1){
			return result.get(r.nextInt(result.size()));
		}else{
			return result.get(0);
		}
		
	}
}
