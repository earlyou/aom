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
var a = 0;
$('#simpleKakaoMap').on('shown.bs.modal', function() {
	if (a == 0) {
		a++;
		var simpleContainer = document.getElementById('simplemap'), // 지도를 표시할 div 
			simpleOption = {
				center: new kakao.maps.LatLng(36.635861, 127.491418), // 지도의 중심좌표
				level: 5 // 지도의 확대 레벨
			};
		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		var simplemap = new kakao.maps.Map(simpleContainer, simpleOption);
	};
	simplemap.relayout();
});


/** Geolocation */
var b = 0;
var geolocationContainer = document.getElementById('geolocation'), // 지도를 표시할 div 
	geolocationOption = {
		center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level: 10 // 지도의 확대 레벨 
	};
var geolocationmap = new kakao.maps.Map(geolocationContainer, geolocationOption); // 지도를 생성합니다
// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displaygeolocationMarker(locPosition, message) {
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
		map: geolocationmap,
		position: locPosition
	});
	var iwContent = message, // 인포윈도우에 표시할 내용
		iwRemoveable = true;
	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
		content: iwContent,
		removable: iwRemoveable
	});
	// 인포윈도우를 마커위에 표시합니다 
	infowindow.open(geolocationmap, marker);
	// 지도 중심좌표를 접속위치로 변경합니다
	geolocationmap.setCenter(locPosition);
}
$('#geolocationKakaoMap').on('shown.bs.modal', function() {
	geolocationmap.relayout();
	if (b == 0) {
		b++;
		// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
		if (navigator.geolocation) {
			// GeoLocation을 이용해서 접속 위치를 얻어옵니다
			navigator.geolocation.getCurrentPosition(function(position) {
				var lat = position.coords.latitude, // 위도
					lon = position.coords.longitude; // 경도
				var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
					message = '<div>여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다
				// 마커와 인포윈도우를 표시합니다
				displaygeolocationMarker(locPosition, message);
			});
		} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
			var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
				message = 'geolocation을 사용할수 없어요..'
			displaygeolocationMarker(locPosition, message);
		}
	};
});


/** Simple Marker */
var c = 0;
$('#simplemarkerKakaoMap').on('shown.bs.modal', function() {
	if (c == 0) {
		c++;
		var simplemarkermapContainer = document.getElementById('simplemarker'), // 지도를 표시할 div 
			simplemarkermapOption = {
				center: new kakao.maps.LatLng(36.635861, 127.491418), // 지도의 중심좌표
				level: 3 // 지도의 확대 레벨
			};
		var simplemarkermap = new kakao.maps.Map(simplemarkermapContainer, simplemarkermapOption); // 지도를 생성합니다
		// 마커가 표시될 위치입니다 
		var simplemarkerPosition = new kakao.maps.LatLng(36.635861, 127.491418);
		// 마커를 생성합니다
		var simplemarker = new kakao.maps.Marker({
			position: simplemarkerPosition
		});
		// 마커가 지도 위에 표시되도록 설정합니다
		simplemarker.setMap(simplemarkermap);
		// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
		// marker.setMap(null);
	};
	simplemarkermap.relayout();
});


/** Custom Overlay */
var d = 0;
var customoverlayloc = new kakao.maps.LatLng(33.451475, 126.570528);
var customoverlaymapContainer = document.getElementById('customoverlay'), // 지도의 중심좌표
	customoverlaymapOption = {
		center: customoverlayloc, // 지도의 중심좌표
		level: 3 // 지도의 확대 레벨
	};
var customoverlaymap = new kakao.maps.Map(customoverlaymapContainer, customoverlaymapOption); // 지도를 생성합니다
// 지도에 마커를 표시합니다 
var customoverlaymarker = new kakao.maps.Marker({
	map: customoverlaymap,
	position: customoverlayloc
});
// 커스텀 오버레이에 표시할 컨텐츠 입니다
// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
// 별도의 이벤트 메소드를 제공하지 않습니다 
var customoverlaycontent = '<div class="wrap">' +
	'    <div class="info">' +
	'        <div class="title">' +
	'            카카오 스페이스닷원' +
	'            <div class="close" onclick="closeOverlay()" title="닫기"></div>' +
	'        </div>' +
	'        <div class="body">' +
	'            <div class="img">' +
	'                <img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/thumnail.png" width="73" height="70">' +
	'           </div>' +
	'            <div class="desc">' +
	'                <div class="ellipsis">제주특별자치도 제주시 첨단로 242</div>' +
	'                <div class="jibun ellipsis">(우) 63309 (지번) 영평동 2181</div>' +
	'                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' +
	'            </div>' +
	'        </div>' +
	'    </div>' +
	'</div>';
