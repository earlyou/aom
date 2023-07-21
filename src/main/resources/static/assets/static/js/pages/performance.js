const cpu = document.getElementById('cpu-usage');
const mem = document.getElementById('memory-usage');
const strg = document.getElementById('storage-usage');
const cputemp = document.getElementById('cpu-temperature');

var mb = 1024 * 1024;
var gb = mb * 1024;
var tb = gb * 1024;
var pb = tb * 1024;
var eb = pb * 1024;

const CpuChart = new Chart(cpu, {
	type: 'doughnut',
	data: {
		labels: ["Usage", "Free"],
		datasets: [{
			label: "CPU Usage (%)",
			backgroundColor: ["Green", "grey"],
			data: [0, 100]
		}]
	},
	options: {
		responsive: true,
		/** maintainAspectRatio should be 'true' but due to chart.js 4.x issues, this option must be 'false' so that the chart acts responsive 
		 *	https://github.com/chartjs/Chart.js/issues/11005
		*/
		maintainAspectRatio: false,
		cutout: '30%',
		plugins: {
			title: {
				color: 'Green',
				position: 'bottom',
				display: true,
				text: '0%'
			}
		}
	}
});

const MemChart = new Chart(mem, {
	type: 'doughnut',
	data: {
		labels: ["Usage", "Free"],
		datasets: [{
			label: "Memory Usage (%)",
			backgroundColor: ["Green", "grey"],
			data: [0, 100]
		}]
	},
	options: {
		responsive: true,
		/** maintainAspectRatio should be 'true' but due to chart.js 4.x issues, this option must be 'false' so that the chart acts responsive 
		 *	https://github.com/chartjs/Chart.js/issues/11005
		*/
		maintainAspectRatio: false,
		cutout: '30%',
		plugins: {
			title: {
				color: 'Green',
				position: 'bottom',
				display: true,
				text: '0%'
			},
			subtitle: {
				display: true,
				color: 'Green',
				position: 'bottom',
				text: '0.0 / 0.0 GB'
			}
		}
	}
});

const StrgChart = new Chart(strg, {
	type: 'bar',
	data: {
		labels: ['Searching'],
		datasets: [
			{
				label: 'Free Space(%)',
				data: [],
				backgroundColor: [], //배경색상
				hoverBackgroundColor: ["#CBCE91"] //hover할때 색상변경
			}
		]
	},
	options: {
		maintainAspectRatio: false,//그래프의 비율 유지 
		plugins: { // 라벨 숨기기
			legend: {
				display: false
			},
		},
		indexAxis: 'y' //수평차트 만들기
	}
});

let width, height, gradient;
function getGradient(ctx, chartArea) {
	const chartWidth = chartArea.right - chartArea.left;
	const chartHeight = chartArea.bottom - chartArea.top;
	if (!gradient || width !== chartWidth || height !== chartHeight) {
		// Create the gradient because this is either the first render
		// or the size of the chart has changed
		width = chartWidth;
		height = chartHeight;
		gradient = ctx.createLinearGradient(0, chartArea.bottom, 0, chartArea.top);
		gradient.addColorStop(0, 'Blue');
		gradient.addColorStop(0.5, 'Orange');
		gradient.addColorStop(1, 'Red');
	}
	return gradient;
}

const TempChart = new Chart(cputemp, {
	type: 'line',
	data: {
		labels: [],
		datasets: [
			{
				label: 'Cpu Temperature(ºC)',
				data: [],
				borderColor: function(context) {
					const chart = context.chart;
					const { ctx, chartArea } = chart;

					if (!chartArea) {
						// This case happens on initial chart load
						return;
					}
					return getGradient(ctx, chartArea);
				},
			}
		]
	},
	options: {
		responsive: true,
		maintainAspectRatio: false,
		plugins: {
			legend: {
				position: 'top',
			},
			title: {
				display: true,
				text: 'CPU Temperature'
			},
			tooltip: {
				position: 'nearest'
			}
		},
		interaction: {
			intersect: false,
			mode: 'index',
		},
	},
});

$(document).ready(function() {
	var cpuusage;
	var memusage;
	var fsinfo;
	var temp;
	var hm = [];
	var tp = [];
	var fan = [];
	fsinfo = getfs();
	var propeller = new Propeller(document.getElementById('turbine'), {
		inertia: 1,
		angle: 0,
		speed: 1
	});
	propeller.unbind();
	updateFsData(StrgChart, fsinfo, fslist);
	setInterval(function() {
		cpuusage = getcpu();
		updateCpuData(CpuChart, cpuusage);
		
		memusage = getmem();
		updateMemData(MemChart, totmem, memusage);
		
		temp = gettemp();
		var now = new Date();
		updateTempData(TempChart, temp, tp, now, hm);
		
		fan = getfan();
		if (fan.length == 0) {
			fan = temp;
			$('#turbspeed').text(Math.round(fan*10)/10*20 + 'rpm');
			fan = fan/4;
		} else {
			var filtered = fan.filter(function(x) {
				return x !== 0;
			});
			fan = average(filtered);
			$('#turbspeed').text(Math.round(fan*10)/10 + 'rpm');
			fan = fan / 40
		};
	}, 1000);
	
	setInterval(function() {
		updateFanSpeed(propeller, fan);
	}, 1000 / 60);

	setInterval(function() {
		fsinfo = getfs();
		updateFsData(StrgChart, fsinfo, fslist);
	}, 60000);
});

function getcpu() {
	var cpu;
	$.ajax({
		type: 'GET',
		url: '/getcpu',
		data: {},
		async: false,
		success: function(data) {
			cpu = data;
		}
	});
	return cpu;
}

function getmem() {
	var mem;
	$.ajax({
		type: 'GET',
		url: '/getmem',
		data: {},
		async: false,
		success: function(data) {
			mem = data;
		}
	});
	return mem;
}

