package org.ndsu.edp.neural.network;

public interface ErrorFunction {

    /**
     * Retruns total network error
     * @return total network error
     */
    public double getTotalError();

    /**
     * Sets total error to zero
     */
    public void reset();

    /**
     * Calculates pattern error for given predicted and target output
     */
    public double[]  calculatePatternError(double[] predictedOutput, double[] targetOutput);

}