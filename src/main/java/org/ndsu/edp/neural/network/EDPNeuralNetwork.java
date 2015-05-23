package org.ndsu.edp.neural.network;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.SupervisedLearning;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.NeuralNetworkFactory;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EDPNeuralNetwork {

	private static final String NNET_SUFFIX = ".nnet";
	private static final String TSET_SUFFIX = ".tset";
	private static final String TRAIN_SET_PATH = "TrainSet/";
	private static final String TEST_SET_PATH = "TestSet/";
	private static final String NN_PATH = "nn/";
	//static EDPDal ds;
	static MultiLayerPerceptron nn;
	static ObjectMapper mapper = new ObjectMapper();
	//static String rootPath = "/VIP/Private/Ammu/Output/";
	static String rootPath = "/home/ec2-user/EDP/EDP-NN/";
	private static Queue<String> trainLog = new ConcurrentLinkedQueue<String>();
	
	static{
	//	ds = new EDPDal(new EDPDataSourceFactory());
	}
		
	public static void createNN(String name, int i , int o, int h){
		
		String neurosNum = i + " " + h + " " + o;
		
		MultiLayerPerceptron nnet2 = NeuralNetworkFactory.createMLPerceptron(neurosNum, TransferFunctionType.SIGMOID,
				MomentumBackpropagation.class, true, false);
		
		nnet2.setLabel(name);
		
		nnet2.save(rootPath+NN_PATH+name+NNET_SUFFIX);
		
	}
	
	public static void deleteNN(String name){
		
		try{
			 
    		File file = new File(rootPath+NN_PATH+name+NNET_SUFFIX); 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
 
    	}catch(Exception e){
 
    		e.printStackTrace();
 
    	}
		
		
	}
	
	public static boolean uploadDataSet(String name, byte[] bytes, int i , int o){
		
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(rootPath+TRAIN_SET_PATH+name)));
            stream.write(bytes);
            stream.close();
            
            // create training set
            DataSet trainingSet = null;
            try {
                trainingSet = TrainingSetImport.importFromFile(rootPath+TRAIN_SET_PATH+name, i, o, ",");
                trainingSet.setFilePath(rootPath+TRAIN_SET_PATH+name+TSET_SUFFIX);
                trainingSet.save();
                
            } catch (FileNotFoundException ex) {
                System.out.println("File not found!");
            } catch (IOException | NumberFormatException ex) {
                System.out.println("Error reading file or bad number format!");
            }
            
            return true;
        } catch (Exception e) {
        	return false;
        }	       
			
	}
	
	
	public static String[] getLogs(){
		
		List<String> logs = new ArrayList<String>();
		long st = new Date().getTime();
		long et = new Date().getTime();
		while((et-st) < 5000){
			String l = trainLog.poll();
			if(l == null) break;
			logs.add(l);
		}
		
		return logs.toArray(new String[logs.size()]);
			
	}
	
	public static void train(String name, Double l , Double me, int mi, Double mom,String tsName){
		
		MultiLayerPerceptron neuralNetwork = (MultiLayerPerceptron)NeuralNetwork.createFromFile(rootPath+NN_PATH+name);		
		
		if(mom != null && mom > 0) ((MomentumBackpropagation)neuralNetwork.getLearningRule()).setMomentum(mom);
		
		if(l != null && l > 0) neuralNetwork.getLearningRule().setLearningRate(l);        
		if(mi > 0) neuralNetwork.getLearningRule().setMaxIterations(mi);        
        if(me != null && me > 0) neuralNetwork.getLearningRule().setMaxError(me);
        neuralNetwork.getLearningRule().addListener(new LearningEventListener() {
			
			@Override
			public void handleLearningEvent(LearningEvent event) {
				
				SupervisedLearning rule = (SupervisedLearning)event.getSource();
				trainLog.add("Training, Iteration " + rule.getCurrentIteration() + ", Error:" + rule.getTotalNetworkError());
				System.out.println( "Training, Iteration " + rule.getCurrentIteration() + ", Error:" + rule.getTotalNetworkError());
			}
		});
        
        // create training set
        DataSet trainingSet = DataSet.load(rootPath+TRAIN_SET_PATH+tsName);
        nn = neuralNetwork;
        nn.learn(trainingSet);
        nn.save(rootPath+NN_PATH+nn.getLabel()+NNET_SUFFIX);
       
	}
	
	public static void stopLearning(){
		if(nn != null){ 
			nn.stopLearning();	
			nn.save(rootPath+NN_PATH+nn.getLabel()+NNET_SUFFIX);
		}
	}
	
	public static void pauseLearning(){
		if(nn != null){
			nn.pauseLearning();
			nn.save(rootPath+NN_PATH+nn.getLabel()+NNET_SUFFIX);
		}
	}
	
	public static void resumeLearning(){
		if(nn != null){
			nn.resumeLearning();
			//nn.save(nn.getLabel());
		}
	}
	
	
	public static void reset(){
		if(nn != null){
			nn.stopLearning();
			nn.reset();
			nn.save(rootPath+NN_PATH+nn.getLabel()+NNET_SUFFIX);
		}
	}
	
	public static CrossValidationResult crossValidate(String name, String tsName){
		
		MultiLayerPerceptron neuralNetwork = (MultiLayerPerceptron)NeuralNetwork.createFromFile(rootPath+NN_PATH+name);	
		CrossValidationResult cvResult = new CrossValidationResult();
		
		TestResult results[] = new TestResult [12];
		 // create training set
        DataSet trainingSet = DataSet.load(rootPath+TRAIN_SET_PATH+tsName);
        
        Random rn = new Random();       
        int daysInMonth;
        int min = 0;
        int max = 0;
        
        List<DataSetRow> selDays = new ArrayList<DataSetRow>();
        
        int [] daysPicked = new int[12];
        
        
        for(int i=1; i<=12; i++){
        	min = max +1;
        	if (i == 4 || i == 6 || i == 9 || i == 11)
        		daysInMonth = 30;
        	else if (i == 2) 
        		daysInMonth = 28;
        	else 
        		daysInMonth = 31;
        	
        	max = (min -1) + daysInMonth; 
        	
        	int randomDay = rn.nextInt(max - min) + min;
        	
        	daysPicked[i-1] = randomDay;
        	
        	selDays.add(trainingSet.getRowAt(randomDay));
        }
        
        
        
        double nwOutput[][] = new double[selDays.size()][24];
        double dOutput[][] = new double[selDays.size()][24];
        double err[] = new double[12];
        
        
        ErrorEvaluator errorEval = new ErrorEvaluator(new MeanSquaredError());
        
       
       
        for(int k=1; k<=12; k++){
        	
        	TestResult result = new TestResult();
        	int c = 0;
        	 int j = k;
	        for (DataSetRow r : selDays) {
	        	
	        	if(k==j){
	        		j++;
	        		continue;
	        	}
	        	
	        	neuralNetwork.setInput(r.getInput());
	        	neuralNetwork.calculate();
	        	nwOutput[c] = neuralNetwork.getOutput();
	        	dOutput[c] =r.getDesiredOutput();
	        	errorEval.processNetworkResult(neuralNetwork.getOutput(), r.getDesiredOutput());
	        	c++;
	        	j++;
			}
	        
	        double tmse = errorEval.getResult();       
	        err[k-1] = tmse;
	        
	        result.setTmse(tmse);
	        result.setNwOp(nwOutput);
	        result.setDesiredOp(dOutput);
	        
	        results[k-1] = result;
	        
	       
        
        }
        
        Mean m = new Mean();
        double meanErr = m.evaluate(err);
        
        StandardDeviation dev = new StandardDeviation();
        double stdDevErr = dev.evaluate(err);
        
        Variance var = new Variance();
        double varErr = var.evaluate(err);
        
        cvResult.setMean(meanErr);
        cvResult.setStdDev(stdDevErr);
        cvResult.setVar(varErr);
        cvResult.setTr(results);
        
        
        return cvResult;
		
	}
   
	public static TestResult test(String name, String tsName){
		
		MultiLayerPerceptron neuralNetwork = (MultiLayerPerceptron)NeuralNetwork.createFromFile(rootPath+NN_PATH+name);	
		TestResult result = new TestResult();
		 // create training set
		 // create training set
        DataSet trainingSet = DataSet.load(rootPath+TRAIN_SET_PATH+tsName);
        
        Iterator<DataSetRow> it = trainingSet.iterator();
        double nwOutput[][] = new double[trainingSet.getRows().size()][24];
        double dOutput[][] = new double[trainingSet.getRows().size()][24];
        int c = 0;
        ErrorEvaluator errorEval = new ErrorEvaluator(new MeanSquaredError());
        while(it.hasNext()){
        	
        	DataSetRow r = it.next();
        	neuralNetwork.setInput(r.getInput());
        	neuralNetwork.calculate();
        	double op[] =neuralNetwork.getOutput();
        	int k = 0;
        	for (double d : op) {
        		nwOutput[c][k] = d;
        		k++;
			}
        	//nwOutput[c] = op;
        	dOutput[c] =r.getDesiredOutput();
        	errorEval.processNetworkResult(neuralNetwork.getOutput(), r.getDesiredOutput());
        	c++;
        }
        
        double tmse = errorEval.getResult();       
        result.setTmse(tmse);
        result.setNwOp(nwOutput);
        result.setDesiredOp(dOutput);

        System.out.println("Mean Square Error:"+tmse);
        System.out.println("Network Output:"+nwOutput);
        
        return result;
        
		
	}
	
	   
		public static TestResult predict(String name, String tsName){
			
			MultiLayerPerceptron neuralNetwork = (MultiLayerPerceptron)NeuralNetwork.createFromFile(rootPath+NN_PATH+name);	
			TestResult result = new TestResult();
			 // create training set
			DataSet trainingSet = DataSet.load(rootPath+TRAIN_SET_PATH+tsName);
			
	        Iterator<DataSetRow> it = trainingSet.iterator();
	        double nwOutput[][] = new double[trainingSet.getRows().size()][24];
	        //double dOutput[][] = new double[trainingSet.getRows().size()][24];
	        int c = 0;
	        //ErrorEvaluator errorEval = new ErrorEvaluator(new MeanSquaredError());
	        while(it.hasNext()){
	        	
	        	DataSetRow r = it.next();
	        	neuralNetwork.setInput(r.getInput());
	        	neuralNetwork.calculate();
	        	nwOutput[c] = neuralNetwork.getOutput();
	        	//dOutput[c] =r.getDesiredOutput();
	        	//errorEval.processNetworkResult(neuralNetwork.getOutput(), r.getDesiredOutput());
	        	c++;
	        }
	        
	        //double tmse = errorEval.getResult();       
	        //result.setTmse(tmse);
	        result.setNwOp(nwOutput);
	        //result.setDesiredOp(dOutput);

	        //System.out.println("Mean Square Error:"+tmse);
	        System.out.println("Network Output:"+nwOutput);
	        
	        return result;
	        
			
		}
	
	public static void calculate(String name, Double l,double [] input){
		
		MultiLayerPerceptron neuralNetwork = (MultiLayerPerceptron)NeuralNetwork.createFromFile(name+NNET_SUFFIX);	
		
        	neuralNetwork.setInput(input);
        	neuralNetwork.calculate();
        	double nwOutput[] = neuralNetwork.getOutput();
        	System.out.println(nwOutput);
		
	}
	
	public static void calculate(String name, Double l,String tsName){
		
		MultiLayerPerceptron neuralNetwork = (MultiLayerPerceptron)NeuralNetwork.createFromFile(name+NNET_SUFFIX);	
		
		 // create training set
		DataSet trainingSet = DataSet.load(rootPath+TRAIN_SET_PATH+tsName);
		
        Iterator<DataSetRow> it = trainingSet.iterator();
        double nwOutput[][] = new double[trainingSet.getRows().size()][24];
        int c = 0;
        
        while(it.hasNext()){
        	
        	DataSetRow r = it.next();
        	neuralNetwork.setInput(r.getInput());
        	neuralNetwork.calculate();
        	nwOutput[c] = neuralNetwork.getOutput();
        }
		      
        System.out.println(nwOutput);
	}
	
	
	public static List<String> getNeuralNetworkList() {
		List<String> results = new ArrayList<String>();

		File[] files = new File(rootPath+NN_PATH).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isFile() && !file.isHidden()) {
		        results.add(file.getName());
		    }
		}
		
		return results;
	}
	
	public static List<String> getTrainingSetList() {

		List<String> results = new ArrayList<String>();

		File[] files = new File(rootPath+TRAIN_SET_PATH).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isFile() && !file.isHidden() && file.getName().contains(".tset")) {
		        results.add(file.getName());
		    }
		}
		
		return results;
	
	}
	
	public static List<String> getTestSetList() {

		List<String> results = new ArrayList<String>();

		File[] files = new File(rootPath+TEST_SET_PATH).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isFile() && !file.isHidden()) {
		        results.add(file.getName());
		    }
		}
		
		return results;
	
	}
	
	public static void main(String[] args) {
		//EDPNeuralNetwork.createNN("edp-1", 27, 55, 24);
		//EDPNeuralNetwork.train("edp-1", 0.2, 0.01,10000, 0.7, "demand_2008.tset");
		System.out.println(EDPNeuralNetwork.getNeuralNetworkList());
	}
	
}
