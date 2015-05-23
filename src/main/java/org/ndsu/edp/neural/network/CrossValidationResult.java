package org.ndsu.edp.neural.network;

public class CrossValidationResult {

	TestResult tr[];
	
	double mean;
	
	double var;
	
	double stdDev;

	public TestResult[] getTr() {
		return tr;
	}

	public void setTr(TestResult[] tr) {
		this.tr = tr;
	}

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getVar() {
		return var;
	}

	public void setVar(double var) {
		this.var = var;
	}

	public double getStdDev() {
		return stdDev;
	}

	public void setStdDev(double stdDev) {
		this.stdDev = stdDev;
	}
	
	
}