function getfs() {
	var fs;
	$.ajax({
		type: 'GET',
		url: '/getfs',
		data: {},
		async: false,
		success: function(data) {
			fs = data;
		}
	});
	return fs;
}

function gettemp() {
	var temp;
	$.ajax({
		type: 'GET',
		url: '/gettemp',
		data: {},
		async: false,
		success: function(data) {
			temp = data
		}
	});
	return temp;
}

function getfan() {
	var fan;
	$.ajax({
		type: 'GET',
		url: '/getfan',
		data: {},
		async: false,
		success: function(data) {
			fan = data
		}
	});
	return fan;
}

function updateCpuData(chart, data) {
	chart.data.datasets[0].data[0] = data;
	chart.data.datasets[0].data[1] = 100 - data;
	chart.options.plugins.title.text = data + ' %'
	if (data <= 33.3) {
		chart.options.plugins.title.color = 'Green';
		chart.data.datasets[0].backgroundColor[0] = 'Green';
	} else if (data <= 66.6) {
		chart.options.plugins.title.color = '#ffa100';
		chart.data.datasets[0].backgroundColor[0] = '#ffa100';
	} else {
		chart.options.plugins.title.color = 'Red';
		chart.data.datasets[0].backgroundColor[0] = 'Red';
	}
	chart.update();
}

function updateMemData(chart, totmem, data) {
	chart.data.datasets[0].data[0] = data;
	chart.data.datasets[0].data[1] = 100 - data;
	chart.options.plugins.title.text = data + ' %'
	if (data <= 33.3) {
		chart.options.plugins.title.color = 'Green';
		chart.options.plugins.subtitle.color = 'Green';
		chart.data.datasets[0].backgroundColor[0] = 'Green';
	} else if (data <= 66.6) {
		chart.options.plugins.title.color = '#ffa100';
		chart.options.plugins.subtitle.color = '#ffa100';
		chart.data.datasets[0].backgroundColor[0] = '#ffa100';
	} else {
		chart.options.plugins.title.color = 'Red';
		chart.options.plugins.subtitle.color = 'Red';
		chart.data.datasets[0].backgroundColor[0] = 'Red';
	}

	var use = Math.ceil((totmem * data / 100) * 10) / 10;
	chart.options.plugins.subtitle.text = use + ' GB / ' + totmem + ' GB';

	chart.update();
}

function updateFsData(chart, fsinfo, fslist) {
	var labels = [];
	var infolist = [];
	var color = [];
	fsinfo.forEach(function(info) {
		infolist.push(info);
		if (info <= 33.3) {
			color.push('Red');
		} else if (info <= 66.6) {
			color.push('#ffa100');
		} else {
			color.push('Green');
		};
	});
	fslist.forEach(function(fs, index) {
		var tot = 0.0;
		var totunit = '';

		if (fs.fstot >= eb) {
			tot = fs.fstot / eb;
			totunit = ' EB';
		} else if (fs.fstot >= pb) {
			tot = Math.round(fs.fstot / pb * 10) / 1;
			totunit = ' PB';
		} else if (fs.fstot >= tb) {
			tot = Math.round(fs.fstot / tb * 10) / 10;
			totunit = ' TB';
		} else if (fs.fstot >= gb) {
			tot = Math.round(fs.fstot / gb * 10) / 10;
			totunit = ' GB';
		} else if (fs.fstot >= mb) {
			tot = Math.round(fs.fstot / mb * 10) / 10;
			totunit = ' MB';
		};

		var free = fs.fstot * infolist[index] / 100;
		var freeunit = '';
		if (free >= eb) {
			free = free / eb;
			freeunit = ' EB';
		} else if (free >= pb) {
			free = Math.round(free / pb * 10) / 10;
			freeunit = ' PB';
		} else if (free >= tb) {
			free = Math.round(free / tb * 10) / 10;
			freeunit = ' TB';
		} else if (free >= gb) {
			free = Math.round(free / gb * 10) / 10;
			freeunit = ' GB';
		} else if (free >= mb) {
			free = Math.round(free / mb * 10) / 10;
			freeunit = ' MB';
		};

		labels.push(free + freeunit + ' / ' + tot + totunit + ' || ' + fs.fsmnt);
	});
	chart.data.labels = labels;
	chart.data.datasets[0].data = infolist;
	chart.data.datasets[0].backgroundColor = color;
	chart.update();
}

function updateTempData(chart, temp, tp, now, hm) {
	if (hm.length < 15) {
		hm.push(now.getMinutes() + ':' + now.getSeconds());
		tp.push(temp);
	} else {
		tp.shift();
		hm.shift();
		hm.push(now.getMinutes() + ':' + now.getSeconds());
		tp.push(temp);
	};
	chart.data.labels = hm;
	chart.data.datasets[0].data = tp;
	chart.options.plugins.title.text = Math.round(temp * 10) / 10 + ' ºC'
	chart.update();
}


function updateFanSpeed(propeller, fan) {
	if (fan == 0) {
		propeller.stop();
	} else if (propeller.speed < fan) {
		propeller.speed = propeller.speed + 0.1;
	} else if (propeller.speed > fan) {
		propeller.speed = propeller.speed - 0.1;
	}
	/*
	window.requestAnimationFrame(function() {
		updateFanSpeed(propeller, fan);
	});
	*/
}

function resetStrgData(chart, list) {
	chart.data.labels.splice(-1, 1);
	list.forEach(function(fs) {
		chart.data.labels.push(fs.fsmnt);
		chart.data.datasets[0].data.push(0);
		chart.update();
	});
}

function average(array) {
	return array.reduce((sum, current) => sum + current, 0) / array.length;
}
