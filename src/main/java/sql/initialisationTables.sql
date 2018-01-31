DELETE FROM actualite;
DELETE FROM article;
DELETE FROM categorie;
DELETE FROM compteclient;
DELETE FROM compterosecorail;
DELETE FROM couleur;
DELETE FROM definir;
DELETE FROM devis;
DELETE FROM lignedevis;
DELETE FROM posseder;
DELETE FROM souscategorie;
DELETE FROM statistiques;

INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (1, 'Salon de Paris', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaPARIS', 'imageSalonParis');
INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (2, 'Salon de Reims', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaREIMS', 'imageSalonREIMS');
INSERT INTO `actualite`(`id_actualite`,`titre`,`contenu`,`image`) VALUES (3, 'Salon de Picardie', 'BLAblaBLAblaBLAblaBLAblaBLAblaBLAblaPICARDIE', 'imageSalonPICARDIE');

INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`) VALUES (1,1, 'Plats à cake', '6030 407', 'Plat à cake', 'imagePlatàCake', '32 x 15 cm', 10.0, 1);
INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`) VALUES (2,1, 'Plat à cake avec bords', '6030 414', 'Plat à cake avec bords', 'imagePlatàCakeAvecBords', '32 x 15 cm', 12.0, 1);
INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`) VALUES (3,1, 'Plats à fromage', '6030 062', 'Plat carré', 'imagePlatàFromage', '30 x 30 cm', 14.0, 1);
INSERT INTO `article`(`id_article`,`id_sous_categorie`,`nom_article`,`reference`,`description`,`image`,`dimensions`,`prix`, `lot_vente`) VALUES (4,1, 'Coupelles', '6030 072', 'Coupelle', 'imageCoupelle', '18 x 18 cm', 10.0, 2);

INSERT INTO `categorie`(`id_categorie`,`nom_categorie`) VALUES (1,'Les plats');
INSERT INTO `categorie`(`id_categorie`,`nom_categorie`) VALUES (2,'Porte couteaux & Décoration');
INSERT INTO `categorie`(`id_categorie`,`nom_categorie`) VALUES (3,'Le verre transparent');

INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`) VALUES (1,'william@evrard.fr', 'HEI', 'EVRARD', '26 BD Bigo Danel', 'Lille', '59000', 'monMDP1', '0606060606');
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`) VALUES (2,'thibaut@demory.fr', 'ISA', 'DEMORY', '20 rue Beaucourt', 'Lille', '59000', 'monMDP2', '0606060607');
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`) VALUES (3,'arnold@blyau.fr', 'ISEN', 'BLYAU', '54 rue Paul Bocuse', 'Lille', '59000', 'monMDP3', '0606060608');
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`) VALUES (4,'remy@schoonaert.fr', 'EDHEC', 'SCHOONAERT', '2 BD Bigo Danel', 'Lille', '59000', 'monMDP4', '0606060609');
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`) VALUES (5,'jules@evrard.fr', 'POLYTECHNIQUE', 'DEMORY', '24 rue du marronnier', 'Lille', '59000', 'monMDP5', '0606060601');
INSERT INTO `compteclient`(`id_compte_client`,`email`,`nom_boutique`,`nom_gerant`,`adresse`,`ville`,`code_postal`,`mdp`, `numero_tel`) VALUES (6,'maxime@evrard.fr', 'PARIS DAUPHINE', 'BLYAU', '154 rue Charlemagne', 'Lille', '59000', 'monMDP6', '0606060602');

INSERT INTO `compterosecorail`(`id_compte_RC`,`email`,`mdp`, `numero_tel`) VALUES (1, 'beatrice.roquette@rosecorail.com', 'MDP21', '06 12 63 07 12');

INSERT INTO `couleur`(`id_couleur`,`nom_couleur`) VALUES (1, 'Bleu gris 087');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`) VALUES (2, 'Bleu glacier 097');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`) VALUES (3, 'Noir 018');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`) VALUES (4, 'Eléphant 035');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`) VALUES (5, 'Taupe 036');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`) VALUES (6, 'Mastic 150');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`) VALUES (7, 'Bronze 032');
INSERT INTO `couleur`(`id_couleur`,`nom_couleur`) VALUES (8, 'Marron glacé 102');


