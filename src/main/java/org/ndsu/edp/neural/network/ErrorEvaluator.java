package org.ndsu.edp.neural.network;


public class ErrorEvaluator {

	private ErrorFunction errorFunction;

    public ErrorEvaluator(final ErrorFunction errorFunction) {
        this.errorFunction = errorFunction;
    }

    public void processNetworkResult(double[] networkOutput, double[] desiredOutput) {
        errorFunction.calculatePatternError(networkOutput, desiredOutput);
    }

    public Double getResult() {
        return errorFunction.getTotalError();
    }

    
    public void reset() {
        errorFunction.reset();
    }
}
