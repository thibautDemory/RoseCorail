window.onload=function(){
    donnerleprixhorstaxemarchandise();
    calculerlesfraisdeport();
    calculerleprixtotalHT();
    calculTVA();
    calculPrixTotalTTC();
}


function donnerleprixhorstaxemarchandise(){
    var prixtotaltableau=document.querySelectorAll(".prixtotal");
    var prixtotalmarchandiseHT=0.0;
    for (var i=0;i<prixtotaltableau.length;i++){
        prixtotalmarchandiseHT=prixtotalmarchandiseHT+Number(prixtotaltableau[i].textContent);
    }
    prixtotalmarchandiseHT=prixtotalmarchandiseHT.toFixed(2);
    document.querySelector(".prixmarchandiseHorsTaxe").textContent=prixtotalmarchandiseHT;
}
function calculerlesfraisdeport(){
    var totalmarchandise=Number(document.querySelector(".prixmarchandiseHorsTaxe").textContent);
    var fraisdeport=totalmarchandise*0.1;
    if (fraisdeport<15){
        fraisdeport=15;
    }if(fraisdeport>40){
        fraisdeport=40;
    }
    fraisdeport=fraisdeport.toFixed(2);
    document.querySelector(".fraisdeport").textContent=fraisdeport;
}
function calculerleprixtotalHT(){
    var prixtotalHT=Number(document.querySelector(".prixmarchandiseHorsTaxe").textContent)+Number(document.querySelector(".fraisdeport").textContent);
    document.querySelector(".prixtotalHT").textContent=prixtotalHT.toFixed(2);
}
function calculTVA(){
    var TVAchiffre=Number(document.querySelector(".prixtotalHT").textContent);
    console.log("tva="+TVAchiffre);
    TVAchiffre=TVAchiffre*0.2;
    TVAchiffre=TVAchiffre.toFixed(2);

    document.querySelector(".TVA").textContent=TVAchiffre;
}
function calculPrixTotalTTC(){
    var prixTTC=Number(document.querySelector(".prixtotalHT").textContent)+Number(document.querySelector(".TVA").textContent);
    prixTTC=prixTTC.toFixed(2);
    document.querySelector(".PrixTotalTTC").textContent=prixTTC;

}


