
<html>

<head>
	<meta charset="UTF-8">
	<title>History</title>
	<link rel="stylesheet" type="text/css" href="css/history.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.1/chart.min.js" integrity="sha512-QSkVNOCYLtj73J4hbmVoOV6KVZuMluZlioC+trLpewV8qMjsWqlIQvkn1KGX2StWvPMdWGBqim1xlC8krl1EKQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>


<body>
<%@ include file="menu.jsp" %>

<div class="div_history">
		<div class="stats">
			<div>
				<p>nombre de partie gagnant: <span id="vict"><c:out value="${vict}" /></span></p>
				<p>nombre de partie perdue: <span id="lose"><c:out value="${lose}" /></span></p>
				<p>nombre de partie à égalité: <span id="egal"><c:out value="${egal}" /></span></p>
				<p>Kill: <span id="nbra"><c:out value="${nbra}" /></span></p>
				<p>Mort: <span id="nbrm"><c:out value="${nbrm}" /></span></p>
			</div>
			<div>
				<canvas id="graphique1">
					
				</canvas>
			</div>
		</div>
		
		<div class="historique">
				<p class="title">HISTORIQUE</p>
				<table class="table_hist">
					<tr class="row_head">
						<th>Id du jeu</th>
						<th>Resultat final</th>
						<th>Mes adversaires</th>
					</tr>
					
					<c:forEach var="game" items="${ games }">
						<tr class="game_line">
							<td class="column1"><c:out value="${game._idgame}" /></td>
							<td class="${game._statusGame }"><c:out value="${game._statusGame }" /></td>
							<td class="column4"><c:out value="${game._nbrplayer}" /></td>
						</tr>
					</c:forEach>	
				</table>
		</div>
		<div class="image_bomberman">
			<img class="bomberman" src="images/p1.png" alt="bomberman">
		</div>

</div>

	<script src="js/graphique.js"></script>
	
</body>
</html>