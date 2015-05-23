package org.ndsu.edp.controller;

import java.util.List;

import org.ndsu.edp.neural.network.CrossValidationResult;
import org.ndsu.edp.neural.network.EDPNeuralNetwork;
import org.ndsu.edp.neural.network.NNData;
import org.ndsu.edp.neural.network.TestResult;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BaseController {

	private static int counter = 0;
	private static final String VIEW_INDEX = "index";
	private static final String LOG_INDEX = "log";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);
	 
	ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);
		logger.debug("[welcome] counter : {}", counter);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/logs", method = RequestMethod.GET)
	public String logs(ModelMap model) {

		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);
		logger.debug("[welcome] counter : {}", counter);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return LOG_INDEX;

	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {

		model.addAttribute("message", "Welcome " + name);
		model.addAttribute("counter", ++counter);
		logger.debug("[welcomeName] counter : {}", counter);
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/train", method = RequestMethod.POST)
	public @ResponseBody String train(@RequestParam("nnselect") String nn,
			@RequestParam("tsselect") String ts,@RequestParam("lr") String lr,
			@RequestParam("mi") String mi, @RequestParam("mom") String mom,
			@RequestParam("me") String me, ModelMap model) {
		try{
			double learnRate = Double.valueOf(lr);
			double maxErr = Double.valueOf(me);
			double momen = Double.valueOf(mom);
			int maxItr = StringUtils.isEmpty(mi)?0: Integer.valueOf(mi);
			
		
			EDPNeuralNetwork.train(nn, learnRate, maxErr, maxItr, momen, ts);
			
			return "success";
		}catch(Throwable e){
			e.printStackTrace();
			return "failure";
		}

	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody String test(@RequestParam("t") String t,@RequestParam("testnnselect") String nn,
			@RequestParam("testtsselect") String ts, ModelMap model) {

		try{
			TestResult res = null;
			if("1".equals(t)){
				res=  EDPNeuralNetwork.test(nn, ts);
			}
			if("2".equals(t)){
				CrossValidationResult crres=  EDPNeuralNetwork.crossValidate(nn, ts);
				return mapper.writeValueAsString(crres);
			}
			if("3".equals(t)){
				res=  EDPNeuralNetwork.test(nn, ts);
			}
			return mapper.writeValueAsString(res);
		}catch(Throwable e){
			e.printStackTrace();
			return "failure";
		}

	}
	
	@RequestMapping(value = "/predict", method = RequestMethod.POST)
	public @ResponseBody String predict(@RequestParam("pnnselect") String nn,
			@RequestParam("ptsselect") String ts,@RequestParam("ptrip") String trip, ModelMap model) {

		try{
			//int ips = Integer.valueOf(trip);
			TestResult res = null;
			res=  EDPNeuralNetwork.predict(nn, ts);
			return mapper.writeValueAsString(res);
		}catch(Throwable e){
			e.printStackTrace();
			return "failure";
		}

	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String createNN(@RequestParam("nam") String name,@RequestParam("ipn") String ipn,@RequestParam("hn") String hn,@RequestParam("opn") String opn,  ModelMap model) {
		
		try{			
			int i = Integer.valueOf(ipn);
			int h = Integer.valueOf(hn);
			int o = Integer.valueOf(opn);			
			EDPNeuralNetwork.createNN(name, i, o, h);
			
			return "success";
		}catch(Throwable e){
			return "failure";
		}
		

	}
	
	@RequestMapping(value = "/trainlog", method = RequestMethod.GET)
	public @ResponseBody String trainlog(ModelMap model) {
		
		try{			
			String[] logs = EDPNeuralNetwork.getLogs();
			return mapper.writeValueAsString(logs);
		}catch(Throwable e){
			return "failure";
		}
		

	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public @ResponseBody String reset(ModelMap model) {
		
		try{			
			EDPNeuralNetwork.reset();
			return "success";
		}catch(Throwable e){
			return "failure";
		}
		

	}
	
	@RequestMapping(value = "/pause", method = RequestMethod.GET)
	public @ResponseBody String pause(ModelMap model) {
		
		try{			
			EDPNeuralNetwork.pauseLearning();
			return "success";
		}catch(Throwable e){
			return "failure";
		}
		

	}
	
	@RequestMapping(value = "/resume", method = RequestMethod.GET)
	public @ResponseBody String resume(ModelMap model) {
		
		try{			
			EDPNeuralNetwork.resumeLearning();
			return "success";
		}catch(Throwable e){
			return "failure";
		}

	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public @ResponseBody String stop(ModelMap model) {
		
		try{			
			EDPNeuralNetwork.stopLearning();
			return "success";
		}catch(Throwable e){
			return "failure";
		}

	}
	
	@RequestMapping(value = "/getData", method = RequestMethod.GET)
	public @ResponseBody String getNNData(ModelMap model) {
		
		List<String> nnList = EDPNeuralNetwork.getNeuralNetworkList();
		List<String> tsList = EDPNeuralNetwork.getTrainingSetList();
		NNData data = new NNData();
		data.setNn(nnList);
		data.setTs(tsList);
		
		try {
			return mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "failed";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("nam") String name ,@RequestParam("trip") String trip,
			@RequestParam("trop") String trop, 
            @RequestParam("tsfile") MultipartFile file){
		
		
        if (!file.isEmpty()) {
            try {
            	int ips = Integer.valueOf(trip);
    			int ops = Integer.valueOf(trop);
    			
                byte[] bytes = file.getBytes();
                EDPNeuralNetwork.uploadDataSet(name,bytes,ips,ops);
                return "success";
            } catch (Exception e) {
                return "failure";
            }
        } else {
        	 return "failure";
        }
    }
	
	/*@RequestMapping(value="/upload", method=RequestMethod.POST)
	 public @ResponseBody String handleFileUpload(MultipartHttpServletRequest request, HttpServletResponse response){
		String name = "ts";
		return "You successfully uploaded " + name + "!";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                EDPNeuralNetwork.uploadDataSet(name,bytes);
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }*/

}