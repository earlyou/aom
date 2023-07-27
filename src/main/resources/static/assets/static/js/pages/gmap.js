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
let simplemap;
async function initSimpleMap() {
	const { Map } = await google.maps.importLibrary("maps");

	simplemap = new Map(document.getElementById("simplemap"), {
		center: { lat: 37.579582, lng: 126.977050 },
		zoom: 16,
	});
}


/** Geolocation */
// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see the error "The Geolocation service
// failed.", it means you probably did not give permission for the browser to
// locate you.
let geolocation, infoWindow;

async function initGeolocationMap() {
	const { Map } = await google.maps.importLibrary("maps");

	geolocation = new Map(document.getElementById("geolocation"), {
		center: { lat: -34.397, lng: 150.644 },
		zoom: 6,
	});
	infoWindow = new google.maps.InfoWindow();

	const locationButton = document.createElement("button");

	locationButton.textContent = "Pan to Current Location";
	locationButton.classList.add("custom-map-control-button");
	geolocation.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
	locationButton.addEventListener("click", () => {
		// Try HTML5 geolocation.
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(
				(position) => {
					const pos = {
						lat: position.coords.latitude,
						lng: position.coords.longitude,
					};

					infoWindow.setPosition(pos);
					infoWindow.setContent("Location found.");
					infoWindow.open(geolocation);
					geolocation.setCenter(pos);
				},
				() => {
					handleLocationError(true, infoWindow, geolocation.getCenter());
				},
			);
		} else {
			// Browser doesn't support Geolocation
			handleLocationError(false, infoWindow, geolocation.getCenter());
		}
	});
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	infoWindow.setContent(
		browserHasGeolocation
			? "Error: The Geolocation service failed."
			: "Error: Your browser doesn't support geolocation.",
	);
	infoWindow.open(geolocation);
}


/** Simple Advanced Marker */
async function initAdvancedMarkerMap() {
	// Request needed libraries.
	const { Map } = await google.maps.importLibrary("maps");
	const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");
	const map = new Map(document.getElementById("advancedmarker"), {
		center: { lat: 37.579582, lng: 126.977050 },
		zoom: 14,
		mapId: "4504f8b37365c3d0",
	});
	const marker = new AdvancedMarkerElement({
		map,
		position: { lat: 37.579582, lng: 126.977050 },
	});
}


/** Interactive Markers using HTML and CSS */
async function initInteractiveMarkerMap() {
	// Request needed libraries.
	const { Map } = await google.maps.importLibrary("maps");
	const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");
	const { LatLng } = await google.maps.importLibrary("core");
	const center = new LatLng(37.43238031167444, -122.16795397128632);
	const map = new Map(document.getElementById("interactivemarker"), {
		zoom: 11,
		center,
		mapId: "4504f8b37365c3d0",
	});

	for (const property of properties) {
		const AdvancedMarkerElement = new google.maps.marker.AdvancedMarkerElement({
			map,
			content: buildContent(property),
			position: property.position,
			title: property.description,
		});

		AdvancedMarkerElement.addListener("click", () => {
			toggleHighlight(AdvancedMarkerElement, property);
		});
	}
}

function toggleHighlight(markerView, property) {
	if (markerView.content.classList.contains("highlight")) {
		markerView.content.classList.remove("highlight");
		markerView.zIndex = null;
	} else {
		markerView.content.classList.add("highlight");
		markerView.zIndex = 1;
	}
}

function buildContent(property) {
	const content = document.createElement("div");

	content.classList.add("property");
	content.innerHTML = `
    <div class="icon">
        <i aria-hidden="true" class="fa fa-icon fa-${property.type}" title="${property.type}"></i>
        <span class="fa-sr-only">${property.type}</span>
    </div>
    <div class="details">
        <div class="price">${property.price}</div>
        <div class="address">${property.address}</div>
        <div class="features">
        <div>
            <i aria-hidden="true" class="fa fa-bed fa-lg bed" title="bedroom"></i>
            <span class="fa-sr-only">bedroom</span>
            <span>${property.bed}</span>
        </div>
        <div>
            <i aria-hidden="true" class="fa fa-bath fa-lg bath" title="bathroom"></i>
            <span class="fa-sr-only">bathroom</span>
            <span>${property.bath}</span>
        </div>
        <div>
            <i aria-hidden="true" class="fa fa-ruler fa-lg size" title="size"></i>
            <span class="fa-sr-only">size</span>
            <span>${property.size} ft<sup>2</sup></span>
        </div>
        </div>
    </div>
    `;
	return content;
}

