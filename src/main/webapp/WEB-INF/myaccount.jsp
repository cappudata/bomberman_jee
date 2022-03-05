<!DOCTYPE html>
<html lang="en">
<head>
	<title>My Account</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/account.css">
<!--===============================================================================================-->
	<script src="https://kit.fontawesome.com/432a990122.js" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img src="${user.profilepic}" alt="IMG">

				</div>
				<form method="post" id="form" action="myaccount" enctype="multipart/form-data" class="login100-form validate-form parent">
					<c:if test="${ !empty erreur }">	
						<div class="alert alert-danger" role="alert">
							<c:out value="${ erreur }" />
	 					 </div>	
					</c:if>
					
					<div class="player-name">
						${user.username }
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
						<label id="mmail" class="modify">modifier</label>
					</div>
					<div>
						<label for="file-upload" class="custom-file-upload">
    						<i class="fa fa-cloud-upload"></i> modifier ma photo de profile
						</label>
						<input name="photodeprofil" id="file-upload" type="file"/>
					</div>
					
					<div class="container-login100-form-btn">
						<input id="sub" type="submit" value="Valider les modifications" class="login100-form-btn" hidden/>
					</div>
					
					<div id="reset" hidden>
						<a href="myaccount">
							<p class="modify" >abandonnez</p>
						</a>
					</div>
					
				</form>
			</div>
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