function affichercouleur(){
	var x= document.getElementById("choix-de-la-couleur").value;
	var myArray=document.getElementById("tableau-couleur");
	var ligne=document.createElement("tr");
	var image=document.createElement("img");
	var nom = document.createElement("span");
	var quantite=document.createElement("input");
	var span=document.createElement("span");
	var td1 = document.createElement("td");
	var td2 = document.createElement("td");
	var td3 = document.createElement("td");
	var td4 = document.createElement("td");
	console.log(x);

	td1.setAttribute("class","col-xs-3");
	var adresse="images/"+x+".png"
	image.setAttribute("src",adresse);
	image.setAttribute("class","img-responsive margin-bottom");
	nom.textContent=x;
	td2.setAttribute("align","center");
	quantite.setAttribute("placeholder","quantite");
	quantite.setAttribute("class","form-control");
	quantite.setAttribute("type","text");

	span.setAttribute("class","glyphicon glyphicon-remove");

	span.onclick=function(){
		ligne.remove();
	}	


	td1.appendChild(image);
	td2.appendChild(nom);
	td3.appendChild(quantite);
	td4.appendChild(span);
	ligne.appendChild(td1);
	ligne.appendChild(td2);
	ligne.appendChild(td3);
	ligne.appendChild(td4);
	document.querySelector("#tableau-couleurs").appendChild(ligne);

/*		<tr>
                              <td> <img src="images/blanc_asperge.png" class="img-responsive"></td>
                              <td> Blanc asperge</td>
                              <td><input type="text" placeholder="quantité" class="form-control"></td>
                              <td> <span class="glyphicon glyphicon-remove"></span></td>
                            </tr>			*/
}

