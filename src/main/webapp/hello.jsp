<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>Hello</title>
<style>
</style>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/customStyle.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">

<script src="js/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="js/myscript.js"></script>
<script
	src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>

</head>
<body>
 <div class="container">
	<div class="tabbable">
		<ul class="nav nav-tabs" id="myTabs">
			<li><a data-target="#onDemand" data-toggle="tab"
				href="javascript:void(0)">POST</a></li>
			<li><a data-target="#eventDriven" data-toggle="tab"
				href="javascript:void(0)">GET</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="onDemand">
				<div>
					<h3>SAMPLE POST:</h3>
				</div>
				<form action="/api/hello">
					<label for="fname">Name</label> <input type="text" id="fname"
						name="firstname" placeholder="Your name.."><span
						id="alertSpan" style="color: red"><h4>Name is
							mandatory!!</h4></span> <input type="button" value="Save to DataStore"
						id="callAPIButtonId">
				</form>
				<div
					style="border-radius: 5px; background-color: #f2f2f2; padding: 20px;">
					API Response
					<div id="responseDivId"></div>
				</div>
			</div>
			<div class="tab-pane" id="eventDriven">
				<div style="margin-top: 10px;">
					<h3>SAMPLE GET:</h3>
				</div>
				<div>
					<input type="button" value="List Datastore Entries"
						id="retrieveEntriesButton">
				</div>
				<div id="entriesTable"
					style="border-radius: 5px; background-color: #f2f2f2; padding: 20px; margin-top: 10px;"></div>
			</div>
		</div>
	</div>
 </div>
</body>
</html>