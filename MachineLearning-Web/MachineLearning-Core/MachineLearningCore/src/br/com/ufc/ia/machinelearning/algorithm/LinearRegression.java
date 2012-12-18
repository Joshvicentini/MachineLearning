package br.com.ufc.ia.machinelearning.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.ufc.ia.machinelearning.spi.Algorithm;
import br.com.ufc.ia.machinelearning.spi.Parameters;
import br.com.ufc.ia.machinelearning.spi.Point;

public class LinearRegression extends Algorithm<List<Point>> {

	@Override
	public void execute(List<Point> source, Parameters parameters) {
		result = new ArrayList<Point>();
		for(int i =0; i < 50;i++){
			Random rand = new Random();
			result.add(new Point(rand.nextInt(100),rand.nextInt(100)));
		}
	}

}
