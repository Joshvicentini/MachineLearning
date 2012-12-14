package br.com.ufc.ia.machinelearning.enumeration;


public enum MethodEnum {
	LINEAR_REGRESSION("LinearRegression");
	
	private String className;
	
	private MethodEnum(String className) {
		this.setClassName(className);
	}
	
	@Override
	public String toString() {
		switch(this){
			case LINEAR_REGRESSION : return "Linear Regression";
			default : return null;
		}
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
