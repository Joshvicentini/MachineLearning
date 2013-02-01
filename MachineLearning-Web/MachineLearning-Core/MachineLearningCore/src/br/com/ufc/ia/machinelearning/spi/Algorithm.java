package br.com.ufc.ia.machinelearning.spi;

import java.io.Serializable;


/*
 * Nessa interface ha metodos que todos os algoritmos devem ter.
 * Eh passado um template no qual especifica sobre o que o algoritmo eh,
 * de forma que o resultado sera desse tipo.
 * 
 */
public abstract class Algorithm<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	Parameters parameters;
	
	protected T result;
	
	protected String identifier;
	
	public Algorithm() {
		this.report = new Report();
		this.parameters = new Parameters();
	}
	
	public T getResult(){
		return this.result;
	}
	
	protected Report report;
	
	public abstract void execute(T source,Parameters parameters);
	
	public String getIdentifier(){
		return this.identifier;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
}
