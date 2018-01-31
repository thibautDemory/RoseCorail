function affichercouleur(){
	var x= document.getElementById("choix-de-la-couleur").value;
	var ligne=document.createElement("div");
	ligne.setAttribute("class","form-inline");
	var span=document.createElement("span");
	span.innerHTML=x;

	var quantite = document.createElement("input");
	quantite.setAttribute("type","text");
	quantite.setAttribute("class","form-control");
	quantite.setAttribute("placeholder","quantité");

	var retirer=document.createElement("span");
	retirer.setAttribute("class","glyphicon glyphicon-remove");
	retirer.onclick=function(){
		ligne.remove();
	}


	
	ligne.appendChild(span);
	ligne.appendChild(quantite);
	ligne.appendChild(retirer);
	document.querySelector("#liste-des-couleurs-choisies").appendChild(ligne);

}

