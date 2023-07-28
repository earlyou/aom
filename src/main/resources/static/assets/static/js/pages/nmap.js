$(window).resize(function() {
	var winWidth = $(window).width();
	var boxWidth = $('.map').width();

	if (winWidth <= 500) {
		$('.map').height(boxWidth * 0.681);
	} else {
		$('.map').height('500px');
	}
});


/** Simple Map */
var simplemapDiv = document.getElementById('simplemap');
var simplemapOptions = {
	center: new naver.maps.LatLng(37.579582, 126.977050),
	zoom: 14
};
//옵션 없이 지도 객체를 생성하면 서울 시청을 중심으로 하는 16 레벨의 지도가 생성됩니다.
var simplemap = new naver.maps.Map(simplemapDiv, simplemapOptions);
var a = 0;
$('#simpleNaverMap').on('shown.bs.modal', function() {
	if (a == 0) {
		a++;
		simplemap.setCenter(new naver.maps.LatLng(37.579582, 126.977050))
	};
	simplemap.autoResize();
});


/** Geolocation */
var geolocationmapDiv = document.getElementById('geolocation');
var geolocationmapOptions = {
	center: new naver.maps.LatLng(37.5666805, 126.9784147),
	zoom: 10,
	mapTypeId: naver.maps.MapTypeId.NORMAL
};
var geolocationmap = new naver.maps.Map(geolocationmapDiv, geolocationmapOptions);
var geolocationinfowindow = new naver.maps.InfoWindow();
function onSuccessGeolocation(position) {
	var location = new naver.maps.LatLng(position.coords.latitude, position.coords.longitude);
	geolocationmap.setCenter(location); // 얻은 좌표를 지도의 중심으로 설정합니다.
	geolocationmap.setZoom(10); // 지도의 줌 레벨을 변경합니다.
	geolocationinfowindow.setContent('<div class="infowindow" style="padding:20px;">' + '현재 위치' + '</div>');
	geolocationinfowindow.open(geolocationmap, location);
}
function onErrorGeolocation() {
	var center = geolocationmap.getCenter();
	geolocationinfowindow.setContent('<div style="padding:20px;">' +
		'<h5 style="margin-bottom:5px;color:#f00;">Geolocation failed!</h5>' + "latitude: " + center.lat() + "<br />longitude: " + center.lng() + '</div>');
	geolocationinfowindow.open(geolocationmap, center);
}
var b = 0;
$('#geolocationNaverMap').on('shown.bs.modal', function() {
	if (b == 0) {
		b++;
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(onSuccessGeolocation, onErrorGeolocation);
		} else {
			var center = geolocationmap.getCenter();
			geolocationinfowindow.setContent('<div style="padding:20px;"><h5 style="margin-bottom:5px;color:#f00;">Geolocation not supported</h5></div>');
			geolocationinfowindow.open(geolocationmap, center);
		}
	};
	geolocationmap.autoResize();
});


/** Simple Marker */
var simplemarkermapDiv = document.getElementById('simplemarker');
var simplemarkermapOptions = {
	center: new naver.maps.LatLng(36.635883, 127.491414),
	zoom: 14
};
var simplemarkermap = new naver.maps.Map(simplemarkermapDiv, simplemarkermapOptions);
var simplemarker = new naver.maps.Marker({
	position: new naver.maps.LatLng(36.635883, 127.491414),
	map: simplemarkermap
});
var c = 0;
$('#simplemarkerNaverMap').on('shown.bs.modal', function() {
	if (c == 0) {
		c++;
		simplemarkermap.setCenter(new naver.maps.LatLng(36.635883, 127.491414))
	};
	simplemarkermap.autoResize();
});


/** Info Window */
var cityhall = new naver.maps.LatLng(36.642411, 127.489012);
var infowindowmapDiv = document.getElementById('infowindowmap');
var infowindowmapOptions = {
	center: cityhall.destinationPoint(0, 500),
	zoom: 15
};
var infowindowmap = new naver.maps.Map(infowindowmapDiv, infowindowmapOptions);
var infowindowmarker = new naver.maps.Marker({
	map: infowindowmap,
	position: cityhall
});
var infowindowcontentString = [
	'<div class="iw_inner">',
	'   <h3>청주시청</h3>',
	'   <p>충청북도 청주시 상당구 상당로 155 청주시청<br />',
	'       <img src="download/web/img/cj_symbol.png" width="55" height="55" alt="청주시청" class="thumb" /><br />',
	'       043-201-0001 | 공공,사회기관 &gt; 시청<br />',
	'       <a href="https://www.cheongju.go.kr/www/index.do" target="_blank">www.cheongju.go.kr/</a>',
	'   </p>',
	'</div>'
].join('');
var infowindow = new naver.maps.InfoWindow({
	content: infowindowcontentString
});
naver.maps.Event.addListener(infowindowmarker, "click", function() {
	if (infowindow.getMap()) {
		infowindow.close();
	} else {
		infowindow.open(infowindowmap, infowindowmarker);
	}
});
var d = 0;
$('#infowindowNaverMap').on('shown.bs.modal', function() {
	infowindowmap.autoResize();
	if (d == 0) {
		d++;
		simplemarkermap.setCenter(cityhall);
		infowindow.open(infowindowmap, infowindowmarker);
	};
});



