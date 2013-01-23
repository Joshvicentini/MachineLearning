package br.com.ufc.ia.machinelearning.spi;
import java.io.Serializable;
import java.util.Stack;

/*
 * Um report eh um log do que esta acontecendo em uma determinada classe.
 * 
 * Exemplo:
 * O mesmo eh utilizado nas classes dos algoritmos, de forma que como um algoritmo
 * pode demorar muito podemos ver em que passo o algoritmo se encontra.
 */
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;

	private Stack<String> reports = new Stack<String>();

	
	public Report() {
		this.reports = new Stack<String>();
	}
	/*
	 * @return report
	 */
	public String getReport() {
		return this.reports.lastElement();
	}

	/*
	 * @param report
	 */
	public void setReport(String report) {
		this.reports.add(report);
	}

	/*
	 * @return reports
	 */
	public String getAllReports() {
		StringBuilder sb = new StringBuilder();
		for(Object report : this.reports.toArray()){
			sb.append((String)report + "\n");
		}
		return sb.toString();
	}
	
	public void addReport(String report){
		this.reports.add(report);
	}

	@Override
	public String toString() {
		return reports.toString();
	}
}
