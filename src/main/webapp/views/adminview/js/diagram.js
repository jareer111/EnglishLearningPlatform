$(document).ready(function(){

    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        var data = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            ['Teachers 18', 18],
            ['Users 55', 55],
            ['Admins 5', 5]
        ]);

        var options = {
            title: 'Users statistics by roles in the system this time',
            pieSliceText: 'value',
            is3D:true
        };


        var chart = new google.visualization.PieChart(document.getElementById('piechart3d'));

        chart.draw(data, options);
    }


});