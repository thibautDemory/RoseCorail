
function affichersouscategorie(){
	
	var x= document.getElementById("categorie-article").value;
	if (!document.getElementById("div-a-afficher-une-fois")) {
		var divform=document.createElement("div");
		var label=document.createElement("label");
		var div9=document.createElement("div");
		var select=document.createElement("select");
	
		divform.setAttribute("class","form-group");
		divform.setAttribute("id","div-a-afficher-une-fois");
		label.setAttribute("class","col-xs-3 control-label");
		label.setAttribute("for","sous-categorie-article");
		label.innerHTML="Sous catégorie : ";

		div9.setAttribute("class","col-xs-9");

		select.setAttribute("class","form-control");
		select.setAttribute("name","sous-categorie-article");

		
		var option1 =document.createElement("option");
		option1.setAttribute("id","option1souscategorie")
		var option2 =document.createElement("option");	
		option2.setAttribute("id","option2souscategorie")	
		var option3 =document.createElement("option");
		option3.setAttribute("id","option3souscategorie")

		select.appendChild(option1);
		select.appendChild(option2);
		select.appendChild(option3);
		div9.appendChild(select);
		divform.appendChild(label);
		divform.appendChild(div9);
		
		document.querySelector("#sous-categorie-div").appendChild(divform);

	}
	
	switch (x){
		case 'plat':
		document.getElementById("option1souscategorie").setAttribute("value","1");
		document.getElementById("option2souscategorie").setAttribute("value","2");
		document.getElementById("option3souscategorie").setAttribute("value","3");
		document.getElementById("option1souscategorie").innerHTML = "Plat à cake";
		document.getElementById("option2souscategorie").innerHTML = "Plat à fromage";
		document.getElementById("option3souscategorie").innerHTML = "Coupelle";
		break;
		case 'portecouteau':
		document.getElementById("option1souscategorie").setAttribute("value","4");
		document.getElementById("option2souscategorie").setAttribute("value","5");
		document.getElementById("option3souscategorie").setAttribute("value","6");
		document.getElementById("option1souscategorie").innerHTML = "Portes couteaux";
		document.getElementById("option2souscategorie").innerHTML = "Dessous de carafe";
		document.getElementById("option3souscategorie").innerHTML = "Dessous de plat";
		break;
		case 'verretransparent':
		document.getElementById("option1souscategorie").setAttribute("value","7");
		document.getElementById("option2souscategorie").setAttribute("value","8");
		document.getElementById("option3souscategorie").setAttribute("value","9");
		document.getElementById("option1souscategorie").innerHTML = "Plat à cake";
		document.getElementById("option2souscategorie").innerHTML = "Dessous de verre";
		document.getElementById("option3souscategorie").innerHTML = "Portes couteaux";
		break;
	}


	





}
