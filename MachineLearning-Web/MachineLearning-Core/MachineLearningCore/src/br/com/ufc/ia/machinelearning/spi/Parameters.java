package br.com.ufc.ia.machinelearning.spi;

import java.util.HashMap;

/*
 * Parametros que todos os algoritmos devem ter
 * 
 */
public class Parameters {
	
	private HashMap<String,Object> parameters;
	
	public void addParam(String name, Object o){
		parameters.put(name, o);
	}
	
	public void removeParam(Object o){
		parameters.remove(o);
	}
	
	public void updateParam(String name, Object o){
		parameters.put(name, o);
	}

}
