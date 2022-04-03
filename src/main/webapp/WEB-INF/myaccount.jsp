<!DOCTYPE html>
<html lang="en">
<head>
	<title>My Account</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:300i,400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/account.css">
<!--===============================================================================================-->
   
</head>
<body>
	<%@ include file="menu.jsp" %>
	<c:if test="${ !empty message }">	
		<div class="by_message" role="alert">
			<span><c:out value="${ message }" /></span>
	 	</div>	
	</c:if>
	
	<div class="div_account">
		<div class="messkins">
		 <p class="title skins"> Mes Skins </p>
			<form class="form-skins" method="POST" action="manageskin">
				<div class="item">
						<p class="title"> Mur cassable </p>
					
						<ul class="listcat">
						  <c:forEach var="item" items="${ wall }">
						  <c:choose>
							  <c:when test="${item.inUsage == true}">
							    <c:set var="alreadyuse" value="checked" scope="page" />
							  </c:when>
							  <c:when test="${item.inUsage == false}">
							    <c:set var="alreadyuse" value=" " scope="page" />
							  </c:when>
							  <c:otherwise>
							   	<c:set var="alreadyuse" value=" " scope="page" />
							  </c:otherwise>
							</c:choose>
						    <li class="itemlist"> <input class="rdbtn other" type="radio" name="wall" value="${item.id}" ${alreadyuse}/><img class="img" src="${item.image}" alt="${item.name}"></li>
						  </c:forEach>
						</ul>
						
						<p class="title"> Mur incassable </p>
						<ul class="listcat">
						  <c:forEach var="item" items="${ wallEx }">
						  <c:choose>
							  <c:when test="${item.inUsage == true}">
							    <c:set var="alreadyuse" value="checked" scope="page" />
							  </c:when>
							  <c:when test="${item.inUsage == false}">
							    <c:set var="alreadyuse" value=" " scope="page" />
							  </c:when>
							  <c:otherwise>
							   	<c:set var="alreadyuse" value=" " scope="page" />
							  </c:otherwise>
							</c:choose>
						    <li class="itemlist"> <input class="rdbtn other" type="radio" name="wallEx" value="${item.id}" ${alreadyuse}/><img class="img" src="${item.image}" alt="${item.name}"></li>
						  </c:forEach>
						</ul>
						
						<p class="title"> Fond de la map </p>
						<ul class="listcat">
						  <c:forEach var="item" items="${ map }">
						  	<c:choose>
							  <c:when test="${item.inUsage == true}">
							    <c:set var="alreadyuse" value="checked" scope="page" />
							  </c:when>
							  <c:when test="${item.inUsage == false}">
							    <c:set var="alreadyuse" value=" " scope="page" />
							  </c:when>
							  <c:otherwise>
							   	<c:set var="alreadyuse" value=" " scope="page" />
							  </c:otherwise>
							</c:choose>
						    <li class="itemlist"> <input class="rdbtn other" type="radio" name="map" value="${item.id}" ${alreadyuse} /><img class="img" src="${item.image}" alt="${item.name}"></li>
						  </c:forEach>
						</ul>
						
						<p class="title"> Toute la map </p>
						<ul class="listcat">
						  <c:forEach var="item" items="${ all }">
						  <c:choose>
							  <c:when test="${item.inUsage == true}">
							    <c:set var="alreadyuse" value="checked" scope="page" />
							  </c:when>
							  <c:when test="${item.inUsage == false}">
							    <c:set var="alreadyuse" value=" " scope="page" />
							  </c:when>
							  <c:otherwise>
							   	<c:set var="alreadyuse" value=" " scope="page" />
							  </c:otherwise>
							</c:choose>
						    <li class="itemlist"> <input class="rdbtn all" type="radio" name="all" value="${item.id}" ${alreadyuse} /><img class="img" src="${item.image}" alt="${item.name}"></li>
						  </c:forEach>
						</ul>
					
				</div>
				<div id="sub1" class="container-login100-form-btn" hidden>
					<input  type="submit" value="Valider les modifications" class="login100-form-btn"/>
				</div>
			</form>
		</div>
		
		<div class="mesdoonnees">
			<div class="profil">
				<img class="profilepic" src="${user.profilepic}" alt="IMG">
				<span class="player-name">
						${user.username }
				</span>
			</div>
			<form method="post" id="form" action="myaccount" enctype="multipart/form-data" class="login100-form validate-form parent">
				<c:if test="${ !empty erreur }">	
					<div class="alert alert-danger" role="alert">
						<c:out value="${ erreur }" />
					</div>	
				</c:if>
				<div class="money">
					<span class="mycoins">Mes coins : ${coins } <i class="fa fa-btc" aria-hidden="true" ></i> </span>
				</div>
				<div id="mmdp">
					<p class="modify">modifier mon mot de passe</p>
				</div>
	
				<div id="divmail">
					<c:if test="${empty user.mail }">
						<input id="cmail" title="pour pouvoir retrouver votre compte si vous oublier le mot de passe" type="mail" value="aucun mail fourni !" class="mail" disabled/>
					</c:if>
					
					<c:if test="${ !empty user.mail }">
						<input name="mail" id="cmail" title="pour pouvoir retrouver votre compte si vous oublier le mot de passe" type="mail" value="${user.mail }" class="mail" disabled/>
					</c:if>
					<p id="mmail" class="modify">modifier</p>
				</div>
				<div>
					<label for="file-upload" class="custom-file-upload">
	  						<i class="fa fa-cloud-upload"></i> modifier ma photo de profile
					</label>
					<input name="photodeprofil" id="file-upload" type="file"/>
				</div>
				
				<div id="sub" class="container-login100-form-btn" hidden>
					<input  type="submit" value="Valider les modifications" class="login100-form-btn"/>
				</div>
				
				<div id="reset" hidden>
					<a href="myaccount">
						<p class="modify">Abandonnez</p>
					</a>
				</div>
			
			</form>
		</div>

	
	</div>
	
	

	
<!--===============================================================================================-->	
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/tilt/tilt.jquery.min.js"></script>

	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>
	<script src="js/account.js"></script>

</body>
</html>