const properties = [
	{
		address: "215 Emily St, MountainView, CA",
		description: "Single family house with modern design",
		price: "$ 3,889,000",
		type: "home",
		bed: 5,
		bath: 4.5,
		size: 300,
		position: {
			lat: 37.50024109655184,
			lng: -122.28528451834352,
		},
	},
	{
		address: "108 Squirrel Ln &#128063;, Menlo Park, CA",
		description: "Townhouse with friendly neighbors",
		price: "$ 3,050,000",
		type: "building",
		bed: 4,
		bath: 3,
		size: 200,
		position: {
			lat: 37.44440882321596,
			lng: -122.2160620727,
		},
	},
	{
		address: "100 Chris St, Portola Valley, CA",
		description: "Spacious warehouse great for small business",
		price: "$ 3,125,000",
		type: "warehouse",
		bed: 4,
		bath: 4,
		size: 800,
		position: {
			lat: 37.39561833718522,
			lng: -122.21855116258479,
		},
	},
	{
		address: "98 Aleh Ave, Palo Alto, CA",
		description: "A lovely store on busy road",
		price: "$ 4,225,000",
		type: "store-alt",
		bed: 2,
		bath: 1,
		size: 210,
		position: {
			lat: 37.423928529779644,
			lng: -122.1087629822001,
		},
	},
	{
		address: "2117 Su St, MountainView, CA",
		description: "Single family house near golf club",
		price: "$ 1,700,000",
		type: "home",
		bed: 4,
		bath: 3,
		size: 200,
		position: {
			lat: 37.40578635332598,
			lng: -122.15043378466069,
		},
	},
	{
		address: "197 Alicia Dr, Santa Clara, CA",
		description: "Multifloor large warehouse",
		price: "$ 5,000,000",
		type: "warehouse",
		bed: 5,
		bath: 4,
		size: 700,
		position: {
			lat: 37.36399747905774,
			lng: -122.10465384268522,
		},
	},
	{
		address: "700 Jose Ave, Sunnyvale, CA",
		description: "3 storey townhouse with 2 car garage",
		price: "$ 3,850,000",
		type: "building",
		bed: 4,
		bath: 4,
		size: 600,
		position: {
			lat: 37.38343706184458,
			lng: -122.02340436985183,
		},
	},
	{
		address: "868 Will Ct, Cupertino, CA",
		description: "Single family house in great school zone",
		price: "$ 2,500,000",
		type: "home",
		bed: 3,
		bath: 2,
		size: 100,
		position: {
			lat: 37.34576403052,
			lng: -122.04455090047453,
		},
	},
	{
		address: "655 Haylee St, Santa Clara, CA",
		description: "2 storey store with large storage room",
		price: "$ 2,500,000",
		type: "store-alt",
		bed: 3,
		bath: 2,
		size: 450,
		position: {
			lat: 37.362863347890716,
			lng: -121.97802139023555,
		},
	},
	{
		address: "2019 Natasha Dr, San Jose, CA",
		description: "Single family house",
		price: "$ 2,325,000",
		type: "home",
		bed: 4,
		bath: 3.5,
		size: 500,
		position: {
			lat: 37.41391636421949,
			lng: -121.94592071575907,
		},
	},
];


