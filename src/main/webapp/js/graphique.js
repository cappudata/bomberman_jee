var ctx = document.getElementById('graphique1').getContext('2d');
var vict = parseInt(document.getElementById('vict').innerHTML);
var egal = parseInt(document.getElementById('egal').innerHTML);
var lose = parseInt(document.getElementById('lose').innerHTML);


var donnee = {
	labels: ['nombre de victoire','nombre de defaite','nombre d\'egalite'],
	datasets: [
		{
			data: [vict,lose,egal],
			backgroundColor: [
      			'#CBF892',
      			'#F7B8AB',
      			'#F7F7AB'
   		    ],
    hoverOffset: 3
		}
	]
}
var options

var config={
	type : 'doughnut',
	data : donnee,
	options : options
}
var graph1 =  new Chart(ctx, config)