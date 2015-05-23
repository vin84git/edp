<html>

 <head>
  <title>Electricity Demand Prediction using Neuroph</title>
   <link href="/edp/css/bootstrap.min.css" rel="stylesheet">
   
  <script type="text/javascript" src="/edp/js/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="/edp/js/bootstrap.min.js"></script>
   <script type="text/javascript">

      $( document ).ready(function() {
          showTweets();
      });

	 
      setInterval(showTweets, 1000);

      function showTweets(){
          //alert('hi');
          $.get( "trainlog", function( data ) {
        	  var dat = JSON.parse(data); 
              var len = dat.length;
			  $.each( dat, function( i, l ){
			  	$("#msglist").prepend( " <a href='#' class='list-group-item'><h4 style='font-size:11px' class='list-group-item-heading'>"+l+"</h4></a>" );
				});

			});
      }

  </script>
  
 </head>
<body>
	
<div class="container">
    <h2>Training logs ....</h2>
    <div id="msglist" class="list-group">

    </div>
</div>
</body>
	
</html>