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
DELETE FROM panelcoloris;
DELETE FROM photopresentation;


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


INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`,actif) VALUES (1,1, 'Plats à cake', '6030 407', 'Plat à cake', 'image\\article\\aze\\image.jpg', '32 x 15 cm', 10.0, 1,1);
INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`,actif) VALUES (2,1, 'Plat à cake avec bords', '6030 414', 'Plat à cake avec bords', 'image\\article\\aze\\image.jpg', '32 x 15 cm', 12.0, 1,1);
INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`,actif) VALUES (3,2, 'Plats à fromage', '6030 062', 'Plat carré', 'image\\article\\aze\\image.jpg', '30 x 30 cm', 14.0, 1,1);
INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`,actif) VALUES (4,3, 'Coupelles', '6030 072', 'Coupelle', 'image\\article\\aze\\image.jpg', '18 x 18 cm', 10.0, 2,1);

INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`, numero_panier_actif) VALUES (1,'william.evrard@hei.yncrea.fr', 'HEI', 'EVRARD', 'William', '26 BD Bigo Danel', 'Lille', '59000', '3a111d3ae4b18d0b477aa911b43f8c3d6453a5705ec7bc81dc380c87e2208a1f', '0606060606', 'FR 40 123456824', 'william.evrard.fr', 'description1',1);
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`, numero_panier_actif) VALUES (2,'thibaut@demory.fr', 'ISA', 'DEMORY', 'Thibaut', '20 rue Beaucourt', 'Lille', '59000', '34199fb0315b63a99582920e7e5a2643ad7d768b4d31b50a28fd763b485a5958', '0606060607', 'FR 41 123456824', 'thib.demory.fr', 'description2',2);
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`, numero_panier_actif) VALUES (3,'arnold@blyau.fr', 'ISEN', 'BLYAU', 'Arnold', '54 rue Paul Bocuse', 'Lille', '59000', 'de7914718e956d89f1f88fd2387c01d25c84d6c7a143f51fac1a91773aef84cc', '0606060608', 'FR 42 123456824', 'arnold.blyau.fr', 'description3',3);
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`, numero_panier_actif) VALUES (4,'remy@schoonaert.fr', 'EDHEC', 'SCHOONAERT', 'Remy', '2 BD Bigo Danel', 'Lille', '59000', '20869049e64dd4d7c1923175c7ed265959ff298dc77c914560bf510c1aee7560', '0606060609', 'FR 43 123456824', 'remy.schonnaert.fr', 'description4',4);
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`, numero_panier_actif) VALUES (5,'jules@evrard.fr', 'POLYTECHNIQUE', 'EVRARD', 'Jules', '24 rue du marronnier', 'Lille', '59000', 'b536b1abdf1ce91450f9f0752f8006798d72b32294fe1916a043c0e967d6a99a', '0606060601', 'FR 44 123456824', 'jules.evrard.fr', 'description5',5);
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`, numero_panier_actif) VALUES (6,'maxime@evrard.fr', 'PARIS DAUPHINE', 'EVRARD', 'Maxime', '154 rue Charlemagne', 'Lille', '59000', 'dda71c10f84aa4e4a98a36dbc1cc20981a4c4b0270d44fe5b99a534877356610', '0606060602', 'FR 45 123456824', 'maxime.evrard.fr', 'description6',6);

INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (1, 'Bleu gris', '087', 'image1', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (2, 'Bleu glacier', '097', 'image1', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (3, 'Bleu vert', '08', 'image1', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (4, 'Sapin bleuté', '091', 'image1', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (5, 'Lichen', '98', 'image1', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (6, 'Forêt', '103', 'image1', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (7, 'Noir', '018', 'image2', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (8, 'Eléphant', '035', 'image2', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (9, 'Taupe', '036', 'image2', 'Printemps-Ete 2018',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (10, 'Mastic', '150', 'image2', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (11, 'Bronze', '032', 'image2', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (12, 'Argent', '030', 'image2', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (13, 'Marron glacé', '102', 'image3', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (14, 'Camel', '101', 'image3', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (15, 'Jaune dor', '039', 'image3', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (16, 'Champagne', '092', 'image3', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (17, 'Rose poudré', '090', 'image4', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (18, 'Pivoine', '100', 'image4', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (19, 'Aubergine', '099', 'image4', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (20, 'Rouge', '028', 'image4', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (21, 'Bordeaux', '019', 'image4', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (22, 'Safran', '096', 'image4', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (23, 'Celadon', '089', 'image5', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (24, 'Turquoise', '020', 'image5', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (25, 'Canard', '009', 'image5', 'Printemps-Ete 2017',1);


INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (1, 1);
INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (2, 2);
INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (3, 3);
INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (4, 4);

INSERT INTO `devis`(`id_devis`, `id_compte_client`, `date_creation`, `etat`, `etatPanier`) VALUES (1, 1, '2017-04-06', 'panier', true);
INSERT INTO `devis`(`id_devis`, `id_compte_client`, `date_creation`, `etat`, `etatPanier`) VALUES (2, 2, '2017-11-20', 'panier', true);
INSERT INTO `devis`(`id_devis`, `id_compte_client`, `date_creation`, `etat`, `etatPanier`) VALUES (3, 3, '2017-10-10', 'panier', true);
INSERT INTO `devis`(`id_devis`, `id_compte_client`, `date_creation`, `etat`, `etatPanier`) VALUES (4, 4, '2017-10-10', 'panier', true);

INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`, quantite) VALUES (1, 1, 1, 1, 3);
INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`, quantite) VALUES (2, 1, 1, 3, 3);
INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`, quantite) VALUES (3, 1, 1, 1, 3);

INSERT INTO `compterosecorail`(`id_compte_RC`,`email`,`mdp`, `numero_tel`) VALUES (1, 'beatrice.roquette@rosecorail.com', '51d59a88b0e1f1ad672dad1fb56626c2169a737ac5d6cd544355c53bc495e769', '06 12 63 07 12');

INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (1, 'Salon de Paris', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaPARIS', 'imageSalonParis');
INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (2, 'Salon de Reims', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaREIMS', 'imageSalonREIMS');
INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (3, 'Salon de Picardie', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaPICARDIE', 'imageSalonPICARDIE');

INSERT INTO `statistiques`(`id_stat`, `nom_boutique_num1`, `nom_boutique_num2`, `nom_boutique_num3`, `ref_article_vu_num1`, `ref_article_vu_num2`, `ref_article_vu_num3`, `ref_article_commandes_num1`, `ref_article_commandes_num2`, `ref_article_commandes_num3`, `nom_couleur1`, `nom_couleur2`, `nom_couleur3`, `mois`, `annee`) VALUES (1, 'HEI', 'ISA', 'ISEN', '6030 407', '6030 414', '6030 062', '6030 062', '6030 414', '6030 407', 'Bleu gris 087', 'Bleu glacier 097', 'Noir 018', 'janvier', '2017');
INSERT INTO `statistiques`(`id_stat`, `nom_boutique_num1`, `nom_boutique_num2`, `nom_boutique_num3`, `ref_article_vu_num1`, `ref_article_vu_num2`, `ref_article_vu_num3`, `ref_article_commandes_num1`, `ref_article_commandes_num2`, `ref_article_commandes_num3`, `nom_couleur1`, `nom_couleur2`, `nom_couleur3`, `mois`, `annee`) VALUES (2, 'HEI', 'ISA', 'ISEN', '6030 407', '6030 414', '6030 062', '6030 062', '6030 414', '6030 407', 'Bleu gris 087', 'Bleu glacier 097', 'Noir 018', 'mars', '2017');
INSERT INTO `statistiques`(`id_stat`, `nom_boutique_num1`, `nom_boutique_num2`, `nom_boutique_num3`, `ref_article_vu_num1`, `ref_article_vu_num2`, `ref_article_vu_num3`, `ref_article_commandes_num1`, `ref_article_commandes_num2`, `ref_article_commandes_num3`, `nom_couleur1`, `nom_couleur2`, `nom_couleur3`, `mois`, `annee`) VALUES (3, 'HEI', 'ISA', 'ISEN', '6030 407', '6030 414', '6030 062', '6030 062', '6030 414', '6030 407', 'Bleu gris 087', 'Bleu glacier 097', 'Noir 018', 'janvier', '2018');

INSERT INTO `definir`(`id_devis`,`id_stat`) VALUES (1, 1);
INSERT INTO `definir`(`id_devis`,`id_stat`) VALUES (2, 1);
INSERT INTO `definir`(`id_devis`,`id_stat`) VALUES (3, 1);

INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (1, 'Bleu gris 087, Bleu glacier 097, bleu vert 08, sapin bleuté 091, lichen 98, forêt 103','/RoseCorail/images/coloris-1.jpg','Printemps-Été 2016');
INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (2, 'Noir 018, éléphant 035, taupe 036, mastic 150, bronze 032, argent 030','/RoseCorail/images/coloris-2.jpg','Printemps-Été 2016');
INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (3, 'Marron glacé 102, camel 101 , mastic 150, jaune d''or 039, champagne 092','/RoseCorail/images/coloris-3.jpg','Printemps-Été 2016');
INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (4, 'Rose poudré 090, pivoine 100, aubergine 099, rouge 028, bordeaux 019, safran 096','/RoseCorail/images/coloris-4.jpg','Printemps-Été 2016');
INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (5, 'Sapin bleuté 091, bleu vert 008, celadon 089, bleu gris 087, turquoise 020, canard 009','/RoseCorail/images/coloris-5.jpg','Printemps-Été 2016');
INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (6, 'Elephant 035, bronze 032, argent 030','/RoseCorail/images/coloris-6.jpg','Printemps-Été 2016');

INSERT INTO photopresentation (id_photo,page,adresse) VALUES (1,'accueil','/RoseCorail/images/accueil/image1.jpg');
INSERT INTO photopresentation (id_photo,page,adresse) VALUES (2,'collection','/RoseCorail/images/collection/image2.jpg');