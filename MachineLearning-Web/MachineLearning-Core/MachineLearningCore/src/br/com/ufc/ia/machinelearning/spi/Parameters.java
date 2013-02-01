package br.com.ufc.ia.machinelearning.spi;

import java.util.HashMap;
import java.util.Map;

/*
 * Parametros que todos os algoritmos devem ter
 * 
 */
public class Parameters {
	
	private Map<String,Object> parameters;
	
	public Parameters() {
		this.parameters = new HashMap<String, Object>();
	}
	
	public void addParam(String name, Object o){
		parameters.put(name, o);
	}
	
	public void removeParam(Object o){
		parameters.remove(o);
	}
	
	public void updateParam(String name, Object o){
		parameters.put(name, o);
	}
	
	public Object getParam(String name){
		return parameters.get(name);
	}
	
	public String printValues(){
		String values = "";
		for(String parameter:parameters.keySet()){
			values += parameter + ":" + parameters.get(parameter) + ", ";
		}
		return values.substring(0,values.length()-2) + ".";
	}

}
