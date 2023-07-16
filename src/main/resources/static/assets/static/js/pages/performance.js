const cpu = document.getElementById('cpu-usage');

const CpuChart = new Chart(cpu, {
	type: 'doughnut',
	data: {
		labels: ["Usage", "Free"],
		datasets: [{
			label: "CPU Usage (%)",
			backgroundColor: ["#3e95cd", "grey"],
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

$(document).ready(function() {
	var cpuusage;
	setInterval(function() {
		cpuusage = getcpu();
		updateData(CpuChart, cpuusage);

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

function updateData(chart, data) {
	chart.data.datasets[0].data[0] = data;
	chart.data.datasets[0].data[1] = 100 - data;
	chart.options.plugins.title.text = data+' %'
	if (data <= 33.3) {
		chart.options.plugins.title.color = 'Green';
	}else if(data <= 66.6) {
		chart.options.plugins.title.color = 'Yellow';
	}else {
		chart.options.plugins.title.color = 'Red';
	}
	chart.update();
}

let optionsMemoryUsage = {
	series: [60, 40],
	labels: ["Usage", "Empty"],
	colors: ["#435ebe", "#55c6e8"],
	chart: {
		type: "donut",
		width: "100%",
		height: "350px",
	},
	legend: {
		position: "bottom",
	},
	plotOptions: {
		pie: {
			donut: {
				size: "30%",
			},
		},
	},
}

var memoryUsage = new ApexCharts(
	document.getElementById("memory-usage"),
	optionsMemoryUsage
)

memoryUsage.render()
