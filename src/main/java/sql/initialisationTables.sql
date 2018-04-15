DELETE FROM posseder;
DELETE FROM lignedevis;
DELETE FROM article;
DELETE FROM actualite;
DELETE FROM devis;
DELETE FROM compteclient;
DELETE FROM compterosecorail;
DELETE FROM couleur;
DELETE FROM panelcoloris;
DELETE FROM photopresentation;



INSERT INTO `article`(`id_article`,id_sous_categorie,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`,actif) VALUES (1,1, 'Plat à cake', '6030 407', 'Plat à cake', '/RoseCorail/images/articles/Plat à cake/image.jpg', '32 x 15 cm', 10.0, 1,1);
INSERT INTO `article`(`id_article`,id_sous_categorie,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`,actif) VALUES (2,1, 'Plat à cake avec bords', '6030 414', 'Plat à cake avec bords', '/RoseCorail/images/articles/Plat à cake avec bords/image.jpg', '32 x 15 cm', 12.0, 1,1);
INSERT INTO `article`(`id_article`,id_sous_categorie,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`,actif) VALUES (3,2, 'Plat à fromage', '6030 062', 'Plat à fromage', '/RoseCorail/images/articles/Plat à fromage/image.jpg', '30 x 30 cm', 14.0, 1,1);
INSERT INTO `article`(`id_article`,id_sous_categorie,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`,actif) VALUES (4,3, 'Coupelles', '6030 072', 'Coupelles', '/RoseCorail/images/articles/Coupelles/image.jpg', '18 x 18 cm', 10.0, 2,1);

INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`prenom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`, `num_tva`, `site_internet`, `description_activite`, numero_panier_actif) VALUES (1,'william.evrard@hei.yncrea.fr', 'HEI', 'EVRARD', 'William', '26 BD Bigo Danel', 'Lille', '59000', '3a111d3ae4b18d0b477aa911b43f8c3d6453a5705ec7bc81dc380c87e2208a1f', '0606060606', 'FR 40 123456824', 'william.evrard.fr', 'description1',1);

INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (1, 'Bleu vert', '08', '/RoseCorail/images/couleurs/Bleu vert/image.jpg', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (2, 'Eléphant', '035', '/RoseCorail/images/couleurs/Eléphant/image.jpg', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (3, 'Rose corail', '040', '/RoseCorail/images/couleurs/Rose Corail/image.jpg', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (4, 'Bordeaux', '019', '/RoseCorail/images/couleurs/Bordeaux/image.jpg', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (5, 'Celadon', '089', '/RoseCorail/images/couleurs/Celadon/image.jpg', 'Printemps-Ete 2017',1);
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`, `num_couleur`, `image`, `saison`, actif) VALUES (6, 'Orange', '21', '/RoseCorail/images/couleurs/Orange/image.jpg', 'Printemps-Ete 2017',1);

INSERT INTO `posseder`(`id_posseder`,`id_couleur`, `id_article`) VALUES (1,1, 1);
INSERT INTO `posseder`(`id_posseder`,`id_couleur`, `id_article`) VALUES (2,2, 2);
INSERT INTO `posseder`(`id_posseder`,`id_couleur`, `id_article`) VALUES (3,3, 3);
INSERT INTO `posseder`(`id_posseder`,`id_couleur`, `id_article`) VALUES (4,4, 4);

INSERT INTO `devis`(`id_devis`, `id_compte_client`, `date_creation`, `etat`, `etatPanier`) VALUES (1, 1, '2017-04-06', 'panier', true);

INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`, quantite) VALUES (1, 1, 1, 1, 3);
INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`, quantite) VALUES (2, 1, 1, 2, 3);
INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`, quantite) VALUES (3, 1, 1, 3, 3);

INSERT INTO `compterosecorail`(`id_compte_RC`,`email`,`mdp`, `numero_tel`) VALUES (1, 'beatrice.roquette@rosecorail.com', '51d59a88b0e1f1ad672dad1fb56626c2169a737ac5d6cd544355c53bc495e769', '06 12 63 07 12');

INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (1, 'SALON ART ET DÉCORATION 2018 À PARIS', 'Le mobilier et la décoration ne sont pas en reste avec ce qu''il vous faut absolument dans votre entrée, cuisine, chambre, salon, etc. Toutes les idées pour refaire votre intérieur !', '/RoseCorail/images/actualites/SALON ART ET DÉCORATION 2018 À PARIS/image.jpg');
INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (2, 'Salon de nice déco habitation', 'Du 11 au 19 novembre 2017, le Palais des Expositions devient la plus grande vitrine de la région des professionnels de l’ameublement, de la décoration et de l’amélioration de l’habitat, tant extérieurs qu’intérieurs.', '/RoseCorail/images/actualites/Salon de nice déco habitation/image.jpg');

INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (1, 'Bleu gris 087, Bleu glacier 097, bleu vert 08, sapin bleuté 091, lichen 98, forêt 103','/RoseCorail/images/coloris-1.jpg','Printemps-Été 2016');
INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (2, 'Noir 018, éléphant 035, taupe 036, mastic 150, bronze 032, argent 030','/RoseCorail/images/coloris-2.jpg','Printemps-Été 2016');
INSERT INTO `panelcoloris`(`id_panelcoloris`,`legende`,`image`,`saison`) VALUES (3, 'Marron glacé 102, camel 101 , mastic 150, jaune d''or 039, champagne 092','/RoseCorail/images/coloris-3.jpg','Printemps-Été 2016');


INSERT INTO photopresentation (id_photo,page,adresse) VALUES (1,'accueil','/RoseCorail/images/accueil/image1.jpg');
INSERT INTO photopresentation (id_photo,page,adresse) VALUES (2,'collection','/RoseCorail/images/collection/image2.jpg');