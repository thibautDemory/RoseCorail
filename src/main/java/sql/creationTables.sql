
CREATE TABLE `article` (
  `id_article` int(11)  AUTO_INCREMENT,
  `id_sous_categorie` int(11) NOT NULL,
  `nom_article` varchar(50) NOT NULL,
  `reference` varchar(40) NOT NULL,
  `description` text NOT NULL,
  `image` varchar(100) NOT NULL,
  `dimensions` varchar(30) NOT NULL,
  `prix` double NOT NULL,
  `lot_vente` int(11) NOT NULL,
  `actif` int(11) NOT NULL,
  PRIMARY KEY (id_article)
)engine = innodb;

CREATE TABLE `compteclient` (
  `id_compte_client` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(70) NOT NULL,
  `nom_boutique` varchar(50) NOT NULL,
  `nom_gerant` varchar(50) NOT NULL,
  `prenom_gerant` varchar(50) NOT NULL,
  `adresse` varchar(130) NOT NULL,
  `ville` varchar(50) NOT NULL,
  `code_postal` char(5) NOT NULL,
  `mdp` varchar(120) NOT NULL,
  `numero_tel` varchar(15) NOT NULL,
  `num_tva` varchar(20) NOT NULL,
  `site_internet` varchar(100) NOT NULL,
  `description_activite` text NOT NULL,
  numero_panier_actif int(11),
  PRIMARY KEY (id_compte_client)
)engine = innodb;

CREATE TABLE `couleur` (
  `id_couleur` int(11) NOT NULL AUTO_INCREMENT,
  `nom_couleur` varchar(40) NOT NULL,
  `num_couleur` varchar(40) NOT NULL,
  `image` varchar(50) NOT NULL,
  `saison` varchar(40) NOT NULL,
  `actif` int(11) NOT NULL,
  PRIMARY KEY (id_couleur)
)engine = innodb;

CREATE TABLE `posseder` (
  `id_posseder` int(11) NOT NULL AUTO_INCREMENT,
  `id_couleur` int(11) NOT NULL,
  `id_article` int(11) NOT NULL,
  KEY `id_couleur_fk` (`id_couleur`),
  CONSTRAINT `id_couleur_fk` FOREIGN KEY (`id_couleur`) REFERENCES `couleur` (`id_couleur`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `id_article_fk` (`id_article`),
  CONSTRAINT `id_article_fk` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  PRIMARY  KEY (id_posseder)
)engine = innodb;

CREATE TABLE `devis` (
  `id_devis` int(11) NOT NULL AUTO_INCREMENT,
  `id_compte_client` int(11) NOT NULL,
  `date_creation` DATE NOT NULL,
  `etat` varchar(20) NOT NULL,
  `etatPanier` boolean NOT NULL,
  KEY `id_compte_client_fk` (`id_compte_client`),
  CONSTRAINT `id_compte_client_fk` FOREIGN KEY (`id_compte_client`) REFERENCES `compteClient` (`id_compte_client`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  PRIMARY KEY (id_devis)
)engine = innodb;


CREATE TABLE `lignedevis` (
  `id_ligne_devis` int(11) NOT NULL AUTO_INCREMENT,
  `id_couleur` int(11) NOT NULL,
  `id_devis` int(11) NOT NULL,
  `id_article` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  KEY `id_couleur_fk` (`id_couleur`),
  CONSTRAINT `id_lignedevis_couleur_fk` FOREIGN KEY (`id_couleur`) REFERENCES `couleur` (`id_couleur`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `id_devis_fk` (`id_devis`),
  CONSTRAINT `id_lignedevis_devis_fk` FOREIGN KEY (`id_devis`) REFERENCES `devis` (`id_devis`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `id_article_fk` (`id_article`),
  CONSTRAINT `id_lignedevis_article_fk` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  PRIMARY KEY (id_ligne_devis)
)engine = innodb;




CREATE TABLE `compterosecorail` (
  `id_compte_RC` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(70) NOT NULL,
  `mdp` varchar(120) NOT NULL,
  `numero_tel` varchar(15) NOT NULL,
  PRIMARY KEY (id_compte_RC)
)engine = innodb;

CREATE TABLE `actualite` (
  `id_actualite` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(100) NOT NULL,
  `contenu` text NOT NULL,
  `image` varchar(300) NOT NULL,
  PRIMARY KEY (id_actualite)
)engine = innodb;

CREATE TABLE `panelcoloris` (
  `id_panelcoloris` int(11) NOT NULL AUTO_INCREMENT,
  `legende` text NOT NULL,
  `image` varchar(300) NOT NULL,
  `saison` varchar(50) NOT NULL,
  PRIMARY KEY (id_panelcoloris)
)engine = innodb;



CREATE TABLE `photopresentation` (
  `id_photo` int(11) NOT NULL AUTO_INCREMENT,
  `page` VARCHAR (20) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  PRIMARY KEY (id_photo)
)engine = innodb;