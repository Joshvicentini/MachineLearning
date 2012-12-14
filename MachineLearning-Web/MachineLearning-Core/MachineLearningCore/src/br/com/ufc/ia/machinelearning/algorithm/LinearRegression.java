package br.com.ufc.ia.machinelearning.algorithm;

import java.util.List;

import br.com.ufc.ia.machinelearning.algorithm.Algorithm;
import br.com.ufc.ia.machinelearning.algorithm.Parameters;
import br.com.ufc.ia.machinelearning.algorithm.Point;

public class LinearRegression extends Algorithm<List<Point>> {

	@Override
	public void execute(List<Point> source, Parameters parameters) {
		for(int i =0; i < 500;i++){
			result.add(new Point((Math.random() % 100),(Math.random() % 100) ));
		}
	}

}
