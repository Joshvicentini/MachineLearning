package br.com.ufc.ia.machinelearning.enumeration;



public enum MethodEnum {
	LINEAR_REGRESSION("LinearRegression","Theta 0","Theta 1", "Alpha", "Num Iters"),
	DECISION_TREE("DecisionTree"),
	GAUSSIAN_DISCRIMINANT_ANALYSIS("GaussianDiscriminantAnalysis","Parameter1", "Parameter2");
	
	private String className;
	private String[] parametersNames;
	
	private MethodEnum(String className, String ... parametersNames) {
		this.setClassName(className);
		this.parametersNames = parametersNames;
	}
	
	@Override
	public String toString() {
		switch(this){
			case LINEAR_REGRESSION				: return "Linear Regression";
			case DECISION_TREE 					: return "Decision Tree";
			case GAUSSIAN_DISCRIMINANT_ANALYSIS	: return "Gaussian Discriminant Analysis";
			default : return null;
		}
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String[] getParametersNames() {
		return parametersNames;
	}

	public void setParametersNames(String[] parametersNames) {
		this.parametersNames = parametersNames;
	}

}