/** Marker Clustering */
async function initMarkerClusteringMap() {
	// Request needed libraries.
	const { Map, InfoWindow } = await google.maps.importLibrary("maps");
	const { AdvancedMarkerElement, PinElement } = await google.maps.importLibrary(
		"marker",
	);
	const map = new Map(document.getElementById("markerclustering"), {
		zoom: 3,
		center: { lat: -28.024, lng: 140.887 },
		mapId: "DEMO_MAP_ID",
	});
	const infoWindow = new google.maps.InfoWindow({
		content: "",
		disableAutoPan: true,
	});
	// Create an array of alphabetical characters used to label the markers.
	const labels = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	// Add some markers to the map.
	const markers = locations.map((position, i) => {
		const label = labels[i % labels.length];
		const pinGlyph = new google.maps.marker.PinElement({
			glyph: label,
			glyphColor: "white",
		});
		const marker = new google.maps.marker.AdvancedMarkerElement({
			position,
			content: pinGlyph.element,
		});

		// markers can only be keyboard focusable when they have click listeners
		// open info window when marker is clicked
		marker.addListener("click", () => {
			infoWindow.setContent(position.lat + ", " + position.lng);
			infoWindow.open(map, marker);
		});
		return marker;
	});

	// Add a marker clusterer to manage the markers.
	const markerCluster = new markerClusterer.MarkerClusterer({ map, markers });
}

const locations = [
	{ lat: -31.56391, lng: 147.154312 },
	{ lat: -33.718234, lng: 150.363181 },
	{ lat: -33.727111, lng: 150.371124 },
	{ lat: -33.848588, lng: 151.209834 },
	{ lat: -33.851702, lng: 151.216968 },
	{ lat: -34.671264, lng: 150.863657 },
	{ lat: -35.304724, lng: 148.662905 },
	{ lat: -36.817685, lng: 175.699196 },
	{ lat: -36.828611, lng: 175.790222 },
	{ lat: -37.75, lng: 145.116667 },
	{ lat: -37.759859, lng: 145.128708 },
	{ lat: -37.765015, lng: 145.133858 },
	{ lat: -37.770104, lng: 145.143299 },
	{ lat: -37.7737, lng: 145.145187 },
	{ lat: -37.774785, lng: 145.137978 },
	{ lat: -37.819616, lng: 144.968119 },
	{ lat: -38.330766, lng: 144.695692 },
	{ lat: -39.927193, lng: 175.053218 },
	{ lat: -41.330162, lng: 174.865694 },
	{ lat: -42.734358, lng: 147.439506 },
	{ lat: -42.734358, lng: 147.501315 },
	{ lat: -42.735258, lng: 147.438 },
	{ lat: -43.999792, lng: 170.463352 },
];


/**
 * Markers Clickable and Accessible
 */
async function initAccessibleMap() {
	// Request needed libraries.
	const { Map, InfoWindow } = await google.maps.importLibrary("maps");
	const { AdvancedMarkerElement, PinElement } = await google.maps.importLibrary(
		"marker",
	);
	const map = new Map(document.getElementById("accessible"), {
		zoom: 12,
		center: { lat: 34.84555, lng: -111.8035 },
		mapId: "4504f8b37365c3d0",
	});
	// Set LatLng and title text for the markers. The first marker (Boynton Pass)
	// receives the initial focus when tab is pressed. Use arrow keys to
	// move between markers; press tab again to cycle through the map controls.
	const tourStops = [
		{
			position: { lat: 34.8791806, lng: -111.8265049 },
			title: "Boynton Pass",
		},
		{
			position: { lat: 34.8559195, lng: -111.7988186 },
			title: "Airport Mesa",
		},
		{
			position: { lat: 34.832149, lng: -111.7695277 },
			title: "Chapel of the Holy Cross",
		},
		{
			position: { lat: 34.823736, lng: -111.8001857 },
			title: "Red Rock Crossing",
		},
		{
			position: { lat: 34.800326, lng: -111.7665047 },
			title: "Bell Rock",
		},
	];
	// Create an info window to share between markers.
	const infoWindow = new InfoWindow();

	// Create the markers.
	tourStops.forEach(({ position, title }, i) => {
		const pin = new PinElement({
			glyph: `${i + 1}`,
		});
		const marker = new AdvancedMarkerElement({
			position,
			map,
			title: `${i + 1}. ${title}`,
			content: pin.element,
		});

		// Add a click listener for each marker, and set up the info window.
		marker.addListener("click", ({ domEvent, latLng }) => {
			const { target } = domEvent;

			infoWindow.close();
			infoWindow.setContent(marker.title);
			infoWindow.open(marker.map, marker);
		});
	});
}


$(document).ready(function() {
	initSimpleMap();
	initGeolocationMap();
	initAdvancedMarkerMap();
	initInteractiveMarkerMap();
	initMarkerClusteringMap();
	initAccessibleMap();
});