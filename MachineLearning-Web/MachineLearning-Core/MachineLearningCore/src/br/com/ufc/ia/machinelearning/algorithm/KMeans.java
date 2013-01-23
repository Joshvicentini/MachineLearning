package br.com.ufc.ia.machinelearning.algorithm;

import java.text.DecimalFormat;
import java.util.Random;

import Jama.Matrix;

public class KMeans {
	/**
	 * private double distanceToCentroid(Matrix X, Matrix centroids, int Xrow, int centroidsrow)
	 * This method computes the distance from a given example in X to a given centroid.
	 * type filter text
	 * @param X - matrix storing the data for the problem
	 * @param centroids - matrix storing the centroids
	 * @param Xrow - the example in X for which the distance is being computed
	 * @param centroidsrow - the centroid for which the distance is being computed
	 * @return - the distance from the example to the centroid
	 */
	private double distanceToCentroid(Matrix X, Matrix centroids, int Xrow, int centroidsrow) {
		double sum = 0.0;
		double[][] Xvals = X.getArray();
    	double[][] centroidsvals = centroids.getArray();
 
    	for (int i = 0; i < X.getColumnDimension(); i++) {
    		sum = sum + Math.pow((Xvals[Xrow][i] - centroidsvals[centroidsrow][i]), 2.0);
    	}
 
		return sum;
	}
 
	/**
	 * private void printCentroids(Matrix centroids, int iteration)
	 * This method prints out the centroids for a given iteration of the 
	 * k-Means algorithm.
	 * 
	 * @param centroids - matrix storing the centroids
	 * @param iteration - the iteration number
	 */
	private void printCentroids(Matrix centroids, int iteration) {
		DecimalFormat formatter = new DecimalFormat(" 0.000000;-0.000000");
 
		double[][] centroidsvals = centroids.getArray();
		System.out.println("Centroids at iteration " + iteration + ":");
		for (int i = 0; i < centroids.getRowDimension(); i++) {
		   for (int j = 0; j < centroids.getColumnDimension(); j++) {	
			   System.out.print(formatter.format(centroidsvals[i][j]) + "   ");
		   }
		   System.out.println();
		}	
		System.out.println();
	}
 
	/**
	 * public Matrix initCentroids(Matrix X, int K)
	 * This method takes a matrix of data and randomly selects K samples
	 * to be used as the initial guesses for the centroids.
	 * 
	 * @param X - matrix storing the data for the problem
	 * @param K - Number of centroids to initialize
	 * @return - matrix representing the centroids 
	 */
    public Matrix initCentroids(Matrix X, int K) {
    	double[][] centroids = new double[K][X.getColumnDimension()];
    	double[][] Xvals = X.getArray();
    	Random randomEntries = new Random();
 
    	for (int i = 0; i < K; i++) {
    		int randomIndex = randomEntries.nextInt(X.getRowDimension());
    		centroids[i] = Xvals[randomIndex];
    	}
 
    	return new Matrix(centroids);
    }
 
    /**
     * public int[] findClosestCentroids(Matrix X, Matrix centroids)
     * This method finds the closes centroid for each example in the data
     * set represented by the matrix X.  The indices of the centroids
     * in the centroids matrix are returned.
     * 
     * @param X - matrix storing the data for the problem
     * @param centroids - matrix storing the centroids
     * @return - integer array storing the indices of the centroids found
     */
    public int[] findClosestCentroids(Matrix X, Matrix centroids) {
    	int K = centroids.getRowDimension();
    	int m = X.getRowDimension();
    	int[] indexArray = new int[m];
 
    	for (int i = 0; i < m; i++) {
    		double mindist = Double.POSITIVE_INFINITY;
    		for (int j = 0; j < K; j++) {
    			double dist = distanceToCentroid(X, centroids, i, j);
    			if (dist < mindist) {
    				mindist = dist;
    				indexArray[i] = j;
    			}
    		}
    	}
    	return indexArray;
    }
 
    /**
     * public Matrix computeCentroids(Matrix X, int[] indexArray, int K)
     * This method is used to compute new centroids for the data stored in the matrix
     * X, using data in indexArray for nearest centroids that was computed using
     * findClosestCentroids.
     * 
     * @param X - matrix storing the data for the problem
     * @param indexArray - integer array storing the indices of the closest centroid
     *                     for each example in X
     * @param K - number of centroids
     * @return - matrix of new centroids
     */
    public Matrix computeCentroids(Matrix X, int[] indexArray, int K) {
    	double[][] Xvals = X.getArray();
    	double[][] centroids = new double[K][X.getColumnDimension()];
    	double[] indexCount = new double[K];
 
    	for (int i = 0; i < X.getRowDimension(); i++) {
    		indexCount[indexArray[i]]++;
    		for (int j = 0; j < X.getColumnDimension(); j++) {
        		centroids[indexArray[i]][j] = centroids[indexArray[i]][j] + Xvals[i][j];
    		}
    	}
 
    	for (int i = 0; i < K; i++) {
    		for (int j = 0; j < X.getColumnDimension(); j++) {
    			centroids[i][j] = centroids[i][j] / indexCount[i];
    		}
    	}
 
    	return new Matrix(centroids);
    }
 
    /**
     * public Matrix run(Matrix X, Matrix centroids, int numIters, boolean showProgress)
     * This method runs the k-Means algorithm to determine the optimum centroids for
     * the data stored in matrix X.  Optionally, progress updates are given as the
     * iterations progress.
     * 
     * @param X - matrix storing the data for the problem
     * @param centroids - matrix storing the initial centroids
     * @param numIters - number of iterations to run
     * @param showProgress - whether or not to show progress as the iterations progress
     * @return - the final centroids computed by the algorithm
     */
    public Matrix run(Matrix X, Matrix centroids, int maxIters, boolean showProgress) {
    	int[] indexArray = new int[X.getRowDimension()];
    	int K = centroids.getRowDimension();
//		double[][] Xvals = X.getArray();
        
 
    	for (int i = 0; i < maxIters; i++) {
    		if (showProgress == true) {
//    			double[][] centroidsvals = centroids.getArray();
                printCentroids(centroids, i);
    		}
    		indexArray = findClosestCentroids(X, centroids);
    		double[][] priorvals = centroids.getArray();
    		centroids = computeCentroids(X, indexArray, K);
 
    		// See if we have reached a point where the centroids aren't moving
    		double[][] curvals = centroids.getArray();
            int smallvals = 0;
            for (int m = 0; m < K; m++) {
            	for (int n = 0; n < X.getColumnDimension(); n++) {
            		if (Math.abs(curvals[m][n] - priorvals[m][n]) <= 0.01) {
            			smallvals = smallvals + 1; 
            		}
            	}
            }
            if (smallvals == K * X.getColumnDimension()) {
            	break;
            }
    	}
 
    	return centroids;
    }
}