/** Marker Cluster */
var markerclustermapDiv = document.getElementById('markerclustering');
var markerclustermapOptions = {
	zoom: 6,
	center: new naver.maps.LatLng(36.2253017, 127.6460516),
	zoomControl: true,
	zoomControlOptions: {
		position: naver.maps.Position.TOP_LEFT,
		style: naver.maps.ZoomControlStyle.SMALL
	}
};
var markerclustermap = new naver.maps.Map(markerclustermapDiv, markerclustermapOptions);
var clustermarkers = [];
var clusterhtmlMarker1 = {
	content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(download/web/img/cluster-marker-1.png);background-size:contain;"></div>',
	size: N.Size(40, 40),
	anchor: N.Point(20, 20)
},
	clusterhtmlMarker2 = {
		content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(download/web/img/cluster-marker-2.png);background-size:contain;"></div>',
		size: N.Size(40, 40),
		anchor: N.Point(20, 20)
	},
	clusterhtmlMarker3 = {
		content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(download/web/img/cluster-marker-3.png);background-size:contain;"></div>',
		size: N.Size(40, 40),
		anchor: N.Point(20, 20)
	},
	clusterhtmlMarker4 = {
		content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(download/web/img/cluster-marker-4.png);background-size:contain;"></div>',
		size: N.Size(40, 40),
		anchor: N.Point(20, 20)
	},
	clusterhtmlMarker5 = {
		content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(download/web/img/cluster-marker-5.png);background-size:contain;"></div>',
		size: N.Size(40, 40),
		anchor: N.Point(20, 20)
	};
function clusteronLoad() {
	var data = accidentDeath.searchResult.accidentDeath;
	for (var i = 0, ii = data.length; i < ii; i++) {
		var spot = data[i],
			latlng = new naver.maps.LatLng(spot.grd_la, spot.grd_lo),
			marker = new naver.maps.Marker({
				position: latlng,
				draggable: true
			});

		clustermarkers.push(marker);
	}

	var markerClustering = new MarkerClustering({
		minClusterSize: 2,
		maxZoom: 8,
		map: markerclustermap,
		markers: clustermarkers,
		disableClickZoom: false,
		gridSize: 120,
		icons: [clusterhtmlMarker1, clusterhtmlMarker2, clusterhtmlMarker3, clusterhtmlMarker4, clusterhtmlMarker5],
		indexGenerator: [10, 100, 200, 500, 1000],
		stylingFunction: function(clusterMarker, count) {
			$(clusterMarker.getElement()).find('div:first-child').text(count);
		}
	});
}
var e = 0;
$('#markerclusteringNaverMap').on('shown.bs.modal', function() {
	markerclustermap.autoResize();
	if (e == 0) {
		e++;
		markerclustermap.setCenter(new naver.maps.LatLng(36.2253017, 127.6460516));
		clusteronLoad();
	};
});


/** Marker Animation */
var animationmapDiv = document.getElementById('animation');
var animationmapOptions = {
	zoom: 11
};
var animationmap = new naver.maps.Map(animationmapDiv, animationmapOptions);

animationmap.fitBounds(naver.maps.LatLngBounds.bounds(new naver.maps.LatLng(37.3724620, 127.1051714),
	new naver.maps.LatLng(37.3542795, 127.1174332)));

var animationurlMarker = new naver.maps.Marker({
	position: new naver.maps.LatLng(37.3542795, 127.1072556),
	map: animationmap,
	title: 'urlMarker',
	icon: "download/web/img/pin_default.png",
	animation: naver.maps.Animation.DROP
});

naver.maps.Event.addListener(animationurlMarker, 'click', function() {
	if (animationurlMarker.getAnimation() !== null) {
		animationurlMarker.setAnimation(null);
	} else {
		animationurlMarker.setAnimation(naver.maps.Animation.BOUNCE);
	}
});

var animationimageMarker = new naver.maps.Marker({
	position: new naver.maps.LatLng(37.3637770, 127.1174332),
	map: animationmap,
	title: 'imageMarker',
	icon: "download/web/img/pin_default.png",
	animation: naver.maps.Animation.BOUNCE
});

var animationsymbolMarker = new naver.maps.Marker({
	map: animationmap,
	position: new naver.maps.LatLng(37.3692417, 127.1134740),
	title: 'symbolMarker',
	icon: {
		path: [
			new naver.maps.Point(0, 70), new naver.maps.Point(20, 100), new naver.maps.Point(40, 70),
			new naver.maps.Point(30, 70), new naver.maps.Point(70, 0), new naver.maps.Point(10, 70)
		],
		style: "closedPath",
		anchor: new naver.maps.Point(23, 103),
		fillColor: '#ff0000',
		fillOpacity: 1,
		strokeColor: '#000000',
		strokeStyle: 'solid',
		strokeWeight: 3
	},
	shape: {
		coords: [0, 70, 20, 100, 40, 70, 30, 70, 70, 0, 10, 70],
		type: "poly"
	},
	animation: naver.maps.Animation.BOUNCE
});

var animationhtmlMarker = new naver.maps.Marker({
	position: new naver.maps.LatLng(37.3578575, 127.1063746),
	map: animationmap,
	title: 'htmlMarker',
	icon: {
		content: [
			'<div class="cs_mapbridge">',
			'<i class="bi bi-geo-alt-fill"></i>',
			'</div>'
		].join(''),
		size: new naver.maps.Size(38, 58),
		anchor: new naver.maps.Point(19, 58)
	},
	animation: naver.maps.Animation.BOUNCE
});
var f = 0;
$('#animationNaverMap').on('shown.bs.modal', function() {
	animationmap.autoResize();
	if (f == 0) {
		f++;
		animationmap.setCenter(new naver.maps.LatLng(37.3724620, 127.1051714));
	};
});


$(document).ready(function() {

});