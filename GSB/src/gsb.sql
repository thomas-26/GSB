-- Adminer 4.7.7 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `authentification`;
CREATE TABLE `authentification` (
  `login` varchar(50) NOT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `authentification` (`login`, `mdp`, `role`) VALUES
('dandre',	'37f2381c9a729782c38410b1ea5b8191',	'visiteur'),
('dandre2',	'593563117b47d7ac12eb650e9ce83deb',	'visiteur'),
('dmartin',	'f827a6dfd47543e1ef9397714fb98499',	'directeur'),
('mludo',	'2d39495b301ced016b6a8f3ab874d7e6',	'responsable');

DROP TABLE IF EXISTS `catalogue`;
CREATE TABLE `catalogue` (
  `numero` int(11) NOT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `login` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`numero`),
  KEY `login` (`login`),
  CONSTRAINT `catalogue_ibfk_1` FOREIGN KEY (`login`) REFERENCES `utilisateur` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `emprunt`;
CREATE TABLE `emprunt` (
  `idEmprunt` int(11) NOT NULL AUTO_INCREMENT,
  `datedebut` date DEFAULT NULL,
  `datefin` date DEFAULT NULL,
  `heuredebut` time DEFAULT NULL,
  `heurefin` time DEFAULT NULL,
  `loginvisiteur` varchar(20) DEFAULT NULL,
  `idobjet` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEmprunt`),
  KEY `loginvisiteur` (`loginvisiteur`),
  KEY `idobjet` (`idobjet`),
  CONSTRAINT `emprunt_ibfk_1` FOREIGN KEY (`loginvisiteur`) REFERENCES `utilisateur` (`login`),
  CONSTRAINT `emprunt_ibfk_2` FOREIGN KEY (`idobjet`) REFERENCES `objet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `emprunt` (`idEmprunt`, `datedebut`, `datefin`, `heuredebut`, `heurefin`, `loginvisiteur`, `idobjet`) VALUES
(2,	'2020-12-01',	'2020-12-03',	'10:30:22',	'10:30:22',	'dandre',	2),
(13,	'2020-12-20',	'2020-12-26',	'00:00:00',	'00:00:00',	'dandre',	1),
(20,	'2020-12-03',	'2020-12-05',	'10:00:00',	'11:00:00',	'dandre',	1),
(21,	'2020-12-08',	'2020-12-17',	'07:00:00',	'07:00:00',	'dandre',	1),
(22,	'2020-12-18',	'2020-12-19',	'07:00:00',	'07:00:00',	'dandre',	1),
(23,	'2020-12-14',	'2020-12-16',	'07:00:00',	'07:00:00',	'dandre',	5);

DROP TABLE IF EXISTS `materiel`;
CREATE TABLE `materiel` (
  `code` int(11) NOT NULL,
  `longueur` int(11) DEFAULT NULL,
  `largeur` int(11) DEFAULT NULL,
  `codemateriel` int(11) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `codeMateriel` (`codemateriel`),
  CONSTRAINT `materiel_ibfk_1` FOREIGN KEY (`code`) REFERENCES `objet` (`id`),
  CONSTRAINT `materiel_ibfk_2` FOREIGN KEY (`codemateriel`) REFERENCES `type_materiel` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `materiel` (`code`, `longueur`, `largeur`, `codemateriel`) VALUES
(1,	12,	2,	1),
(2,	5,	1,	1),
(3,	40,	10,	2),
(4,	15,	5,	2);

DROP TABLE IF EXISTS `objet`;
CREATE TABLE `objet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `objet` (`id`, `nom`) VALUES
(1,	'tournevis'),
(2,	'scie'),
(3,	'clavier'),
(4,	'souris'),
(5,	'AB-157-ZA');

DROP TABLE IF EXISTS `statistique`;
CREATE TABLE `statistique` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(25) DEFAULT NULL,
  `valeur` int(11) DEFAULT NULL,
  `idobjet` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idobjet` (`idobjet`),
  CONSTRAINT `statistique_ibfk_1` FOREIGN KEY (`idobjet`) REFERENCES `objet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `type_materiel`;
CREATE TABLE `type_materiel` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `type_materiel` (`code`, `libelle`) VALUES
(1,	'outil'),
(2,	'informatique');

DROP TABLE IF EXISTS `type_vehicule`;
CREATE TABLE `type_vehicule` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `type_vehicule` (`code`, `libelle`) VALUES
(1,	'camion');

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE `utilisateur` (
  `login` varchar(20) NOT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`login`),
  CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`login`) REFERENCES `authentification` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `utilisateur` (`login`, `nom`, `prenom`) VALUES
('dandre',	'Andre',	'David'),
('dandre2',	'andre',	'david'),
('dmartin',	'Dupont',	'Martin'),
('mludo',	'Ludo',	'Monsouris');

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE `vehicule` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `immat` varchar(15) DEFAULT NULL,
  `modele` varchar(25) DEFAULT NULL,
  `marque` varchar(25) DEFAULT NULL,
  `nbplaces` int(11) DEFAULT NULL,
  `codevehicule` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `codevehicule` (`codevehicule`),
  CONSTRAINT `vehicule_ibfk_1` FOREIGN KEY (`code`) REFERENCES `objet` (`id`),
  CONSTRAINT `vehicule_ibfk_2` FOREIGN KEY (`codevehicule`) REFERENCES `type_vehicule` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `vehicule` (`code`, `immat`, `modele`, `marque`, `nbplaces`, `codevehicule`) VALUES
(5,	'AB-157-ZA',	'Vario 815 D blindé',	'Mercedes-Benz',	2,	1);

-- 2020-12-11 11:56:15
