
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Shop</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:300i,400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/shop.css">
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="div_shop">
	<c:forEach var="item" items="${ Items }">
		<div class="content">
	        <img src="${item.image}">
	        <h3><c:out value="${item.name}" /></h3>
	        <p><c:out value="${item.description}" /></p>
	        <h6><i class="fa fa-btc" aria-hidden="true"></i> <c:out value="${item.price}" /></h6>
	        <ul>
	          <c:forEach var="i" begin="1" end="${item.rate}" step="1">
					<li><i class="fa fa-star" aria-hidden="true"></i></li>
			  </c:forEach>
			  <c:forEach var="i" begin="1" end="${5-item.rate}" step="1">
					<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
			  </c:forEach>
	        </ul>
	        <button class="buy_button">Acheter</button>
     	 </div>
    </c:forEach> 
	

</div>

</body>
</html>