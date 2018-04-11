function changerletatBDD(etat,idDevis){
    var requetechangeretatBDD= new XMLHttpRequest();
    requetechangeretatBDD.open("POST","/RoseCorail/webservice/DevisRCWS",true);
    requetechangeretatBDD.responseType="text";
    requetechangeretatBDD.setRequestHeader("Content-type","application/x-www-form-urlencoded");

    requetechangeretatBDD.onload=function () {
        console.log("changer l'état");
    }
    requetechangeretatBDD.send("idDevis="+idDevis+"&etat="+etat);


}
function changerletatdelaligne(idDevis){
    var etat=document.querySelector(".etat"+idDevis).value;
    changerletatBDD(etat,idDevis);
    console.log("Si on est là, c'est que tout a bien marché");

}