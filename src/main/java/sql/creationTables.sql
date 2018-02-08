CREATE TABLE `categorie` (
  `id_categorie` int(11) NOT NULL AUTO_INCREMENT,
  `nom_categorie` varchar(30) NOT NULL,
  PRIMARY KEY (id_categorie)
);


CREATE TABLE `souscategorie` (
  `id_sous_categorie` int(11) NOT NULL AUTO_INCREMENT,
  `id_categorie` int(11) NOT NULL,
  `nom_sous_categorie` varchar(30) NOT NULL,
  PRIMARY KEY (id_sous_categorie),
  KEY `id_categorie_fk` (`id_categorie`),
  CONSTRAINT `id_categorie_fk` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id_categorie`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `article` (
  `id_article` int(11) NOT NULL AUTO_INCREMENT,
  `id_sous_categorie` int(11) NOT NULL,
  `nom_article` varchar(50) NOT NULL,
  `reference` varchar(40) NOT NULL,
  `description` text NOT NULL,
  `image` varchar(50) NOT NULL,
  `dimensions` varchar(30) NOT NULL,
  `prix` double NOT NULL,
  `lot_vente` int(11) NOT NULL,
  PRIMARY KEY (id_article),
  KEY `id_sous_categorie_fk` (`id_sous_categorie`),
  CONSTRAINT `id_sous_categorie_fk` FOREIGN KEY (`id_sous_categorie`) REFERENCES `souscategorie` (`id_sous_categorie`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE `compteclient` (
  `id_compte_client` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(70) NOT NULL,
  `nom_boutique` varchar(50) NOT NULL,
  `nom_gerant` varchar(50) NOT NULL,
  `prenom_gerant` varchar(50) NOT NULL,
  `adresse` varchar(130) NOT NULL,
  `ville` varchar(50) NOT NULL,
  `code_postal` char(5) NOT NULL,
  `mdp` varchar(60) NOT NULL,
  `numero_tel` varchar(15) NOT NULL,
  `num_tva` varchar(20) NOT NULL,
  `site_internet` varchar(100) NOT NULL,
  `description_activite` text NOT NULL,
  PRIMARY KEY (id_compte_client)
);

CREATE TABLE `couleur` (
  `id_couleur` int(11) NOT NULL AUTO_INCREMENT,
  `nom_couleur` varchar(40) NOT NULL,
  `num_couleur` varchar(40) NOT NULL,
  `image` varchar(50) NOT NULL,
  `saison` varchar(40) NOT NULL,
  PRIMARY KEY (id_couleur)
);

CREATE TABLE `posseder` (
  `id_couleur` int(11) NOT NULL,
  `id_article` int(11) NOT NULL,
  KEY `id_couleur_fk` (`id_couleur`),
  CONSTRAINT `id_couleur_fk` FOREIGN KEY (`id_couleur`) REFERENCES `couleur` (`id_couleur`) ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `id_article_fk` (`id_article`),
  CONSTRAINT `id_article_fk` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `devis` (
  `id_devis` int(11) NOT NULL AUTO_INCREMENT,
  `id_compte_client` int(11) NOT NULL,
  `date` DATE NOT NULL,
  `etat` varchar(20) NOT NULL,
  `etatPanier` boolean NOT NULL,
  KEY `id_compte_client_fk` (`id_compte_client`),
  CONSTRAINT `id_compte_client_fk` FOREIGN KEY (`id_compte_client`) REFERENCES `compteClient` (`id_compte_client`) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id_devis)
);


CREATE TABLE `lignedevis` (
  `id_ligne_devis` int(11) NOT NULL AUTO_INCREMENT,
  `id_couleur` int(11) NOT NULL,
  `id_devis` int(11) NOT NULL,
  `id_article` int(11) NOT NULL,
  KEY `id_couleur_fk` (`id_couleur`),
  CONSTRAINT `id_lignedevis_couleur_fk` FOREIGN KEY (`id_couleur`) REFERENCES `couleur` (`id_couleur`) ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `id_devis_fk` (`id_devis`),
  CONSTRAINT `id_lignedevis_devis_fk` FOREIGN KEY (`id_devis`) REFERENCES `devis` (`id_devis`) ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `id_article_fk` (`id_article`),
  CONSTRAINT `id_lignedevis_article_fk` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id_ligne_devis)
);




CREATE TABLE `compterosecorail` (
  `id_compte_RC` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(70) NOT NULL,
  `mdp` varchar(60) NOT NULL,
  `numero_tel` varchar(15) NOT NULL,
  PRIMARY KEY (id_compte_RC)
);

CREATE TABLE `actualite` (
  `id_actualite` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(30) NOT NULL,
  `contenu` text NOT NULL,
  `image` varchar(50) NOT NULL,
  PRIMARY KEY (id_actualite)
);

CREATE TABLE `statistiques` (
  `id_stat` int(11) NOT NULL AUTO_INCREMENT,
  `nom_boutique_num1` varchar(50) NOT NULL,
  `nom_boutique_num2` varchar(50) NOT NULL,
  `nom_boutique_num3` varchar(50) NOT NULL,
  `ref_article_vu_num1` varchar(40) NOT NULL,
  `ref_article_vu_num2` varchar(40) NOT NULL,
  `ref_article_vu_num3` varchar(40) NOT NULL,
  `ref_article_commandes_num1` varchar(40) NOT NULL,
  `ref_article_commandes_num2` varchar(40) NOT NULL,
  `ref_article_commandes_num3` varchar(40) NOT NULL,
  `nom_couleur1` varchar(40) NOT NULL,
  `nom_couleur2` varchar(40) NOT NULL,
  `nom_couleur3` varchar(40) NOT NULL,
  `mois` varchar(10) NOT NULL,
  `annee` char(4) NOT NULL,
  PRIMARY KEY (id_stat)
);

CREATE TABLE `definir` (
  `id_devis` int(11) NOT NULL,
  `id_stat` int(11) NOT NULL,
  KEY `id_devis_fk`  (`id_devis`),
  CONSTRAINT `id_devis_fk` FOREIGN KEY (`id_devis`) REFERENCES `devis` (`id_devis`) ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `id_stat_fk` (`id_stat`),
  CONSTRAINT `id_stat_fk` FOREIGN KEY (`id_stat`) REFERENCES `statistiques` (`id_stat`) ON DELETE CASCADE ON UPDATE CASCADE
);

