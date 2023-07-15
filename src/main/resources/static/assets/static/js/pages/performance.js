const cpu = document.getElementById('cpu-usage');

const CpuChart = new Chart(cpu, {
	type: 'pie',
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
		title: {
			display: true,
			text: 'Predicted world population (millions) in 2050'
		}
	}
});

$(document).ready(function() {
	var cpuusage;
	setInterval(function() {
		cpuusage = getcpu();
		console.log('getcpu: ' + cpuusage)
		updateData(CpuChart, cpuusage);
		CpuChart.update();

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
	console.log('ajax cpu: ' + cpu)
	return cpu;
}

function updateData(chart, data) {
	chart.data.datasets[0].data[0] = data;
	chart.data.datasets[0].data[1] = 100 - data;
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
