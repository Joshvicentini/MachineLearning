package br.com.ufc.ia.machinelearning.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.ufc.ia.machinelearning.spi.Algorithm;
import br.com.ufc.ia.machinelearning.spi.Parameters;
import br.com.ufc.ia.machinelearning.spi.Point;

public class LinearRegression extends Algorithm<List<Point>> {

	private static final long serialVersionUID = 1L;

	@Override
	public void execute(List<Point> source, Parameters parameters) {
		this.report.addReport("Executing method Linear Regression...\n");
		result = new ArrayList<Point>();
		for(int i =0; i < 50;i++){
			Random rand = new Random();
			double x = rand.nextInt(100);
			double y = rand.nextInt(100);
			result.add(new Point(x,y));
			this.report.addReport("Added calculated point P(" + x + ", " + y + ").\n");
		}
		this.report.addReport("Executing method Linear Regression...\n");
	}

}
