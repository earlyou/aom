const cpu = document.getElementById('cpu-usage');
const mem = document.getElementById('memory-usage');
const strg = document.getElementById('storage-usage');

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

$(document).ready(function() {
	var cpuusage;
	var memusage;
	var fsinfo;
	setInterval(function() {
		cpuusage = getcpu();
		updateCpuData(CpuChart, cpuusage);

		memusage = getmem();
		updateMemData(MemChart, totmem, memusage);

		fsinfo = getfs();
		updateFsData(StrgChart, fsinfo, fslist);
	}, 1000);
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
			tot = Math.round(fs.fstot / pb*10)/1;
			totunit = ' PB';
		} else if (fs.fstot >= tb) {
			tot = Math.round(fs.fstot / tb*10)/10;
			totunit = ' TB';
		} else if (fs.fstot >= gb) {
			tot = Math.round(fs.fstot / gb*10)/10;
			totunit = ' GB';
		} else if (fs.fstot >= mb) {
			tot = Math.round(fs.fstot / mb*10)/10;
			totunit = ' MB';
		};
		
		var free = fs.fstot * infolist[index]/100;
		var freeunit = '';
		if (free >= eb) {
			free = free / eb;
			freeunit = ' EB';
		} else if (free >= pb) {
			free = Math.round(free / pb*10)/10;
			freeunit = ' PB';
		} else if (free >= tb) {
			free = Math.round(free / tb*10)/10;
			freeunit = ' TB';
		} else if (free >= gb) {
			free = Math.round(free / gb*10)/10;
			freeunit = ' GB';
		} else if (free >= mb) {
			free = Math.round(free / mb*10)/10;
			freeunit = ' MB';
		};
		
		labels.push(free + freeunit + ' / ' + tot + totunit + ' || ' + fs.fsmnt);
	});
	chart.data.labels = labels;
	chart.data.datasets[0].data = infolist;
	chart.data.datasets[0].backgroundColor = color;
	chart.update();
}

function resetStrgData(chart, list) {
	chart.data.labels.splice(-1, 1);
	list.forEach(function(fs) {
		chart.data.labels.push(fs.fsmnt);
		chart.data.datasets[0].data.push(0);
		chart.update();
	});
}