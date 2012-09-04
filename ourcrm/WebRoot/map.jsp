<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	
	<title></title>
</head>
<body>
	<input type="text" name="" id="address" /><input type="button" value="显示地图" id="btn" />
	<div id="map" style="width:700px;height:500px">
		
    </div>
    <script src="js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
	<script type="text/javascript" src="https://raw.github.com/HPNeo/gmaps/master/gmaps.js"></script>
    <script type="text/javascript">
		var map;
		$(document).ready(function(){
			
			map = new GMaps({
					div: '#map',
					lat: 35.2193612,
					lng: 113.2482923 
			});
			
			$("#btn").click(function(){
				GMaps.geocode({
				  address: $('#address').val(),
				  callback: function(results, status) {
					if (status == 'OK') {
					  var latlng = results[0].geometry.location;
					  map.setCenter(latlng.lat(), latlng.lng());
					  map.addMarker({
						lat: latlng.lat(),
						lng: latlng.lng()
					  });
					}
				  }
				});
			});
			
			
			
		});
    </script>
</body>
</html>