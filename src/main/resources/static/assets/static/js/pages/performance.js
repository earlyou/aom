const cpu = document.getElementById('cpu-usage');

const CpuChart = new Chart(cpu, {
	type: 'pie',
	data: {
		labels: ["Usage", "Free"],
		datasets: [{
			label: "CPU Usage (%)",
			backgroundColor: ["#3e95cd", "#8e5ea2"],
			data: [0, 100]
		}]
	},
	options: {
		responsive: true,
		layout: {
            padding: {
				left: 50,
				right: 50
			}
        },
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
		updateData(CpuChart, cpuusage);
		CpuChart.update();

	}, 1000);

	/**
	
		let optionsCpuUsage = {
			series: [0, 100],
			labels: ["Usage", "Free"],
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
	
		var cpuUsage = new ApexCharts(
			document.getElementById("cpu-usage"),
			optionsCpuUsage
		)
		cpuUsage.render();
	
		var cpuusage;
		setInterval(function() {
			cpuusage = getcpu();
	
			optionsCpuUsage = {
				series: [cpuusage, 100 - cpuusage],
				labels: ["Usage", "Free"],
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
	
	
			var cpuUsage = new ApexCharts(
				document.getElementById("cpu-usage"),
				optionsCpuUsage
			)
			$("#cpu-usage").empty();
			cpuUsage.render();
	
		}, 2000);
	 */
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
