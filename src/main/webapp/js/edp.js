  $(document).ready(function () {
			  
			  var testtype="1";
			  var backgrounds = new Array('url(/edp/imgs/h1.jpg)','url(/edp/imgs/h2.jpg)','url(/edp/imgs/h3.jpg)','url(/edp/imgs/h4.jpg)','url(/edp/imgs/h5.jpg)','url(/edp/imgs/h6.jpg)');
			  var current = 0;
			  function nextBackground() {
				  $("body").css('background-image',backgrounds[current = ++current % backgrounds.length]);
			  }
			  
			  setInterval(nextBackground, 10000);
			  
			
			  $("#validate").click(function(){
				  $("#testModalLabel").html("CrossValidate your MLP network");
				  $("#testBtn").html("Validate");
				   testtype="2";
			  });
			  $("#test").click(function(){
				  $("#testModalLabel").html("Test your MLP network");
				  $("#testBtn").html("Test");
				  testtype="1";
			  });
			  
				  
			  $('#createModal').on('show.bs.modal', function (event) {
				  var button = $(event.relatedTarget) // Button that triggered the modal
				  var recipient = button.data('whatever') // Extract info from data-* attributes
				  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
				  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
				  var modal = $(this)
				});
			  
			  $('#uploadModal').on('show.bs.modal', function (event) {
				  var button = $(event.relatedTarget) // Button that triggered the modal
				  var recipient = button.data('whatever') // Extract info from data-* attributes
				  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
				  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
				  var modal = $(this)
				});
			  $('#trainModal').on('show.bs.modal', function (event) {
				  var button = $(event.relatedTarget) // Button that triggered the modal
				  var recipient = button.data('whatever') // Extract info from data-* attributes
				  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
				  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
				  var modal = $(this)
				});
			  $('#testModal').on('show.bs.modal', function (event) {
				  var button = $(event.relatedTarget) // Button that triggered the modal
				  var recipient = button.data('whatever') // Extract info from data-* attributes
				  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
				  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
				  var modal = $(this)
				});
			  
			  $('#predictModal').on('show.bs.modal', function (event) {
				  var button = $(event.relatedTarget) // Button that triggered the modal
				  var recipient = button.data('whatever') // Extract info from data-* attributes
				  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
				  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
				  var modal = $(this)
				});
			  
			  $('#trainLogModal').on('show.bs.modal', function (event) {
				  $(this).find('.modal-body').css({
		              width:'auto', //probably not needed
		              height:'auto', //probably not needed 
		              'max-height':'100%'
		       		});
				  var button = $(event.relatedTarget) // Button that triggered the modal
				  var recipient = button.data('whatever') // Extract info from data-* attributes
				  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
				  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
				  var modal = $(this)
				});
			  
			  $('#trainLogModal').on('hidden.bs.modal', function (event) {
				  $("#trainlogiframe").attr("src","");
			  });
				
			 $('#testOutModal').on('show.bs.modal', function (event) {
				  $(this).find('.modal-body').css({
		              'overflow-x':'auto', //probably not needed
		              'overflow-y':'auto', 
		              'max-height':'500px'
		       		});
				  var button = $(event.relatedTarget) // Button that triggered the modal
				  var recipient = button.data('whatever') // Extract info from data-* attributes
				  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
				  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
				  var modal = $(this)
				});
				
				 $('#cvOutModal').on('show.bs.modal', function (event) {
					  $(this).find('.modal-body').css({
			              'overflow-x':'auto', //probably not needed
			              'overflow-y':'auto', 
			              'max-height':'500px'
			       		});
					  var button = $(event.relatedTarget) // Button that triggered the modal
					  var recipient = button.data('whatever') // Extract info from data-* attributes
					  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
					  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
					  var modal = $(this)
					});

			 $('#predictOutModal').on('show.bs.modal', function (event) {
				  $(this).find('.modal-body').css({
		              'overflow-x':'auto', //probably not needed
		              'overflow-y':'auto', 
		              'max-height':'500px'
		       		});
				  var button = $(event.relatedTarget) // Button that triggered the modal
				  var recipient = button.data('whatever') // Extract info from data-* attributes
				  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
				  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
				  var modal = $(this)
				});
			  
			  $.ajax({
		            type: "GET",
		            url: "getData", 
		            success: function(msg){
		            	var dat = JSON.parse(msg); 
		            	var nns = dat.nn;
		            	var ts = dat.ts;
		            	$.each(nns, function(key, value) {   
		            	     $('#nnselect')
		            	         .append($("<option></option>")
		            	         .attr("value",value)
		            	         .text(value)); 
		            	     $('#testnnselect')
		            	         .append($("<option></option>")
		            	         .attr("value",value)
		            	         .text(value)); 
		            	     $('#pnnselect')
	            	         .append($("<option></option>")
	            	         .attr("value",value)
	            	         .text(value)); 
		            	});
		            	$.each(ts, function(key, value) {   
		            	     $('#tsselect')
		            	         .append($("<option></option>")
		            	         .attr("value",value)
		            	         .text(value));
		            	     $('#testtsselect')
		            	         .append($("<option></option>")
		            	         .attr("value",value)
		            	         .text(value));
		            	     $('#ptsselect')
	            	         .append($("<option></option>")
	            	         .attr("value",value)
	            	         .text(value));
		            	});
		            },
		            error: function(){
		                alert("failure");
		            }
		        });
			  
				$("#pauseBtn").click(function(){
					
					//alert($('#createfrm').serialize());
					
					 $.ajax({
				            type: "GET",
				            url: "pause", 
				            success: function(msg){
				            	
				            	if("success" === msg){
					                $("#smsg").html("Success! Paused training your neural Network...."); //hide button and show thank you
					                $("#createModal").modal('hide'); //hide popup  
					                $("#salert").removeClass('fade');
					                createAutoClosingAlert("#salert", 2000);
				            	}else{
				            		 $("#emsg").html("Error! Cannot pause training your neural Network...."); //hide button and show thank you
						             $("#ealert").removeClass('fade');
						             createAutoClosingAlert("#ealert", 2000);
				            	}
				            },
				            error: function(){
				                alert("failure");
				            }
				        });
				});
		

				$("#resumeBtn").click(function(){
					
					//alert($('#createfrm').serialize());
					
					 $.ajax({
				            type: "GET",
				            url: "resume", 
				            success: function(msg){
				            	
				            	if("success" === msg){
					                $("#smsg").html("Success! Resumed training your neural Network...."); //hide button and show thank you
					                $("#createModal").modal('hide'); //hide popup  
					                $("#salert").removeClass('fade');
					                createAutoClosingAlert("#salert", 2000);
				            	}else{
				            		 $("#emsg").html("Error! Cannot resume training your neural Network...."); //hide button and show thank you
						             $("#ealert").removeClass('fade');
						             createAutoClosingAlert("#ealert", 2000);
				            	}
				            },
				            error: function(){
				                alert("failure");
				            }
				        });
				});
				
				$("#resetBtn").click(function(){
					
					//alert($('#createfrm').serialize());
					
					 $.ajax({
				            type: "GET",
				            url: "reset", 
				            success: function(msg){
				            	
				            	if("success" === msg){
					                $("#smsg").html("Success! Reset done for your neural Network...."); //hide button and show thank you
					                //$("#createModal").modal('hide'); //hide popup  
					                $("#salert").removeClass('fade');
					                createAutoClosingAlert("#salert", 2000);
				            	}else{
				            		 $("#emsg").html("Error! Cannot reset your neural Network...."); //hide button and show thank you
						             $("#ealert").removeClass('fade');
						             createAutoClosingAlert("#ealert", 2000);
				            	}
				            },
				            error: function(){
				                alert("failure");
				            }
				        });
				});
				
				$("#logBtn").click(function(){
					
					 $("#trainlogiframe").attr("src","logs");
					 $('#trainLogModal').modal('show');
					 
					 
				});
				
				$("#stopBtn").click(function(){
					
					//alert($('#createfrm').serialize());
					
					 $.ajax({
				            type: "GET",
				            url: "stop", 
				            success: function(msg){
				            	
				            	if("success" === msg){
					                $("#smsg").html("Success! Stopped training your neural Network...."); //hide button and show thank you
					                //$("#createModal").modal('hide'); //hide popup  
					                $("#salert").removeClass('fade');
					                createAutoClosingAlert("#salert", 2000);
				            	}else{
				            		 $("#emsg").html("Error! Cannot stop training your neural Network...."); //hide button and show thank you
						             $("#ealert").removeClass('fade');
						             createAutoClosingAlert("#ealert", 2000);
				            	}
				            },
				            error: function(){
				                alert("failure");
				            }
				        });
				});
			  
				$("#createBtn").click(function(){
					
					//alert($('#createfrm').serialize());
					
					 $.ajax({
				            type: "POST",
				            url: "create", //process to mail
				            data: $('#createfrm').serialize(),
				            success: function(msg){
				            	
				            	if("success" === msg){
					                $("#smsg").html("Success! Created your neural Network...."); //hide button and show thank you
					                $("#createModal").modal('hide'); //hide popup  
					                $("#salert").removeClass('fade');
					                createAutoClosingAlert("#salert", 2000);
					                location.reload();
				            	}else{
				            		 $("#emsg").html("Error! Creating your neural Network...."); //hide button and show thank you
						             $("#ealert").removeClass('fade');
						             createAutoClosingAlert("#ealert", 2000);
				            	}
				            },
				            error: function(){
				                alert("failure");
				            }
				        });
				});
				
				
				function createAutoClosingAlert(selector, delay) {
					   var alert = $(selector).alert();
					   window.setTimeout(function() { alert.addClass('fade'); }, delay);
					}
				
				$("#trainBtn").click(function(){
					 var actual = $("#trainModalLabel").html();
					 $("#trainModalLabel").html("Training .......");
					 $.ajax({
				            type: "POST",
				            url: "train", //process to mail
				            data: $('#trainfrm').serialize(),
				            success: function(msg){
				            	if("success" === msg){
					                $("#smsg").html("Success! Training completed for your neural Network...."); //hide button and show thank you
					                $("#trainModal").modal('hide'); //hide popup
					                $("#salert").removeClass('fade');
					                createAutoClosingAlert("#salert", 2000);
				            	}else{
				            		 $("#emsg").html("Error! Training your neural Network...."); //hide button and show thank you
						             $("#ealert").removeClass('fade');
						             createAutoClosingAlert("#ealert", 2000);
				            	}
				            	$("#trainModalLabel").html(actual);
				            },
				            error: function(){
				            	 $("#emsg").html("Request Error! Training your neural Network...."); //hide button and show thank you
					             $("#ealert").removeClass('fade');
					             createAutoClosingAlert("#ealert", 2000);
					             $("#trainModalLabel").html(actual);
				            }
				        });
					 
					 
				});
				
				$("#testBtn").click(function(){
					
					 $.ajax({
				            type: "POST",
				            url: "test?t="+testtype, //process to mail
				            data: $('#testfrm').serialize(),
				            success: function(msg){
				            	if("failure" === msg){
				            		 $("#emsg").html("Error! Testing your neural Network...."); //hide button and show thank you
						             $("#ealert").removeClass('fade');
						             createAutoClosingAlert("#ealert", 2000);
				            	}else{
				            		
				            		if("1"===testtype){
					            		 var dat = JSON.parse(msg);
					            		 var tmse = dat.tmse;
					            		 var nwo = dat.nwOp;
					            		 var dop = dat.desiredOp;
					            		 $("#outputlist").html('');
					            		 $.each( nwo, function( i, l ){
					                         var t = new Date(l.t);
					         			  	$("#outputlist").prepend( " <a href='#' class='list-group-item'><h6 style='font-size:9px' class='list-group-item-heading'>Actual: "+l+"</h6><p style='font-size:9px' class='list-group-item-text'>Desired: "+dop[i] +"</p></a>" );
					         				  
					         				});
					            		 
					            		 $('#tmse').html("Mean Square Error: "+tmse);
					            		 $('#testoutput').html(nwo);
					            		 $('#testOutModal').modal('show');
				            	    }
					            	if("2"===testtype){
					            		 var dat = JSON.parse(msg);
					            		 var vari = dat.var;
					            		 var mean = dat.mean;
					            		 var stddev = dat.stdDev;
					            		 $("#outputlist").html('');
					            		
					            		 
					            		 $('#mean').html("Mean : "+mean);
					            		 $('#variance').html("Variance : "+vari);
					            		 $('#stddev').html("StdDev : "+stddev);
					            		 
					            		 $('#cvOutModal').modal('show');
				            	    }
				            		
				            		$("#smsg").html("Success! Testing completed for your neural Network...."); //hide button and show thank you
					                $("#testModal").modal('hide'); //hide popup  
					                $("#salert").removeClass('fade');
					                createAutoClosingAlert("#salert", 2000);
					                
				            	}
				               
				            },
				            error: function(){
				            	 $("#emsg").html("Request Error! Testing your neural Network...."); //hide button and show thank you
					             $("#ealert").removeClass('fade');
					             createAutoClosingAlert("#ealert", 2000);
				            }
				        });
				});
				
				$("#predictBtn").click(function(){
					
					 $.ajax({
				            type: "POST",
				            url: "predict", //process to mail
				            data: $('#predictfrm').serialize(),
				            success: function(msg){
				            	if("failure" === msg){
				            		 $("#emsg").html("Error! Predicting demand from your neural Network...."); //hide button and show thank you
						             $("#ealert").removeClass('fade');
						             createAutoClosingAlert("#ealert", 2000);
				            	}else{
				            		 var dat = JSON.parse(msg);
				            		// var tmse = dat.tmse;
				            		 var nwo = dat.nwOp;
				            		/// var dop = dat.desiredOp;
				            		 $("#predictlist").html('');
				            		 $.each( nwo, function( i, l ){
				                         var t = new Date(l.t);
				         			  	$("#predictlist").prepend( " <a href='#' class='list-group-item'><h6 style='font-size:9px' class='list-group-item-heading'>"+l+"</h6></a>" );
				         				  
				         				});
				            		 
				            		 //$('#tmse').html("Mean Square Error: "+tmse);
				            		 //$('#testoutput').html(nwo);
				            		 $('#predictOutModal').modal('show');
				            		
				            		$("#smsg").html("Success! Demnad prediction completed...."); //hide button and show thank you
					                $("#predictModal").modal('hide'); //hide popup  
					                $("#salert").removeClass('fade');
					                createAutoClosingAlert("#salert", 2000);
					                
				            	}
				               
				            },
				            error: function(){
				            	 $("#emsg").html("Request Error! Predicting demand from your neural Network...."); //hide button and show thank you
					             $("#ealert").removeClass('fade');
					             createAutoClosingAlert("#ealert", 2000);
				            }
				        });
				});
				
				$("#uploadBtn").click(function(){
					
					var formData = new FormData($("#uploadfrm")[0]);
					
					$.ajax({
				            type: "POST",
				            url: "upload", //process to mail
				            processData: false,
				            enctype: 'multipart/form-data',
				            contentType: false,
				            data: formData,
				            success: function(msg){
				            	if("success" === msg){
					                $("#smsg").html("Success! Upload completed for your data set...."); //hide button and show thank you
					                $("#uploadModal").modal('hide'); //hide popup  
					                $("#salert").removeClass('fade');
					                createAutoClosingAlert("#salert", 2000);
					                location.reload();
				            	}else{
				            		 $("#emsg").html("Error! Uploading your data set...."); //hide button and show thank you
						             $("#ealert").removeClass('fade');
						             createAutoClosingAlert("#ealert", 2000);
				            	}
				                
				            },
				            error: function(){
				            	 $("#emsg").html("Request Error! Uploading your data set...."); //hide button and show thank you
					             $("#ealert").removeClass('fade');
					             createAutoClosingAlert("#ealert", 2000);
				            }
				        });
				});
		  });
