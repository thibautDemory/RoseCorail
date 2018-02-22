package hei.ProjetRoseCorail.entities;

public class CompteClient {
    private Integer id_compte_client;
    private String email;
    private String nom_boutique;
    private String nom_gerant;
    private String prenom_gerant;
    private String adresse;
    // complément d'adresse?
    private String ville;
    private String code_postal;
    private String mdp;
    private String numero_tel;
    private String num_tva;
    private String site_internet;
    private String description_activite;
    private Integer numero_panier_actif;


    public CompteClient(Integer id_compte_client, String email, String nom_boutique, String nom_gerant, String prenom_gerant, String adresse, String ville, String code_postal, String mdp, String numero_tel, String num_tva, String site_internet, String description_activite, Integer numeroPanier) {
        this.id_compte_client = id_compte_client;
        this.email = email;
        this.nom_boutique = nom_boutique;
        this.nom_gerant = nom_gerant;
        this.prenom_gerant = prenom_gerant;
        this.adresse = adresse;
        this.ville = ville;
        this.code_postal = code_postal;
        this.mdp = mdp;
        this.numero_tel = numero_tel;
        this.num_tva = num_tva;
        this.site_internet = site_internet;
        this.description_activite = description_activite;
        this.numero_panier_actif=numeroPanier;
    }

    public Integer getId_compte_client() {
        return id_compte_client;
    }

    public void setId_compte_client(Integer id_compte_client) {
        this.id_compte_client = id_compte_client;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom_boutique() {
        return nom_boutique;
    }

    public void setNom_boutique(String nom_boutique) {
        this.nom_boutique = nom_boutique;
    }

    public String getNom_gerant() {
        return nom_gerant;
    }

    public void setNom_gerant(String nom_gerant) {
        this.nom_gerant = nom_gerant;
    }

    public String getPrenom_gerant() {
        return prenom_gerant;
    }

    public void setPrenom_gerant(String prenom_gerant) {
        this.prenom_gerant = prenom_gerant;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNumero_tel() {
        return numero_tel;
    }

    public void setNumero_tel(String numero_tel) {
        this.numero_tel = numero_tel;
    }

    public String getNum_tva() {
        return num_tva;
    }

    public void setNum_tva(String num_tva) {
        this.num_tva = num_tva;
    }

    public String getSite_internet() {
        return site_internet;
    }

    public void setSite_internet(String site_internet) {
        this.site_internet = site_internet;
    }

    public String getDescription_activite() {
        return description_activite;
    }

    public void setDescription_activite(String description_activite) {
        this.description_activite = description_activite;
    }

    public Integer getNumero_panier_actif() {
        return numero_panier_actif;
    }

    public void setNumero_panier_actif(Integer numero_panier_actif) {
        this.numero_panier_actif = numero_panier_actif;
    }
}
