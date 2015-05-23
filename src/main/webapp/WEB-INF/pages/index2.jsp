<html>

 <head>
  <title>Electricity Demand Prediction using Neuroph</title>
   <link href="/edp/css/bootstrap.min.css" rel="stylesheet">
   
  <script type="text/javascript" src="/edp/js/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="/edp/js/bootstrap.min.js"></script>
  <style type="text/css">
  
   body	{
   	background: url(/edp/imgs/h1.jpg) no-repeat center center fixed; 
    -webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
   }
   
   #header{
   	 width:40%;
   	 height: 100%;
   	 margin-top: 10%;
   	 position:absolute;
   	 top:0;
   	 text-align:right;
   	 color: white;
   	 font-family: 'Myriad Set Pro', 'Lucida Grande', 'Helvetica Neue', Helvetica, Arial, Verdana, sans-serif;
   }
   
    #content{
     position:static;
     background: rgba(255,255,255,.5);
   	 width:50%;
   	 height: 100%;
   	 text-align:right;
   	 
   	 float:right;
   	 color: white;
   	 font-family: 'Myriad Set Pro', 'Lucida Grande', 'Helvetica Neue', Helvetica, Arial, Verdana, sans-serif;
   }
   
   .clk {
    background-color: black;
    color: white;
    display: block;
    height: 100px;
    line-height: 40px;
    text-decoration: none;
    width: 100px;
    text-align: center;
}
   
  
  </style>
 </head>
<body>

<div class="row">
  <div class="span6">
     	<div id="header" >			
			<h1>Electricity Demand Prediction</h1>
			<h1>using</h1>
			<h1>Neuroph</h1>			
		</div>
  </div>
  <div id="content" class="span6">
       <div class="row">
       	<div class="span3">
       		<a class="clk" id="create" href="#">Create</a>
       	</div>
	    <div class="span3">  
	      <a class="clk" id="load" href="#">Load</a>
	      </div>
   		 </div>
     <div class="row">
	      <a class="clk" id="train" href="#">Train</a>
	      <a class="clk" id="test" href="#">Test</a>
    </div>
  </div>
</div>
<!-- 
	<div style="position: relative;">
		<div id="header" >			
			<h1>Electricity Demand Prediction</h1>
			<h1>using</h1>
			<h1>Neuroph</h1>			
		</div>
		<div id="content" >
		   
		</div>
	</div>  -->

</body>
	
</html>