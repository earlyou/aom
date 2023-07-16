const cpu = document.getElementById('cpu-usage');
const mem = document.getElementById('memory-usage');

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


$(document).ready(function() {
	var cpuusage;
	var memusage;
	setInterval(function() {
		cpuusage = getcpu();
		updateCpuData(CpuChart, cpuusage);
		memusage = getmem();
		updateMemData(MemChart, totmem, memusage);

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

function updateCpuData(chart, data) {
	chart.data.datasets[0].data[0] = data;
	chart.data.datasets[0].data[1] = 100 - data;
	chart.options.plugins.title.text = data+' %'
	if (data <= 33.3) {
		chart.options.plugins.title.color = 'Green';
		chart.data.datasets[0].backgroundColor[0] = 'Green';
	}else if(data <= 66.6) {
		chart.options.plugins.title.color = '#ffa100';
		chart.data.datasets[0].backgroundColor[0] = '#ffa100';
	}else {
		chart.options.plugins.title.color = 'Red';
		chart.data.datasets[0].backgroundColor[0] = 'Red';
	}
	chart.update();
}

function updateMemData(chart, totmem, data) {
	chart.data.datasets[0].data[0] = data;
	chart.data.datasets[0].data[1] = 100 - data;
	chart.options.plugins.title.text = data+' %'
	if (data <= 33.3) {
		chart.options.plugins.title.color = 'Green';
		chart.options.plugins.subtitle.color = 'Green';
		chart.data.datasets[0].backgroundColor[0] = 'Green';
	}else if(data <= 66.6) {
		chart.options.plugins.title.color = '#ffa100';
		chart.options.plugins.subtitle.color = '#ffa100';
		chart.data.datasets[0].backgroundColor[0] = '#ffa100';
	}else {
		chart.options.plugins.title.color = 'Red';
		chart.options.plugins.subtitle.color = 'Red';
		chart.data.datasets[0].backgroundColor[0] = 'Red';
	}
	
	var use = Math.ceil((totmem * data/100)*10)/10;
	chart.options.plugins.subtitle.text = use + ' GB / ' + totmem + ' GB';
	
	chart.update();
}