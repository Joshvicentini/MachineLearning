package br.com.ufc.ia.machinelearning.algorithm;


/*
 * Nessa interface ha metodos que todos os algoritmos devem ter.
 * Eh passado um template no qual especifica sobre o que o algoritmo eh,
 * de forma que o resultado sera desse tipo.
 * 
 */
public abstract class Algorithm<T> {
	
	Parameters parameters;
	
	protected T result;
	
	protected String identifier;
	
	public T getResult(){
		return this.result;
	}
	
	protected Report report;
	
	public abstract void execute(T source,Parameters parameters);
	
	public String getIdentifier(){
		return this.identifier;
	}
	
	

}
