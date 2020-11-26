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
('dmartin',	'f827a6dfd47543e1ef9397714fb98499',	'directeur'),
('mludo',	'2d39495b301ced016b6a8f3ab874d7e6',	'responsable');

DROP TABLE IF EXISTS `catalogue`;
CREATE TABLE `catalogue` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) DEFAULT NULL,
  `idResponsable` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero`),
  KEY `idResponsable` (`idResponsable`),
  CONSTRAINT `catalogue_ibfk_1` FOREIGN KEY (`idResponsable`) REFERENCES `responsable` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `directeur`;
CREATE TABLE `directeur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) DEFAULT NULL,
  `prenom` varchar(25) DEFAULT NULL,
  `login` varchar(25) DEFAULT NULL,
  `mdp` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `emprunt`;
CREATE TABLE `emprunt` (
  `idEmprunt` int(11) NOT NULL AUTO_INCREMENT,
  `datedebut` date DEFAULT NULL,
  `datefin` date DEFAULT NULL,
  `heuredebut` time DEFAULT NULL,
  `heurefin` time DEFAULT NULL,
  `idvisiteur` int(11) DEFAULT NULL,
  `idobjet` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEmprunt`),
  KEY `idvisiteur` (`idvisiteur`),
  KEY `idobjet` (`idobjet`),
  CONSTRAINT `emprunt_ibfk_1` FOREIGN KEY (`idvisiteur`) REFERENCES `visiteur` (`id`),
  CONSTRAINT `emprunt_ibfk_2` FOREIGN KEY (`idobjet`) REFERENCES `objet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `materiel`;
CREATE TABLE `materiel` (
  `code` int(11) NOT NULL,
  `longueur` int(11) DEFAULT NULL,
  `largeur` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`),
  CONSTRAINT `materiel_ibfk_1` FOREIGN KEY (`code`) REFERENCES `objet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `materiel` (`code`, `longueur`, `largeur`) VALUES
(1,	15,	4),
(2,	40,	15);

DROP TABLE IF EXISTS `objet`;
CREATE TABLE `objet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) DEFAULT NULL,
  `etat` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `objet` (`id`, `nom`, `etat`) VALUES
(1,	'tournevis',	'disponible'),
(2,	'scie',	'emprunte');

DROP TABLE IF EXISTS `responsable`;
CREATE TABLE `responsable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) DEFAULT NULL,
  `prenom` varchar(25) DEFAULT NULL,
  `login` varchar(25) DEFAULT NULL,
  `mdp` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


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


DROP TABLE IF EXISTS `type_vehicule`;
CREATE TABLE `type_vehicule` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE `vehicule` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `immat` varchar(15) DEFAULT NULL,
  `modele` varchar(25) DEFAULT NULL,
  `marque` varchar(25) DEFAULT NULL,
  `nbplaces` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`),
  CONSTRAINT `vehicule_ibfk_1` FOREIGN KEY (`code`) REFERENCES `type_vehicule` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `visiteur`;
CREATE TABLE `visiteur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) DEFAULT NULL,
  `prenom` varchar(25) DEFAULT NULL,
  `login` varchar(25) DEFAULT NULL,
  `mdp` varchar(25) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `cp` int(5) DEFAULT NULL,
  `ville` varchar(25) DEFAULT NULL,
  `dateEmbauche` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 2020-11-26 10:49:06
