// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
var ctx = document.getElementById("myPieChart");
var ticketCount = document.getElementById("ticket-not-started").value;
var ticketOpen = document.getElementById("ticket-open").value;
var ticketClosed = document.getElementById("ticket-closed").value;
var ticketBlocked = document.getElementById("ticket-blocked").value;
console.log(ticketCount);
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ["Not Started", "In Progress", "Blocked", "Closed"],
    datasets: [{
      data: [ticketCount, ticketOpen, ticketBlocked, ticketClosed],
      backgroundColor: ['#4e73df', '#1cc88a', '#d9534f', '#f0ad4e'],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#c24135', '#c08d2e'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});
