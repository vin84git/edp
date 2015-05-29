<html>

 <head>
  <title>Electricity Demand Prediction using Neuroph</title>
   <link href="/edp/css/bootstrap.min.css" rel="stylesheet">
   <link href="/edp/css/edp.css" rel="stylesheet">
   
  <script type="text/javascript" src="/edp/js/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="/edp/js/bootstrap.min.js"></script>
  
  <script type="text/javascript" src="/edp/js/edp.js"></script>
 
 </head>
<body>
	

	<div class="container-fluid">    
		  <div style="height: 100%; color: white;" class="row">
		    <div style="height: 100%; display: table; min-height: 100%;" class="col-sm-6 ">
		     <div style="display: table-cell;vertical-align: middle; text-align: right;">
		     	<h1>Electricity Demand Prediction</h1>
				<h1>using</h1>
				<h1>Neuroph</h1>
		     </div>
		    	  
		    </div>
		    <div style="height: 100%; display: table; min-height: 100%;background: rgba(255,255,255,.5);" class="col-sm-6">
		     <div style="display: table-cell; vertical-align:middle ;text-align:center;">
		       <div class="row">
		      		<div style="width: 40%; margin-left: 28%" class="col-sm-3">
		      			 <div class="jumbotron">
			                 <h4>Predict Demand</h4>
			                <p> <a id="predict" data-toggle="modal" data-target="#predictModal" class="btn btn-lg btn-primary"  role="button">Predict »</a>
			
			                </p>
			            </div>
		      		</div>
		      		
		      </div>
		      <div class="row">
		      		<div style="width: 40%; margin-left: 5%" class="col-sm-3">
		      			 <div class="jumbotron">
			                 <h4>Cross Validate</h4>
			                <p> <a id="validate" data-toggle="modal" data-target="#testModal" class="btn btn-lg btn-primary"  role="button">Validate »</a>
			
			                </p>
			            </div>
		      		</div>
		      		<div style="width: 40%; margin-right: 5%; float: right;" class="col-sm-3 ">
		      			<div class="jumbotron">
			                <h4>Create MLP NN</h4>
			                <p> <a id="create" class="btn btn-lg btn-primary" data-toggle="modal" data-target="#createModal"  role="button">Create »</a>
			
			                </p>
			            </div>
		      		</div>
		      </div>
		      <div class="row">
		      		<div style="width: 40%; margin-left: 5%" class="col-sm-3">
		      			 <div class="jumbotron">
							  <h4>Test your NN</h4>
							  <p><a id="test" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#testModal"   role="button">Test »</a></p>
							</div>
		      		</div>
		      		<div style="width: 40%;  margin-right: 5%; float: right;" class="col-sm-3 ">
		      			<div class="jumbotron">
							  <h4>Upload Datasets</h4>
							  <p><a id="upload" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#uploadModal"   role="button">Upload »</a></p>
							</div>
		      		</div>
		      </div>
		      <div class="row">
		      		<div style="width: 40%; margin-left: 28%" class="col-sm-3">
		      			 <div class="jumbotron">
			                 <h4>Train your NN</h4>
			                <p> <a id="train" class="btn btn-lg btn-primary" data-toggle="modal" data-target="#trainModal"  role="button">Train »</a>
			
			                </p>
			            </div>
		      		</div>
		      		
		      </div>
		      </div>
		    </div>
		  </div>
	</div>


		<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="createModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="createModalLabel">Create MLP Neural Network</h4>
		      </div>
		      <div class="modal-body">
		        <form id="createfrm">
		           <div class="form-group">
		            <label for="recipient-name" class="control-label">Name:</label>
		            <input name="nam" type="text" class="form-control" id="nn-name">
		          </div>
		          <div class="form-group">
		            <label for="recipient-name" class="control-label">Input Neurons:</label>
		            <input name="ipn" type="text" class="form-control" value="27" id="ipn">
		          </div>
		          <div class="form-group">
		            <label for="message-text" class="control-label">Hidden Neurons:</label>
		            <input name="hn" type="text" class="form-control" id="hn">
		          </div>
		          <div class="form-group">
		            <label for="message-text" class="control-label">Output Neurons:</label>
		            <input name="opn" type="text" class="form-control" value="24" id="opn">
		          </div>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button  type="submit" id="createBtn" class="btn btn-primary createBtn">Create</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="trainModal" tabindex="-1" role="dialog" aria-labelledby="trainModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="trainModalLabel">Train MLP Neural Network</h4>
		        <button  type="button"  id="stopBtn" class="btn btn-default" >Stop</button>
		        <button  type="button" id="pauseBtn" class="btn btn-default ">Pause</button>
		        <button  type="button" id="resumeBtn" class="btn btn-default ">Resume</button>
		        <button  type="button"  id="resetBtn" class="btn btn-default" >Reset</button>
		        <button  type="button" id="logBtn" class="btn btn-default ">Log</button>
		      </div>
		      <div class="modal-body">
		        <form id="trainfrm">
		           <div class="form-group">
		            <label for="recipient-name" class="control-label">NeuralNetwork:</label>		           
		            <select name="nnselect" id="nnselect" class="selectpicker form-control" data-size="8">
			                <option selected></option>
			                
			         </select>
		          </div>
		          <div class="form-group">
		            <label for="recipient-name" class="control-label">DataSet:</label>
		            <select name="tsselect" id="tsselect" class="selectpicker form-control" data-size="8">
			                <option selected></option>			                
			         </select>
		          </div>		         
		          <div class="form-group">
		            <label for="message-text" class="control-label">LearningRate:</label>
		            <input name="lr" type="text"  value="0.2"  class="form-control" id="hn">
		          </div>
		          <div class="form-group">
		            <label for="message-text" class="control-label">MaxIterations:</label>
		            <input name="mi" type="text" class="form-control" id="opn">
		          </div>
		          <div class="form-group">
		            <label for="message-text" class="control-label">Momentum:</label>
		            <input name="mom" type="text"  value="0.7"  class="form-control" id="opn">
		          </div>
		          <div class="form-group">
		            <label for="message-text" class="control-label">MaxError:</label>
		            <input name="me" type="text"  value="0.02"  class="form-control" id="opn">
		          </div>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button  type="submit" id="trainBtn" class="btn btn-primary trainBtn">Train</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="testModal" tabindex="-1" role="dialog" aria-labelledby="testModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="testModalLabel">Test MLP Neural Network</h4>
		      </div>
		      <div class="modal-body">
		        <form id="testfrm">
		           <div class="form-group">
		            <label for="recipient-name" class="control-label">NeuralNetwork:</label>		           
		            <select name="testnnselect" id="testnnselect" class="selectpicker form-control" data-size="8">
			                <option selected></option>
			                
			         </select>
		          </div>
		          <div class="form-group">
		            <label for="recipient-name" class="control-label">DataSet:</label>
		            <select name="testtsselect" id="testtsselect" class="selectpicker form-control" data-size="8">
			                <option selected></option>			                
			         </select>
		          </div>		         
		        </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button  type="submit" id="testBtn" class="btn btn-primary trainBtn">Test</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="predictModal" tabindex="-1" role="dialog" aria-labelledby="predictModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="predictModalLabel">Predict Demand</h4>
		      </div>
		      <div class="modal-body">
		        <form id="predictfrm">
		           <div class="form-group">
		            <label for="recipient-name" class="control-label">NeuralNetwork:</label>		           
		            <select name="pnnselect" id="pnnselect" class="selectpicker form-control" data-size="8">
			                <option selected></option>
			                
			         </select>
		          </div>
		          <div class="form-group">
		            <label for="recipient-name" class="control-label">DataSet:</label>
		            <select name="ptsselect" id="ptsselect" class="selectpicker form-control" data-size="8">
			                <option selected></option>			                
			         </select>
		          </div>
		         <div class="form-group">
		            <label for="recipient-name" class="control-label">Inputs:</label>
		            <input name="ptrip" type="text" class="form-control"  value="27"  id=ptrip>
		          </div>
		          
		        </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button  type="submit" id="predictBtn" class="btn btn-primary predictBtn">Predict</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="uploadModalLabel">Upload Trainset</h4>
		      </div>
		      <div class="modal-body">
		        <form id="uploadfrm" method="post" action="ulpoad" enctype="multipart/form-data" >
		           <div class="form-group">
		            <label for="recipient-name" class="control-label">Name:</label>
		            <input id="tsname" name="nam" type="text" class="form-control" id="nn-name">
		          </div>
		          <div class="form-group">
		            <label for="recipient-name" class="control-label">Inputs:</label>
		            <input name="trip" type="text" class="form-control"  value="27"  id="trip">
		          </div>
		          <div class="form-group">
		            <label for="message-text" class="control-label">Outputs:</label>
		            <input name="trop" type="text" class="form-control" value="24" id="trop">
		          </div>
		          <div class="form-group">
		            <label for="recipient-name" class="control-label">Select Train set:</label>
		             <input type="file" name="tsfile"  class="form-control"  id="tsfile">
		          </div>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="submit" id="uploadBtn" class="btn btn-primary">Upload</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="trainLogModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-body">
		        <iframe id="trainlogiframe" src="" style="zoom:0.60" width="100%" height="80%"></iframe>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="testOutModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		     <div class="modal-header">
		        <h4 class="modal-title" id="uploadModalLabel">Test output</h4>
		      </div>
		      <div class="modal-body">
		       <label for="message-text" id="tmse" class="control-label">Mean Square Error:</label>
		       <br/>
		       <label for="message-text" class="control-label">Output:</label>
		       <div id="outputlist" class="list-group">

    		  </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
	 	<div class="modal fade" id="cvOutModal" tabindex="-1" role="dialog" aria-labelledby="cvModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		     <div class="modal-header">
		        <h4 class="modal-title" id="cvModalLabel">Cross Validation output</h4>
		      </div>
		      <div class="modal-body">
		       <div id="mean" class="control-label">Mean:</div>
		       <div id="variance" class="control-label">Variance:</div>
		       <div id="stddev" class="control-label">StdDev:</div>
		      
		      <!--  <label for="message-text" class="control-label">Output:</label> -->
		       <div id="outputlist" class="list-group">

    		  </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
	<div class="modal fade" id="predictOutModal" tabindex="-1" role="dialog" aria-labelledby="predictModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		     <div class="modal-header">
		        <h4 class="modal-title" id="predictModalLabel">Demand Predicted.</h4>
		      </div>
		      <div class="modal-body">
		       <label for="message-text" class="control-label">Output:</label>
		       <div id="predictlist" class="list-group">

    		  </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>

	<div class="msg">
		<div id="salert" class="alert alert-success alert-dismissable fade imsg">
		   <button type="button" class="close" data-dismiss="alert" 
		      aria-hidden="true">
		      &times;
		   </button>
		   <span id="smsg">Success! Well done its submitted.</span>
		</div>
	</div>
	<div class="msg">
		<div id="ealert" class="alert alert-danger alert-dismissable fade imsg">
		   <button type="button" class="close" data-dismiss="alert" 
		      aria-hidden="true">
		      &times;
		   </button>
		   	<span id="emsg">Error ! Change few things.</span>
		</div>
	</div>
</body>
	
</html>
