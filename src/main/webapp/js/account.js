function insertAfter(newNode, existingNode) {
    existingNode.parentNode.insertBefore(newNode, existingNode.nextSibling);
}

var firstmodify = document.querySelector("#mmdp");
firstmodify.addEventListener("click", function(){
	
		
		let div = document.createElement("div");
		div.innerHTML= "<input placeholder=\"nouveau mot de passe\" type=\"password\" name=\"pass1\" class=\"mail\" required/>";
		
		let div2 = document.createElement("div");
		div2.innerHTML= "<input placeholder=\"confirmez mot de passe\" type=\"password\" name=\"pass2\" class=\"mail\" required/>";
		insertAfter(div,this);
		insertAfter(div2,div);
	this.remove();
	
	let sub = document.getElementById("sub");
	sub.hidden = false;
	
	let res = document.getElementById("reset");
	res.hidden = false;
	
},false);


var secondmodify = document.querySelector("#mmail");

secondmodify.addEventListener("click", function(){
	
	let mail = document.getElementById("cmail");
	
	let sub = document.getElementById("sub");
	sub.hidden = false;
	
	let res = document.getElementById("reset");
	res.hidden = false;
	
	let input = document.createElement("input");
	input.type = "mail";
	input.name = "useremail";
	input.className = "mail";
	input.id="cmail";
	input.title="pour pouvoir retrouver votre compte si vous oublier le mot de passe";
	input.placeholder="Entrez votre mail";
	 
	insertAfter(input,mail);
	mail.remove();
	this.remove();
},false);

var file = document.getElementById("file-upload");
file.addEventListener("click", function(){

	let sub = document.getElementById("sub");
	sub.hidden = false;
	
	let res = document.getElementById("reset");
	res.hidden = false;
},false);