// 마커 위에 커스텀오버레이를 표시합니다
// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
var customoverlay = new kakao.maps.CustomOverlay({
	content: customoverlaycontent,
	map: customoverlaymap,
	position: customoverlaymarker.getPosition()
});
// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
kakao.maps.event.addListener(customoverlaymarker, 'click', function() {
	customoverlay.setMap(customoverlaymap);
});
// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
function closeOverlay() {
	customoverlay.setMap(null);
}
$('#customoverlayKakaoMap').on('shown.bs.modal', function() {
	customoverlaymap.relayout();
	if (d == 0) {
		d++;
		customoverlaymap.setCenter(customoverlayloc);
	};
});


/** Marker Cluster */
var e = 0;
var markerclusterloc = new kakao.maps.LatLng(36.2683, 127.6358);
var markerclustermap = new kakao.maps.Map(document.getElementById('markercluster'), { // 지도를 표시할 div
	center: markerclusterloc, // 지도의 중심좌표 
	level: 14 // 지도의 확대 레벨 
});
// 마커 클러스터러를 생성합니다 
var clusterer = new kakao.maps.MarkerClusterer({
	map: markerclustermap, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
	averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
	minLevel: 10 // 클러스터 할 최소 지도 레벨 
});
// 데이터를 가져오기 위해 jQuery를 사용합니다
// 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
$.get("/download/web/data/chicken.json", function(data) {
	// 데이터에서 좌표 값을 가지고 마커를 표시합니다
	// 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
	var markerclustermarkers = $(data.positions).map(function(i, position) {
		return new kakao.maps.Marker({
			position: new kakao.maps.LatLng(position.lat, position.lng)
		});
	});
	// 클러스터러에 마커들을 추가합니다
	clusterer.addMarkers(markerclustermarkers);
});
$('#markerclusterKakaoMap').on('shown.bs.modal', function() {
	markerclustermap.relayout();
	if (e == 0) {
		e++;
		markerclustermap.setCenter(markerclusterloc);
	};
});


/** Marker Events */
var f = 0;
var MARKER_WIDTH = 33, // 기본, 클릭 마커의 너비
	MARKER_HEIGHT = 36, // 기본, 클릭 마커의 높이
	OFFSET_X = 12, // 기본, 클릭 마커의 기준 X좌표
	OFFSET_Y = MARKER_HEIGHT, // 기본, 클릭 마커의 기준 Y좌표
	OVER_MARKER_WIDTH = 40, // 오버 마커의 너비
	OVER_MARKER_HEIGHT = 42, // 오버 마커의 높이
	OVER_OFFSET_X = 13, // 오버 마커의 기준 X좌표
	OVER_OFFSET_Y = OVER_MARKER_HEIGHT, // 오버 마커의 기준 Y좌표
	SPRITE_MARKER_URL = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markers_sprites2.png', // 스프라이트 마커 이미지 URL
	SPRITE_WIDTH = 126, // 스프라이트 이미지 너비
	SPRITE_HEIGHT = 146, // 스프라이트 이미지 높이
	SPRITE_GAP = 10; // 스프라이트 이미지에서 마커간 간격
var markereventmarkerSize = new kakao.maps.Size(MARKER_WIDTH, MARKER_HEIGHT), // 기본, 클릭 마커의 크기
	markereventmarkerOffset = new kakao.maps.Point(OFFSET_X, OFFSET_Y), // 기본, 클릭 마커의 기준좌표
	markereventoverMarkerSize = new kakao.maps.Size(OVER_MARKER_WIDTH, OVER_MARKER_HEIGHT), // 오버 마커의 크기
	markereventoverMarkerOffset = new kakao.maps.Point(OVER_OFFSET_X, OVER_OFFSET_Y), // 오버 마커의 기준 좌표
	markereventspriteImageSize = new kakao.maps.Size(SPRITE_WIDTH, SPRITE_HEIGHT); // 스프라이트 이미지의 크기
var markereventpositions = [  // 마커의 위치
	new kakao.maps.LatLng(33.44975, 126.56967),
	new kakao.maps.LatLng(33.450579, 126.56956),
	new kakao.maps.LatLng(33.4506468, 126.5707)
],
	selectedMarker = null; // 클릭한 마커를 담을 변수
