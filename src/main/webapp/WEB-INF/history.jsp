
<html>

<head>
<meta charset="UTF-8">
<title>History</title>
<link rel="stylesheet" type="text/css" href="css/history.css">
<link rel="stylesheet" type="text/css" href="css/menu.css">

</head>


<body>
<!-- <%@ include file="menu.jsp" %> -->
<div class="menu">
	<ul>
		<li><a href="connected">Home</a></li>
		<li><a href="buyitem">Store</a></li>
	</ul>
</div>


<div>
	<img class="bomberman" src="images/p1.png" alt="bomberman">
</div>

<div class="container-table">
	<div class="wrap-table">
		<div class="table">
			<table>
				<thead>
					<tr class="table-head">
						<th class="column1">ID game</th>
						<th class="column2">User name</th>
						<th class="column3">Status</th>
						<th class="column4">Numer player</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="game" items="${ games }">
						<tr>
							<td class="column1"><c:out value="${game._idgame}" /></td>
							<td class="column2"><c:out value="${game._username}"  /></td>
							<td class="column3"><c:out value="${game._statusGame }" /></td>
							<td class="column4"><c:out value="${game._nbrplayer}" /></td>
						</tr>
					</c:forEach>				
				</tbody>
			</table>
		</div>
	</div>
</div>

	
</body>
</html>