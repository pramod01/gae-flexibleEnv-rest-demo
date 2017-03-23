$(function(){
	$('#alertSpan').css('display','none');
	$('#entriesTable').css('display','none');
	$("#callAPIButtonId").click(function(){
       var fname = $("#fname").val();	
       if(fname != ''){
    	   $('#alertSpan').css('display','none');
    	   $.post("/api/hello",
    		        {
    		          name: fname
    		        },
    		        function(data,status){
    		        	console.log(data);
    		        	$( "#responseDivId").text(""+(data));
    		        });   
       }
       else{
    	   $('#alertSpan').css('display','block');
       }
        
    });
	
    $('#retrieveEntriesButton').click(function(){
    	$('#entriesTable').css('display','none');
    	$.get("/api/hello",
		        {},
		        function(data,status){
		        	console.log(data);
		        	$('#entriesTable').css('display','block');
		        	var searchResultsHtml = '<table class="table table-striped" id="listResultsTable" cellspacing="0" width="90%"></table>';
		        	$('#entriesTable').html(searchResultsHtml);
		        	$('#listResultsTable').dataTable({
								"aoColumns" : [{"sTitle" : "remoteclientIpAddress"},{"sTitle" : "timestamp"},{"sTitle" : "visitor_name"}],
								"aaData" : JSON.parse(data),
								"bDestroy" : true,
								"language" : {
									"emptyTable" : "No results found."
								}
							});
		        });
    });
});