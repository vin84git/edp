package org.ndsu.edp.neural.network;

import java.io.Serializable;

public class MeanSquaredError implements ErrorFunction, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2134323099188468727L;
	private transient double totalError;
    /**
     * Number of patterns - n 
     */
    private transient double n;

    public MeanSquaredError() {
        reset();
    }


    public void reset() {
        totalError = 0d;
        n = 0;
    }


    public double getTotalError() {
        return totalError / n;
    }

    public double[]calculatePatternError(double[] predictedOutput, double[] targetOutput) {
        double[] patternError = new double[targetOutput.length];

        for (int i = 0; i < predictedOutput.length; i++) {
            patternError[i] =  targetOutput[i] - predictedOutput[i];
            totalError += patternError[i] * patternError[i] * 0.5;
        }
        n++;
        return patternError;
    }
}
