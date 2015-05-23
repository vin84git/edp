package org.ndsu.edp.neural.network;

public class TestResult {

	double tmse;
	double nwOp[][];
	double desiredOp[][];
	public double getTmse() {
		return tmse;
	}

	public void setTmse(double tmse) {
		this.tmse = tmse;
	}

	public double[][] getNwOp() {
		return nwOp;
	}

	public void setNwOp(double[][] nwOp) {
		this.nwOp = nwOp;
	}

	public double[][] getDesiredOp() {
		return desiredOp;
	}

	public void setDesiredOp(double[][] desiredOp) {
		this.desiredOp = desiredOp;
	}
	
	
	
}