INSERT INTO `definir`(`id_devis`,`id_stat`) VALUES (1, 1);
INSERT INTO `definir`(`id_devis`,`id_stat`) VALUES (2, 1);
INSERT INTO `definir`(`id_devis`,`id_stat`) VALUES (3, 1);
INSERT INTO `definir`(`id_devis`,`id_stat`) VALUES (4, 2);
INSERT INTO `definir`(`id_devis`,`id_stat`) VALUES (5, 3);

INSERT INTO `devis`(`id_devis`, `id_compte_client`, `date`, `etat`, `etatPanier`) VALUES (1, 1, '2017-04-06', 'Demandé', true);
INSERT INTO `devis`(`id_devis`, `id_compte_client`, `date`, `etat`, `etatPanier`) VALUES (2, 2, '2017-11-20', 'En préparation', true);
INSERT INTO `devis`(`id_devis`, `id_compte_client`, `date`, `etat`, `etatPanier`) VALUES (3, 3, '2017-10-10', 'Expédié', false);

INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`) VALUES (1, 1, 1, 1);
INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`) VALUES (2, 1, 1, 3);
INSERT INTO `lignedevis`(`id_ligne_devis`, `id_couleur`, `id_devis`, `id_article`) VALUES (3, 1, 1, 1);

INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (1, 1);
INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (2, 2);
INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (3, 3);
INSERT INTO `posseder`(`id_couleur`, `id_article`) VALUES (4, 4);

INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (1, 1, 'Plats à cake');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (2, 1, 'Plats à fromage');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (3, 1, 'Coupelle');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (4, 2, 'Dessous de carafe');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (5, 2, 'Dessous de plat');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (6, 2, 'Portes couteaux');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (7, 3, 'Plats à cake');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (8, 3, 'Dessous de verre');
INSERT INTO `souscategorie`(`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES (9, 3, 'Portes couteaux');

INSERT INTO `statistiques`(`id_stat`, `nom_boutique_num1`, `nom_boutique_num2`, `nom_boutique_num3`, `ref_article_vu_num1`, `ref_article_vu_num2`, `ref_article_vu_num3`, `ref_article_commandes_num1`, `ref_article_commandes_num2`, `ref_article_commandes_num3`, `nom_couleur1`, `nom_couleur2`, `nom_couleur3`, `mois`, `annee`) VALUES (1, 'HEI', 'ISA', 'ISEN', '6030 407', '6030 414', '6030 062', '6030 062', '6030 414', '6030 407', 'Bleu gris 087', 'Bleu glacier 097', 'Noir 018', 'janvier', '2017');
INSERT INTO `statistiques`(`id_stat`, `nom_boutique_num1`, `nom_boutique_num2`, `nom_boutique_num3`, `ref_article_vu_num1`, `ref_article_vu_num2`, `ref_article_vu_num3`, `ref_article_commandes_num1`, `ref_article_commandes_num2`, `ref_article_commandes_num3`, `nom_couleur1`, `nom_couleur2`, `nom_couleur3`, `mois`, `annee`) VALUES (2, 'HEI', 'ISA', 'ISEN', '6030 407', '6030 414', '6030 062', '6030 062', '6030 414', '6030 407', 'Bleu gris 087', 'Bleu glacier 097', 'Noir 018', 'mars', '2017');
INSERT INTO `statistiques`(`id_stat`, `nom_boutique_num1`, `nom_boutique_num2`, `nom_boutique_num3`, `ref_article_vu_num1`, `ref_article_vu_num2`, `ref_article_vu_num3`, `ref_article_commandes_num1`, `ref_article_commandes_num2`, `ref_article_commandes_num3`, `nom_couleur1`, `nom_couleur2`, `nom_couleur3`, `mois`, `annee`) VALUES (3, 'HEI', 'ISA', 'ISEN', '6030 407', '6030 414', '6030 062', '6030 062', '6030 414', '6030 407', 'Bleu gris 087', 'Bleu glacier 097', 'Noir 018', 'janvier', '2018');

