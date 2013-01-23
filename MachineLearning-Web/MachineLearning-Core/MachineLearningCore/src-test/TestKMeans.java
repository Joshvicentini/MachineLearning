import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import br.com.ufc.ia.machinelearning.algorithm.KMeans;

import Jama.Matrix;


public class TestKMeans {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		double[][] Xvals = new double[300][2];
        try {
        	int count = 0;
			Scanner input = new Scanner(new File("data/k_means_example.txt"));
			while (input.hasNext()) {
				String nextLine = input.nextLine();
				String[] stringVals = nextLine.split(new String(" "));
 
				Xvals[count][0] = Double.valueOf(stringVals[0]);
				Xvals[count][1] = Double.valueOf(stringVals[1]);
 
				count++;
			}
			input.close();
        } catch (Exception e) {
        	System.out.println("Could not open data/k_means_example.txt");
        }
 
        Matrix X = new Matrix(Xvals);
        KMeans km = new KMeans();
        Matrix centroids = km.initCentroids(X, 3);
        centroids = km.run(X, centroids, 10, true);
	}

}
