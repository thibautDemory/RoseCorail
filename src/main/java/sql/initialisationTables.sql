DELETE FROM posseder;
DELETE FROM lignedevis;
DELETE FROM article;
DELETE FROM souscategorie;
DELETE FROM categorie;
DELETE FROM definir;
DELETE FROM actualite;
DELETE FROM devis;
DELETE FROM compteclient;
DELETE FROM compterosecorail;
DELETE FROM couleur;
DELETE FROM statistiques;

INSERT INTO `categorie`(`id_categorie`,`nom_categorie`) VALUES (1,'Les plats');
INSERT INTO `categorie`(`id_categorie`,`nom_categorie`) VALUES (2,'Porte-couteaux');
INSERT INTO `categorie`(`id_categorie`,`nom_categorie`) VALUES (3,'Décoration de table');
INSERT INTO `categorie`(`id_categorie`,`nom_categorie`) VALUES (4,'La maison');

INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (1, 1, 'Plats à cake');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (2, 1, 'Plats à fromage');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (3, 1, 'Coupelle et apéritif');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (4, 2, 'Porte-couteaux');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (5, 3, 'Dessous de Verre');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (6, 3, 'Dessous de Plat');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (7, 4, 'La maison');


INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`) VALUES (1,1, 'Plats à cake', '6030 407', 'Plat à cake', 'D:/Informatique/Projet 100h/RoseCorail/src/main/webapp/image/platacake.jpg', '32 x 15 cm', 10.0, 1);
INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`) VALUES (2,1, 'Plat à cake avec bords', '6030 414', 'Plat à cake avec bords', 'D:/Informatique/Projet 100h/RoseCorail/src/main/webapp/image/platacake.jpg', '32 x 15 cm', 12.0, 1);
INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`) VALUES (3,2, 'Plats à fromage', '6030 062', 'Plat carré', 'D:/Informatique/Projet 100h/RoseCorail/src/main/webapp/image/platacake.jpg', '30 x 30 cm', 14.0, 1);
INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`) VALUES (4,3, 'Coupelles', '6030 072', 'Coupelle', 'D:/Informatique/Projet 100h/RoseCorail/src/main/webapp/image/platacake.jpg', '18 x 18 cm', 10.0, 2);

INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`) VALUES (1,'william@evrard.fr', 'HEI', 'EVRARD', 'William', '26 BD Bigo Danel', 'Lille', '59000', 'monMDP1', '0606060606', 'FR 40 123456824', 'william.evrard.fr', 'description1');
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`) VALUES (2,'thibaut@demory.fr', 'ISA', 'DEMORY', 'Thibaut', '20 rue Beaucourt', 'Lille', '59000', 'monMDP2', '0606060607', 'FR 41 123456824', 'thib.demory.fr', 'description2');
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`) VALUES (3,'arnold@blyau.fr', 'ISEN', 'BLYAU', 'Arnold', '54 rue Paul Bocuse', 'Lille', '59000', 'monMDP3', '0606060608', 'FR 42 123456824', 'arnold.blyau.fr', 'description3');
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`) VALUES (4,'remy@schoonaert.fr', 'EDHEC', 'SCHOONAERT', 'Remy', '2 BD Bigo Danel', 'Lille', '59000', 'monMDP4', '0606060609', 'FR 43 123456824', 'remy.schonnaert.fr', 'description4');
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`) VALUES (5,'jules@evrard.fr', 'POLYTECHNIQUE', 'EVRARD', 'Jules', '24 rue du marronnier', 'Lille', '59000', 'monMDP5', '0606060601', 'FR 44 123456824', 'jules.evrard.fr', 'description5');
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`) VALUES (6,'maxime@evrard.fr', 'PARIS DAUPHINE', 'EVRARD', 'Maxime', '154 rue Charlemagne', 'Lille', '59000', 'monMDP6', '0606060602', 'FR 45 123456824', 'maxime.evrard.fr', 'description6');

INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (1, 'Bleu gris', '087', 'image1', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (2, 'Bleu glacier', '097', 'image1', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (3, 'Bleu vert', '08', 'image1', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (4, 'Sapin bleuté', '091', 'image1', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (5, 'Lichen', '98', 'image1', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (6, 'Forêt', '103', 'image1', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (7, 'Noir', '018', 'image2', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (8, 'Eléphant', '035', 'image2', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (9, 'Taupe', '036', 'image2', 'Printemps-Ete 2018');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (10, 'Mastic', '150', 'image2', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (11, 'Bronze', '032', 'image2', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (12, 'Argent', '030', 'image2', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (13, 'Marron glacé', '102', 'image3', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (14, 'Camel', '101', 'image3', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (15, 'Mastic', '150', 'image3', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (16, 'Jaune dor', '039', 'image3', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (17, 'Champagne', '092', 'image3', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (18, 'Rose poudré', '090', 'image4', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (19, 'Pivoine', '100', 'image4', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (20, 'Aubergine', '099', 'image4', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (21, 'Rouge', '028', 'image4', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (22, 'Bordeaux', '019', 'image4', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (23, 'Safran', '096', 'image4', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (24, 'Sapin bleuté', '091', 'image5', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (25, 'Bleu vert', '008', 'image5', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (26, 'Celadon', '089', 'image5', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (27, 'Bleu gris', '087', 'image5', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (28, 'Turquoise', '020', 'image5', 'Printemps-Ete 2017');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`) VALUES (29, 'Canard', '009', 'image5', 'Printemps-Ete 2017');

INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (1, 1);
INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (2, 2);
INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (3, 3);
INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (4, 4);

INSERT INTO `devis`(`id_devis`, `id_compte_client`, `date`, `etat`, `etatPanier`) VALUES (1, 1, '2017-04-06', 'Demandé', true);
INSERT INTO `devis`(`id_devis`, `id_compte_client`, `date`, `etat`, `etatPanier`) VALUES (2, 2, '2017-11-20', 'En préparation', true);
INSERT INTO `devis`(`id_devis`, `id_compte_client`, `date`, `etat`, `etatPanier`) VALUES (3, 3, '2017-10-10', 'Expédié', false);

INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`) VALUES (1, 1, 1, 1);
INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`) VALUES (2, 1, 1, 3);
INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`) VALUES (3, 1, 1, 1);

INSERT INTO `compterosecorail`(`id_compte_RC`,`email`,`mdp`, `numero_tel`) VALUES (1, 'beatrice.roquette@rosecorail.com', 'monmdp', '06 12 63 07 12');

INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (1, 'Salon de Paris', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaPARIS', 'imageSalonParis');
INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (2, 'Salon de Reims', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaREIMS', 'imageSalonREIMS');
INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (3, 'Salon de Picardie', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaPICARDIE', 'imageSalonPICARDIE');

INSERT INTO `statistiques`(`id_stat`, `nom_boutique_num1`, `nom_boutique_num2`, `nom_boutique_num3`, `ref_article_vu_num1`, `ref_article_vu_num2`, `ref_article_vu_num3`, `ref_article_commandes_num1`, `ref_article_commandes_num2`, `ref_article_commandes_num3`, `nom_couleur1`, `nom_couleur2`, `nom_couleur3`, `mois`, `annee`) VALUES (1, 'HEI', 'ISA', 'ISEN', '6030 407', '6030 414', '6030 062', '6030 062', '6030 414', '6030 407', 'Bleu gris 087', 'Bleu glacier 097', 'Noir 018', 'janvier', '2017');
INSERT INTO `statistiques`(`id_stat`, `nom_boutique_num1`, `nom_boutique_num2`, `nom_boutique_num3`, `ref_article_vu_num1`, `ref_article_vu_num2`, `ref_article_vu_num3`, `ref_article_commandes_num1`, `ref_article_commandes_num2`, `ref_article_commandes_num3`, `nom_couleur1`, `nom_couleur2`, `nom_couleur3`, `mois`, `annee`) VALUES (2, 'HEI', 'ISA', 'ISEN', '6030 407', '6030 414', '6030 062', '6030 062', '6030 414', '6030 407', 'Bleu gris 087', 'Bleu glacier 097', 'Noir 018', 'mars', '2017');
INSERT INTO `statistiques`(`id_stat`, `nom_boutique_num1`, `nom_boutique_num2`, `nom_boutique_num3`, `ref_article_vu_num1`, `ref_article_vu_num2`, `ref_article_vu_num3`, `ref_article_commandes_num1`, `ref_article_commandes_num2`, `ref_article_commandes_num3`, `nom_couleur1`, `nom_couleur2`, `nom_couleur3`, `mois`, `annee`) VALUES (3, 'HEI', 'ISA', 'ISEN', '6030 407', '6030 414', '6030 062', '6030 062', '6030 414', '6030 407', 'Bleu gris 087', 'Bleu glacier 097', 'Noir 018', 'janvier', '2018');

INSERT INTO `definir`(`id_devis`,`id_stat`) VALUES (1, 1);
INSERT INTO `definir`(`id_devis`,`id_stat`) VALUES (2, 1);
INSERT INTO `definir`(`id_devis`,`id_stat`) VALUES (3, 1);