var markereventmapContainer = document.getElementById('markerevent'), // 지도를 표시할 div
	markereventmapOption = {
		center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level: 3 // 지도의 확대 레벨
	};
var markereventmap = new kakao.maps.Map(markereventmapContainer, markereventmapOption); // 지도를 생성합니다
// 지도 위에 마커를 표시합니다
for (var i = 0, len = markereventpositions.length; i < len; i++) {
	var gapX = (MARKER_WIDTH + SPRITE_GAP), // 스프라이트 이미지에서 마커로 사용할 이미지 X좌표 간격 값
		originY = (MARKER_HEIGHT + SPRITE_GAP) * i, // 스프라이트 이미지에서 기본, 클릭 마커로 사용할 Y좌표 값
		overOriginY = (OVER_MARKER_HEIGHT + SPRITE_GAP) * i, // 스프라이트 이미지에서 오버 마커로 사용할 Y좌표 값
		normalOrigin = new kakao.maps.Point(0, originY), // 스프라이트 이미지에서 기본 마커로 사용할 영역의 좌상단 좌표
		clickOrigin = new kakao.maps.Point(gapX, originY), // 스프라이트 이미지에서 마우스오버 마커로 사용할 영역의 좌상단 좌표
		overOrigin = new kakao.maps.Point(gapX * 2, overOriginY); // 스프라이트 이미지에서 클릭 마커로 사용할 영역의 좌상단 좌표

	// 마커를 생성하고 지도위에 표시합니다
	addMarker(markereventpositions[i], normalOrigin, overOrigin, clickOrigin);
}
// 마커를 생성하고 지도 위에 표시하고, 마커에 mouseover, mouseout, click 이벤트를 등록하는 함수입니다
function addMarker(position, normalOrigin, overOrigin, clickOrigin) {
	// 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다
	var normalImage = createMarkerImage(markereventmarkerSize, markereventmarkerOffset, normalOrigin),
		overImage = createMarkerImage(markereventoverMarkerSize, markereventoverMarkerOffset, overOrigin),
		clickImage = createMarkerImage(markereventmarkerSize, markereventmarkerOffset, clickOrigin);
	// 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
	var marker = new kakao.maps.Marker({
		map: markereventmap,
		position: position,
		image: normalImage
	});
	// 마커 객체에 마커아이디와 마커의 기본 이미지를 추가합니다
	marker.normalImage = normalImage;
	// 마커에 mouseover 이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'mouseover', function() {

		// 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
		// 마커의 이미지를 오버 이미지로 변경합니다
		if (!selectedMarker || selectedMarker !== marker) {
			marker.setImage(overImage);
		}
	});
	// 마커에 mouseout 이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'mouseout', function() {
		// 클릭된 마커가 없고, mouseout된 마커가 클릭된 마커가 아니면
		// 마커의 이미지를 기본 이미지로 변경합니다
		if (!selectedMarker || selectedMarker !== marker) {
			marker.setImage(normalImage);
		}
	});
	// 마커에 click 이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'click', function() {
		// 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
		// 마커의 이미지를 클릭 이미지로 변경합니다
		if (!selectedMarker || selectedMarker !== marker) {
			// 클릭된 마커 객체가 null이 아니면
			// 클릭된 마커의 이미지를 기본 이미지로 변경하고
			!!selectedMarker && selectedMarker.setImage(selectedMarker.normalImage);
			// 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
			marker.setImage(clickImage);
		}
		// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
		selectedMarker = marker;
	});
}
// MakrerImage 객체를 생성하여 반환하는 함수입니다
function createMarkerImage(markerSize, offset, spriteOrigin) {
	var markerImage = new kakao.maps.MarkerImage(
		SPRITE_MARKER_URL, // 스프라이트 마커 이미지 URL
		markerSize, // 마커의 크기
		{
			offset: offset, // 마커 이미지에서의 기준 좌표
			spriteOrigin: spriteOrigin, // 스트라이프 이미지 중 사용할 영역의 좌상단 좌표
			spriteSize: markereventspriteImageSize // 스프라이트 이미지의 크기
		}
	);
	return markerImage;
}
$('#markereventKakaoMap').on('shown.bs.modal', function() {
	markereventmap.relayout();
	if (f == 0) {
		f++;
		markereventmap.setCenter(new kakao.maps.LatLng(33.450701, 126.570667));
	};
});


$(document).ready(function() {

});